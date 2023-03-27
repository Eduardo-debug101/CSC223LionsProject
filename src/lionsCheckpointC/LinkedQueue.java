package lionsCheckpointC;

/* **
   This class implements a queue based 
	on linked lists.
*/

public class LinkedQueue implements Comparable<LinkedQueue>{

    private int queueId;
    private static int nextQueueNum = 1;

    public int compareTo(LinkedQueue o) {

        if(this.size() == o.size()){
            return 0;
        } else if (this.size() > o.size()) {
            return 1;
        }else {
            return -1;
        }
    }

    public LinkedQueue(){
        queueId = nextQueueNum;
        nextQueueNum++;
    }

    private class Node {
        Customer value;
        Node next;

        Node(Customer val, Node n) {
            value = val;
            next = n;
        }
    }

    private Node front = null;
    private Node rear = null;

    /**
     * The method enqueue adds a value
     * to the queue.
     *
     * @param s The value to be added
     *          to the queue.
     */

    public void enqueue(Customer s) {
        if (rear != null) {
            rear.next = new Node(s, null);
            rear = rear.next;
        } else {
            rear = new Node(s, null);
            front = rear;
        }
    }

    /**
     * The empty method checks to see if
     * the queue is empty.
     *
     * @return true if and only if queue
     * is empty.
     */

    public boolean empty() {
        return front == null;
    }

    /**
     * The method peek returns value at the
     * front of the queue.
     *
     * @return item at front of queue.
     * @excepton EmptyQueueException When the
     * queue is empty.
     */

    public Customer peek() {
        if (empty())
            throw new EmptyQueueException();
        else
            return front.value;
    }

    /**
     * Returns the last element in the queue.
     *
     * @return the last element in the queue.
     * @throws EmptyQueueException if the queue is empty.
     */
    public Customer getRear() {
        if (empty()) {
            throw new EmptyQueueException();
        }
        return rear.value;
    }

    /**
     * The dequeue method removes and returns
     * the item at the front of the queue.
     *
     * @return item at front of queue.
     * @throws EmptyQueueException When
     *                             the queue is empty.
     */

    public Customer dequeue() {
        if (empty())
            throw new EmptyQueueException();
        else {
            Customer value = front.value;
            front = front.next;
            if (front == null) rear = null;
            return value;
        }
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return the number of elements in the queue.
     */
    public int size() {
        int count = 0;
        Node p = front;
        while (p != null) {
            count++;
            p = p.next;
        }
        return count;
    }

    public Customer indexOf(int index) {
        Node p = front;
        int trueIndex = 0;
        while (p != null) {
            if (trueIndex == index) {
                return p.value;
            }
            p = p.next;
            trueIndex++;
        }
        return null;
    }

    /**
     * The toString method concatenates all strings
     * in the queue to give a string representation
     * of the contents of the queue.
     *
     * @return string representation of this queue.
     */

    public String toString() {
        StringBuilder sBuilder = new StringBuilder();

        // Walk down the list and append all values
        Node p = front;
        while (p != null) {
            sBuilder.append(p.value + " ");
            p = p.next;
        }
        return sBuilder.toString();
    }

    public int getQueueId() {
        return queueId;
    }

    public void setQueueId(int queueId) {
        this.queueId = queueId;
    }
}