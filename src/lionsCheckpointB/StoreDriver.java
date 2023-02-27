package lionsCheckpointB;

import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class StoreDriver {
	static Scanner scan = new Scanner(System.in);
	
	//	ArrayList<Integer> arrivalNums = new ArrayList<Integer>();
	//	ArrayList<Integer> serviceNums = new ArrayList<Integer>();
	
	/*
	 * A method to safely parse Ints while catching errors
	 * 	It will print whatever is fed/prompted and return the int
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
		
		
		/*
		int minArrival = parseInt("Enter minimum arrival time between customers: ");
		int maxArrival = parseInt("Enter maximum arrival time between customers: ");
		int minService = parseInt("Enter minimum service time: ");
		int maxService = parseInt("Enter maximum service time: ");
		int numCustomers = parseInt("Number of customers to serve: ");
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
		
		
		List<List<String>> arrivalNums = new ArrayList<List<String>>(numCustomers); 
		List<List<String>> serviceNums = new ArrayList<List<String>>(numCustomers); 
		
		// CustomerCreator cc = new CustomerCreator(minArrival, maxArrival, minService, maxService);
		for (int i = 0; i <= numCustomers; i++) {
			arrivalNums.add(new ArrayList<String>());
			serviceNums.add(new ArrayList<String>());
			
			
			int QueueASize = A.size();
			int QueueBSize = B.size();
			int QueueCSize = C.size();
			
			// System.out.println(QueueASize + " - " + QueueBSize + " - " + QueueCSize);
			
			if ((QueueASize == QueueBSize) && (QueueASize == QueueCSize)) {
				System.out.println("Adding to A");
				A.add(new CustomerCreator(minArrival, maxArrival, minService, maxService));
			} else if ((QueueBSize	==	QueueCSize) && (QueueBSize	<=	QueueCSize)) {
				System.out.println("Adding to B");
				B.add(new CustomerCreator(minArrival, maxArrival, minService, maxService));
			} else if ((QueueASize	<	QueueBSize) && (QueueASize	<	QueueCSize)) {
				System.out.println("Adding to A");
				A.add(new CustomerCreator(minArrival, maxArrival, minService, maxService));
			} else if ((QueueBSize	<	QueueCSize) && (QueueBSize	<	QueueASize)) {
				System.out.println("Adding to B");
				B.add(new CustomerCreator(minArrival, maxArrival, minService, maxService));
			} else if ((QueueCSize	<	QueueASize) && (QueueCSize	<	QueueBSize)) {
				System.out.println("Adding to C");
				C.add(new CustomerCreator(minArrival, maxArrival, minService, maxService));
			} else {
				System.out.println("Adding to C");
				C.add(new CustomerCreator(minArrival, maxArrival, minService, maxService));
			}
			
			
			ArrayList CurrentBlock = A.getLatest().value.getAllInfo();
			
			System.out.println(CurrentBlock);
			
			// cc.create();
			//System.out.println(cc.getCurrent().toString());
		}
		
		/*
		for (int i = 0; i < numCustomers; i++) {
			System.out.println(arrivalNums.get(i));
		}
		*/
		
		System.out.println(arrivalNums.size() + "\n\n\n" + serviceNums.size());
		
		
		testClock();
			
	}
	
	// Use this method to test clock functionality - Eduardo 2/26
	public static void testClock(){
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
	

}
