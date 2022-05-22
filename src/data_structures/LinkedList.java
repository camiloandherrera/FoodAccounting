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
    private int listSize;
    private Node head; 
    private Node tail;

    // Default LinkedList constructor, creates an empty LinkedList
    public LinkedList() {
        head = null;
        tail = null;

        listSize = 0;
    }

    // Methods
    // Method to insert a new Node
    public void insert(Integer value) {
        Node newNode = new Node(value);
        newNode.setNext(null);

        // If the List is empty, it'll make the New Node its head
        if (head == null) 
            head = newNode;
        // Else it'll traverse till finding the last node, then insert the New Node there
        else {
            Node last = head;
            while(last.getNext() != null)
                last = last.getNext();

            last.setNext(newNode); // Inserts the New Node at the end
        }
        listSize++;
    }

    // Method to remove a Node
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
            
            // If value was not present in linked list, currentNode should be null
            if (currentNode == null) 
                System.out.println(value + " not found");
             }
        }
    
    // Method to remove a Node at a Position i
    public void removeAt(Integer index) {
        // Stores the head Bode
        Node currentNode = head;
        Node previous = null;
 
        // If the index is 0, then head Node will be removed
        if (index == 0 && currentNode != null)
            head = currentNode.getNext();

        else {
            // If the index is higher than 0 but lower than the size of LinkedList
            int counter = 0;
            // Count for the index that will be be removed, keep track of the previous Node
            while (currentNode != null) {
                if (counter == index) {
                    // Since the current Node is the searched position, remove it from the List
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
        }  
    }

    public void printList() {
        Node current = head;

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