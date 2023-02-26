package lionsCheckpointB;

import java.time.LocalTime;

public class Customer extends CustomerCreator {
	private LocalTime arrivalTime;
	private LocalTime serviceStart;
	private LocalTime waitTime;
	private LocalTime leaveTime;
	
	public Customer(){	
	}
	
	public Customer(LocalTime a, LocalTime s, LocalTime w, LocalTime l){	
		arrivalTime = a;
		serviceStart = s;
		waitTime = w;
		leaveTime = l;
	}
	

	public String toString() {
		return "Customer [arrivalTime=" + arrivalTime + ", serviceStart=" + serviceStart + ", waitTime=" + waitTime
				+ ", leaveTime=" + leaveTime + "]";
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	

	public LocalTime getServiceStart() {
		return serviceStart;
	}

	public void setServiceStart(LocalTime serviceStart) {
		this.serviceStart = serviceStart;
	}

	public LocalTime getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(LocalTime waitTime) {
		this.waitTime = waitTime;
	}

	public LocalTime getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(LocalTime leaveTime) {
		this.leaveTime = leaveTime;
	}
	
	
}
