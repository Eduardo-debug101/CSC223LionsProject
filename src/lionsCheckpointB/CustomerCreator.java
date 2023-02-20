package lionsCheckpointB;

import java.time.LocalTime;

public class CustomerCreator {
	private LocalTime arrivalTime;
	private LocalTime waitTime;
	private LocalTime leaveTime;
	
	public CustomerCreator(){	
	}
	
	public CustomerCreator(LocalTime a, LocalTime w, LocalTime l){	
		arrivalTime = a;
		waitTime = w;
		leaveTime = l;
	}
	
	@Override
	public String toString() {
		return "Customer [arrivalTime=" + arrivalTime + ", waitTime=" + waitTime + ", leaveTime=" + leaveTime + "]";
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
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