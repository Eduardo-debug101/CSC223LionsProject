package lionsCheckpointE;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class Simulator {
	private int arrivalMinTime;
	private int arrivalMaxTime;
	private int serviceMinTime;
	private int serviceMaxTime;
	private int selfSlowTime;
	private int numCustomers;
	private int fullQueuesAmt;
	private int selfLanesAmt;

	// Global variables used by various methods, mainly for stats collection
	// **************************
	private int timer = -1;
	private static int satisfiedCusts = 0;
	private static int dissatisfiedCusts = 0;
	private static int customersServedAndLeft = 0;
	private static int timeQueuesAreFree = 0;
	private static int timeLanesAreFree = 0;
	// **************************

	public Simulator() {

	}

	public Simulator(int a, int b, int c, int d, int n, int selfSlow, int fullQueuesAmt, int selfLanesAmt) {
		arrivalMinTime = a;
		arrivalMaxTime = b;
		serviceMinTime = c;
		serviceMaxTime = d;
		numCustomers = n;
		selfSlowTime = selfSlow;
		this.fullQueuesAmt = fullQueuesAmt;
		this.selfLanesAmt = selfLanesAmt;
        satisfiedCusts = 0;
        dissatisfiedCusts = 0;
        customersServedAndLeft = 0;
        timeQueuesAreFree = 0;
        timeLanesAreFree = 0;
	}

	public String toString() {
		return "Simulator [arrivalMinTime=" + arrivalMinTime + ", arrivalMaxTime=" + arrivalMaxTime
				+ ", serviceMinTime=" + serviceMinTime + ", serviceMaxTime=" + serviceMaxTime + ", numCustomers="
				+ numCustomers + "]";
	}

	private void populateQueueArray(ArrayList<LinkedQueue> array, int size) {
		for (int i = 0; i < size; i++) {
			array.add(new LinkedQueue());
		}
	}

	private void populateLaneArray(ArrayList<CheckoutLane> array, int size) {
		for (int i = 0; i < size; i++) {
			array.add(new CheckoutLane());
		}
	}

	public void start() {

		ArrayList<LinkedQueue> fullQueues = new ArrayList<>(this.fullQueuesAmt);
		ArrayList<CheckoutLane> selfLanes = new ArrayList<>(this.selfLanesAmt);

		// Populates the queue/lane arrays with empty objects
		populateQueueArray(fullQueues, this.fullQueuesAmt);
		populateLaneArray(selfLanes, this.selfLanesAmt);

		LinkedQueue selfQueue = new LinkedQueue();

		CustomerCreator cc = new CustomerCreator(arrivalMinTime, arrivalMaxTime, serviceMinTime, serviceMaxTime, 0,
				selfSlowTime);

		ArrayList<Customer> waitingCustomers = new ArrayList<Customer>();
		waitingCustomers = customerWaitList(cc);

		// This array is a total of all created Customers for printStats()
		ArrayList<Customer> queuedCustsForStats = new ArrayList<>();

		// Main while loop continues until every Customer has been served
		while (customersServedAndLeft != numCustomers) {

			timer++;
			System.out.println("Time: " + timer);

			if (timer == 0) {
				System.out.println("\tStart");
			}

			// ************************** START Adding Customers to queues/lanes section
			if (!waitingCustomers.isEmpty()) {

				// While loop is used to keep adding Customers that have the same arrivalTime as
				// the current Timer
				boolean flag = true;
				while (flag && !waitingCustomers.isEmpty()) {
					/*
					 * If the arrivalTime of the customer object is the same as the current time,
					 * then it will add it to the selected queue and remove the customer object from
					 * the arraylist
					 */
					Customer newCustForQueue = waitingCustomers.get(0);
					if (newCustForQueue.getArrivalTime() == timer) {

						// Finds the smallest queue / self queue to add customers into
						LinkedQueue selectedQueue = findBestQueue(fullQueues, selfQueue, newCustForQueue.getCoinFlip());

						String queueNum = String.valueOf(selectedQueue.getQueueId());

						updateCustomerTimes(newCustForQueue, selectedQueue);
						selectedQueue.enqueue(newCustForQueue);
						if (newCustForQueue.getCoinFlip() == 0)
							newCustForQueue.setAssignedQueueLetter("S");
						else
							newCustForQueue.setAssignedQueueLetter(queueNum);

						// This array is for the stats at the end of the program
						queuedCustsForStats.add(newCustForQueue);

						waitingCustomers.remove(0);
					} else {
						flag = false;
					}
				}
			}

			// Handles adding Customer's to lanes and dequeus them from the selfQueue
			// Loops until both Lanes are full or if the selfQueue has no more Customers
			CheckoutLane selectedLane = new CheckoutLane();
			boolean flag2 = true;
			while (flag2) {
				selectedLane = findBestLane(selfLanes);
				if (!selectedLane.isInUse() && !selfQueue.empty()) {
					selectedLane.setCheckoutCustomer(selfQueue.peek());
					selfQueue.dequeue();
				}
				if (allLanesFull(selfLanes) || selfQueue.empty()) {
					flag2 = false;
				}
			}

			// ************************** END Adding Customers to queues/lanes section

			output(selfQueue, selfLanes, fullQueues, timer);

			// ************************** START Removing Customers from queues section
			for (LinkedQueue queue : fullQueues) {
				handleQueueRemoval(queue, timer);
			}

			for (CheckoutLane lane : selfLanes) {
				handleLaneRemoval(lane, timer);
			}
			// ************************** END Removing Customers from queues section

			// If someone is currently checking out then those who are still in self queue
			// must wait longer
			if (selectedLane.isInUse() && !selfQueue.empty()) {
				for (int i = 0; i < selfQueue.size(); i++) {
					selfQueue.indexOf(i).setFinishTime(selfQueue.indexOf(i).getFinishTime() + 1);
				}
			}
		}

		System.out.println("\n\n\nBeginning of Stats:\n");
		printStats(queuedCustsForStats);
	}

	/**
	 * Removes the Customer using the full service queue if they are ready to leave
	 * via finishTime
	 * <p>
	 * Updates satisfiedCusts/dissatisfiedCusts globals if the Customer was happy or
	 * not
	 * <p>
	 * Updates total queue time for stats
	 *
	 * @param queue Queue for Customers
	 * @param time  Time for comparison
	 */
	private void handleQueueRemoval(LinkedQueue queue, int time) {
		if (!queue.empty()) {
			if (queue.peek().getFinishTime() == time) {
				if (queue.peek().getWaitTime() >= 5) {
					dissatisfiedCusts += 1;
				} else {
					satisfiedCusts += 1;
				}
				queue.dequeue();
				customersServedAndLeft++;
			}
		} else {
			timeQueuesAreFree++;
		}
	}

	/**
	 * Removes the Customer using the self service lane if they are ready to leave
	 * via finishTime
	 * <p>
	 * Updates satisfiedCusts/dissatisfiedCusts globals if the Customer was happy or
	 * not
	 * <p>
	 * Updates total lane time for stats
	 *
	 * @param lane Lane for Customers
	 * @param time Time for comparison
	 */
	private void handleLaneRemoval(CheckoutLane lane, int time) {
		if (lane.isInUse()) {
			if (lane.getCheckoutCustomer().getFinishTime() == time) {
				if (lane.getCheckoutCustomer().getWaitTime() >= 5) {
					dissatisfiedCusts += 1;
				} else {
					satisfiedCusts += 1;
				}
				lane.setCheckoutCustomer(null);
				customersServedAndLeft++;
			}
		} else {
			timeLanesAreFree++;
		}
	}

	/**
	 * If the Customer's coinFlip is 0, they are placed in the self service queue
	 * <p>
	 * If the Customer's coinFlip is 1, they are placed in the smallest full service
	 * queue
	 *
	 * @param fullQueues Array of full-service queues
	 * @param selfQueue  Array of self-service queues
	 * @param coinFlip   The Customer's coinFlip value (0 or 1)
	 * @return The best queue for a Customer based on the Customer's coinFlip as
	 *         well as queue size
	 */
	private LinkedQueue findBestQueue(ArrayList<LinkedQueue> fullQueues, LinkedQueue selfQueue, int coinFlip) {
		LinkedQueue result;
		if (coinFlip == 0) {
			result = selfQueue;
		} else {
			result = Collections.min(fullQueues);
		}
		return result;
	}

	/**
	 * @param lanes Array of self-service lanes
	 * @return The best lane for a Customer based on the lane's size
	 */
	private CheckoutLane findBestLane(ArrayList<CheckoutLane> lanes) {
		return Collections.min(lanes);
	}

	/**
	 * This is for making sure all Customer's with the same arrival time have a
	 * chance to be added into a lane if there is an available one
	 *
	 * @param selfLanes Array of lanes
	 * @return True if all the lanes in the array is full
	 */
	private boolean allLanesFull(ArrayList<CheckoutLane> selfLanes) {
		boolean flag = true;
		for (CheckoutLane lane : selfLanes) {
			if (!lane.isInUse()) {
				flag = false;
			}
		}
		return flag;
	}

	private ArrayList<Customer> customerWaitList(CustomerCreator cc) {
		ArrayList<Customer> wc = new ArrayList<Customer>();
		for (int i = 0; i < numCustomers; i++) {
			wc.add(cc.create());
		}
		return wc;
	}

	/**
	 * When being added to a queue, calculates wait time for the customer by getting
	 * the absolute value of the leave time of the person in front of the queue
	 * minus the arrival time of the current customer.
	 *
	 * @param cust  The Customer
	 * @param queue Queue the Customer will be added to
	 */
	private void updateCustomerTimes(Customer cust, LinkedQueue queue) {
		if (!queue.empty()) {
			int finishTimeOfRear = queue.getRear().getFinishTime();
			cust.calcWait(finishTimeOfRear);
			cust.calcLeave();
		} else {
			cust.setFinishTime(cust.getArrivalTime() + cust.getServiceTime());
			cust.setWaitTime(0);
		}
	}

	private void output(LinkedQueue self, ArrayList<CheckoutLane> selfLanes, ArrayList<LinkedQueue> fullQueues,
			int time) {
		ArrayList<String> customersWaitingInQueue = new ArrayList<>();

		for (LinkedQueue queue : fullQueues) {
			handleQueueOutput(queue, queue.getQueueId(), time, customersWaitingInQueue);
		}
		handleSelfQueueOutput(self);
		for (CheckoutLane lane : selfLanes) {
			handleLaneOutput(lane, lane.getLaneId(), time);
		}

		if (!customersWaitingInQueue.isEmpty()) {
			for (String s : customersWaitingInQueue) {
				System.out.println(s);
			}
		}
	}

	private void handleSelfQueueOutput(LinkedQueue self) {
		if (self.empty()) {
			System.out.println("\tSelf-Queue: free");
		} else {
			System.out.println("\tSelf-Queue: " + self.size() + " Customer(s) waiting in line");
		}
	}

	private void handleLaneOutput(CheckoutLane lane, int laneNum, int time) {
		if (lane.isInUse()) {
			if (lane.getCheckoutCustomer().getFinishTime() == time) {
				System.out.println(
						"\tLane " + laneNum + ": Customer #" + lane.getCheckoutCustomer().getCustId() + " leaves");
			} else {
				System.out.println(
						"\tLane " + laneNum + ": Customer #" + lane.getCheckoutCustomer().getCustId() + " in checkout");
			}
		} else {
			System.out.println("\tLane " + laneNum + ": free");
		}
	}

	private void handleQueueOutput(LinkedQueue queue, int queueNum, int time,
			ArrayList<String> customersWaitingInQueue) {
		if (queue.empty())
			System.out.println("\tCheckout " + queueNum + ": free");
		else {
			for (int i = 0; i < queue.size(); i++) {
				Customer cc = queue.indexOf(i);
				if (cc != null) {
					if (cc == queue.peek()) {
						if (cc.getArrivalTime() == time)
							System.out.println(
									"\tCheckout " + queueNum + ": Customer #" + cc.getCustId() + " begins service");

						if (cc.getFinishTime() == time)
							System.out.println("\tCheckout " + queueNum + ": Customer #" + cc.getCustId() + " leaves");
						if (cc.getArrivalTime() != time && cc.getFinishTime() != time)
							System.out.println("\tCheckout " + queueNum + ": Customer #" + cc.getCustId() + " (cont)");

					} else {
						if (cc.getArrivalTime() + cc.getWaitTime() == time && i == 1)
							System.out.println(
									"\tCheckout " + queueNum + ": Customer #" + cc.getCustId() + " begins service");
						else if (cc.getArrivalTime() == time)
							customersWaitingInQueue.add("\tCustomer " + cc.getCustId()
									+ " arrives and goes into Checkout " + queueNum + " queue");
					}
				}
			}
		}
	}

	private void printStats(ArrayList<Customer> customers) {

		String dashedLines = String.format("%0" + 63 + "d", 0).replace("0", "-");
		System.out.println(dashedLines);
		System.out.format("%1s%6s%9s%10s%9s%5s%5s%1s", "| ", "Cust # ", "| Arrival Time ", "| Service Time ", "| LOC ",
				"| Dep ", "| Notes ", "|");
		System.out.println("\n" + dashedLines);

		double selfAverage = 0;
		double average = 0;
		int numOfSelfCheckOuters = 1;

		for (Customer cust : customers) {

			cust.setCustomerNotes();

			String lineDivider = "|";
			System.out.format("%1s%3s%6s%9s%6s%9s%9s%3s%3s%3s%3s%3s", lineDivider, cust.getCustId(), lineDivider,
					cust.getArrivalTime(), lineDivider, cust.getServiceTime(), lineDivider,
					cust.getAssignedQueueLetter(), lineDivider, cust.getFinishTime(), lineDivider,
					cust.getCustomerNotes());
			System.out.println("\n" + dashedLines);

			if (cust.getCoinFlip() == 0) {
				selfAverage += cust.getWaitTime();
				numOfSelfCheckOuters++;

			} else {
				average += cust.getWaitTime();
			}
		}

		DecimalFormat f = new DecimalFormat("0.00");

		System.out.println();

		System.out.println("Average wait for FULL queue: " + f.format(average / numCustomers) + " min");
		System.out.println("Average wait for self-checkout: " + f.format(selfAverage / numOfSelfCheckOuters) + " min");
		System.out.println("Total time checkouts were not in use: " + (timeQueuesAreFree / 10) + " min");
		System.out.println("Total time self-check lanes were not in use: " + (timeLanesAreFree / 10) + " min");
		System.out.println("Satisfied customers: " + satisfiedCusts);
		System.out.println("Dissatisfied customers: " + dissatisfiedCusts);

		System.out.println();

		// Calculating "percentage" of time that self-checkout lane is occupied.
		double laneUsage = (timeQueuesAreFree / 10) / timer;
		// Recommendations logic for self-checkout
		if ((selfAverage / numOfSelfCheckOuters) > 5 && laneUsage < 0.5) {
			System.out.println("Suggest adding self-checkout lanes.");
		} else if ((selfAverage / numOfSelfCheckOuters) < 2 && laneUsage > 0.8) {
			System.out.println("Suggest removing self-checkout lanes.");
		} else {
			System.out.println("No suggestion for self-checkout lanes.");
		}
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

	public int getSelfSlowTime() {
		return selfSlowTime;
	}

	public void setSelfSlowTime(int selfSlowTime) {
		this.selfSlowTime = selfSlowTime;
	}
}
