package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

import application.SLLSort.Node;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class circularDLLSort {
	private brandNode first;
	private brandNode last;
	private int size;

	class brandNode {
		protected Brand data;
		protected brandNode previous;
		protected brandNode next;
		protected SLLSort st = new SLLSort();
		static Queue inProcessOrders = new Queue();
		static Stack finishedOrders = new Stack();

		brandNode(Brand data) {
			this.data = data;
		}

		public SLLSort getSt() {
			return st;
		}

		public void setSt(SLLSort st) {
			this.st = st;
		}

		public static Queue getInProcessOrders() {
			return inProcessOrders;
		}

		public static void setInProcessOrders(Queue inProcessOrders) {
			brandNode.inProcessOrders = inProcessOrders;
		}

		public static Stack getFinishedOrders() {
			return finishedOrders;
		}

		public static void setFinishedOrders(Stack finishedOrders) {
			brandNode.finishedOrders = finishedOrders;
		}

		public Brand getData() {
			return data;
		}

		public void setData(Brand data) {
			this.data = data;
		}

		public brandNode getPrevious() {
			return previous;
		}

		public void setPrevious(brandNode previous) {
			this.previous = previous;
		}

		public brandNode getNext() {
			return next;
		}

		public void setNext(brandNode next) {
			this.next = next;
		}
		

		public Object getHead() {

			return st.getHead();
		}
	}

	public circularDLLSort() {
		super();

	}

	public void insert(String brand) {
		Brand newBrand = new Brand(brand);
		brandNode newNode = new brandNode(newBrand);

		if (first == null) {
			// If the list is empty, make the new node the first and last node

			newNode.previous = newNode;
			newNode.next = newNode;
			first = newNode;
			last = newNode;
		} else {
			brandNode current = first;
			while (current.next != first && current.next.data.getBrand().compareTo(brand) < 0) {
				// Traverse the list to find the correct position to insert the new brand

				current = current.next;
			}
			// Insert the new node into the list
			newNode.next = current.next;
			newNode.previous = current;
			current.next = newNode;

			if (newNode.next != first) {
				// Update the previous node of the next node
				newNode.next.previous = newNode;
			} else {
				// Update the last node if the new node is inserted at the end
				last = newNode;
			}

			if (current == first && brand.compareTo(first.data.getBrand()) < 0) {
				// Update the first node if the new node is inserted at the beginning

				first = newNode;
			}
		}

		size++;
	}

	public void insert2(Brand brand) {
		brandNode newNode = new brandNode(brand);

		if (first == null) {
			// If the list is empty, make the new node the first and last node

			newNode.previous = newNode;
			newNode.next = newNode;
			first = newNode;
			last = newNode;
		} else {
			brandNode current = first;
			while (current.next != first && current.next.data.getBrand().compareTo(brand.getBrand()) < 0) {
				// Traverse the list to find the correct position to insert the new brand

				current = current.next;
			}
			// Insert the new node into the list
			newNode.next = current.next;
			newNode.previous = current;
			current.next = newNode;

			if (newNode.next != first) {
				// Update the previous node of the next node

				newNode.next.previous = newNode;
			} else {
				// Update the last node if the new node is inserted at the end
				last = newNode;
			}

			if (current == first && brand.getBrand().compareTo(first.data.getBrand()) < 0) {
				// Update the first node if the new node is inserted at the beginning
				first = newNode;
			}
		}

		size++;
	}

	public void delete(String brand) {
		if (first == null)
			return;// If the list is empty, there is nothing to delete

		brandNode current = first;

		do {
			if (current.data.getBrand().equals(brand)) {
				if (current.next == current) {
					// Only one node in the list
					first = null;
					last = null;
				} else {
					// Remove the current node from the list
					current.previous.next = current.next;
					current.next.previous = current.previous;

					if (current == first) {
						first = current.next;
					}
					if (current == last) {
						last = current.previous;
					}
				}
				size--;
				return;
			}
			current = current.next;
		} while (current != first);
	}

	public void update(String oldBrand, String newBrand) {
		brandNode current = first;
		do {
			if (current.data.getBrand().equals(oldBrand)) {
				// Update the brand name with the new brand
				current.data.setBrand(newBrand);
				return;
			}
			current = current.next;
		} while (current != first);
	}

	public brandNode search3(String brand) {
		if (isEmpty()) {
			return null; // If the list is empty, the brand cannot be found
		}

		brandNode current = first;
		do {
			if (current.data.getBrand().equals(brand)) {
				return current; // Brand found, return the node
			}
			current = current.next;
		} while (current != first);

		return null; // Brand not found
	}

	public int getSize() {
		return size;
	}

	public brandNode getLast() {
		return last;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public brandNode searchNode(String brand) {
		if (isEmpty()) {
			return null; // List is empty, brand cannot be found
		}

		brandNode current = first;
		do {
			if (current.data.getBrand().equals(brand)) {
				return current;// Brand found, return the node
			}
			current = current.next;
		} while (current != first);

		return null; // Brand not found
	}

	public void printList2() {
		if (first == null) {
			System.out.println("List is empty");
			return;
		}

		brandNode current = first;
		do {
			System.out.println(current.data.getBrand()); // Print brand name
			SLLSort carList = current.st;
			Node carCurrent = carList.getHead();
			while (carCurrent != null) {
				System.out.println("\t" + carCurrent.carobj); // Print car details
				carCurrent = carCurrent.next;
			}
			current = current.next;
		} while (current != first);
	}

	public brandNode getFirst() {
		return first;
	}

	public void setFirst(brandNode first) {
		this.first = first;
	}

	public void setLast(brandNode last) {
		this.last = last;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void remove(car soldCar) {
		if (isEmpty()) {
			return; // List is empty, nothing to remove
		}

		brandNode current = first;

		do {
			if (current.data.equals(soldCar)) {
				if (size == 1) {
					// Only one node in the list
					first = null;
					last = null;
				} else {
					// Remove the current node from the list
					current.previous.next = current.next;
					current.next.previous = current.previous;

					if (current == first) {
						first = current.next;
					}
					if (current == last) {
						last = current.previous;
					}
				}
				size--;
				return;
			}
			current = current.next;
		} while (current != first);

	}// Move forward to the next location node

	public void forward() {
		if (first == null) {
			adminTab.getNg().setDisable(true);
			return; // Empty list, nothing to move forward
		}
		first = first.getNext();
		Brand data = first.getData();
		adminTab.BrandField.setText(data.getBrand());
	}

	// Move backward to the previous location node
	public void backwardButtonActionPerformed() {
		if (first == null) {
			adminTab.getPg().setDisable(true);
			return; // Empty list, nothing to move backward
		}
		first = first.getPrevious(); // Move to previous node
		if (first == null) {
			// At the end of the list, move to the last node
			brandNode lastNode = getLast();
			if (lastNode != null) {
				first = lastNode;
			} else {
				adminTab.BrandField.setText("");
				return;
			}
		}
		Brand data = first.getData();
		adminTab.BrandField.setText(data.getBrand());
	}

	public void saveCarsToFile() {
		// Create a file chooser dialog
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select File to Save");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
		// Show the save file dialog and get the selected file
		File selectedFile = fileChooser.showSaveDialog(new Stage());

		if (selectedFile != null) {
			try (FileWriter writer = new FileWriter(selectedFile)) {
				brandNode current = first;

				while (current != null) {
					String brand = current.data.getBrand();
					SLLSort carList = current.st;
					Node carCurrent = carList.getHead();

					while (carCurrent != null) {
						writer.write(brand + ", " + carCurrent.carobj.toString() + "\n");
						carCurrent = carCurrent.next;
					}

					current = current.next;
					if (current == first) {
						break; // Exit the loop when we reach the end of the circular list
					}
				}

				// Display a success alert
				Platform.runLater(() -> {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Data Saved");
					alert.setHeaderText(null);
					alert.setContentText("Data saved successfully to:\n" + selectedFile.getAbsolutePath());
					alert.showAndWait();
				});
			} catch (IOException e) {
				// Display an error alert
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("Error saving data to file:\n" + e.getMessage());
				alert.showAndWait();
			}
		} else {
			// Display a warning alert if no file was selected
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("No File Selected");
			alert.setHeaderText(null);
			alert.setContentText("No file selected.");
			alert.showAndWait();
		}
	}

	public void saveOrdersToFile2(Stack inProcessStack, Queue inProcessOrders) {
		// Create a file chooser dialog
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Orders File");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
		// Show the save file dialog and get the selected file

		File selectedFile = fileChooser.showSaveDialog(null);

		if (selectedFile != null) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
				// Save orders from the stack
				Stack stackCopy = new Stack();
				while (!inProcessStack.isEmpty()) {
					Order order = inProcessStack.peek();
					stackCopy.push(order);

					String formattedOrderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
					int carYear = order.getCar().getYear().getYear();
					String line = order.getCustomerName() + "," + order.getCustomerMobile() + ","
							+ order.getBrand().brand + "," + order.getCar().getModel() + "," + carYear + ","
							+ order.getCar().getColor() + "," + order.getCar().getPrice() + "," + formattedOrderDate
							+ "," + order.getOrderStatus();
					writer.write(line);
					writer.newLine();

					inProcessStack.pop();
				}
				// Restore the original stack
				while (!stackCopy.isEmpty()) {
					inProcessStack.push(stackCopy.pop());
				}

				// Save orders from the queue
				Queue queueCopy = new Queue();
				while (!inProcessOrders.isEmpty()) {
					Order order = (Order) inProcessOrders.peek();
					queueCopy.enqueue(order);

					String formattedOrderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
					int carYear = order.getCar().getYear().getYear();
					String line = order.getCustomerName() + "," + order.getCustomerMobile() + ","
							+ order.getBrand().brand + "," + order.getCar().getModel() + "," + carYear + ","
							+ order.getCar().getColor() + "," + order.getCar().getPrice() + "," + formattedOrderDate
							+ "," + order.getOrderStatus();
					writer.write(line);
					writer.newLine();

					inProcessOrders.dequeue();
				}
				// Restore the original queue
				while (!queueCopy.isEmpty()) {
					inProcessOrders.enqueue((Order) queueCopy.dequeue());
				}

				// Display a success alert
				Platform.runLater(() -> {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Data Saved");
					alert.setHeaderText(null);
					alert.setContentText("Data saved successfully to:\n" + selectedFile.getAbsolutePath());
					alert.showAndWait();
				});
			} catch (IOException e) {
				// Display an error alert
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("Error saving data to file:\n" + e.getMessage());
				alert.showAndWait();
			}
		} else {
			// Display a warning alert if no file was selected
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("No File Selected");
			alert.setHeaderText(null);
			alert.setContentText("No file selected.");
			alert.showAndWait();
		}
	}

	// Invoke the getHighestModelNumber method
	public String getHighestModelNumber(String brand) {
		if (first == null) {
			return null; // List is empty
		}

		String highestModelNumber = null;
		brandNode current = first;
		// Iterate through the circular list

		do {
			if (current.getData().getBrand().equals(brand)) {
				String currentModelNumber = current.getSt().getHighestModelNumber();
				// Update the highest model number if necessary

				if (currentModelNumber != null
						&& (highestModelNumber == null || currentModelNumber.compareTo(highestModelNumber) > 0)) {
					highestModelNumber = currentModelNumber;
				}
			}

			current = current.getNext();
		} while (current != first);

		return highestModelNumber;
	}

	// Invoke the getLowestModelNumber method
	public String getLowestModelNumber(String brand) {
		if (first == null) {
			return null; // List is empty
		}

		String lowestModelNumber = null;
		brandNode current = first;
		// Iterate through the circular list

		do {
			if (current.getData().getBrand().equals(brand)) {
				String currentModelNumber = current.getSt().getLowestModelNumber();
				// Update the lowest model number if necessary

				if (currentModelNumber != null
						&& (lowestModelNumber == null || currentModelNumber.compareTo(lowestModelNumber) < 0)) {
					lowestModelNumber = currentModelNumber;
				}
			}

			current = current.getNext();
		} while (current != first);

		return lowestModelNumber;
	}

	public String getHighestPrice(String brand) {
	    if (first == null) {
	        return ""; // List is empty
	    }

	    int highestPrice = -1;
	    brandNode current = first;

	    do {
	        if (current.getData().getBrand().equals(brand)) {
	            SLLSort.Node currentCarNode = current.getSt().getHead();

	            while (currentCarNode != null) {
	                double currentPrice = convertPriceToNumeric(currentCarNode.getCar().getPrice());
	                // Multiply the price by 1000
	                int priceInThousands = (int) (currentPrice);

	                // Update the highest price if necessary
	                if (priceInThousands > highestPrice) {
	                    highestPrice = priceInThousands;
	                }

	                currentCarNode = currentCarNode.getNext();
	            }
	        }

	        current = current.getNext();
	    } while (current != first);

	    return highestPrice+"K";
	}

	public String getLowestPrice(String brand) {
	    if (first == null) {
	        return ""; // List is empty
	    }

	    int lowestPrice = Integer.MAX_VALUE;
	    brandNode current = first;

	    do {
	        if (current.getData().getBrand().equals(brand)) {
	            SLLSort.Node currentCarNode = current.getSt().getHead();

	            while (currentCarNode != null) {
	                double currentPrice = convertPriceToNumeric(currentCarNode.getCar().getPrice());
	                // Multiply the price by 1000
	                int priceInThousands = (int) (currentPrice);

	                // Update the lowest price if necessary
	                if (priceInThousands < lowestPrice) {
	                    lowestPrice = priceInThousands;
	                }

	                currentCarNode = currentCarNode.getNext();
	            }
	        }

	        current = current.getNext();
	    } while (current != first);

	    return lowestPrice+"K";
	}

	// Utility method to convert price string to a numeric value
	private double convertPriceToNumeric(String price) {
	    String numericString = price.replaceAll("[^0-9.]", ""); // Remove non-numeric characters except decimal point
	    return Double.parseDouble(numericString); // Convert the numeric string to a double value
	}
}
	