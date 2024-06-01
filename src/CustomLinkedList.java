import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class CustomLinkedList {
    private Node head;  

    // Method to insert a new node with the data at the end 
    public void insert(int data) {
        Node newNode = new Node(data);
        if (head == null) {  // List empty, new node as head
            head = newNode;
        } else {  // Not true, the traverse to the end following adding new node
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    //  delete a node with the data that was given from the list
    public void delete(int data) {
        if (head == null) {  
            return;
        }
        if (head.data == data) {  // If the head node is to be deleted, then it will update the head
            head = head.next;
            return;
        }
        Node current = head;
        while (current.next != null && current.next.data != data) {  
            current = current.next;
        }
        if (current.next != null) {  
            current.next = current.next.next;
        }
    }

    // Method to get an iterator for the linked list
    public Iterator<Integer> iterator() {
        return new LinkedListIterator();
    }

    
    private class Node {
        int data;  // Data stored in the node
        Node next;  // The next node

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Inner class implementing the Iterator interface for the linked list
    private class LinkedListIterator implements Iterator<Integer> {
        private Node current = head;  

        @Override
        public boolean hasNext() {
            return current != null;  
        }

        @Override
        public Integer next() {
            if (!hasNext()) {  // If no more elements, then do exception
                throw new NoSuchElementException();
            }
            int data = current.data;  // Retrieve data of the current node
            current = current.next;  // Move to the next node
            return data;
        }
    }

    // Main method to demonstrate usage of the CustomLinkedList class
    public static void main(String[] args) {
        CustomLinkedList linkedList = new CustomLinkedList();

        // Generate an array of ten random integers
        Random random = new Random();
        int[] randomNumbers = new int[10];
        for (int i = 0; i < 10; i++) {
            randomNumbers[i] = random.nextInt(100); // Random integers between 0 and 99
        }

        // Insert these random integers into the linked list
        for (int number : randomNumbers) {
            linkedList.insert(number);
        }

        // Iterate and then display elements
        System.out.print("Linked List Elements: ");
        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");  // Display each element
        }
        System.out.println();

        // Let's delete an element from the list for testing purposes (for example, the first element)
        if (randomNumbers.length > 0) {
            int numberToDelete = randomNumbers[0];
            linkedList.delete(numberToDelete);  // Delete the node with the first random number
            System.out.println("After deleting element: " + numberToDelete);
        }

        // Display the list again
        System.out.print("Linked List Elements after deletion: ");
        iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");  // Print each element
        }
    }
}