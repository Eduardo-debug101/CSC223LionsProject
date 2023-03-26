package lionsCheckpointC;

import java.util.concurrent.ThreadLocalRandom;

public class CustomerCreator{
    private int minimumTime;
    private int maximumTime;
    private int serviceMinTime;
    private int serviceMaxTime;
    private int startValue;
    private Customer current;

    public CustomerCreator() {
    }

    public CustomerCreator(int m, int n, int s, int t) {
        minimumTime = m;
        maximumTime = n;
        serviceMinTime = s;
        serviceMaxTime = t;
        startValue = 0;
    }

    public CustomerCreator(int m, int n, int s, int t, int startTime) {
        minimumTime = m;
        maximumTime = n;
        serviceMinTime = s;
        serviceMaxTime = t;
        startValue = startTime;
    }

    public String toString() {
        return super.toString();
    }

    // Creates a customer - Eduardo 2/27
    public Customer create() {
        int randomArrivalNum = ThreadLocalRandom.current().nextInt(minimumTime, maximumTime + 1);
        int randomServiceNum = ThreadLocalRandom.current().nextInt(serviceMinTime, serviceMaxTime + 1);
        startValue += randomArrivalNum;
        current = new Customer(startValue, randomServiceNum);
        return current;
    }

//    public void calcWait(Customer c, int leaveFirst, int arrivalSecond) {
//    	int waitTime = Math.abs(leaveFirst - arrivalSecond);
//    	c.setWaitTime(waitTime);
//    }

    public Customer getCurrent() {
        return current;
    }

    public void setCurrent(Customer c) {
        this.current = c;
    }

    public int getMinimumTime() {
        return minimumTime;
    }

    public void setMinimumTime(int minimumTime) {
        this.minimumTime = minimumTime;
    }

    public int getMaximumTime() {
        return maximumTime;
    }

    public void setMaximumTime(int maximumTime) {
        this.maximumTime = maximumTime;
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

    public int getStartValue() {
        return startValue;
    }

    public void setStartValue(int startValue) {
        this.startValue = startValue;
    }
}