package lionsCheckpointB;

public class Customer extends CustomerCreator {
	private int arrivalTime;
	private int serviceTime;
	private int waitTime;
	private int leaveTime;
	
	public Customer(){	
	}
	
	public Customer(int a, int s, int w) {
		arrivalTime = a;
		serviceTime = s;	
		waitTime = w;
	}
	
	public Customer(int a, int s, int w, int l){	
		arrivalTime = a;
		serviceTime = s;
		waitTime = w;
		leaveTime = l;
	}
	

	public String toString() {
		return "Customer [arrivalTime=" + arrivalTime + ", serviceTime=" + serviceTime + ", waitTime=" + waitTime
				+ ", leaveTime=" + leaveTime + "]";
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	

	public int getServiceStart() {
		return serviceTime;
	}

	public void setServiceStart(int serviceStart) {
		this.serviceTime = serviceStart;
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
