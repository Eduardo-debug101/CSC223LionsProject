package lionsCheckpointB;

import java.util.ArrayList;
import java.util.Arrays;

public class Customer {
    private int arrivalTime;
    private int serviceTime;
    private int waitTime;
    private int leaveTime;
    private int custId;
    private static int nextNum = 0;
    

    public Customer() {
    	custId = nextNum;
		nextNum++;
    }

    public Customer(int a, int s) {
    	custId = nextNum;
		nextNum++;
        arrivalTime = a;
        serviceTime = s;
        //waitTime = arrivalTime + serviceTime;
        //leaveTime = (waitTime - arrivalTime) + serviceTime;
    }

    public Customer(int a, int s, int w, int l) {
    	custId = nextNum;
		nextNum++;
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
	
	public ArrayList getAllInfo() {
        ArrayList x = new ArrayList<>(Arrays.asList(
                Integer.toString(arrivalTime),
                Integer.toString(serviceTime),
                Integer.toString(waitTime),
                Integer.toString(leaveTime),
                Integer.toString(custId)
        ));
        return x;
    }
}
