package lionsCheckpointB;

import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.Scanner;

public class StoreDriver {
	static Scanner scan = new Scanner(System.in);
	
	
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
		int minArrival = parseInt("Enter minimum arrival time between customers: ");
		int maxArrival = parseInt("Enter maximum arrival time between customers: ");
		int minService = parseInt("Enter minimum service time: ");
		int maxService = parseInt("Enter maximum service time: ");
		int numCustomers = parseInt("Number of customers to serve: ");
		
		ArrayList<Integer> arrivalNums = new ArrayList<Integer>();
		ArrayList<Integer> serviceNums = new ArrayList<Integer>();
		int startValue = 0;
		for (int i = 0; i < numCustomers; i++) {
			int randomArrivalNum = ThreadLocalRandom.current().nextInt(minArrival, maxArrival + 1);
			int randomServiceNum = ThreadLocalRandom.current().nextInt(minService, maxService + 1);
			startValue += randomArrivalNum;
			arrivalNums.add(startValue);
			serviceNums.add(randomServiceNum);
		}
		
	
		
		
		

	
	}

}
