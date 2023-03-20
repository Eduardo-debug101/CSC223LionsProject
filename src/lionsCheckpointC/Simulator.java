package lionsCheckpointC;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Simulator {
	private int arrivalMinTime;
	private int arrivalMaxTime;
	private int serviceMinTime;
	private int serviceMaxTime;
	private int numCustomers;
	static ArrayList<Integer> colWT = new ArrayList<>();
	private static int satisfiedCusts = 0;
	private static int dissatisfiedCusts = 0;
	// This is to keep the start() while loop running until EVERY customer has been
	// placed into a queue, served, and left
	private static int customersServedAndLeft = 0;
	private static int timeQueuesAreFree = 0;

	public Simulator() {

	}

	public Simulator(int a, int b, int c, int d, int n) {
		arrivalMinTime = a;
		arrivalMaxTime = b;
		serviceMinTime = c;
		serviceMaxTime = d;
		numCustomers = n;
	}

	public String toString() {
		return "Simulator [arrivalMinTime=" + arrivalMinTime + ", arrivalMaxTime=" + arrivalMaxTime
				+ ", serviceMinTime=" + serviceMinTime + ", serviceMaxTime=" + serviceMaxTime + ", numCustomers="
				+ numCustomers + "]";
	}

	public void start() {

		LinkedQueue A = new LinkedQueue();
		LinkedQueue B = new LinkedQueue();
		LinkedQueue C = new LinkedQueue();

		LinkedQueue self = new LinkedQueue();
		LinkedQueue D = new LinkedQueue();
		LinkedQueue E = new LinkedQueue();

		List<List<String>> allData = new ArrayList<List<String>>(numCustomers);

		CustomerCreator cc = new CustomerCreator(arrivalMinTime, arrivalMaxTime, serviceMinTime, serviceMaxTime, 0);

		ArrayList<Customer> waitingCustomers = new ArrayList<Customer>();
		waitingCustomers = customerWaitList(cc);

		// This counts the "minutes" that has passed. This is used for the sample
		// output.
		int timer = -1;

		// Main while loop continues until every Customer has been served
		while (customersServedAndLeft != numCustomers) {

			timer++;
			System.out.println("Time: " + timer);

			if (timer == 0) {
				System.out.println("\tStart");

			}

			// ************************** START For when there is an available Customer to
			// be put into a queue
			if (!waitingCustomers.isEmpty()) {

				// While loop is used to keep adding Customers that have the same arrivalTime as
				// the current Timer
				boolean flag = true;
				while (flag && !waitingCustomers.isEmpty()) {
					int coinFlip = ThreadLocalRandom.current().nextInt(0, 1 + 1); // 0 = SELF 1 = FULL
					if (coinFlip == 1) {
						// Finds the smallest queue to add customers into
						LinkedQueue selectedQueue = selectAQueue(A, B, C);
						String QueueLetter = findLowestQueueNum(A, B, C);

						// Gets the first Customer object element from the arraylist
						Customer newCustForQueue = waitingCustomers.get(0);

						// If the arrivalTime of the customer object is the same as the current
						// time, then it will add it to the selected queue and delete the customer
						// object from the arraylist
						if (newCustForQueue.getArrivalTime() == timer) {

							// Calculates wait time for each customer by getting the absloute value of the
							// leave time of the
							// person in front minus the arrival time of the customer.
							if (!selectedQueue.empty()) {
								int finishTimeOfRear = selectedQueue.getRear().getFinishTime();
								newCustForQueue.calcWait(finishTimeOfRear);
								newCustForQueue.calcLeave();
							} else {
								newCustForQueue.setFinishTime(
										newCustForQueue.getArrivalTime() + newCustForQueue.getServiceTime());
								newCustForQueue.setWaitTime(0);
							}

							selectedQueue.enqueue(newCustForQueue);

							ArrayList tmpArr = newCustForQueue.getAllInfo();
							String e = String.valueOf(tmpArr.get(2));
							int b = Integer.parseInt(e);
							colWT.add(b);

							String Notes = " Goes to " + QueueLetter + "@" + tmpArr.get(0) + ";leaves@" + tmpArr.get(3)
									+ " WAIT:" + tmpArr.get(2);

							tmpArr.add(QueueLetter);
							tmpArr.add(Notes);
							allData.add(tmpArr);

							waitingCustomers.remove(0);
						} else {
							flag = false;
						}
					} else {
						self.enqueue(waitingCustomers.get(0));
						if (A.empty() || B.empty()) {
							
						}
						else {
							
						}

					}
				}
			} // ************************** END Adding Customers to queues section

			output(A, B, C, D, timer);

			// ************************** START Removing Customers from queues section
			handleQueueRemoval(A, timer);
			handleQueueRemoval(B, timer);
			handleQueueRemoval(C, timer);
			handleQueueRemoval(D, timer);
			// ************************** END Removing Customers from queues section

		}

		System.out.println("\n\n\nBeginning of Stats:\n");
		printStats(allData);

	}

	public void handleQueueRemoval(LinkedQueue queue, int time) {
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

	public static void printStats(List<List<String>> x) {

		String rere = String.format("%0" + 63 + "d", 0).replace("0", "-");
		System.out.println(rere);
		System.out.format("%1s%6s%9s%10s%9s%5s%5s%1s", "| ", "Cust # ", "| Arrival Time ", "| Service Time ", "| LOC ",
				"| Dep ", "| Notes ", "|");
		System.out.println("\n" + rere);

		for (int m = 0; m < x.size(); m++) {
			String p = "|";
			System.out.format("%1s%3s%6s%9s%6s%9s%9s%3s%3s%3s%3s%3s", p, x.get(m).get(4), p, x.get(m).get(0), p,
					x.get(m).get(1), p, x.get(m).get(5), p, x.get(m).get(3), p, x.get(m).get(6));
			System.out.println("\n" + rere);
		}

		Double BeepBeep = 0.0;
		int counter = 0;
		for (int a = 0; a < colWT.size(); a++) {
			BeepBeep = BeepBeep + colWT.get(a);
			counter++;
		}

		System.out.format("%1s%.2f%1s", "Average wait: ", (BeepBeep / counter), " min\n");
		System.out.println("Total time checkouts were not in use: " + timeQueuesAreFree + " min");
		System.out.println("Satisfied customers: " + satisfiedCusts);
		System.out.println("Dissatisfied customers: " + dissatisfiedCusts);

	}

	public LinkedQueue selectAQueue(LinkedQueue A, LinkedQueue B, LinkedQueue C) {
		if (A.size() <= B.size() && A.size() <= C.size()) {
			return A;
		} else if (B.size() <= C.size() && B.size() <= A.size()) {
			return B;
		} else {
			return C;
		}
	}

	public String findLowestQueueNum(LinkedQueue A, LinkedQueue B, LinkedQueue C) {
		if (A.size() <= B.size() && A.size() <= C.size()) {
			return "A";
		} else if (B.size() <= C.size() && B.size() <= A.size()) {
			return "B";
		} else {
			return "C";
		}
	}

	public void output(LinkedQueue A, LinkedQueue B, LinkedQueue C, LinkedQueue D, int time) {
		ArrayList<String> customersWaitingInQueue = new ArrayList<>();

		handleQueueOutput(A, 'A', time, customersWaitingInQueue);
		handleQueueOutput(B, 'B', time, customersWaitingInQueue);
		handleQueueOutput(C, 'C', time, customersWaitingInQueue);
		handleQueueOutput(D, 'D', time, customersWaitingInQueue);

		if (!customersWaitingInQueue.isEmpty()) {
			for (String s : customersWaitingInQueue) {
				System.out.println(s);
			}
		}
	}

	public void handleQueueOutput(LinkedQueue queue, char queueLetter, int time,
			ArrayList<String> customersWaitingInQueue) {
		if (queue.empty())
			System.out.println("\tCheckout " + queueLetter + ": free");
		else {
			for (int i = 0; i < queue.size(); i++) {
				Customer cc = queue.indexOf(i);
				if (cc != null) {
					if (cc == queue.peek()) {
						if (cc.getArrivalTime() == time)
							System.out.println(
									"\tCheckout " + queueLetter + ": Customer #" + cc.getCustId() + " begins service");

						if (cc.getFinishTime() == time)
							// We could probably add a method where it deletes the entry instead of in the
							// start method.
							System.out
									.println("\tCheckout " + queueLetter + ": Customer #" + cc.getCustId() + " leaves");
						if (cc.getArrivalTime() != time && cc.getFinishTime() != time)
							System.out
									.println("\tCheckout " + queueLetter + ": Customer #" + cc.getCustId() + " (cont)");

					} else {
						if (cc.getArrivalTime() + cc.getWaitTime() == time && i == 1)
							System.out.println(
									"\tCheckout " + queueLetter + ": Customer #" + cc.getCustId() + " begins service");
						else if (cc.getArrivalTime() == time)
							customersWaitingInQueue.add("\tCustomer " + cc.getCustId()
									+ " arrives and goes into Checkout " + queueLetter + " queue");
					}
				}
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

//    public static String numToLet(String string) {
//        String number = "NA";
//        switch (string) {
//            case "1" -> number = "A";
//            case "2" -> number = "B";
//            case "3" -> number = "C";
//        }
//        return number;
//    }
//
//    public int findLowestQueueSize(final int queueSizeA, final int queueSizeB, final int queueSizeC) {
//        int finalQueue = 0;
//        if (queueSizeA <= queueSizeB && queueSizeA <= queueSizeC) {
//            finalQueue = 1;
//        } else if (queueSizeB <= queueSizeC && queueSizeB <= queueSizeA) {
//            finalQueue = 2;
//        } else {
//            finalQueue = 3;
//        }
//
//        return finalQueue;
//    }

}
