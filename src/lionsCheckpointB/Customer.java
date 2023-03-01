package lionsCheckpointB;

public class Customer extends CustomerCreator {
    private int arrivalTime;
    private int serviceStart;
    private int waitTime;
    private int leaveTime;

    public Customer() {
    }

    public Customer(int a, int s) {
        arrivalTime = a;
        serviceStart = s;
    }

    public Customer(int a, int s, int w, int l) {
        arrivalTime = a;
        serviceStart = s;
        waitTime = w;
        leaveTime = l;
    }


    public String toString() {
        return "Customer --- Arrival time: " + arrivalTime + ", Service start: " + serviceStart + ", Wait time: " + waitTime + ", Leave time: " + leaveTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getServiceStart() {
        return serviceStart;
    }

    public void setServiceStart(int serviceStart) {
        this.serviceStart = serviceStart;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public int getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(int leaveTime) {
        this.leaveTime = leaveTime;
    }
}
