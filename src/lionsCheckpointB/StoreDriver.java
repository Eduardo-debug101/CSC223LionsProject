package lionsCheckpointB;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class StoreDriver {
    static Scanner scan = new Scanner(System.in);

    //	ArrayList<Integer> arrivalNums = new ArrayList<Integer>();
    //	ArrayList<Integer> serviceNums = new ArrayList<Integer>();

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
        int maxService = 5;
        int numCustomers = 10;


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
        Clock c = new Clock();
        c.start();
        for (int i = 0; i < numCustomers; i++) {
            arrivalNums.add(new ArrayList<String>());
            serviceNums.add(new ArrayList<String>());

            // System.out.println(QueueASize + " - " + QueueBSize + " - " + QueueCSize);


            // Updated this for readability and allows us to utilize the selected queue for each customer
            int lowestQueueNumber = findLowestQueueSize(A.size(), B.size(), C.size());
            LinkedListStore selectedQueue = new LinkedListStore();

            switch (lowestQueueNumber) {
                case 1 -> selectedQueue = A;
                case 2 -> selectedQueue = B;
                case 3 -> selectedQueue = C;
            }
           
            if (!selectedQueue.isEmpty()) {
            int startValue = selectedQueue.getLatest().value.getStartValue();
            int holdTime = selectedQueue.getLatest().value.getCurrent().getServiceStart();
            System.out.println("Waiting for person in front of me to leave...");
//            try {
//				Thread.sleep(holdTime * 1000); 
//				selectedQueue.add(new CustomerCreator(minArrival, maxArrival, minService, maxService, startValue));
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//				}
//            }
            selectedQueue.add(new CustomerCreator(minArrival, maxArrival, minService, maxService, startValue));
            }
            else {
            selectedQueue.add(new CustomerCreator(minArrival, maxArrival, minService, maxService));
            }

            ArrayList CurrentBlock = selectedQueue.getLatest().value.getAllInfo();
            System.out.println(selectedQueue.getLatest().value.toString());

            // cc.create();
            //System.out.println(cc.getCurrent().toString());
        }
		
		/*
		for (int i = 0; i < numCustomers; i++) {
			System.out.println(arrivalNums.get(i));
		}
		*/

        System.out.println(arrivalNums.size() + "\n\n\n" + serviceNums.size());
        //testClock();

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
