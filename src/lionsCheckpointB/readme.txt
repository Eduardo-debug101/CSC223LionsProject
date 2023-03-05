JavaMarket Simulation Project:
This project simulates customers at the checkout counters in the world-famous JavaMarket. The goal is to maximize the profit while minimizing the customer wait time in line to keep the customers happy.
The market has three full-service lines served by three separate queues, simulated as Mod 3 linked lists. Arrival interval and service time will be determined by a random number of minutes within a given range.

Usage:
The program is executed using an IDE like Eclipse. To run the program in Eclipse, follow these steps:

Go to File -> Import -> Archive File -> Browse -> lionsCheckpointB.jar -> Finish


Required input:
When prompted, the user must provide the following input:

Minimum arrival time between customers
Maximum arrival time between customers
Minimum service time
Maximum service time
Number of customers to serve

Output:
The program logs the following information for each customer:

- The clock time when the customer arrives
- The time when the customer first gets served
- The time when the customer is done being served and leaves

The program also calculates the following:

- The overall average waiting time
- The amount of time the checkout lines are not being used

Sample data:
Here is some sample data for a simulation run:

---------------------------------------------------------------
| Cust # | Arrival Time | Service Time    | LOC | Dep | Notes |
---------------------------------------------------------------
|  1     |        1     |       14        |  A  | 15  | Goes to A@1;leaves@15 WAIT:0
---------------------------------------------------------------
|  2     |        2     |       16        |  B  | 18  | Goes to B@2;leaves@18 WAIT:0
---------------------------------------------------------------
|  3     |        4     |       20        |  C  | 24  | Goes to C@4;leaves@24 WAIT:0
---------------------------------------------------------------
|  4     |        5     |       16        |  A  | 31  | Goes to A@5;leaves@31 WAIT:10
---------------------------------------------------------------
|  5     |        8     |       15        |  B  | 33  | Goes to B@8;leaves@33 WAIT:10
---------------------------------------------------------------
|  6     |       12     |       10        |  C  | 34  | Goes to C@12;leaves@34 WAIT:12
---------------------------------------------------------------
|  7     |       14     |       20        |  A  | 51  | Goes to A@14;leaves@51 WAIT:17
---------------------------------------------------------------
|  8     |       17     |       11        |  A  | 62  | Goes to A@17;leaves@62 WAIT:34
---------------------------------------------------------------
|  9     |       20     |       19        |  B  | 52  | Goes to B@20;leaves@52 WAIT:13
---------------------------------------------------------------
| 10     |       25     |       16        |  C  | 50  | Goes to C@25;leaves@50 WAIT:9
---------------------------------------------------------------
Average wait: 10.50 min
Total time checkouts were not in use: 29 min
Satisfied customers: 3
Dissatisfied customers: 7

Required classes:
Customer - Contains information about the customers.
Clock - Implements clock time for queues.
LinkedListQueue - Implements linked lists that emulate queue systems.
StoreDriver - Contains the main method.
CustomerCreator - Creates customer objects.
Simulator - Display output and simulates queues.