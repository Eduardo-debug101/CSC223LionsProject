package lionsCheckpointB;

import java.util.Scanner;


public class StoreDriver {
    static Scanner scan = new Scanner(System.in);

    //	ArrayList<Integer> arrivalNums = new ArrayList<Integer>();
    //	ArrayList<Integer> serviceNums = new ArrayList<Integer>();

    public static void main(String[] args) {
       

        int minArrival = 0;
        int maxArrival = 3;
        int minService = 1;
        int maxService = 5;
        int numCustomers = 50;
        
        Simulator sim = new Simulator(minArrival, maxArrival, minService, maxService, numCustomers);
        sim.start();
       
    }

    public static int findLowestQueueSize(final int queueSizeA, final int queueSizeB, final int queueSizeC) {
        int finalQueue = 0;

//        if ((queueSizeA == queueSizeB) && (queueSizeA == queueSizeC)) {
//            System.out.println("Adding to queueA");
//            finalQueue = 1;
//        } else if ((queueSizeB == queueSizeC) && (queueSizeB <= queueSizeC)) {
//            System.out.println("Adding to queueB");
//            finalQueue = 2;
//        } else if ((queueSizeA < queueSizeB) && (queueSizeA < queueSizeC)) {
//            System.out.println("Adding to queueA");
//            finalQueue = 1;
//        } else if ((queueSizeB < queueSizeC) && (queueSizeB < queueSizeA)) {
//            System.out.println("Adding to queueB");
//            finalQueue = 2;
//        } else if ((queueSizeC < queueSizeA) && (queueSizeC < queueSizeB)) {
//            System.out.println("Adding to queueC");
//            finalQueue = 3;
//        } else {
//            System.out.println("Adding to queueC");
//            finalQueue = 3;
//        }
        
        if (queueSizeA <= queueSizeB && queueSizeA <= queueSizeC) {
            System.out.println("Adding to queueA");
            finalQueue = 1;
        } else if (queueSizeB <= queueSizeC && queueSizeB <= queueSizeA) {
            System.out.println("Adding to queueB");
            finalQueue = 2;
        } else {
            System.out.println("Adding to queueC");
            finalQueue = 3;
        }

        return finalQueue;
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
