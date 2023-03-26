package lionsCheckpointC;

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

        int minArrival = 1;
        int maxArrival = 3;
        int minService = 1;
        int maxService = 10;
        int numCustomers = 20;
        int selfSlow = 0;
//    	int minArrival = parseInt("Enter minimum arrival time between customers: ");
//		int maxArrival = parseInt("Enter maximum arrival time between customers: ");
//		int minService = parseInt("Enter minimum service time: ");
//		int maxService = parseInt("Enter maximum service time: ");
//		int numCustomers = parseInt("Number of customers to serve: ");
//      int selfSlow = parseInt("Percentage of slowing for self checkouts (ex. 10 = 10%): ");

        Simulator sim = new Simulator(minArrival, maxArrival, minService, maxService, numCustomers, selfSlow);
        sim.start();

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
}
