package lionsCheckpointC;

import java.util.ArrayList;
import java.util.Arrays;


public class Customer {
    private int arrivalTime;
    private int serviceTime;
    private int waitTime;
    private int fakeWaitTime;
    private int finishTime;
    private int custId;
    private static int nextNum = 0;
    private String assignedQueueLetter;
    private String customerNotes;


    public Customer() {
        //custId = nextNum++;
    }

    public Customer(int a, int s) {
        custId = nextNum++;
        arrivalTime = a;
        serviceTime = s;
        waitTime = arrivalTime + serviceTime;
        fakeWaitTime = waitTime;
        finishTime = (waitTime - arrivalTime) + serviceTime;
    }

    public Customer(int a, int s, int w, int l) {
        custId = nextNum++;
        arrivalTime = a;
        serviceTime = s;
        waitTime = w;
        fakeWaitTime = waitTime;
        finishTime = l;
    }

    public void calcWait(int leaveFirst) {
        int waitTime = Math.abs(leaveFirst - arrivalTime);
        fakeWaitTime = waitTime;
        setWaitTime(waitTime);
    }

    public void calcLeave() {
        int leave = arrivalTime + waitTime + serviceTime;
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
        this.fakeWaitTime = waitTime;
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
    
     public int getFakeWaitTime() {
		return fakeWaitTime;
	}

	public void setFakeWaitTime(int fakeWaitTime) {
		this.fakeWaitTime = fakeWaitTime;
	}

	public ArrayList getAllInfo() {
         ArrayList x = new ArrayList<>(Arrays.asList(
                 Integer.toString(arrivalTime),
                 Integer.toString(serviceTime),
                 Integer.toString(waitTime),
                 Integer.toString(finishTime),
                 Integer.toString(this.custId)
         ));
         return x;
     }
}
