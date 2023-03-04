package lionsCheckpointB;

import java.util.Scanner;


public class StoreDriver {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        int minArrival = 0;
        int maxArrival = 3;
        int minService = 1;
        int maxService = 5;
        int numCustomers = 50;

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
