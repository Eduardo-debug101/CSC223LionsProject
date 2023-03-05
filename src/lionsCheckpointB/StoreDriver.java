package lionsCheckpointB;

import java.util.Scanner;


public class StoreDriver {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        int minArrival = 1; // Fix the problem with customer not showing up to queue when set to zero
        int maxArrival = 5;
        int minService = 20;
        int maxService = 40;
        int numCustomers = 10;

        Simulator sim = new Simulator(minArrival, maxArrival, minService, maxService, numCustomers);
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
