JavaMarket Simulation Project:
This project simulates customers at the checkout counters in the world-famous JavaMarket.  
The goal is to maximize the profit while minimizing the customer wait time in line to keep the customers happy.
Arrival interval and service time will be determined by a random number of minutes within a given range.
Users also have the option to set a slower service speed for self checkout.
The market has the option to add more full queues and self-checkout lanes. 
In the latest checkpoint, the market's data is now represented in a GUI instead of the Java console. 


Usage:
The program is executed using an IDE like Eclipse. To run the program in Eclipse, follow these steps:

Go to File -> Import -> Archive File -> Browse -> lionsCheckpointE.jar -> Finish


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
10
Enter minimum service time: 
1
Enter maximum service time: 
10
Number of customers: 
30
Percentage of slowing for self checkouts (ex. 10 = 10%): 
10
Number of full queues to use: 
5
Number of self-checkout lanes to use: 
2

Beginning of Stats:

---------------------------------------------------------------
| Cust # | Arrival Time | Service Time    | LOC | Dep | Notes |

---------------------------------------------------------------
|  1     |        1     |        3        |  S  |  4  | Goes to S @ 1; leaves @ 4; WAIT: 0

---------------------------------------------------------------
|  2     |        6     |        3        |  S  |  9  | Goes to S @ 6; leaves @ 9; WAIT: 0

---------------------------------------------------------------
|  3     |        8     |       10        |  1  | 18  | Goes to 1 @ 8; leaves @ 18; WAIT: 0

---------------------------------------------------------------
|  4     |       18     |        5        |  2  | 23  | Goes to 2 @ 18; leaves @ 23; WAIT: 0

---------------------------------------------------------------
|  5     |       23     |       10        |  S  | 33  | Goes to S @ 23; leaves @ 33; WAIT: 0

---------------------------------------------------------------
|  6     |       29     |       10        |  1  | 39  | Goes to 1 @ 29; leaves @ 39; WAIT: 0

---------------------------------------------------------------
|  7     |       30     |        6        |  2  | 36  | Goes to 2 @ 30; leaves @ 36; WAIT: 0

---------------------------------------------------------------
|  8     |       40     |        7        |  S  | 47  | Goes to S @ 40; leaves @ 47; WAIT: 0

---------------------------------------------------------------
|  9     |       41     |        1        |  S  | 42  | Goes to S @ 41; leaves @ 42; WAIT: 0

---------------------------------------------------------------
| 10     |       46     |        1        |  S  | 47  | Goes to S @ 46; leaves @ 47; WAIT: 0

---------------------------------------------------------------
| 11     |       54     |        9        |  1  | 63  | Goes to 1 @ 54; leaves @ 63; WAIT: 0

---------------------------------------------------------------
| 12     |       59     |        3        |  2  | 62  | Goes to 2 @ 59; leaves @ 62; WAIT: 0

---------------------------------------------------------------
| 13     |       61     |        7        |  S  | 68  | Goes to S @ 61; leaves @ 68; WAIT: 0

---------------------------------------------------------------
| 14     |       70     |        9        |  S  | 79  | Goes to S @ 70; leaves @ 79; WAIT: 0

---------------------------------------------------------------
| 15     |       78     |        6        |  S  | 84  | Goes to S @ 78; leaves @ 84; WAIT: 0

---------------------------------------------------------------
| 16     |       79     |        6        |  S  | 86  | Goes to S @ 79; leaves @ 86; WAIT: 0

---------------------------------------------------------------
| 17     |       81     |        1        |  S  | 86  | Goes to S @ 81; leaves @ 86; WAIT: 0

---------------------------------------------------------------
| 18     |       88     |        8        |  S  | 96  | Goes to S @ 88; leaves @ 96; WAIT: 0

---------------------------------------------------------------
| 19     |       92     |        7        |  1  | 99  | Goes to 1 @ 92; leaves @ 99; WAIT: 0

---------------------------------------------------------------
| 20     |      102     |        4        |  S  |106  | Goes to S @ 102; leaves @ 106; WAIT: 0

---------------------------------------------------------------
| 21     |      109     |        6        |  S  |115  | Goes to S @ 109; leaves @ 115; WAIT: 0

---------------------------------------------------------------
| 22     |      115     |        4        |  S  |119  | Goes to S @ 115; leaves @ 119; WAIT: 0

---------------------------------------------------------------
| 23     |      119     |       10        |  1  |129  | Goes to 1 @ 119; leaves @ 129; WAIT: 0

---------------------------------------------------------------
| 24     |      120     |        8        |  2  |128  | Goes to 2 @ 120; leaves @ 128; WAIT: 0

---------------------------------------------------------------
| 25     |      124     |        6        |  S  |130  | Goes to S @ 124; leaves @ 130; WAIT: 0

---------------------------------------------------------------
| 26     |      131     |        7        |  S  |138  | Goes to S @ 131; leaves @ 138; WAIT: 0

---------------------------------------------------------------
| 27     |      137     |        5        |  1  |142  | Goes to 1 @ 137; leaves @ 142; WAIT: 0

---------------------------------------------------------------
| 28     |      145     |        6        |  1  |151  | Goes to 1 @ 145; leaves @ 151; WAIT: 0

---------------------------------------------------------------
| 29     |      155     |        6        |  S  |161  | Goes to S @ 155; leaves @ 161; WAIT: 0

---------------------------------------------------------------
| 30     |      161     |        9        |  1  |170  | Goes to 1 @ 161; leaves @ 170; WAIT: 0

---------------------------------------------------------------

Average wait for FULL queue: 0.00 min
Average wait for self-checkout: 0.00 min
Total time checkouts were not in use: 75 min
Total time self-check lanes were not in use: 22 min
Satisfied customers: 30
Dissatisfied customers: 0

No suggestion for self-checkout lanes.

Required classes:
Customer - Contains information about the customers.
LinkedQueue - Implements queue-like systems.
EmptyQueueException - Contains logic for LinkedQueue
CheckoutLane - Contains logic for self-checkout lanes.
StoreDriver - Contains the main method.
CustomerCreator - Creates customer objects.
Simulator - Display output and simulates queues.
StoreGUI and other Panel classes - Displays user interface