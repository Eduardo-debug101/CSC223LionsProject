package lionsCheckpointB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class CustomerCreator {
	private int minimumTime;
	private int maximumTime;
	private int serviceMinTime;
	private int serviceMaxTime;
	private int waitTime;
	private int startValue = 0;
	private Customer current;
	
	public CustomerCreator() {
	}
	
	public CustomerCreator(int m, int n, int s, int t, int w) {
		minimumTime = m;
		maximumTime = n;
		serviceMinTime = s;
		serviceMaxTime = t;
		waitTime = w;
	}
	
	@Override
	public String toString() {
		return "CustomerCreator [minimumTime=" + minimumTime + ", maximumTime=" + maximumTime + ", serviceMinTime="
				+ serviceMinTime + ", serviceMaxTime=" + serviceMaxTime + "]";
	}
	
	// Creates a customer - Eduardo 2/27
	public void create() {
		int randomArrivalNum = ThreadLocalRandom.current().nextInt(minimumTime, maximumTime + 1);
		int randomServiceNum = ThreadLocalRandom.current().nextInt(serviceMinTime, serviceMaxTime + 1);
		startValue += randomArrivalNum;
		current = new Customer(startValue, randomServiceNum, waitTime);
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
	
	
	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	public ArrayList<String> getAllInfo() {
		ArrayList<String> x = new ArrayList<>(Arrays.asList(Integer.toString(minimumTime)
		, Integer.toString(maximumTime)
		, Integer.toString(serviceMinTime)
		, Integer.toString(serviceMaxTime)
		, Integer.toString(startValue)));
		return x;
	}
}