package lionsCheckpointB;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulator {
    private int arrivalMinTime;
    private int arrivalMaxTime;
    private int serviceMinTime;
    private int serviceMaxTime;
    private int numCustomers;

    public Simulator() {

    }

    public Simulator(int a, int b, int c, int d, int n) {
        arrivalMinTime = a;
        arrivalMaxTime = b;
        serviceMinTime = c;
        serviceMaxTime = d;
        numCustomers = n;
    }

    @Override
    public String toString() {
        return "Simulator [arrivalMinTime=" + arrivalMinTime + ", arrivalMaxTime=" + arrivalMaxTime
                + ", serviceMinTime=" + serviceMinTime + ", serviceMaxTime=" + serviceMaxTime + ", numCustomers="
                + numCustomers + "]";
    }

    public void start() {

        LinkedListQueue A = new LinkedListQueue();
        LinkedListQueue B = new LinkedListQueue();
        LinkedListQueue C = new LinkedListQueue();

        List<List<String>> allData = new ArrayList<List<String>>(numCustomers);

        // This is to keep the while loop running until EVERY customer has been placed
        // into a queue, served, and left
        int customersServedAndLeft = 0;

        // This counts the "minutes" that has passed. This is used for the sample
        // output.
        int timer = -1;

        CustomerCreator cc = new CustomerCreator(arrivalMinTime, arrivalMaxTime, serviceMinTime, serviceMaxTime, 0);

        ArrayList<Customer> waitingCustomers = new ArrayList<Customer>();
        waitingCustomers = customerWaitList(cc);

        while (customersServedAndLeft < numCustomers) {
            //allData.add(new ArrayList<String>());

            timer++;
            System.out.println("Time: " + timer);

            if (timer == 0) {
                System.out.println("\tStart");

            } else {
                // ************************** For when there is an available Customer to be put into a queue
                if (!waitingCustomers.isEmpty()) {

                    boolean flag = true;
                    while (flag && !waitingCustomers.isEmpty()) {

                        // Moved this into the while loop so that duplicate times will get to select a queue
                        int lowestQueueNumber = findLowestQueueSize(A.size(), B.size(), C.size());
                        LinkedListQueue selectedQueue = new LinkedListQueue();

                        switch (lowestQueueNumber) {
                            case 1 -> selectedQueue = A;
                            case 2 -> selectedQueue = B;
                            case 3 -> selectedQueue = C;
                        }

                        // Gets the first Customer object element from the arraylist
                        Customer newCustForQueue = waitingCustomers.get(0);

                        // If the arrivalTime of the customer object is the same as the current
                        // time, then it will add it to the selected queue and delete the customer
                        // object from the arraylist
                        if (newCustForQueue.getArrivalTime() == timer) {
                            // Calculates wait time for each customer by getting the absloute value of the leave time of the
                            // person in front minus the arrival time of the customer.
                            if (!selectedQueue.isEmpty()) {
                                int finishTimeOfRear = selectedQueue.getRear().getFinishTime();
                                int serviceTimeOfCust = newCustForQueue.getServiceTime();
                                cc.calcLeave(newCustForQueue, finishTimeOfRear, serviceTimeOfCust);
                                newCustForQueue.calcWait();
                            } else {
                                newCustForQueue.setFinishTime(newCustForQueue.getArrivalTime() + newCustForQueue.getServiceTime());
                                newCustForQueue.setWaitTime(0);
                            }

                            selectedQueue.add(newCustForQueue);

                            ArrayList tmpArr = selectedQueue.getLast().getAllInfo();
                            tmpArr.add(Integer.toString(lowestQueueNumber));
                            allData.add(tmpArr);

                            waitingCustomers.remove(0);
                        } else {
                            flag = false;
                        }
                    }
                }// ************************** Adding Customers to queues section

                output(A, B, C, timer);
                if (!A.isEmpty()) {
                    if (A.getRear().getFinishTime() == timer) {
                        A.remove(A.getRear());
                        customersServedAndLeft++;
                    }
                }

                if (!B.isEmpty()) {
                    if (B.getRear().getFinishTime() == timer) {
                        B.remove(B.getRear());
                        customersServedAndLeft++;
                    }
                }

                if (!C.isEmpty()) {
                    if (C.getRear().getFinishTime() == timer) {
                        C.remove(C.getRear());
                        customersServedAndLeft++;
                    }
                }
            }
        }

        System.out.println("\n\n\nBeginning of Stats:\n");
        printStats(allData);

    }

    public static void printStats(List<List<String>> x) {

        String rere = String.format("%0" + 63 + "d", 0).replace("0", "-");
        System.out.println(rere);
        System.out.format("%1s%6s%9s%10s%9s%5s%5s%1s", "| ", "Cust # ", "| Arrival Time ", "| Service Time ", "| LOC ", "| Dep ", "| Notes ", "|");
        System.out.println("\n" + rere);

        for (int m = 0; m < x.size(); m++) {
            //System.out.println(allData.get(m).toString());
            String p = "|";
            System.out.format("%1s%3s%6s%9s%6s%9s%9s%3s%3s%3s%3s", p, x.get(m).get(4), p, x.get(m).get(0), p, x.get(m).get(1), p, numToLet(x.get(m).get(5)), p, x.get(m).get(3), p);
            System.out.println("\n" + rere);
            //System.out.println("|  " + x.get(m).get(4) + "    " + x.get(m).get(0) + "   "
            //		+ x.get(m).get(1) + "   " + x.get(m).get(5) + "    " + x.get(m).get(3));
        }

    }

    public static String numToLet(String string) {
        String number = "NA";
        switch (string) {
            case "1" -> number = "A";
            case "2" -> number = "B";
            case "3" -> number = "C";
        }
        return number;
    }

    public int findLowestQueueSize(final int queueSizeA, final int queueSizeB, final int queueSizeC) {
        int finalQueue = 0;
        if (queueSizeA <= queueSizeB && queueSizeA <= queueSizeC) {
            // System.out.println("Adding to queueA");
            finalQueue = 1;
        } else if (queueSizeB <= queueSizeC && queueSizeB <= queueSizeA) {
            // System.out.println("Adding to queueB");
            finalQueue = 2;
        } else {
            // System.out.println("Adding to queueC");
            finalQueue = 3;
        }

        return finalQueue;
    }

    // Going to eventually support most of the output. Similar to Dr. Wolff's output
    // at the end of the document.
    public void output(LinkedListQueue A, LinkedListQueue B, LinkedListQueue C, int time) {
        if (A.isEmpty()) {
            System.out.println("\tCheckout A: free");
        } else {
            Customer custA = A.getLast();
            if (custA.getArrivalTime() == time) {
                System.out.println("\tCheckout A: Customer " + custA.getCustId() + " added to queue");
            } else if (custA.getFinishTime() == time) {
                System.out.println("\tQueue A: removed Customer #" + custA.getCustId());
            } else {
                System.out.println("\tQueue A: (cont)");
            }
        }
        if (B.isEmpty()) {
            System.out.println("\tCheckout B: free");
        } else {
            Customer custB = B.getLast();
            if (custB.getArrivalTime() == time) {
                System.out.println("\tCheckout B: Customer " + custB.getCustId() + " starts service");
            } else if (custB.getFinishTime() == time) {
                System.out.println("\tQueue B: removed Customer #" + custB.getCustId());
            } else {
                System.out.println("\tQueue B: (cont)");
            }
        }
        if (C.isEmpty()) {
            System.out.println("\tCheckout C: free");
        } else {
            Customer custC = C.getLast();
            if (custC.getArrivalTime() == time) {
                System.out.println("\tCheckout C: Customer " + custC.getCustId() + " starts service");
            } else if (custC.getFinishTime() == time) {
                System.out.println("\tQueue C: removed Customer #" + custC.getCustId());
            } else {
                System.out.println("\tQueue C: (cont)");
            }
        }
    }

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

}
