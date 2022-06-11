package data_structures;

// Implements the Node of a Queue
class Node {
    private Integer value; // Value to store in a Node
    private Node next; // Pointer to the next Node; null by default
    private Node previous; // Pointer to the previous Node; null by default, used by DLL only
    // Constructors
    // Node's default constructor
    Node(Integer value) {
        this.value = value;
        previous = null;
        next = null;
    }
    // Node's constructor, indicating next Node
    Node(Integer value, Node next) {
        this.value = value;
        this.next = next;
    }

    // Encapsulation implementation
    // Getters
    public Integer getValue() {
        return value;
    }
    public Node getPrevious() {
        return previous;
    }
    public Node getNext() {
        return next;
    }
    // Setters
    public void setValue(Integer value) {
        this.value = value;
    }
    public void setPrevious(Node previous) {
        this.previous = previous;
    }
    public void setNext(Node next) {
        this.next = next;
    }
}

public class Queue {
    protected int queueSize = 0;
    protected Node front, back;

    // Default Queue constructor
    Queue() {
        this.front = null;
        this.back = null;
    }

    // Methods
    // Adds a value to the queue
    public void enqueue(Integer value) {
        Node newNode = new Node(value);

        // If queue is empty, the new Node is both front and back
        if (this.back == null) { 
            this.front = newNode;
            this.back = newNode;

            queueSize++;
            return;
        }
        else { 
            // Adds the new Node at the end of the Queue
            this.back.setNext(newNode);
            this.back = newNode;
            queueSize++;
        }
    }

    // Removes a value from the queue
    public void dequeue() {
         // If the queue is empty, return a null value
         if (this.front == null)
            return;

        // Moves front one node ahead
        Node prevFront = this.front;
        this.front = this.front.getNext();

        // If the front becomes null, then change the back as null
        if (this.front == null)
            this.back = null;

        queueSize--;
    }

    // Finds a Node given its value
    public boolean search(Integer value) {
        Node current = front;
        int position = 0;

        while (current != null) {
            if (current.getValue() == value) {
                System.out.println("Node " + value + " found at index " + position);
                return true;
            }
            current = current.getNext();
            position++;
        }

        System.out.println("Node " + value + " not found");
        return false;
    }

    // Finds a Node given a position/index
    public Integer getKth(Integer index) {
        Node current = front;
        int position = 0;

        while (current != null) {
            if (position == index) { // Looks for the position count until it's equal as the given index

                System.out.println("The node at index " + index + " contains a Node with value " + current.getValue());
                return current.getValue();
            }
            position++;
            current = current.getNext();
        }

        System.out.println("Node at index " + index + " not found; it doesn't exist");
        return -1; // If a non-existent value is given, a neggative number is returned
    }

    public void printQueue() {
        Node current = front;

        System.out.println("Queue size = " + queueSize);
        System.out.println("Front: ");
        // Traverses the Queue, printing the getValue() at the current Node, then moving on
        while (current != null) {
            System.out.print(current.getValue() + " ");
            current = current.getNext();
        }
    }

    public boolean isEmpty() {
        return queueSize == 0; // If queueSize == 0, returns true
    }

    // Encapsulation implementation
    // Getters
    public int getQueueSize() { // Returns the number of items in this collection
        return queueSize;
    }
    public Node getFront() {
        return front;
    }
    public Node getBack() {
        return back;
    }
    // Setters
    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }
    public void setFront(Node front) {
        this.front = front;
    }
    public void setBack(Node back) {
        this.back = back;
    }

    public static void main(String[] args) {

    }
}