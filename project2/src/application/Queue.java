package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Queue {
	private Node front;
	private Node rear;
	private int size;

	class Node {
		protected Object data;
		protected Node next;

		public Node(Object data) {
			this.data = data;
		}
	}

	public Queue() {
	}

//Adds an item to the rear of the queue.
	public void enqueue(Order item) {
		Node newNode = new Node(item);
		if (isEmpty()) {
			// If the queue is empty, set both front and rear to the new node
			front = newNode;
			rear = newNode;
		} else {
			// If the queue is not empty, add the new node to the rear
			rear.next = newNode;
			rear = newNode;
		}
		size++;
	}

// Removes and returns the item at the front of the queue.
	public Object dequeue() {
		if (isEmpty()) {
			throw new IllegalStateException("Queue is empty");
		}
		Object item = front.data;
		front = front.next;
		if (front == null) {
			// If the front becomes null after removing the item, the queue becomes empty,
			// so the rear should also be set to null
			rear = null;
		}
		size--;
		return item;
	}

//Returns the item at the front of the queue without removing it.
	public Object peek() {
		if (isEmpty()) {
			throw new IllegalStateException("Queue is empty");
		}
		return front.data;
	}

// Checks if the queue is empty.
	public boolean isEmpty() {
		return size == 0;
	}

//Returns the size of the queue.
	public int getSize() {
		return size;
	}

//Prints the items in the queue.
	public void printList() {
		if (isEmpty()) {
			System.out.println("Queue is empty");
			return;
		}

		Node current = front;
		while (current != null) {
			System.out.println(current.data);
			current = current.next;
		}
	}

}
