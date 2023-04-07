JavaMarket Simulation Project:
This project simulates customers at the checkout counters in the world-famous JavaMarket.  
The goal is to maximize the profit while minimizing the customer wait time in line to keep the customers happy.
Arrival interval and service time will be determined by a random number of minutes within a given range.
Users also have the option to set a slower service speed for self checkout.
In checkpoint D, the market now has the option to add more full queues and self-checkout lanes. 


Usage:
The program is executed using an IDE like Eclipse. To run the program in Eclipse, follow these steps:

Go to File -> Import -> Archive File -> Browse -> lionsCheckpointD.jar -> Finish


Required input:
When prompted, the user must provide the following input:

Minimum arrival time between customers
Maximum arrival time between customers
Minimum service time
Maximum service time
Number of customers to serve
Percentage of slowing for self checkouts
Number of full queues to use
Number of self-checkout lanes

Output:
The program logs the following information for each customer:

- The clock time when the customer arrives
- The time when the customer first gets served
- The time when the customer is done being served and leaves

The program also calculates the following:

- The overall average waiting time
- The amount of time the checkout lines are not being used

The program will individually give averages and wait times for full service and self-checkout lanes.

Sample data:
Here is some sample data for a simulation run:

Enter minimum arrival time between customers: 
1
Enter maximum arrival time between customers: 
5
Enter minimum service time: 
1
Enter maximum service time: 
20
Number of customers to serve: 
10
Percentage of slowing for self checkouts (ex. 10 = 10%): 
10
Number of full queues to use: 
4
Number of self-checkout lanes to use: 
1

Beginning of Stats:

---------------------------------------------------------------
| Cust # | Arrival Time | Service Time    | LOC | Dep | Notes |
---------------------------------------------------------------
|  1     |        5     |        1        |  S  |  6  | Goes to S @ 5; leaves @ 6; WAIT: 0
---------------------------------------------------------------
|  2     |       10     |        7        |  S  | 17  | Goes to S @ 10; leaves @ 17; WAIT: 0
---------------------------------------------------------------
|  3     |       14     |        8        |  S  | 25  | Goes to S @ 14; leaves @ 25; WAIT: 0
---------------------------------------------------------------
|  4     |       19     |        5        |  S  | 30  | Goes to S @ 19; leaves @ 30; WAIT: 0
---------------------------------------------------------------
|  5     |       23     |       11        |  S  | 45  | Goes to S @ 23; Service starts @ 34; leaves @ 45; WAIT: 5
---------------------------------------------------------------
|  6     |       24     |       15        |  S  | 74  | Goes to S @ 24; Service starts @ 39; leaves @ 74; WAIT: 16
---------------------------------------------------------------
|  7     |       29     |       19        |  S  |121  | Goes to S @ 29; Service starts @ 48; leaves @ 121; WAIT: 30
---------------------------------------------------------------
|  8     |       34     |       14        |  S  |181  | Goes to S @ 34; Service starts @ 48; leaves @ 181; WAIT: 48
---------------------------------------------------------------
|  9     |       37     |       17        |  1  | 54  | Goes to 1 @ 37; leaves @ 54; WAIT: 0
---------------------------------------------------------------
| 10     |       40     |        1        |  S  |241  | Goes to S @ 40; Service starts @ 41; leaves @ 241; WAIT: 62
---------------------------------------------------------------
Average wait for FULL queue: 0.00 min
Average wait for self-checkout: 16.10 min
Total time checkouts were not in use: 95 min
Total time self-check lanes were not in use: 0 min
Satisfied customers: 5
Dissatisfied customers: 5
Suggest adding self-checkout lanes.


Required classes:
Customer - Contains information about the customers.
LinkedQueue - Implements queue-like systems.
EmptyQueueException - Contains logic for LinkedQueue
CheckoutLane - Contains logic for self-checkout lanes.
StoreDriver - Contains the main method.
CustomerCreator - Creates customer objects.
Simulator - Display output and simulates queues.