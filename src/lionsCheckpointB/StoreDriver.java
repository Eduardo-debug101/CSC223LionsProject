package lionsCheckpointB;

import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;

public class StoreDriver {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter minimum arrival time between customers:");
		int minArrival = scan.nextInt();
		System.out.println("Enter maximum arrival time between customers:");
		int maxArrival = scan.nextInt();
		System.out.println("Enter minimum service time:");
		int minService = scan.nextInt();
		System.out.println("Enter maximum service time:");
		int maxService = scan.nextInt();
		System.out.println("Number of customers to serve:");
		int numCustomers = scan.nextInt();
		
		int randomNum = ThreadLocalRandom.current().nextInt(minArrival, maxArrival + 1);
		int randomNum2 = ThreadLocalRandom.current().nextInt(minService, maxService + 1);
		
		
		

	
	}

}
