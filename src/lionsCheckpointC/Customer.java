package lionsCheckpointC;

import java.util.concurrent.ThreadLocalRandom;

public class Customer {
    private int arrivalTime;
    private int serviceTime;
    private int waitTime;
    private int selfSlowTime;
    private int finishTime;
    private int custId;
    private static int nextNum = 1;
    private String assignedQueueLetter;
    private String customerNotes;
    private int coinFlip;


    public Customer() {
        custId = nextNum;
        nextNum++;
    }

    public Customer(int a, int s, int selfSlow) {
        custId = nextNum;
        nextNum++;
        arrivalTime = a;
        serviceTime = s;
        //waitTime = arrivalTime + serviceTime;
        //leaveTime = (waitTime - arrivalTime) + serviceTime;
        coinFlip = ThreadLocalRandom.current().nextInt(0, 1 + 1); // 0 = SELF 1 = FULL
        selfSlowTime = selfSlow;
    }

    public Customer(int a, int s, int w, int l) {
        custId = nextNum;
        nextNum++;
        arrivalTime = a;
        serviceTime = s;
        waitTime = w;
        finishTime = l;
    }

    public void calcWait(int leaveFirst) {
        int waitTime = Math.abs(leaveFirst - arrivalTime);
        setWaitTime(waitTime);
    }

    public void calcLeave() {
        int leave = 0;
        if (coinFlip == 0) { // Self
            leave = arrivalTime + waitTime + serviceTime + (serviceTime *(selfSlowTime/100));
        } else {
            leave = arrivalTime + waitTime + serviceTime;
        }
        this.setFinishTime(leave);
    }

    public String toString() {
        return "Customer --- Arrival time: " + arrivalTime + ", Service time: " + serviceTime + ", Wait time: " + waitTime + ", Leave time: " + finishTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public int getNextNum() {
        return nextNum;
    }

    public void setNextNum(int nextNum) {
        this.nextNum = nextNum;
    }

    public String getAssignedQueueLetter() {
        return assignedQueueLetter;
    }

    public void setAssignedQueueLetter(String assignedQueueLetter) {
        this.assignedQueueLetter = assignedQueueLetter;
    }

    public String getCustomerNotes() {
        return customerNotes;
    }

    public void setCustomerNotes() {
        String notes = " Goes to " + assignedQueueLetter + " @ " + serviceTime + "; leaves @ " + finishTime + "; WAIT: " + waitTime;
        this.customerNotes = notes;
    }

    public int getCoinFlip() {
        return coinFlip;
    }

    public void setCoinFlip(int coinFlip) {
        this.coinFlip = coinFlip;
    }


    // public ArrayList getAllInfo() {
    //     ArrayList x = new ArrayList<>(Arrays.asList(
    //             Integer.toString(arrivalTime),
    //             Integer.toString(serviceTime),
    //             Integer.toString(waitTime),
    //             Integer.toString(finishTime),
    //             Integer.toString(custId)
    //     ));
    //     return x;
    // }
}
