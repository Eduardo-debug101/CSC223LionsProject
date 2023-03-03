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
		LinkedListQueue A = new LinkedListQueue();
		LinkedListQueue B = new LinkedListQueue();
		LinkedListQueue C = new LinkedListQueue();

		// This is to keep the while loop running until EVERY customer has been placed
		// into a queue, served, and left
		int customersServedAndLeft = 0;
		// This will have to eventually equal the amount of customers that the user
		// entered
		int customersAddedToQueues = 0;

		// This counts the "minutes" that has passed. This is used for the sample output.
		int timer = -1;
		
		// This Creator will create a Customer based on our user's inputted parameters
		// as well as a start time that starts
		// at zero and continues to increase for every Customer created
		CustomerCreator cc = new CustomerCreator(arrivalMinTime, arrivalMaxTime, serviceMinTime, serviceMaxTime, 0);

		ArrayList<Customer> waitingCustomers = new ArrayList<Customer>();
		waitingCustomers = customerWaitList(cc);
		
		while (customersServedAndLeft < numCustomers) {
			timer++;
			// Remember to remove this. For debug purposes.
			customersServedAndLeft++;
			System.out.println("Time: " + timer);
			if (timer == 0) {
				System.out.println("\tStart");
			} else {

				// **************************
				// For when there is an available Customer to be put into a queue
				if (customersAddedToQueues < numCustomers) {
					int lowestQueueNumber = findLowestQueueSize(A.size(), B.size(), C.size());
					LinkedListQueue selectedQueue = new LinkedListQueue();

					switch (lowestQueueNumber) {
					case 1 -> selectedQueue = A;
					case 2 -> selectedQueue = B;
					case 3 -> selectedQueue = C;
					}

					Customer newCustomerForQueue = waitingCustomers.get(0);
					
					if (newCustomerForQueue.getArrivalTime() == timer) {
					selectedQueue.add(newCustomerForQueue);
					customersAddedToQueues++;
					waitingCustomers.remove(0);
					}
					output(A, B, C, timer);
					
				}
			}
			// **************************
		}

//        for (int i = 0; i < numCustomers; i++) {
//
//            int lowestQueueNumber = findLowestQueueSize(A.size(), B.size(), C.size());
//            LinkedListStore2 selectedQueue = new LinkedListStore2();
//
//            switch (lowestQueueNumber) {
//                case 1 -> selectedQueue = A;
//                case 2 -> selectedQueue = B;
//                case 3 -> selectedQueue = C;
//            }
//
//            if (!selectedQueue.isEmpty()) {
//                int startValue = selectedQueue.getLatest().value.getStartValue();
//                int holdTime = selectedQueue.getLatest().value.getCurrent().getServiceTime();
//                System.out.println("Waiting for person in front of me to leave...");
////		            try {
////						Thread.sleep(holdTime * 1000);
////						selectedQueue.add(new CustomerCreator(minArrival, maxArrival, minService, maxService, startValue));
////					} catch (InterruptedException e) {
////						e.printStackTrace();
////						}
////		            }
//                selectedQueue.add(new CustomerCreator(arrivalMinTime, arrivalMaxTime, serviceMinTime, serviceMaxTime, startValue));
//                setCustomerWaitTime(selectedQueue);
//                System.out.println(selectedQueue.getLatest().value.getCurrent().toString());
//            } else {
//                selectedQueue.add(new CustomerCreator(arrivalMinTime, arrivalMaxTime, serviceMinTime, serviceMaxTime));
//                System.out.println(selectedQueue.getLatest().value.getCurrent().toString());
//            }
//
////		            ArrayList CurrentBlock = selectedQueue.getLatest().value.getAllInfo();
////		            System.out.println(selectedQueue.getLatest().value.toString());
//        }
	}

	public int findLowestQueueSize(final int queueSizeA, final int queueSizeB, final int queueSizeC) {
		int finalQueue = 0;
		if (queueSizeA <= queueSizeB && queueSizeA <= queueSizeC) {
			//System.out.println("Adding to queueA");
			finalQueue = 1;
		} else if (queueSizeB <= queueSizeC && queueSizeB <= queueSizeA) {
			//System.out.println("Adding to queueB");
			finalQueue = 2;
		} else {
			//System.out.println("Adding to queueC");
			finalQueue = 3;
		}

		return finalQueue;
	}
	
	public void output(LinkedListQueue A, LinkedListQueue B, LinkedListQueue C, int time) {
		if (A.isEmpty()) {
			System.out.println("\tCheckout A: free");
		}
		else {
			Customer custA = A.getLast();
			if (custA.getArrivalTime() == time) {
				System.out.println("\tCheckout A: Customer " + custA.getCustId() + " starts service");
			}	
		}
		if (B.isEmpty()) {
			System.out.println("\tCheckout B: free");
		}
		else {
			Customer custB = B.getLast();
			if (custB.getArrivalTime() == time) {
				System.out.println("\tCheckout B: Customer " + custB.getCustId() + " starts service");
			}	
		}
		if (C.isEmpty()) {
			System.out.println("\tCheckout C: free");
		}
		else {
			Customer custC = C.getLast();
			if (custC.getArrivalTime() == time) {
				System.out.println("\tCheckout C: Customer " + custC.getCustId() + " starts service");
			}	
		}	
	}
	
	public ArrayList<Customer> customerWaitList(CustomerCreator cc) {
		ArrayList<Customer> wc = new ArrayList<Customer>();
		for (int i = 0; i < numCustomers; i++) {
			wc.add(cc.create());
		}
		return wc;
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
