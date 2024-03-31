package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Stack {
	private Node top;
	private int size;

	class Node {
		protected Object data;
		protected Node next;

		public Node(Object data) {
			this.data = data;
		}
	}

	public Stack() {
		super();
	}

//Pushes an order onto the top of the stack.
	public void push(Order order) {
		Node newNode = new Node(order);
		if (isEmpty()) {
			// If the stack is empty, set the new node as the top
			top = newNode;
		} else {
			// If the stack is not empty, link the new node to the top and set it as the new
			// top
			newNode.next = top;
			top = newNode;
		}
		size++; // Increment size
	}

//Pops and returns the order at the top of the stack.
	public Order pop() {
		if (isEmpty()) {
			throw new IllegalStateException("Stack is empty");
		}
		Order item = (Order) top.data;
		top = top.next;
		size--; // Decrement size
		return item;
	}

//Returns the order at the top of the stack without removing it.
	public Order peek() {
		if (isEmpty()) {
			throw new IllegalStateException("Stack is empty");
		}
		return (Order) top.data;
	}

//Prints the orders in the stack.
	public void printStack() {
		if (isEmpty()) {
			System.out.println("Stack is empty");
		}
		Node currentNode = top;
		while (currentNode != null) {
			System.out.println(currentNode.data);
			currentNode = currentNode.next;
		}
	}

//Checks if the stack is empty.
	public boolean isEmpty() {
		return top == null;
	}

// Returns the node at the top of the stack without removing it.
	public Node peek2() {
		if (isEmpty()) {
			throw new IllegalStateException("Stack is empty");
		}
		return top;
	}

// Returns the size of the stack.
	public int getSize() {
		return size;
	}

}
