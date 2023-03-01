package lionsCheckpointB;

import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StoreDriver {
	static Scanner scan = new Scanner(System.in);


	/*
	 * A method to safely parse Ints while catching errors It will print whatever is
	 * fed/prompted and return the int
	 */
	public static int parseInt(String x) {
		System.out.println(x);

		int var = 0;

		boolean momo = true;
		do {
			try {
				String tempAns = scan.next();

				var = Integer.parseInt(tempAns);
				momo = false;
			} catch (NumberFormatException e) {
				System.out.println("Wrong input try again.");
			}
		} while (momo);

		return var;
	}

	public static void main(String[] args) {
		LinkedListStore A = new LinkedListStore();
		LinkedListStore B = new LinkedListStore();
		LinkedListStore C = new LinkedListStore();
		
//		ArrayList<Integer> arrivalNums = new ArrayList<Integer>();
//		ArrayList<Integer> serviceNums = new ArrayList<Integer>();

		/*
		 * int minArrival = parseInt("Enter minimum arrival time between customers: ");
		 * int maxArrival = parseInt("Enter maximum arrival time between customers: ");
		 * int minService = parseInt("Enter minimum service time: "); int maxService =
		 * parseInt("Enter maximum service time: "); int numCustomers =
		 * parseInt("Number of customers to serve: ");
		 */

		int minArrival = 0;
		int maxArrival = 10;
		int minService = 1;
		int maxService = 9;
		int numCustomers = 50;

//		int startValue = 0;
//		for (int i = 0; i < numCustomers; i++) {
//			int randomArrivalNum = ThreadLocalRandom.current().nextInt(minArrival, maxArrival + 1);
//			int randomServiceNum = ThreadLocalRandom.current().nextInt(minService, maxService + 1);
//			startValue += randomArrivalNum;
//			arrivalNums.add(startValue);
//			serviceNums.add(randomServiceNum);
//		}
		Clock c = new Clock();
		c.start();
		int waitA = 0;
		int waitB = 0;
		int waitC = 0;
		for (int i = 0; i <= numCustomers; i++) {
			int queueASize = A.size();
			int queueBSize = B.size();
			int queueCSize = C.size();
			
			if (!A.isEmpty()) {
			waitA =+ A.getLatest().value.getWaitTime();
			}
			if (!B.isEmpty()) {
			waitB =+ B.getLatest().value.getWaitTime();
			}
			if (!C.isEmpty()) {
			waitC =+ C.getLatest().value.getWaitTime();
			}
			
			if (waitA != 0 && waitB != 0 && waitC != 0) {
				 int lowestWait = findLowestWait(waitA, waitB, waitC);
				 try {
						Thread.sleep(lowestWait * 1000); 
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				 if (lowestWait == waitA) {
					System.out.println("Lowest wait time is " + lowestWait + " mins.Adding to A");
					
					A.add(new CustomerCreator(minArrival, maxArrival, minService, maxService, c.getCurrentTime()));
				 }
				 if (lowestWait == waitB) {
					System.out.println("Lowest wait time is " + lowestWait + " mins.Adding to B");
					B.add(new CustomerCreator(minArrival, maxArrival, minService, maxService, c.getCurrentTime()));
				 }
				 if (lowestWait == waitC) {
					System.out.println("Lowest wait time is " + lowestWait + " mins.Adding to C");
					C.add(new CustomerCreator(minArrival, maxArrival, minService, maxService, c.getCurrentTime()));
				 }
			}
			else {
			
			if (queueASize <= queueBSize && queueASize <= queueCSize) {
				System.out.println("Adding to A");
				A.add(new CustomerCreator(minArrival, maxArrival, minService, maxService, c.getCurrentTime()));
			} else if (queueBSize <= queueASize && queueBSize <= queueCSize) {
				System.out.println("Adding to B");
				B.add(new CustomerCreator(minArrival, maxArrival, minService, maxService, c.getCurrentTime()));
			} else {
				System.out.println("Adding to C");
				C.add(new CustomerCreator(minArrival, maxArrival, minService, maxService, c.getCurrentTime()));
			}
			}

			//ArrayList CurrentBlock = A.getLatest().value.getAllInfo();

			//System.out.println(CurrentBlock);

		}
		c.stop();
		System.out.println("Total wait time of line A: " + waitA + " mins");
		System.out.println("Total wait time of line B: " + waitB + " mins");
		System.out.println("Total wait time of line C: " + waitC + " mins");
		//testClock();

	}

	// Use this method to test clock functionality - Eduardo 2/26
	public static void testClock() {
		Clock c = new Clock();
		c.start();
		System.out.println("The time is " + c.getCurrentTime() + "s");

		try {
			Thread.sleep(5000); // Wait for 5 secs
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		c.stop();

		System.out.println("The time is " + c.getCurrentTime() + "s");
	}
	
	public static int findLowestWait(int a, int b, int c) {
	    int min = a;
	    if (b < min) {
	        min = b;
	    }
	    if (c < min) {
	        min = c;
	    }
	    return min;
	}

}
