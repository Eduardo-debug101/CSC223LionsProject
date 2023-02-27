package lionsCheckpointB;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class CustomerCreator {
	private int minimumTime;
	private int maximumTime;
	private int serviceMinTime;
	private int serviceMaxTime;
	private int startValue = 0;
	
	public CustomerCreator() {
	}
	
	public CustomerCreator(int m, int n, int s, int t) {
		minimumTime = m;
		maximumTime = n;
		serviceMinTime = s;
		serviceMaxTime = t;
	}
	
	@Override
	public String toString() {
		return "CustomerCreator [minimumTime=" + minimumTime + ", maximumTime=" + maximumTime + ", serviceMinTime="
				+ serviceMinTime + ", serviceMaxTime=" + serviceMaxTime + "]";
	}
	
	public void create() {
		int randomArrivalNum = ThreadLocalRandom.current().nextInt(minimumTime, maximumTime + 1);
		int randomServiceNum = ThreadLocalRandom.current().nextInt(serviceMinTime, serviceMaxTime + 1);
		startValue += randomArrivalNum;
		Customer c = new Customer(startValue, randomServiceNum);
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
}