package lionsCheckpointB;

public class Customer {
    private int arrivalTime;
    private int serviceTime;
    private int waitTime;
    private int leaveTime;

    public Customer() {
    }

    public Customer(int a, int s) {
        arrivalTime = a;
        serviceTime = s;
        leaveTime = arrivalTime + serviceTime;
    }

    public Customer(int a, int s, int w, int l) {
        arrivalTime = a;
        serviceTime = s;
        waitTime = w;
        leaveTime = l;
    }


    public String toString() {
        return "Customer --- Arrival time: " + arrivalTime + ", Service time: " + serviceTime + ", Wait time: " + waitTime + ", Leave time: " + leaveTime;
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

    public int getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(int leaveTime) {
        this.leaveTime = leaveTime;
    }
}
