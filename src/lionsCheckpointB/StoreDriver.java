package lionsCheckpointB;

import java.util.Scanner;


public class StoreDriver {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        int minArrival = 1;
        int maxArrival = 5;
        int minService = 1;
        int maxService = 7;
        int numCustomers = 25;

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
