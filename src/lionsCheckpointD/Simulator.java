package lionsCheckpointD;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class Simulator {
    private int arrivalMinTime;
    private int arrivalMaxTime;
    private int serviceMinTime;
    private int serviceMaxTime;
    private int selfSlowTime;
    private int numCustomers;
    private int fullQueuesAmt;
    private int selfLanesAmt;
    private static int satisfiedCusts = 0;
    private static int dissatisfiedCusts = 0;
    // This is to keep the start() while loop running until EVERY customer has been
    // placed into a queue, served, and left
    private static int customersServedAndLeft = 0;
    private static int timeQueuesAreFree = 0;
    private static int timeLanesAreFree = 0;
    private static ArrayList<LinkedQueue> fullQueues = new ArrayList<>();
    private static ArrayList<CheckoutLane> selfLanes = new ArrayList<>();


    public Simulator() {

    }

    public Simulator(int a, int b, int c, int d, int n, int selfSlow, int fullQueuesAmt, int selfLanesAmt) {
        arrivalMinTime = a;
        arrivalMaxTime = b;
        serviceMinTime = c;
        serviceMaxTime = d;
        numCustomers = n;
        selfSlowTime = selfSlow;
        this.fullQueuesAmt = fullQueuesAmt;
        this.selfLanesAmt = selfLanesAmt;
    }

    public String toString() {
        return "Simulator [arrivalMinTime=" + arrivalMinTime + ", arrivalMaxTime=" + arrivalMaxTime + ", serviceMinTime=" + serviceMinTime + ", serviceMaxTime=" + serviceMaxTime + ", numCustomers=" + numCustomers + "]";
    }

    private void populateQueueArray(ArrayList<LinkedQueue> array, int size) {

        for (int i = 0; i < size; i++) {
            array.add(new LinkedQueue());
        }

    }

    private void populateLaneArray(ArrayList<CheckoutLane> array, int size) {

        for (int i = 0; i < size; i++) {
            array.add(new CheckoutLane());
        }

    }

    public void start() {

        populateQueueArray(fullQueues, this.fullQueuesAmt);
        populateLaneArray(selfLanes, this.selfLanesAmt);

        LinkedQueue selfQueue = new LinkedQueue();

        CustomerCreator cc = new CustomerCreator(arrivalMinTime, arrivalMaxTime, serviceMinTime, serviceMaxTime, 0, selfSlowTime);

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

                    // If the arrivalTime of the customer object is the same as the current
                    // time, then it will add it to the selected queue and delete the customer
                    // object from the arraylist
                    if (newCustForQueue.getArrivalTime() == timer) {

                        // Finds the smallest queue / self queue to add customers into
                        LinkedQueue selectedQueue;
                        if (newCustForQueue.getCoinFlip() == 0) {
                            selectedQueue = selfQueue;
                        } else {
                            selectedQueue = findBestQueue(fullQueues);
                        }
                        String queueNum = String.valueOf(selectedQueue.getQueueId());

                        // Calculates wait time for each customer by getting the absloute value of the
                        // leave time of the
                        // person in front minus the arrival time of the customer.
                        if (!selectedQueue.empty()) {
                            int finishTimeOfRear = selectedQueue.getRear().getFinishTime();
                            newCustForQueue.calcWait(finishTimeOfRear);
                            newCustForQueue.calcLeave();
                        } else {
                            newCustForQueue.setFinishTime(newCustForQueue.getArrivalTime() + newCustForQueue.getServiceTime());
                            newCustForQueue.setWaitTime(0);
                        }

                        selectedQueue.enqueue(newCustForQueue);

                        // This array is for the stats at the end of the program
                        queuedCustsForStats.add(newCustForQueue);

                        newCustForQueue.setAssignedQueueLetter(queueNum);

                        waitingCustomers.remove(0);
                    } else {
                        flag = false;
                    }
                }
            }

            // Handles adding Customer's to lanes and dequeus them from the selfQueue
            // Loops until both Lanes are full or if the selfQueue has no more Customers
            CheckoutLane selectedLane = new CheckoutLane();
            boolean flag2 = true;
            while (flag2) {
                selectedLane = findBestLane(selfLanes);
                if (!selectedLane.isInUse() && !selfQueue.empty()) {
                    selectedLane.setCheckoutCustomer(selfQueue.peek());
                    selfQueue.dequeue();
                }
                if (allLanesFull() || selfQueue.empty()) {
                    flag2 = false;
                }
            }

            // ************************** END Adding Customers to queues section

            output(selfQueue, timer);

            // ************************** START Removing Customers from queues section
            for (LinkedQueue queue : fullQueues) {
                handleQueueRemoval(queue, timer);
            }

            for (CheckoutLane lane : selfLanes) {
                handleLaneRemoval(lane, timer);
            }
            // ************************** END Removing Customers from queues section

            // If someone is currently checking out then those who are still in queue must wait longer
            if (selectedLane.isInUse() && !selfQueue.empty()) {
                for (int i = 0; i < selfQueue.size(); i++) {
                    selfQueue.indexOf(i).setFinishTime(selfQueue.indexOf(i).getFinishTime() + 1);
                }
            }
        }

        System.out.println("\n\n\nBeginning of Stats:\n");
        printStats(queuedCustsForStats);

    }


    // Removes the Customer using the full service queue if they are ready to leave via finishTime
    private void handleQueueRemoval(LinkedQueue queue, int time) {
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


    // Removes the Customer using the checkout lane if they are ready to leave via finishTime
    private void handleLaneRemoval(CheckoutLane lane, int time) {
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


    private void printStats(ArrayList<Customer> customers) {

        String dashedLines = String.format("%0" + 63 + "d", 0).replace("0", "-");
        System.out.println(dashedLines);
        System.out.format("%1s%6s%9s%10s%9s%5s%5s%1s", "| ", "Cust # ", "| Arrival Time ", "| Service Time ", "| LOC ", "| Dep ", "| Notes ", "|");
        System.out.println("\n" + dashedLines);

        double selfAverage = 0;
        double average = 0;
        int numOfSelfCheckOuters = 1;

        for (Customer cust : customers) {

            cust.setCustomerNotes();

            String lineDivider = "|";
            System.out.format("%1s%3s%6s%9s%6s%9s%9s%3s%3s%3s%3s%3s", lineDivider, cust.getCustId(), lineDivider, cust.getArrivalTime(), lineDivider, cust.getServiceTime(), lineDivider, cust.getAssignedQueueLetter(), lineDivider, cust.getFinishTime(), lineDivider, cust.getCustomerNotes());
            System.out.println("\n" + dashedLines);

            if (cust.getCoinFlip() == 0) {
                selfAverage += cust.getWaitTime();
                numOfSelfCheckOuters++;

            } else {
                average += cust.getWaitTime();
            }
        }

        DecimalFormat f = new DecimalFormat("0.00");

        System.out.println("Average wait for FULL queue: " + f.format(average / numCustomers) + " min");
        System.out.println("Average wait for self-checkout: " + f.format(selfAverage / numOfSelfCheckOuters) + " min");
        System.out.println("Total time checkouts were not in use: " + (timeQueuesAreFree / 10) + " min");
        System.out.println("Total time self-check lanes were not in use: " + (timeLanesAreFree / 10) + " min");
        System.out.println("Satisfied customers: " + satisfiedCusts);
        System.out.println("Dissatisfied customers: " + dissatisfiedCusts);

    }


    private LinkedQueue findBestQueue(ArrayList<LinkedQueue> queues) {
        return Collections.min(queues);
    }

    private CheckoutLane findBestLane(ArrayList<CheckoutLane> lanes) {
        return Collections.min(lanes);
    }

    // Used to see if all of the lanes in the array is full. This is for making sure all Customer's with the same
    // arrival time have a chance to be added into a lane if there is an available one
    private boolean allLanesFull() {
        boolean flag = true;
        for (CheckoutLane lane : selfLanes) {
            if (!lane.isInUse()) {
                flag = false;
            }
        }
        return flag;
    }

    private void output(LinkedQueue self, int time) {
        ArrayList<String> customersWaitingInQueue = new ArrayList<>();

        for(LinkedQueue queue : fullQueues){
            handleQueueOutput(queue, queue.getQueueId(), time, customersWaitingInQueue);
        }
        handleSelfQueueOutput(self);
        for(CheckoutLane lane : selfLanes){
            handleLaneOutput(lane, lane.getLaneId(), time);
        }

        if (!customersWaitingInQueue.isEmpty()) {
            for (String s : customersWaitingInQueue) {
                System.out.println(s);
            }
        }
    }

    private void handleSelfQueueOutput(LinkedQueue self) {
        if (self.empty()) {
            System.out.println("\tSelf-Queue: free");
        } else {
            System.out.println("\tSelf-Queue: " + self.size() + " Customer(s) waiting in line");
        }
    }

    private void handleLaneOutput(CheckoutLane lane, int laneNum, int time) {
        if (lane.isInUse()) {
            if (lane.getCheckoutCustomer().getFinishTime() == time) {
                System.out.println("\tLane " + laneNum + ": Customer #" + lane.getCheckoutCustomer().getCustId() + " leaves");
            } else {
                System.out.println("\tLane " + laneNum + ": Customer #" + lane.getCheckoutCustomer().getCustId() + " in checkout");
            }
        } else {
            System.out.println("\tLane " + laneNum + ": free");
        }
    }

    private void handleQueueOutput(LinkedQueue queue, int queueNum, int time, ArrayList<String> customersWaitingInQueue) {
        if (queue.empty()) System.out.println("\tCheckout " + queueNum + ": free");
        else {
            for (int i = 0; i < queue.size(); i++) {
                Customer cc = queue.indexOf(i);
                if (cc != null) {
                    if (cc == queue.peek()) {
                        if (cc.getArrivalTime() == time)
                            System.out.println("\tCheckout " + queueNum + ": Customer #" + cc.getCustId() + " begins service");

                        if (cc.getFinishTime() == time)
                            System.out.println("\tCheckout " + queueNum + ": Customer #" + cc.getCustId() + " leaves");
                        if (cc.getArrivalTime() != time && cc.getFinishTime() != time)
                            System.out.println("\tCheckout " + queueNum + ": Customer #" + cc.getCustId() + " (cont)");

                    } else {
                        if (cc.getArrivalTime() + cc.getWaitTime() == time && i == 1)
                            System.out.println("\tCheckout " + queueNum + ": Customer #" + cc.getCustId() + " begins service");
                        else if (cc.getArrivalTime() == time)
                            customersWaitingInQueue.add("\tCustomer " + cc.getCustId() + " arrives and goes into Checkout " + queueNum + " queue");
                    }
                }
            }
        }
    }

    private ArrayList<Customer> customerWaitList(CustomerCreator cc) {
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

    public int getSelfSlowTime() {
        return selfSlowTime;
    }

    public void setSelfSlowTime(int selfSlowTime) {
        this.selfSlowTime = selfSlowTime;
    }
}
