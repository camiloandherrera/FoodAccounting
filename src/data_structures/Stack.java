package data_structures;

public class Stack { // Una pila será utilizada para llevar cuentas de dinero neto (ganancia o adeudado)
    StackNode root;
    
    static class StackNode {
        Integer value;
        StackNode next;
 
        StackNode(int value) {
            this.value = value;
        }
    }
 
    public boolean isEmpty() {
        if (root == null)
            return true;
        else
            return false;
    }
 
    public void push(int value) {
        StackNode newNode = new StackNode(value);
 
        if (root == null) 
            root = newNode;
        else {
            StackNode temp = root;
            root = newNode;
            newNode.next = temp;
        }
        System.out.println(value + " stacked to the queue");
    }
 
    public int pop() {
        int popped = Integer.MIN_VALUE;
        if (root == null) {
            System.out.println("Stack is empty");
        }
        else {
            popped = root.value;
            root = root.next;
        }
        return popped;
    }
 
    public int peek() {
        if (root == null) {
            System.out.println("Stack is empty");
            return Integer.MIN_VALUE;
        }
        else {
            return root.value;
        }
    }
}