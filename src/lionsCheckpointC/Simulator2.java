package lionsCheckpointC;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Simulator2 {
    private int arrivalMinTime;
    private int arrivalMaxTime;
    private int serviceMinTime;
    private int serviceMaxTime;
    private int numCustomers;
    //static ArrayList<Integer> colWT = new ArrayList<>();
    private static int satisfiedCusts = 0;
    private static int dissatisfiedCusts = 0;
    private static int timeQueuesAreFree = 0;
    // Sums up all of the Customer's waitTime to get the average waitTime in printStats()
    private static double averagesF;
    private static double averagesS;
    private static int FA;
    private static int SA;
    private String currentQueue = "";
    private double selfQueueSlower;
    
    

    public Simulator2() {

    }

    public Simulator2(int a, int b, int c, int d, int n, double z) {
        arrivalMinTime = a;
        arrivalMaxTime = b;
        serviceMinTime = c;
        serviceMaxTime = d;
        numCustomers = n;
        selfQueueSlower = z;
    }

    public String toString() {
        return "Simulator [arrivalMinTime=" + arrivalMinTime + ", arrivalMaxTime=" + arrivalMaxTime
                + ", serviceMinTime=" + serviceMinTime + ", serviceMaxTime=" + serviceMaxTime + ", numCustomers="
                + numCustomers + "]";
    }

    public void start() {

    	/*
    	 * Queues
    	 */
    	
        LinkedQueue A = new LinkedQueue();
        LinkedQueue B = new LinkedQueue();
        LinkedQueue C = new LinkedQueue();

        LinkedQueue self = new LinkedQueue();
        
        LinkedQueue D = new LinkedQueue();
        LinkedQueue E = new LinkedQueue();
        
        ArrayList<Customer> waitList = customerWaitList();
        ArrayList<Customer> DoneArray = new ArrayList<Customer>();
        
        
        
        
        CheckoutLane laneD = new CheckoutLane();
        CheckoutLane laneE = new CheckoutLane();
        
        int ticks = 0;
        
        
        while ((waitList.size() >= 1)) {
        	int coinFlip = ThreadLocalRandom.current().nextInt(0, 1 + 1);
        	
        	LinkedQueue SelectedQueue = selectAQueue(A, B, C, self, D, E, coinFlip);
        	
        	if (coinFlip == 0) {
        		int oldWait = waitList.get(0).getWaitTime();
        		int newWait = (int)(Math.round(oldWait + (oldWait * (selfQueueSlower/100))));
        		
        		//System.out.println(oldWait + " --- " + (oldWait + (oldWait * (selfQueueSlower/100))));
        		//System.out.println(oldWait + " -+- " + newWait);
        		
        		waitList.get(0).setWaitTime(newWait);
        	}
        	
        	waitList.get(0).setAssignedQueueLetter(currentQueue);
    		SelectedQueue.enqueue(waitList.get(0));
			waitList.remove(0);
        }
        
        
        System.out.println("Start");
        
        boolean ea = true;
        while (ea) {
        	
        	
        	DoneArray = prosQueue(A, DoneArray);
        	DoneArray = prosQueue(B, DoneArray);
        	DoneArray = prosQueue(C, DoneArray);
        	//DoneArray = prosQueue(self, DoneArray);
        	DoneArray = prosQueue(D, DoneArray);
        	DoneArray = prosQueue(E, DoneArray);
        	
        	output(A, B, C, self, D, E, ticks);
        	
        	ticks++;
        	
        	if (A.empty() && B.empty() && C.empty() && self.empty() && D.empty() && E.empty())
        		ea = false;
        }
        
        DoneArray = sorted(DoneArray);
        
        System.out.println("\n\n\nBeginning of Stats:\n");
        printStats(DoneArray);
        
    }


    private ArrayList<Customer> sorted(ArrayList<Customer> DoneArray) {
    	ArrayList<Customer> tmpArray = DoneArray;
    	
    	for (int z = 0; z < DoneArray.size()/2; z++) {
    		for (int i = 0; i < tmpArray.size()-1; i++) {
             	Customer f = tmpArray.get(i);
             	Customer s = tmpArray.get(i+1);
             	
             	//System.out.println(f.getCustId() + " - " + s.getCustId());
             	if (f.getCustId() > s.getCustId()) {
             		tmpArray.set(i, s);
             		tmpArray.set(i+1, f);
             	}
             }
    		//System.out.println("\n");
         }
    	 return tmpArray;
	}

	public void tmpLoop(String Name, LinkedQueue ll) {
    	System.out.println(Name);
    	for (int i = 0; i < ll.size(); i++) {
    		Customer Node = ll.indexOf(i);
    		System.out.println("\t" + Node + " --- " + Node.getWaitTime());
        }
    }

    public ArrayList<Customer> prosQueue(LinkedQueue SelectedQueue, ArrayList<Customer> doneArray) {
    	ArrayList<Customer> tmpArr = doneArray;
    	if (SelectedQueue.size() >= 1) {
    		int tmpFakeWait = SelectedQueue.indexOf(0).getFakeWaitTime();
    		int newFakeWait = tmpFakeWait-1;
    		if (tmpFakeWait > 0) {
    			SelectedQueue.indexOf(0).setFakeWaitTime(newFakeWait);
    			SelectedQueue.indexOf(0).setCustomerNotes();
    			
    			//System.out.println(SelectedQueue.indexOf(0).getCustId() + " - " + tmpFakeWait);
    		} else {
    			tmpArr.add(SelectedQueue.indexOf(0));
    			SelectedQueue.dequeue();
    			
    		}
    	}
    	return tmpArr;
    }
    

    public ArrayList<Customer> customerWaitList() {
        ArrayList<Customer> tmpARR = new ArrayList<Customer>();
        for (int i = 0; i < numCustomers; i++) {
        	CustomerCreator cc = new CustomerCreator(arrivalMinTime, arrivalMaxTime, serviceMinTime, serviceMaxTime, 0);
            tmpARR.add(cc.create());
        }
        
        return tmpARR;
    }
    
    public LinkedQueue selectAQueue(LinkedQueue A, LinkedQueue B, LinkedQueue C, LinkedQueue self, LinkedQueue D, LinkedQueue E, int EB) {
    	
    	if (EB == 1) {
    		int QueueASize = A.size();
    		int QueueBSize = B.size();
    		int QueueCSize = C.size();
        	
        	if ((QueueASize == QueueBSize) && (QueueASize == QueueCSize)) {
    			currentQueue = "A";
                return A;
    		} else if ((QueueBSize	==	QueueCSize) && (QueueBSize	<=	QueueCSize)) {
            	currentQueue = "B";
                return B;
    		} else if ((QueueASize	<	QueueBSize) && (QueueASize	<	QueueCSize)) {
    			currentQueue = "A";
                return A;
    		} else if ((QueueBSize	<	QueueCSize) && (QueueBSize	<	QueueASize)) {
            	currentQueue = "B";
                return B;
    		} else if ((QueueCSize	<	QueueASize) && (QueueCSize	<	QueueBSize)) {
            	currentQueue = "C";
                return C;
    		} else {
            	currentQueue = "C";
                return C;
    		}
    	} else {
    		int QueueDSize = D.size();
    		int QueueESize = E.size();
    		
    		
    		if ((QueueDSize == QueueESize)) {
    			currentQueue = "D";
                return D;
    		} else if ((QueueDSize	<=	QueueESize)) {
            	currentQueue = "E";
                return E;
    		} else {
    			currentQueue = "E";
    			return E;
    		}
    	}
    	
    }
    
    public String checkQueue(LinkedQueue ll, int tic) {
    	
    	if (ll.size() > 0) {
    		
    		if ((tic == 0) && (ll.indexOf(0).getWaitTime() == ll.indexOf(0).getFakeWaitTime()+1))
    			return "Customer #" + ll.indexOf(0).getCustId() + " begins service";
    		
    		if (ll.indexOf(0).getWaitTime() == ll.indexOf(0).getFakeWaitTime()) {
    			return "Customer #" + ll.indexOf(0).getCustId() + " begins service";
    		} else if (ll.indexOf(0).getFakeWaitTime() != 0) {
        		return "Customer #" + ll.indexOf(0).getCustId() + " (cont)";
        	} else {
        		return "Customer #" + ll.indexOf(0).getCustId() + " leaves";
        	}
    	} else {
    		timeQueuesAreFree = timeQueuesAreFree+1;
    		return "free";
    	}
    }
    
    public void output(LinkedQueue A, LinkedQueue B, LinkedQueue C, LinkedQueue self, LinkedQueue D, LinkedQueue E, int time) {
    	System.out.println("Time: " + time);
    	System.out.println("\tCheckout A: " + checkQueue(A, time)
    			+ "\n\tCheckout B: " + checkQueue(B, time)
    			+ "\n\tCheckout C: " + checkQueue(C, time)
    			+ "\n\tCheckout D: " + checkQueue(D, time)
    			+ "\n\tCheckout E: " + checkQueue(E, time));
    }
    
    
    public void printStats(ArrayList<Customer> customers) {
    	
    	DecimalFormat df = new DecimalFormat("##");

        String dashedLines = String.format("%0" + 63 + "d", 0).replace("0", "-");
        System.out.println(dashedLines);
        System.out.format("%1s%6s%9s%10s%9s%5s%5s%1s", "| ", "Cust # ", "| Arrival Time ", "| Service Time ", "| LOC ",
                "| Dep ", "| Notes ", "|");
        System.out.println("\n" + dashedLines);

        for (Customer cust : customers) {
        	int wtt = 0;
        	if ((cust.getAssignedQueueLetter() != "D") && (cust.getAssignedQueueLetter() != "E")) {
        		int wt = cust.getWaitTime();
        		wtt = wt;
        		averagesF += wt;
        		FA++;
        	} else {
        		int wt = cust.getWaitTime();
        		wtt = wt;
        		averagesS += wt;
        		SA++;
        	}
        	
        	if (wtt <= 5) {
        		satisfiedCusts++;
        	} else {
        		dissatisfiedCusts++;
        	}
        	
            String lineDivider = "|";
            System.out.format("%1s%3s%6s%9s%6s%9s%9s%3s%3s%3s%3s%3s",
                    lineDivider, cust.getCustId(),
                    lineDivider, cust.getArrivalTime(),
                    lineDivider, cust.getServiceTime(),
                    lineDivider, cust.getAssignedQueueLetter(),
                    lineDivider, cust.getFinishTime(),
                    lineDivider, cust.getCustomerNotes());
            System.out.println("\n" + dashedLines);
        }

        //System.out.format("%1s%.2f%1s", "Average wait: ", (averages / numCustomers)), " min\n");
        System.out.println("Average wait: " + df.format(averagesF / FA) + " min");
        System.out.println("Self Checkout Average wait: " + df.format(averagesS / SA) + " min");
        System.out.println("Total time checkouts were not in use: " + (timeQueuesAreFree / 10) + " min");
        System.out.println("Satisfied customers: " + satisfiedCusts);
        System.out.println("Dissatisfied customers: " + dissatisfiedCusts);

    }
    

    public int getArrivalMinTime() {
        return arrivalMinTime;
    }

    public void setArrivalMinTime(int arrivalMinTime) {
        this.arrivalMinTime = arrivalMinTime;
    }

    public int getArrivalMaxTime() {
        return arrivalMaxTime;
    }

    public void setArrivalMaxTime(int arrivalMaxTime) {
        this.arrivalMaxTime = arrivalMaxTime;
    }

    public int getServiceMinTime() {
        return serviceMinTime;
    }

    public void setServiceMinTime(int serviceMinTime) {
        this.serviceMinTime = serviceMinTime;
    }

    public int getServiceMaxTime() {
        return serviceMaxTime;
    }

    public void setServiceMaxTime(int serviceMaxTime) {
        this.serviceMaxTime = serviceMaxTime;
    }

    public int getNumCustomers() {
        return numCustomers;
    }

    public void setNumCustomers(int numCustomers) {
        this.numCustomers = numCustomers;
    }
}
