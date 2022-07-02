package data_structures;

// Implements the Node_String of LinkedList
class Node_String {
    protected String value; // Value to store in a Node_String
    protected Node_String next; // Pointer to the next Node_String; null by default
    protected Node_String previous; // Pointer to the previous Node_String; null by default, used by DLL only
    // Constructors
    // Node_String's default constructor
    public Node_String(String value) {
        this.value = value;
        previous = null;
        next = null;
    }
    // Node_String's constructor, indicating next Node_String
    public Node_String(String value, Node_String next) {
        this.value = value;
        this.next = next;
    }

    // Encapsulation implementation
    // Getters
    public String getValue() {
        return value;
    }
    public Node_String getPrevious() {
        return previous;
    }
    public Node_String getNext() {
        return next;
    }
    // Setters
    public void setValue(String value) {
        this.value = value;
    }
    public void setPrevious(Node_String previous) {
        this.previous = previous;
    }
    public void setNext(Node_String next) {
        this.next = next;
    }
}


public class LinkedList_String {
    protected int listSize;
    protected Node_String head; 
    protected Node_String tail;

    // Default LinkedList constructor, creates an empty LinkedList
    public LinkedList_String() {
        head = null;
        tail = null;

        listSize = 0;
    }

    // Methods
    // Method to insert a new Node_String at the end (tail)
    public void insert(String value) {
        Node_String newNode = new Node_String(value);
        newNode.setNext(null);

        // If the List is empty, it'll make the New Node_String its head
        if (head == null) {
            head = newNode;
            makeTail(newNode);
        }
        // Else it'll traverse till finding the last node, then insert the New Node_String there
        else {
            Node_String last = head;
            while(last.getNext() != null)
                last = last.getNext();

            last.setNext(newNode); // Inserts the New Node_String at the end
            makeTail(newNode);
        }

        listSize++;
    }

    // Method that inserts a Node_String at the front (push)
    public void push(String value) {
        Node_String newNode = new Node_String(value); // Creates new Node_String with the data to insert

        newNode.setNext(head); // Sets the current head as the new Node_String's next Node_String
        setHead(newNode); // Sets the new Node_String as the new head
        makeTail(newNode);

        listSize++;
    }

    // Method that inserts a Node_String after a given Node_String
    public void insertAfter(Node_String previousNode, String newValue) {
        if (previousNode == null) {
            System.out.println("The Node_String cannot be null or empty");
            return;
        }

        Node_String newNode = new Node_String(newValue); // Creates new Node_String with the data to insert
        newNode.setNext(previousNode.getNext()); // Sets the previous Node_String's next as the new Node_String's next
        previousNode.setNext(newNode); // Sets the new Node_String as the previous new next

        makeTail(newNode); // Checks if it's also the tail
        listSize++;
    }

    // Method to remove a Node_String given a value
    public void remove(String value)
    {
        // Stores the head Node_String
        Node_String currentNode = head;
        Node_String previous = null;

        // If the head Node_String contains the value to be deleted
        if (currentNode != null && currentNode.getValue().equals(value))
            head = currentNode.getNext(); // Changed head
        else {
            // If the value is somewhere other than at the head, search for the value to be deleted
            while (currentNode != null && currentNode.getValue() != value) {
                // In case current Node_String does not contain the searched value continue to next Node_String
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
    
    // Method to remove a Node_String at a Position i
    public void removeAt(Integer index) {
        // Stores the head Bode
        Node_String currentNode = head;
        Node_String previous = null;
 
        // If the index is 0, then head Node_String will be removed
        if (index == 0 && currentNode != null) {
            head = currentNode.getNext();
            tail = null;
        }

        else {
            // If the index is higher than 0 but lower than the size of LinkedList
            int counter = 0;
            // Count for the index that will be be removed, keep track of the previous Node_String
            while (currentNode != null) {
                if (counter == index) {
                    // Since the current Node_String is the searched position, remove it from the List
                    makeTail(currentNode.getNext()); // Checks if it's also the tail
                    previous.setNext(currentNode.getNext());
                    break;
                }
                else {
                    // If the current position is not the looked index continue to next Node_String
                    previous = currentNode;
                    currentNode = currentNode.getNext();
                    counter++;
                }
            }
            // If the index was present, it should be found at the currentNode variable, so it shouldn't be null
            
            // If the index is greater than the List's size, the current Node_String should be null
            if (currentNode == null) 
                System.out.println(index + " position not found.");
            
            listSize--;
        }  
    }
    
    // Finds a Node_String given its value
    public boolean search(String value) {
        Node_String current = head;
        int position = 0;

        while (current != null) {
            if (current.getValue().equals(value)) {
                //System.out.println("Node_String " + value + " found at index " + position);
                return true;
            }
            current = current.getNext();
            position++;
        }

        //System.out.println("Node_String " + value + " not found");
        return false;
    }

    // Finds a Node_String given a position/index
    public String getKth(Integer index) {
        Node_String current = head;
        int position = 0;

        while (current != null) {
            if (position == index) { // Looks for the position count until it's equal as the given index

                System.out.println("The node at index " + index + " contains a Node_String with value " + current.getValue());
                return current.getValue();
            }
            position++;
            current = current.getNext();
        }

        System.out.println("Node_String at index " + index + " not found; it doesn't exist");
        return null; // If a non-existent value is given, a null value
    }

    public void printList() {
        Node_String current = head;

        System.out.println("LinkedList size = " + listSize);
        System.out.println("LinkedList: ");
        // Traverses the LinkedList, printing the getValue() at the current Node_String, then moving on
        while (current != null) {
            System.out.print("- " + current.getValue() + "\n");
            current = current.getNext();
        }
    }

    public boolean isEmpty() {
        return listSize == 0; // If listSize == 0, returns true
    }

    // If a Next pointer is null, the Node_String becomes the tail
    public void makeTail(Node_String node) {
        if (node.getNext() == null)
            tail = node;
    }

    // Encapsulation implementation
    // Getters
    public int getListSize() { // Returns the number of items in this collection
        return listSize;
    }
    public Node_String getHead() {
        return head;
    }
    public Node_String getTail() {
        return tail;
    }
    // Setters
    public void setListSize(int listSize) {
        this.listSize = listSize;
    }
    public void setHead(Node_String head) {
        this.head = head;
    }
    public void setTail(Node_String tail) {
        this.tail = tail;
    }

    public static void main(String[] args) {
       
    }
}