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

Cus #	Arrival time (absolute)	Service Time (between 5-9)	LOC	Departure Time (absolute)	NOTES
1	1	5	A	6	Open lane..immediate service
2	2	9	B	11	Open lane..immediate service
3	4	7	C	11	Open lane..immediate service
4	5	8	A	14	All busy. Goes into A @5; open @6; leaves 14 WAIT:1
5	8	9	B	20	All busy. Goes into B@8; open@11;leaves@20 WAIT:3
6	11	6	C	18	C just finished Goes into E @11; leaves@18 WAIT:0
7	12	5	A	20	All busy. Goes into A@12; open@14;leaves@20 WAIT:2
8	13	6	B	27	All busy; One waiting in line at C so goes into B@13; opene@20;leaves26 WAIT:7
9	17	7	A	27	All busy.Goes to lowest A@17;open@20;leaves@27 WAIT:3
10	20	6	C	26	C is empty so immediately served@20;leaves26 WAIT:0
Required classes:
Customer - Contains information about the customers.
Clock - Implements clock time for queues.
LinkedListQueue - Implements linked lists that emulate queue systems.
StoreDriver - Contains the main method.
CustomerCreator - Creates customer objects.
Simulator - Display output and simulates queues.