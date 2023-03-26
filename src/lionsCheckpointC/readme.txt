JavaMarket Simulation Project:
This project simulates customers at the checkout counters in the world-famous JavaMarket. The goal is to maximize the profit while minimizing the customer wait time in line to keep the customers happy.
The market has three full-service lines served by three separate queues. In checkpoint C, the market has also add two new self-checkout lanes that uses one queue. Arrival interval and service time will be determined by a random number of minutes within a given range.
Users also have the option to set a slower service speed for self checkout.

Usage:
The program is executed using an IDE like Eclipse. To run the program in Eclipse, follow these steps:

Go to File -> Import -> Archive File -> Browse -> lionsCheckpointC.jar -> Finish


Required input:
When prompted, the user must provide the following input:

Minimum arrival time between customers
Maximum arrival time between customers
Minimum service time
Maximum service time
Number of customers to serve
Percentage of slowing for self checkouts

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

---------------------------------------------------------------
| Cust # | Arrival Time | Service Time    | LOC | Dep | Notes |
---------------------------------------------------------------
|  1     |        5     |        3        |  A  |  8  | Goes to A @ 5; leaves @ 8; WAIT: 0
---------------------------------------------------------------
|  2     |        8     |       17        |  S  | 25  | Goes to S @ 8; leaves @ 25; WAIT: 0
---------------------------------------------------------------
|  3     |       13     |       10        |  S  | 23  | Goes to S @ 13; leaves @ 23; WAIT: 0
---------------------------------------------------------------
|  4     |       18     |       11        |  S  | 34  | Goes to S @ 18; leaves @ 34; WAIT: 0
---------------------------------------------------------------
|  5     |       20     |       17        |  S  | 53  | Goes to S @ 20; Service starts @ 37; leaves @ 53; WAIT: 11
---------------------------------------------------------------
|  6     |       24     |        1        |  S  | 62  | Goes to S @ 24; Service starts @ 25; leaves @ 62; WAIT: 27
---------------------------------------------------------------
|  7     |       27     |       14        |  S  | 95  | Goes to S @ 27; Service starts @ 41; leaves @ 95; WAIT: 28
---------------------------------------------------------------
|  8     |       31     |       18        |  A  | 49  | Goes to A @ 31; leaves @ 49; WAIT: 0
---------------------------------------------------------------
|  9     |       32     |       10        |  S  |113  | Goes to S @ 32; Service starts @ 42; leaves @ 113; WAIT: 42
---------------------------------------------------------------
| 10     |       36     |       17        |  B  | 53  | Goes to B @ 36; leaves @ 53; WAIT: 0
---------------------------------------------------------------
Average wait for FULL queue: 0.00 min
Average wait for self-checkout: 13.50 min
Total time checkouts were not in use: 30 min
Total time self-check lanes were not in use: 3 min
Satisfied customers: 6
Dissatisfied customers: 4

Required classes:
Customer - Contains information about the customers.
LinkedQueue - Implements queue-like systems.
EmptyQueueException - Contains logic for LinkedQueue
CheckoutLane - Contains logic for self-checkout lanes.
StoreDriver - Contains the main method.
CustomerCreator - Creates customer objects.
Simulator - Display output and simulates queues.