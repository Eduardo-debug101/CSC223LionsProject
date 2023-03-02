package lionsCheckpointB;

import java.util.ArrayList;

public class Simulator {
	private int arrivalMinTime;
	private int arrivalMaxTime;
	private int serviceMinTime;
	private int serviceMaxTime;
	private int numCustomers;

	public Simulator() {

	}

	public Simulator(int a, int b, int c, int d, int n) {
		arrivalMinTime = a;
		arrivalMaxTime = b;
		serviceMinTime = c;
		serviceMaxTime = d;
		numCustomers = n;
	}

	@Override
	public String toString() {
		return "Simulator [arrivalMinTime=" + arrivalMinTime + ", arrivalMaxTime=" + arrivalMaxTime
				+ ", serviceMinTime=" + serviceMinTime + ", serviceMaxTime=" + serviceMaxTime + ", numCustomers="
				+ numCustomers + "]";
	}

	public void start() {
		LinkedListStore2 A = new LinkedListStore2();
		LinkedListStore2 B = new LinkedListStore2();
		LinkedListStore2 C = new LinkedListStore2();
		for (int i = 0; i < numCustomers; i++) {
			int lowestQueueNumber = findLowestQueueSize(A.size(), B.size(), C.size());
			LinkedListStore2 selectedQueue = new LinkedListStore2();

			switch (lowestQueueNumber) {
			case 1 -> selectedQueue = A;
			case 2 -> selectedQueue = B;
			case 3 -> selectedQueue = C;
			}
			
			  if (!selectedQueue.isEmpty()) {
		            int startValue = selectedQueue.getLatest().value.getStartValue();
		            int holdTime = selectedQueue.getLatest().value.getCurrent().getServiceTime();
		            System.out.println("Waiting for person in front of me to leave...");
//		            try {
//						Thread.sleep(holdTime * 1000); 
//						selectedQueue.add(new CustomerCreator(minArrival, maxArrival, minService, maxService, startValue));
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//						}
//		            }
		            selectedQueue.add(new CustomerCreator(arrivalMinTime, arrivalMaxTime, serviceMinTime, serviceMaxTime, startValue));
		            setCustomerWaitTime(selectedQueue);
		            System.out.println(selectedQueue.getLatest().value.getCurrent().toString());
		            }
		            else {
		            selectedQueue.add(new CustomerCreator(arrivalMinTime, arrivalMaxTime, serviceMinTime, serviceMaxTime));
		            System.out.println(selectedQueue.getLatest().value.getCurrent().toString());
		            }

//		            ArrayList CurrentBlock = selectedQueue.getLatest().value.getAllInfo();
//		            System.out.println(selectedQueue.getLatest().value.toString());
		}
	}

	public int findLowestQueueSize(final int queueSizeA, final int queueSizeB, final int queueSizeC) {
		int finalQueue = 0;
		if (queueSizeA <= queueSizeB && queueSizeA <= queueSizeC) {
			System.out.println("Adding to queueA");
			finalQueue = 1;
		} else if (queueSizeB <= queueSizeC && queueSizeB <= queueSizeA) {
			System.out.println("Adding to queueB");
			finalQueue = 2;
		} else {
			System.out.println("Adding to queueC");
			finalQueue = 3;
		}

		return finalQueue;
	}
	
	public void setCustomerWaitTime(LinkedListStore2 current) {
		int begin = current.getLatest().value.getCurrent().getArrivalTime();
		int end = current.getLatest().prev.value.getCurrent().getLeaveTime();
		int waitTime = end - begin;
        current.getLatest().value.getCurrent().setWaitTime(waitTime);
	}

	public int getArrivalMinTime() {
		return arrivalMinTime;
	}

	public void setArrivalMinTime(int arrivalMinTime) {
		this.arrivalMinTime = arrivalMinTime;
	}

	public int getArrivalMaxTime() {
		return arrivalMaxTime;
	}

	public void setArrivalMaxTime(int arrivalMaxTime) {
		this.arrivalMaxTime = arrivalMaxTime;
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

	public int getNumCustomers() {
		return numCustomers;
	}

	public void setNumCustomers(int numCustomers) {
		this.numCustomers = numCustomers;
	}

}
