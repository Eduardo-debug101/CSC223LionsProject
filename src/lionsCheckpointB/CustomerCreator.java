package lionsCheckpointB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class CustomerCreator {
    private int minimumTime;
    private int maximumTime;
    private int serviceMinTime;
    private int serviceMaxTime;
    private static int startValue = 0;
    private Customer current;

    public CustomerCreator() {
    }

    public CustomerCreator(int m, int n, int s, int t) {
        minimumTime = m;
        maximumTime = n;
        serviceMinTime = s;
        serviceMaxTime = t;
    }

    public String toString() {
        return "CustomerCreator --- Minimum time: " + minimumTime + ", Maximum time: " + maximumTime + ", Service min time: " + serviceMinTime + ", Service max time: " + serviceMaxTime + " Start value: " + startValue;
    }

    // Creates a customer - Eduardo 2/27
    public void create() {
        int randomArrivalNum = ThreadLocalRandom.current().nextInt(minimumTime, maximumTime + 1);
        int randomServiceNum = ThreadLocalRandom.current().nextInt(serviceMinTime, serviceMaxTime + 1);
        startValue += randomArrivalNum;
        current = new Customer(startValue, randomServiceNum);
        System.out.println(current + " HHHEEERRREEE");
    }

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

    public ArrayList getAllInfo() {
        ArrayList x = new ArrayList<>(Arrays.asList(
                Integer.toString(minimumTime),
                Integer.toString(maximumTime),
                Integer.toString(serviceMinTime),
                Integer.toString(serviceMaxTime),
                Integer.toString(startValue)
        ));
        return x;
    }
}