package application;

import java.util.Date;

import application.SLLSort.Node;
import application.circularDLLSort.brandNode;



public class SLLSort {
	private Node head;
	private int size;
	// Inner class representing a node in the linked list
	public class Node {
		car carobj;
		Node next;
		//public Order data;

		Node(car car2) {
			this.carobj = car2;
		}

		public car getCar() {
			return carobj;
		}

		public void setCar(car car2) {
			this.carobj = car2;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
	}

	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public SLLSort() {
		super();

	}

	public void insert(String model, Date year, String color, String price) {
		car newCar = new car(model, year, color, price);
		Node newNode = new Node(newCar);
		 // Check if the list is empty or if the new car's year is before the head car's year
		if (head == null || year.before(head.carobj.getYear())) {
			 // If so, make the new car the new head of the list
			newNode.next = head;
			head = newNode;
		} else {
			 // If not, traverse the list to find the correct position to insert the new car
			Node current = head;
			while (current.next != null && year.after(current.next.carobj.getYear())) {
				  // Move to the next node until finding a node with a year greater than the new car's year
				current = current.next;
			}
			
			newNode.next = current.next;
			current.next = newNode;
		}

		size++;
	}

	public int getSize() {
		return size;
	}
//Searches for a car by model in the linked list and returns the car if found.
	public car searchByModel(String model) {
		Node current = head;
		while (current != null) {
			if (current.carobj.getModel().equals(model)) {
				return current.carobj;// Found a car with the matching model
			}
			current = current.next;
		}
		return null; // Car not found
	}
//Searches for a node containing a car with the specified model in the linked list and returns the node if found.
	public Node searchByModel2(String model) {
		Node current = head;
		while (current != null) {
			if (current.carobj.getModel().equals(model)) {
				return current;// Found a node containing a car with the matching model
			}
			current = current.next;
		}
		return null; // Car not found
	}
//Deletes a car with the specified model from the linked list
	public void delete(String model) {
		// Check if the linked list is empty
		if (head == null)
			return;// Linked list is empty, nothing to delete
//Check if the car to delete is at the head of the list
		if (head.carobj.getModel().equals(model)) {
			head = head.next;// Remove the car at the head of the list
			size--;
			return;
		}
		 // Traverse the linked list to find the car to delete
		Node current = head;
		while (current.next != null) {
			 // Check if the next car's model matches the one to delete
			if (current.next.carobj.getModel().equals(model)) {
				current.next = current.next.next;// Remove the car after the current node
				size--;
				return;
			}
			current = current.next;
		}
	}

	
//Prints the list of cars to the console.
	public void printList2() {
		Node current = head;
		while (current != null) {
			System.out.println(current.carobj);// Print the current car
			current = current.next;
		}
	}

	

	//Updates the model of a car with the specified old model to the new model.
	public void updateByModel(String oldModel, car updatedCar) {
		// Traverse the linked list to find the car with the old model
		Node current = head;
		while (current != null) {
			 // Check if the current car's model matches the old model
			if (current.carobj.getModel().equals(oldModel)) {
				// Update the model of the current car
				current.carobj.setModel(updatedCar.getModel());
			}
			current = current.next;
		}
	}
//Moves the head to the next node in the linked list and updates the fields in the adminTab.
	public void nextSLL() {
		 if (head != null && head.getNext() != null) {
		        head = head.getNext();
		        car data = head.carobj;
		        if (data != null) {
		        	System.out.println("LM hereeeeeeee");
		            adminTab.modelField.setText(data.getModel());
		            adminTab.colorField.setText(data.getColor());
		            adminTab.yearField.setText(String.valueOf(data.getYear().getYear() + 1900));
		            adminTab.priceField.setText(data.getPrice());
		        }
		    }
	}

	// Method to get the highest model number
	public String getHighestModelNumber() {
		 // Check if the linked list is empty
		if (head == null) {
			return null; // Linked list is empty
		}
		 // Initialize the highest model number as the model number of the head car
		String highestModelNumber = head.carobj.getModel();
		Node current = head.next;
		 // Traverse the linked list to find the car with the highest model number
		while (current != null) {
			 // Compare the current car's model number with the highest model number
			if (current.carobj.getModel().compareTo(highestModelNumber) > 0) {
				highestModelNumber = current.carobj.getModel();
			}
			current = current.next;
		}

		return highestModelNumber;
	}

	// Method to get the lowest model number
	public String getLowestModelNumber() {
		
		if (head == null) {
			return null; // Linked list is empty
		}

		String lowestModelNumber = head.carobj.getModel();
		 // Traverse the linked list to find the car with the lowest model number
		Node current = head.next;

		while (current != null) {
			 // Compare the current car's model number with the lowest model number
			if (current.carobj.getModel().compareTo(lowestModelNumber) < 0) {
				lowestModelNumber = current.carobj.getModel();
			}
			current = current.next;
		}

		return lowestModelNumber;
	}

}
