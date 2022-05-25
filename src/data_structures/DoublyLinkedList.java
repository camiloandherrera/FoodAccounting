package data_structures;

public class DoublyLinkedList extends LinkedList {

    // Default DoublyLinkedList constructor, creates an empty LinkedList
    public DoublyLinkedList() {
        super();
    }
    // Methods
    @Override
    // Method to insert a new Node at the end (tail)
    public void insert(Integer value) {
        Node newNode = new Node(value);

        newNode.setNext(null);

        // If the List is empty, it'll make the New Node its head
        if (head == null) {
            newNode.setPrevious(null);
            head = newNode;
            makeTail(newNode);
        }
        // Else it'll traverse till finding the last node, then insert the New Node there
        else {
            Node last = head;
            while(last.getNext() != null)
                last = last.getNext();

            last.setNext(newNode); // Inserts the New Node at the end
            newNode.setPrevious(last);

            makeTail(newNode);
        }

        listSize++;
    }

    // Method that inserts a Node at the front (push)
    @Override
    public void push(Integer value) {
        Node newNode = new Node(value); // Creates new Node with the data to insert

        newNode.setNext(head); // Sets the current head as the new Node's next Node
        newNode.setPrevious(null);

        if (head != null) // Changes the previous of the head to the new Node
            head.setPrevious(newNode);

        setHead(newNode); // Sets the new Node as the new head

        makeTail(newNode); // Checks if it's also the tail
        listSize++;
    }
    
    @Override
     // Method that inserts a Node after a given Node
     public void insertAfter(Node previousNode, Integer newValue) {
        if (previousNode == null) {
            System.out.println("The Node cannot be null or empty");
            return;
        }

        Node newNode = new Node(newValue); // Creates new Node with the data to insert

        newNode.setNext(previousNode.getNext()); // Sets the previous Node's next as the new Node's next
        previousNode.setNext(newNode); // Sets the new Node as the previous new next
       
        newNode.setPrevious(previousNode); // Sets the previous Node as the new Node's previous
        if (newNode.getNext() != null) // Makes the previous of the new Node's next to the new Node
            newNode.getNext().setPrevious(newNode);

        makeTail(newNode); // Checks if it's also the tail
        listSize++;
     }

     // General method to remove a Node in a DLL
     public void deleteNode(Node toDelete) {
        boolean wasReduced = false; // Controls if the list size has been recuded

        // Case 1
        if (head == null && toDelete == null)
            return;

        // Case 2: If Node to delete is the head Node
        if (head == toDelete) {
            head  = toDelete.getNext();
            wasReduced = true;
        }  
        
        // Case 3: Change next only if Node to delete is not the last
        if (toDelete.getNext() != null) {
            toDelete.getNext().setPrevious(toDelete.getPrevious());
            wasReduced = true;
        }

        // Case 4: Change previous only if Node to be deleted is not the first
        if (toDelete.getPrevious() != null) {
            toDelete.getPrevious().setNext(toDelete.getNext());
            wasReduced = true;
        }

        if (wasReduced == true)
            listSize--;

        return;
     }

    
    public static void main(String[] args) {
       
    }
}
