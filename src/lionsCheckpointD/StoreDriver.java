package lionsCheckpointD;

import java.util.Scanner;

public class StoreDriver {
	static Scanner scan = new Scanner(System.in);

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
		System.out.println("\r\n"
				+ "  _      _                        _                    __  __            _        _   \r\n"
				+ " | |    (_)                      | |                  |  \\/  |          | |      | |  \r\n"
				+ " | |     _  ___  _ __  ___       | | __ ___   ____ _  | \\  / | __ _ _ __| | _____| |_ \r\n"
				+ " | |    | |/ _ \\| '_ \\/ __|  _   | |/ _` \\ \\ / / _` | | |\\/| |/ _` | '__| |/ / _ \\ __|\r\n"
				+ " | |____| | (_) | | | \\__ \\ | |__| | (_| |\\ V / (_| | | |  | | (_| | |  |   <  __/ |_ \r\n"
				+ " |______|_|\\___/|_| |_|___/  \\____/ \\__,_| \\_/ \\__,_| |_|  |_|\\__,_|_|  |_|\\_\\___|\\__|\r\n"
				+ "                                                                                      \r\n"
				+ "                                                                                      \r\n" + "");
		int minArrival = parseInt("Enter minimum arrival time between customers: ");
		int maxArrival = parseInt("Enter maximum arrival time between customers: ");
		int minService = parseInt("Enter minimum service time: ");
		int maxService = parseInt("Enter maximum service time: ");
		int numCustomers = parseInt("Number of customers to serve: ");
		int selfSlow = parseInt("Percentage of slowing for self checkouts (ex. 10 = 10%): ");
		int fullQueuesAmt = parseInt("Number of full queues to use: ");
		int selfQueuesAmt = parseInt("Number of self-checkout lanes to use: ");

		Simulator sim = new Simulator(minArrival, maxArrival, minService, maxService, numCustomers, selfSlow,
				fullQueuesAmt, selfQueuesAmt);
		sim.start();

	}
}
