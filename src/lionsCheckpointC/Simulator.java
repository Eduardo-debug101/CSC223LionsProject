package lionsCheckpointC;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Simulator {
    private int arrivalMinTime;
    private int arrivalMaxTime;
    private int serviceMinTime;
    private int serviceMaxTime;
    private int numCustomers;
    private static int satisfiedCusts = 0;
    private static int dissatisfiedCusts = 0;
    // This is to keep the start() while loop running until EVERY customer has been
    // placed into a queue, served, and left
    private static int customersServedAndLeft = 0;
    private static int timeQueuesAreFree = 0;
    private static int timeLanesAreFree = 0;


    public Simulator() {

    }

    public Simulator(int a, int b, int c, int d, int n) {
        arrivalMinTime = a;
        arrivalMaxTime = b;
        serviceMinTime = c;
        serviceMaxTime = d;
        numCustomers = n;
    }

    public String toString() {
        return "Simulator [arrivalMinTime=" + arrivalMinTime + ", arrivalMaxTime=" + arrivalMaxTime
                + ", serviceMinTime=" + serviceMinTime + ", serviceMaxTime=" + serviceMaxTime + ", numCustomers="
                + numCustomers + "]";
    }

    public void start() {

        LinkedQueue A = new LinkedQueue();
        LinkedQueue B = new LinkedQueue();
        LinkedQueue C = new LinkedQueue();

        LinkedQueue self = new LinkedQueue();

        CheckoutLane laneD = new CheckoutLane();
        CheckoutLane laneE = new CheckoutLane();

        CustomerCreator cc = new CustomerCreator(arrivalMinTime, arrivalMaxTime, serviceMinTime, serviceMaxTime, 0);

        ArrayList<Customer> waitingCustomers = new ArrayList<Customer>();
        waitingCustomers = customerWaitList(cc);

        // This array is a total of all Customers for printStats()
        ArrayList<Customer> queuedCustsForStats = new ArrayList<>();

        // This counts the "minutes" that has passed. This is used for the sample
        // output.
        int timer = -1;

        // Main while loop continues until every Customer has been served
        while (customersServedAndLeft != numCustomers) {

            timer++;
            System.out.println("Time: " + timer);

            if (timer == 0) {
                System.out.println("\tStart");

            }

            // ************************** START For when there is an available Customer to
            // be put into a queue
            if (!waitingCustomers.isEmpty()) {

                // While loop is used to keep adding Customers that have the same arrivalTime as
                // the current Timer
                boolean flag = true;
                while (flag && !waitingCustomers.isEmpty()) {
                    Customer newCustForQueue = waitingCustomers.get(0);

                    // Finds the smallest queue to add customers into
                    LinkedQueue selectedQueue;
                    if (newCustForQueue.getCoinFlip() == 0) {
                        selectedQueue = self;
                    } else {
                        selectedQueue = selectAQueue(A, B, C);
                    }
                    String QueueLetter = findLowestQueueNum(A, B, C, newCustForQueue.getCoinFlip());

                    // If the arrivalTime of the customer object is the same as the current
                    // time, then it will add it to the selected queue and delete the customer
                    // object from the arraylist
                    if (newCustForQueue.getArrivalTime() == timer) {

                        // Calculates wait time for each customer by getting the absloute value of the
                        // leave time of the
                        // person in front minus the arrival time of the customer.
                        if (!selectedQueue.empty()) {
                            int finishTimeOfRear = selectedQueue.getRear().getFinishTime();
                            newCustForQueue.calcWait(finishTimeOfRear);
                            newCustForQueue.calcLeave();
                        } else {
                            newCustForQueue.setFinishTime(
                                    newCustForQueue.getArrivalTime() + newCustForQueue.getServiceTime());
                            newCustForQueue.setWaitTime(0);
                        }

                        selectedQueue.enqueue(newCustForQueue);

                        // This array is for the stats at the end of the program
                        queuedCustsForStats.add(newCustForQueue);

                        newCustForQueue.setAssignedQueueLetter(QueueLetter);
                        newCustForQueue.setCustomerNotes();

                        waitingCustomers.remove(0);
                    } else {
                        flag = false;
                    }
                }
            }

            // Handles adding to both lanes as well as dequeuing for the self queue
            CheckoutLane bestLane = nextLane(laneD, laneE);
            if (!bestLane.isInUse() && !self.empty()) {
                bestLane.setCheckoutCustomer(self.peek());
                self.dequeue();

            }

            // ************************** END Adding Customers to queues section

            output(A, B, C, self, laneD, laneE, timer);

            // ************************** START Removing Customers from queues section
            // Need to add logic to "remove" an element from the array: make the first element = null
            handleQueueRemoval(A, timer);
            handleQueueRemoval(B, timer);
            handleQueueRemoval(C, timer);
            handleLaneRemoval(laneD, timer);
            handleLaneRemoval(laneE, timer);

            // ************************** END Removing Customers from queues section

            // If someone is currently checking out then those who are still in queue must wait longer
            if(bestLane.isInUse() && !self.empty()){
                for (int i = 0; i < self.size(); i++){
                    self.indexOf(i).setFinishTime(self.indexOf(i).getFinishTime() + 1);
                }
            }
        }

        System.out.println("\n\n\nBeginning of Stats:\n");
        printStats(queuedCustsForStats);

    }

    // The bestLane is either one of the Lanes if they are empty, or if they are full it will be the Lane
    // that has the lowest leave time
    public CheckoutLane nextLane(CheckoutLane D, CheckoutLane E){
        if(!D.isInUse()){
            return D;
        }else if(!E.isInUse()){
            return E;
        }
        if(D.getCheckoutCustomer().getServiceTime() <= E.getCheckoutCustomer().getServiceTime()){
            return D;
        }else{
            return E;
        }
    }

    public void handleQueueRemoval(LinkedQueue queue, int time) {
        if (!queue.empty()) {
            if (queue.peek().getFinishTime() == time) {
                if (queue.peek().getWaitTime() >= 5) {
                    dissatisfiedCusts += 1;
                } else {
                    satisfiedCusts += 1;
                }
                queue.dequeue();
                customersServedAndLeft++;
            }
        } else {
            timeQueuesAreFree++;
        }
    }


    // Removes the Customer using the checkout lane if they are ready to leave
    public void handleLaneRemoval(CheckoutLane lane, int time) {
        if (lane.isInUse()) {
            if (lane.getCheckoutCustomer().getFinishTime() == time) {
                if (lane.getCheckoutCustomer().getWaitTime() >= 5) {
                    dissatisfiedCusts += 1;
                } else {
                    satisfiedCusts += 1;
                }
                lane.setCheckoutCustomer(null);
                customersServedAndLeft++;
            }
        } else {
            timeLanesAreFree++;
        }
    }

    public void printStats(ArrayList<Customer> customers) {

        String dashedLines = String.format("%0" + 63 + "d", 0).replace("0", "-");
        System.out.println(dashedLines);
        System.out.format("%1s%6s%9s%10s%9s%5s%5s%1s", "| ", "Cust # ", "| Arrival Time ", "| Service Time ", "| LOC ",
                "| Dep ", "| Notes ", "|");
        System.out.println("\n" + dashedLines);

        double average = 0;

        for (Customer cust : customers) {
            String lineDivider = "|";
            System.out.format("%1s%3s%6s%9s%6s%9s%9s%3s%3s%3s%3s%3s",
                    lineDivider, cust.getCustId(),
                    lineDivider, cust.getArrivalTime(),
                    lineDivider, cust.getServiceTime(),
                    lineDivider, cust.getAssignedQueueLetter(),
                    lineDivider, cust.getFinishTime(),
                    lineDivider, cust.getCustomerNotes());
            System.out.println("\n" + dashedLines);
            average += cust.getWaitTime();
        }

        //System.out.format("%1s%.2f%1s", "Average wait: ", (averages / numCustomers)), " min\n");
        System.out.println("Average wait: " + (average / numCustomers) + " min");
        System.out.println("Total time checkouts were not in use: " + (timeQueuesAreFree / 10) + " min");
        System.out.println("Satisfied customers: " + satisfiedCusts);
        System.out.println("Dissatisfied customers: " + dissatisfiedCusts);

    }

    public LinkedQueue selectAQueue(LinkedQueue A, LinkedQueue B, LinkedQueue C) {
        if (A.size() <= B.size() && A.size() <= C.size()) {
            return A;
        } else if (B.size() <= C.size() && B.size() <= A.size()) {
            return B;
        } else {
            return C;
        }
    }

    public String findLowestQueueNum(LinkedQueue A, LinkedQueue B, LinkedQueue C, int coinflip) {
        if(coinflip == 0){
            return "S";
        }else{
            if (A.size() <= B.size() && A.size() <= C.size()) {
                return "A";
            } else if (B.size() <= C.size() && B.size() <= A.size()) {
                return "B";
            } else {
                return "C";
            }
        }
    }

    public void output(LinkedQueue A, LinkedQueue B, LinkedQueue C, LinkedQueue self, CheckoutLane laneD, CheckoutLane laneE, int time) {
        ArrayList<String> customersWaitingInQueue = new ArrayList<>();

        handleQueueOutput(A, 'A', time, customersWaitingInQueue);
        handleQueueOutput(B, 'B', time, customersWaitingInQueue);
        handleQueueOutput(C, 'C', time, customersWaitingInQueue);
        handleQueueOutput(self, 'S', time, customersWaitingInQueue);
        handleLaneOutput(laneD, 'D');
        handleLaneOutput(laneE, 'E');

        if (!customersWaitingInQueue.isEmpty()) {
            for (String s : customersWaitingInQueue) {
                System.out.println(s);
            }
        }
    }

    public void handleLaneOutput(CheckoutLane lane, char laneLetter){
        if(lane.isInUse()){
            System.out.println("\tLane " + laneLetter + ": Customer #" + lane.getCheckoutCustomer().getCustId() + " in checkout");
        }else{
            System.out.println("\tLane " + laneLetter + ": Lane is empty");
        }
    }

    public void handleQueueOutput(LinkedQueue queue, char queueLetter, int time, ArrayList<String> customersWaitingInQueue) {
        if (queue.empty())
            System.out.println("\tCheckout " + queueLetter + ": free");
        else {
            for (int i = 0; i < queue.size(); i++) {
                Customer cc = queue.indexOf(i);
                if (cc != null) {
                    if (cc == queue.peek()) {
                        if (cc.getArrivalTime() == time)
                            System.out.println(
                                    "\tCheckout " + queueLetter + ": Customer #" + cc.getCustId() + " begins service");

                        if (cc.getFinishTime() == time)
                            // We could probably add a method where it deletes the entry instead of in the
                            // start method.
                            System.out
                                    .println("\tCheckout " + queueLetter + ": Customer #" + cc.getCustId() + " leaves");
                        if (cc.getArrivalTime() != time && cc.getFinishTime() != time)
                            System.out
                                    .println("\tCheckout " + queueLetter + ": Customer #" + cc.getCustId() + " (cont)");

                    } else {
                        if (cc.getArrivalTime() + cc.getWaitTime() == time && i == 1)
                            System.out.println(
                                    "\tCheckout " + queueLetter + ": Customer #" + cc.getCustId() + " begins service");
                        else if (cc.getArrivalTime() == time)
                            customersWaitingInQueue.add("\tCustomer " + cc.getCustId()
                                    + " arrives and goes into Checkout " + queueLetter + " queue");
                    }
                }
            }
        }
    }

    // Still need to work on this
//    public void handleQueueOutput(LinkedQueue queue, CheckoutLane lane, char queueLetter, int time, ArrayList<String> customersWaitingInQueue) {
//        if (servicePoint[0] == null)
//            System.out.println("\tCheckout " + queueLetter + ": free");
//        else {
//            Customer cc = null;
//            for (int i = 0; i < queue.size(); i++) {
//                if (i == 1) {
//                    cc = servicePoint[0];
//                } else {
//                    cc = queue.indexOf(i);
//                }
//                if (cc != null) {
//                    if (cc == queue.peek()) {
//                        if (cc.getArrivalTime() == time)
//                            System.out.println(
//                                    "\tCheckout " + queueLetter + ": Customer #" + cc.getCustId() + " begins service");
//
//                        if (cc.getFinishTime() == time)
//                            // We could probably add a method where it deletes the entry instead of in the
//                            // start method.
//                            System.out
//                                    .println("\tCheckout " + queueLetter + ": Customer #" + cc.getCustId() + " leaves");
//                        if (cc.getArrivalTime() != time && cc.getFinishTime() != time)
//                            System.out
//                                    .println("\tCheckout " + queueLetter + ": Customer #" + cc.getCustId() + " (cont)");
//
//                    } else {
//                        if (cc.getArrivalTime() + cc.getWaitTime() == time && i == 1)
//                            System.out.println(
//                                    "\tCheckout " + queueLetter + ": Customer #" + cc.getCustId() + " begins service");
//                        else if (cc.getArrivalTime() == time)
//                            customersWaitingInQueue.add("\tCustomer " + cc.getCustId()
//                                    + " arrives and goes into Checkout " + queueLetter + " queue");
//                    }
//                }
//            }
//        }
//    }

    public ArrayList<Customer> customerWaitList(CustomerCreator cc) {
        ArrayList<Customer> wc = new ArrayList<Customer>();
        for (int i = 0; i < numCustomers; i++) {
            wc.add(cc.create());
        }
        return wc;
    }

    public int getArrivalMinTime() {
        return arrivalMinTime;
    }

    public void setArrivalMinTime(int arrivalMinTime) {
        this.arrivalMinTime = arrivalMinTime;
    }

    public int getArrivalMaxTime() {
        return arrivalMaxTime;
    }

    public void setArrivalMaxTime(int arrivalMaxTime) {
        this.arrivalMaxTime = arrivalMaxTime;
    }

    public int getServiceMinTime() {
        return serviceMinTime;
    }

    public void setServiceMinTime(int serviceMinTime) {
        this.serviceMinTime = serviceMinTime;
    }

    public int getServiceMaxTime() {
        return serviceMaxTime;
    }

    public void setServiceMaxTime(int serviceMaxTime) {
        this.serviceMaxTime = serviceMaxTime;
    }

    public int getNumCustomers() {
        return numCustomers;
    }

    public void setNumCustomers(int numCustomers) {
        this.numCustomers = numCustomers;
    }

//    public static String numToLet(String string) {
//        String number = "NA";
//        switch (string) {
//            case "1" -> number = "A";
//            case "2" -> number = "B";
//            case "3" -> number = "C";
//        }
//        return number;
//    }
//
//    public int findLowestQueueSize(final int queueSizeA, final int queueSizeB, final int queueSizeC) {
//        int finalQueue = 0;
//        if (queueSizeA <= queueSizeB && queueSizeA <= queueSizeC) {
//            finalQueue = 1;
//        } else if (queueSizeB <= queueSizeC && queueSizeB <= queueSizeA) {
//            finalQueue = 2;
//        } else {
//            finalQueue = 3;
//        }
//
//        return finalQueue;
//    }

}
