import java.util.*;

// Implements the Node of LinkedList
class Node {
    private Integer value; // Value to store in a Node
    private Node next; // Pointer to the next Node; null by default
    private Node previous; // Pointer to the previous Node; null by default, used by DLL only
    // Constructors
    // Node's default constructor
    public Node(Integer value) {
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


public class LinkedList {
    protected int listSize;
    protected Node head; 
    protected Node tail;

    // Default LinkedList constructor, creates an empty LinkedList
    public LinkedList() {
        head = null;
        tail = null;

        listSize = 0;
    }

    // Methods
    // Method to insert a new Node at the end (tail)
    public void insert(Integer value) {
        Node newNode = new Node(value);
        newNode.setNext(null);

        // If the List is empty, it'll make the New Node its head
        if (head == null) {
            head = newNode;
            makeTail(newNode);
        }
        // Else it'll traverse till finding the last node, then insert the New Node there
        else {
            Node last = head;
            while(last.getNext() != null)
                last = last.getNext();

            last.setNext(newNode); // Inserts the New Node at the end
            makeTail(newNode);
        }

        listSize++;
    }

    // Method that inserts a Node at the front (push)
    public void push(Integer value) {
        Node newNode = new Node(value); // Creates new Node with the data to insert

        newNode.setNext(head); // Sets the current head as the new Node's next Node
        setHead(newNode); // Sets the new Node as the new head
        makeTail(newNode);

        listSize++;
    }

    // Method that inserts a Node after a given Node
    public void insertAfter(Node previousNode, Integer newValue) {
        if (previousNode == null) {
            System.out.println("The Node cannot be null or empty");
            return;
        }

        Node newNode = new Node(newValue); // Creates new Node with the data to insert
        newNode.setNext(previousNode.getNext()); // Sets the previous Node's next as the new Node's next
        previousNode.setNext(newNode); // Sets the new Node as the previous new next

        makeTail(newNode); // Checks if it's also the tail
        listSize++;
    }

    // Method to remove a Node given a value
    public void remove(Integer value)
    {
        // Stores the head Node
        Node currentNode = head;
        Node previous = null;

        // If the head Node contains the value to be deleted
        if (currentNode != null && currentNode.getValue() == value)
            head = currentNode.getNext(); // Changed head
        else {
            // If the value is somewhere other than at the head, search for the value to be deleted
            while (currentNode != null && currentNode.getValue() != value) {
                // In case current Node does not contain the searched value continue to next Node
                previous = currentNode;
                currentNode = currentNode.getNext();
            }
            // If the value was present, it should be found at the currentNode variable, so it shouldn't be null
            if (currentNode != null) 
                // If the value is at currentNode, remove currentNode from LinkedList
                previous.setNext(currentNode.getNext());
            makeTail(previous); // Checks if it's also the tail

            // If value was not present in linked list, currentNode should be null
            if (currentNode == null) 
                System.out.println(value + " not found");
             }

        listSize--;
    }
    
    // Method to remove a Node at a Position i
    public void removeAt(Integer index) {
        // Stores the head Bode
        Node currentNode = head;
        Node previous = null;
 
        // If the index is 0, then head Node will be removed
        if (index == 0 && currentNode != null) {
            head = currentNode.getNext();
            tail = null;
        }

        else {
            // If the index is higher than 0 but lower than the size of LinkedList
            int counter = 0;
            // Count for the index that will be be removed, keep track of the previous Node
            while (currentNode != null) {
                if (counter == index) {
                    // Since the current Node is the searched position, remove it from the List
                    makeTail(currentNode.getNext()); // Checks if it's also the tail
                    previous.setNext(currentNode.getNext());
                    break;
                }
                else {
                    // If the current position is not the looked index continue to next Node
                    previous = currentNode;
                    currentNode = currentNode.getNext();
                    counter++;
                }
            }
            // If the index was present, it should be found at the currentNode variable, so it shouldn't be null
            
            // If the index is greater than the List's size, the current Node should be null
            if (currentNode == null) 
                System.out.println(index + " position not found.");
            
            listSize--;
        }  
    }
    
    // Finds a Node given its value
    public boolean search(Integer value) {
        Node current = head;
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
        Node current = head;
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

    public void printList() {
        Node current = head;

        System.out.println("LinkedList size = " + listSize);
        System.out.println("LinkedList: ");
        // Traverses the LinkedList, printing the getValue() at the current Node, then moving on
        while (current != null) {
            System.out.print(current.getValue() + " ");
            current = current.getNext();
        }
    }

    public boolean isEmpty() {
        return listSize == 0; // If listSize == 0, returns true
    }

    // If a Next pointer is null, the Node becomes the tail
    public void makeTail(Node node) {
        if (node.getNext() == null)
            tail = node;
    }

    // Encapsulation implementation
    // Getters
    public int getListSize() { // Returns the number of items in this collection
        return listSize;
    }
    public Node getHead() {
        return head;
    }
    public Node getTail() {
        return tail;
    }
    // Setters
    public void setListSize(int listSize) {
        this.listSize = listSize;
    }
    public void setHead(Node head) {
        this.head = head;
    }
    public void setTail(Node tail) {
        this.tail = tail;
    }

    public static void main(String[] args) {
       
    }
}