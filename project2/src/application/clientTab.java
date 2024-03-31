package application;


import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.security.auth.callback.Callback;

import application.SLLSort.Node;
import application.circularDLLSort.brandNode;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class clientTab {

	private ComboBox<String> brandComboBox;
	private int currentIndex = 0;
	static ObservableList<car> carData = FXCollections.observableArrayList();
	Stage s;
	Stage s2;
	static TableView<car> carTableView;
	private Button nextButton;
	private Button previousButton;
	private Button filter;
	private Button addCart;
	private Button search;
	private Button ng;
	private Button show;
	private Button cancel;
	private ComboBox<String> modelComboBox;
	private ChoiceBox<String> colorChoiceBox;
	private TextField yearTextField;
	private TextField priceTextField;

	public Stage getClientTab() {

		Main.comboBox.setOnAction(event -> {

			String brandName = Main.comboBox.getSelectionModel().getSelectedItem();

			if (brandName != null && !brandName.isEmpty()) {
				// Find the brand in the carsList
				brandNode foundNode = Main.carsList.searchNode(brandName);

				if (foundNode != null) {
					carData.clear();
					Node currentBrandNode = foundNode.st.getHead();

					while (currentBrandNode != null) {
						carData.add(currentBrandNode.getCar());
						currentBrandNode = currentBrandNode.next;
					}

					carTableView.getSelectionModel().selectFirst();
					carTableView.refresh();

					// Perform additional actions based on the selected brand
					if (brandName.equals("BMW")) {
						// Perform action for BMW selection
						System.out.println("BMW selected");
						// Additional actions for BMW
					} else if (brandName.equals("FORD")) {
						// Perform action for FORD selection
						System.out.println("FORD selected");
						// Additional actions for FORD
					} else if (brandName.equals("KIA")) {
						// Perform action for KIA selection
						System.out.println("KIA selected");
						// Additional actions for KIA
					} else if (brandName.equals("MERCEDES")) {
						// Perform action for MERCEDES selection
						System.out.println("MERCEDES selected");
						// Additional actions for MERCEDES
					} else if (brandName.equals("TESLA")) {
						// Perform action for TESLA selection
						System.out.println("TESLA selected");
						// Additional actions for TESLA
					}
				} else {
					System.out.println("Brand not found");
				}
			}
		});

		carTableView = new TableView<>();
		TableColumn<car, String> modelColumn = new TableColumn<>("Model");
		modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
		TableColumn<car, String> yearColumn = new TableColumn<>("Year");
		yearColumn.setCellValueFactory(cellData -> {
			car car = cellData.getValue();
			Date year = car.getYear();
			DateFormat formatter = new SimpleDateFormat("yyyy");
			String yearString = formatter.format(year);
			return new SimpleStringProperty(yearString);
		});
		TableColumn<car, String> colorColumn = new TableColumn<>("Color");
		colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));

		TableColumn<car, Double> priceColumn = new TableColumn<>("Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		carTableView.setItems(carData);
		// Create table view and set columns
		carTableView = new TableView<>();
		carTableView.getColumns().addAll(modelColumn, yearColumn, colorColumn, priceColumn);
		modelColumn.setPrefWidth(100);
		carTableView.setItems(carData);
		if (!Main.comboBox.getItems().isEmpty()) {
			// Check if the comboBox is not empty
			Main.comboBox.getSelectionModel().selectFirst();
			// Select the first item in the comboBox
			String brandName = Main.comboBox.getSelectionModel().getSelectedItem();
			// Get the selected brand name
			updateTableView2(brandName);
			// Update the table view with the data for the selected brand
		}
		// Create buttons
		ng = new Button();
		ng.setOnAction(e -> {
			Main.carsList.saveCarsToFile();
		});
		Image image4 = new Image("saveFile.png");
		ImageView imageView4 = new ImageView(image4);
		ng.setGraphic(imageView4);
		nextButton = new Button();
		previousButton = new Button();
		Image image3 = new Image("nex.png");
		ImageView imageView3 = new ImageView(image3);
		nextButton.setGraphic(imageView3);
		colorColumn.setPrefWidth(100);
		Image image2 = new Image("pri.png");
		ImageView imageView2 = new ImageView(image2);
		previousButton.setGraphic(imageView2);
		filter = new Button("Filters");
		addCart = new Button("Add to Cart");
		search = new Button("Search");
		show = new Button("Show All Cars");
		HBox h2 = new HBox(10);
		filter.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 18));// type of The Text
		h2.setPadding(new Insets(10, 15, 10, 10));// To determine the dimensions of the sides

		filter.setStyle("-fx-background-color: white");// To determine the Color background for Button
		addCart.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 18));// type of The Text

		addCart.setStyle("-fx-background-color: white");// To determine the Color background for Button
		filter.setMaxWidth(200);
		addCart.setMaxWidth(200);
		search.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 18));// type of The Text

		show.setStyle("-fx-background-color: white");// To determine the Color background for Button
		show.setMaxWidth(200);
		show.setMaxWidth(350);
		ng.setMaxWidth(40);
		ng.setMaxWidth(40);
		show.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 18));// type of The Text

		Button cancel = new Button();
		Image image = new Image("homePage.jpg");
		ImageView imageView = new ImageView(image);
		cancel.setGraphic(imageView);
		imageView.setFitWidth(140);
		imageView.setFitHeight(70);
		cancel.setMaxWidth(100);
		cancel.setMaxHeight(100);
		cancel.setStyle("-fx-background-color: white;");

		show.setOnAction(e -> {
			SLLSort.Node currentNode2 = Main.carsList.getLast().st.getHead().next;

			while (currentNode2 != null) {
				// Iterate through the nodes in the linked list
				carData.add(currentNode2.carobj);
				// Add the car from the current node to the carData list
				currentNode2 = currentNode2.next;
				// Move to the next node
			}

			carTableView.setItems(carData);
			// Set the items of the carTableView to the carData list
		});
		addCart.setOnAction(e -> {
			getAddCart();
		});
		search.setStyle("-fx-background-color: white");// To determine the Color background for Button
		search.setMaxWidth(200);
		search.setMaxHeight(200);
		filter.setMaxHeight(200);
		addCart.setMaxHeight(200);
		ng.setStyle("-fx-background-color: white");// To determine the Color background for Button
		h2.getChildren().addAll(show, search, filter, addCart, ng, cancel);
		h2.setAlignment(Pos.CENTER);
		imageView3.setFitWidth(60);
		imageView3.setFitHeight(60);

		imageView4.setFitWidth(60);
		imageView4.setFitHeight(60);

		imageView2.setFitWidth(60);
		imageView2.setFitHeight(60);
		filter.setOnAction(e -> {
			getFilters();
		});

		// Handle button actions
		search.setOnAction(e -> {

			car selectedCar = carTableView.getSelectionModel().getSelectedItem();
			if (selectedCar != null) {
				// A car is selected in the table view
				System.out.println(selectedCar.toString());
				String selectedModel = selectedCar.getModel();

				// Search for the selected model in the carsList
				Node node = Main.carsList.search3(Main.comboBox.getSelectionModel().getSelectedItem()).st
						.searchByModel2(selectedModel);

				if (node.getCar().model != null) {
					// The selected model is found in the list
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Model Found");
					alert.setHeaderText(null);
					alert.setContentText("The selected model is found in the list.\n\n" + selectedCar.toString());
					alert.showAndWait();
				} else {
					// The selected model is not found in the list
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setTitle("Model not found");
					alert.setHeaderText(null);
					alert.setContentText("The selected model is not found in the list.");
					alert.showAndWait();
				}
			} else {
				// No car is selected in the table view
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("No Car Selected");
				alert.setHeaderText(null);
				alert.setContentText("Please select a car from the table.");
				alert.showAndWait();
			}

		});
		nextButton.setOnAction(event -> {
			// Get the index of the currently selected item in the comboBox
			int currentIndex = Main.comboBox.getSelectionModel().getSelectedIndex();
			// Calculate the index of the next item by incrementing the currentIndex and
			// using modulo operator to wrap around
			int nextIndex = (currentIndex + 1) % Main.comboBox.getItems().size();
			// Select the next item in the comboBox
			Main.comboBox.getSelectionModel().select(nextIndex);
			// Get the selected brand name
			String brandName = Main.comboBox.getSelectionModel().getSelectedItem();
			// Update the table view with the data for the selected brand
			updateTableView2(brandName);
		});

		previousButton.setOnAction(event -> {
			// Get the index of the currently selected item in the comboBox
			int currentIndex = Main.comboBox.getSelectionModel().getSelectedIndex();
			// Calculate the index of the previous item by decrementing the currentIndex,
			// adding the size of the comboBox items, and using modulo operator to wrap
			// around
			int previousIndex = (currentIndex - 1 + Main.comboBox.getItems().size()) % Main.comboBox.getItems().size();
			// Select the previous item in the comboBox
			Main.comboBox.getSelectionModel().select(previousIndex);
			// Get the selected brand name
			String brandName = Main.comboBox.getSelectionModel().getSelectedItem();
			// Update the table view with the data for the selected brand
			updateTableView2(brandName);
		});
		// Create layout
		VBox layout = new VBox();
		HBox h = new HBox(10);
		h.getChildren().addAll(previousButton, nextButton);
		previousButton.setMaxHeight(200);
		nextButton.setMaxHeight(200);
		cancel.setOnAction(e -> {
			s.close();
		});
		previousButton.setMaxWidth(230);
		nextButton.setMaxWidth(230);
		h.setAlignment(Pos.CENTER);

		h.setPadding(new Insets(15, 15, 15, 15));
		ng.setAlignment(Pos.BOTTOM_RIGHT);
		HBox h3 = new HBox(10);
		h3.setAlignment(Pos.CENTER);
		h3.getChildren().addAll(ng, cancel);
		h3.setPadding(new Insets(15, 15, 15, 15));

		ng.setPadding(new Insets(15, 15, 15, 15));
		layout.setAlignment(Pos.CENTER);
		layout.setPadding(new Insets(15, 15, 15, 15));
		layout.getChildren().addAll(Main.comboBox, carTableView, h, h2, h3);

		// Create tab
		Tab carTab = new Tab("Client");
		carTab.setContent(layout);

		// Create tab pane and add the car tab
		TabPane tabPane = new TabPane();
		tabPane.getTabs().add(carTab);
		s = new Stage();
		s.setTitle("Client Screen");// There is a title for the window
		// Create scene and set it on the stage
		Scene scene = new Scene(tabPane, 800, 700);
		s.setScene(scene);
		s.show();
		return s;

	}

	private void updateTableView2(String selectedBrand) {
		carData.clear();

		// Find the brand in the carsList
		brandNode foundNode = Main.carsList.searchNode(selectedBrand);

		if (foundNode == null) {
			// If the brand is not found, insert it into the carsList
			Main.carsList.insert(selectedBrand);
			// Retrieve the newly inserted brand node
			foundNode = Main.carsList.searchNode(selectedBrand);
		}

		// Retrieve the head node of the selected brand
		Node currentBrandNode = foundNode.st.getHead();

		// Iterate through each car node of the selected brand
		while (currentBrandNode != null) {
			// Add the car to the carData list
			carData.add(currentBrandNode.getCar());
			currentBrandNode = currentBrandNode.next;
		}

		// Select the first car in the table view and refresh the view
		carTableView.getSelectionModel().selectFirst();
		carTableView.refresh();
	}

	public Stage getFilters() {
		VBox layout = new VBox();
		HBox h = new HBox(10);
		Button filter = new Button("Filters");
		Button cancel = new Button("Cancel");
		h.getChildren().addAll(filter, cancel);
		filter.setMaxHeight(200);
		cancel.setMaxHeight(200);
		filter.setMaxWidth(230);
		cancel.setMaxWidth(230);
		h.setAlignment(Pos.CENTER);
		h.setPadding(new Insets(15, 15, 15, 15));
		cancel.setOnAction(e -> {
			s2.close();
		});
		filter.setOnAction(event -> {
			String selectedBrand = Main.comboBox.getValue();

			String selectedModel = modelComboBox.getValue();
			String selectedColor = colorChoiceBox.getValue();
			String yearFilter = yearTextField.getText().trim();
			String priceFilter = priceTextField.getText().trim();

			ObservableList<car> filteredCars = FXCollections.observableArrayList();
			brandNode cur = Main.carsList.getFirst();
			for (int i = 0; i < Main.carsList.getSize(); i++) {
				if (cur.getData().getBrand().trim().equalsIgnoreCase(selectedBrand.trim())) {
					break;
				}
				cur = cur.getNext();
			}

			// Iterate through each car in the list
			for (SLLSort.Node current = cur.st.getHead(); current != null; current = current.getNext()) {
				car car2 = current.getCar();

				// Check if the car matches the selected model, color, year, and price filters
				boolean matchModel = selectedModel == null || car2.getModel().equalsIgnoreCase(selectedModel);
				boolean matchColor = selectedColor == null || car2.getColor().equalsIgnoreCase(selectedColor);
				boolean matchYear = yearFilter.isEmpty() || getYear(car2.getYear()) == Integer.parseInt(yearFilter);
				boolean matchPrice = priceFilter.isEmpty() || comparePrice(car2.getPrice(), priceFilter) >= 0;

				// Check for a match based on any selected filter
				if (matchModel && matchColor && matchYear && matchPrice) {
					filteredCars.add(car2);
				}
			}

			// Set the filtered cars in the table view and refresh the view
			carTableView.setItems(filteredCars);
			carTableView.refresh();

		});
		// Create a grid pane to organize the filter components
		GridPane filterGrid = new GridPane();
		filterGrid.setHgap(10);
		filterGrid.setVgap(10);
		filterGrid.setPadding(new Insets(10));
		modelComboBox = new ComboBox<>();
		modelComboBox.getItems().addAll("C200", "C300", "X", "3", "X3", "X5", "X6", "MUSTANG", "RIO", "OPTIMA");
		yearTextField = new TextField();
		priceTextField = new TextField();
		colorChoiceBox = new ChoiceBox<>();
		colorChoiceBox.getItems().addAll("Gray", "Black", "White", "Green", "Blue", "Red", "Metallic Gray",
				"Metallic Silver");

		filterGrid.addRow(0, new Label("Model:"), modelComboBox);
		filterGrid.addRow(1, new Label("Color:"), colorChoiceBox);
		filterGrid.addRow(2, new Label("Year:"), yearTextField);
		filterGrid.addRow(3, new Label("Price:"), priceTextField);

		layout.getChildren().addAll(filterGrid, h);
		s2 = new Stage();
		s2.setTitle("Filters Screen");// There is a title for the window
		// Create scene and set it on the stage
		Scene scene = new Scene(layout, 400, 400);
		s2.setScene(scene);
		s2.show();
		return s2;

	}
// Method to compare two prices
	private int comparePrice(String price1, String price2) {
	    BigDecimal decimal1 = removeKAndParseBigDecimal(price1);
	    BigDecimal decimal2 = removeKAndParseBigDecimal(price2);

	    // Compare the prices using BigDecimal's compareTo method
	    int result = decimal1.compareTo(decimal2);

	    // Check if the prices are equal and have the same string representation
	    if (result == 0 && price1.equals(price2)) {
	        return 0; // Return 0 for exact matches
	    }

	    return result;
	}

	// Method to get the year from a Date object
	private int getYear(Date date) {
		// Create a Calendar instance and set it to the provided date
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// Retrieve the year from the Calendar instance
		return calendar.get(Calendar.YEAR);
	}

	// Method to remove the 'K' character from the price string and parse it as
	// BigDecimal
	private BigDecimal removeKAndParseBigDecimal(String price) {
		// Remove any non-digit or non-decimal point characters from the price string

		String cleanPrice = price.replaceAll("[^0-9.]", "");
		// If the cleaned price is empty, return zero as BigDecimal
		if (cleanPrice.isEmpty()) {
			return BigDecimal.ZERO;
		}
		try {
			// Parse the cleaned price as BigDecimal
			return new BigDecimal(cleanPrice);
		} catch (NumberFormatException ex) {
			// Handle the case where the price is not a valid number
			System.out.println("Invalid price value: " + price);
			return BigDecimal.ZERO;
		}
	}

	private void getAddCart() {
		car selectedCar = carTableView.getSelectionModel().getSelectedItem();
		if (selectedCar != null) {
			// Create a new stage
			Stage addCartStage = new Stage();
			addCartStage.setTitle("Add to Cart");

			Label nameLabel = new Label("Customer Name:");
			TextField nameTextField = new TextField();
			Label mobileLabel = new Label("Customer Mobile:");
			TextField mobileTextField = new TextField();
			Label orderDateLabel = new Label("Order Date:");
			TextField orderDateTextField = new TextField();
			Label orderStatusLabel = new Label("Order Status:");

			// Create a label and set it to the selected car's brand
			Label brandLabel = new Label("Brand:");
			Label selectedBrandLabel = new Label(Main.comboBox.getSelectionModel().getSelectedItem());

			// Create a label and set it to the selected car's details
			Label carLabel = new Label("Car Details:");
			Label selectedCarLabel = new Label(selectedCar.toString());

			// Create a button to add the order to the cart
			Button addToCartButton = new Button("Add to Cart");

			// Add event handler to the button
			addToCartButton.setOnAction(event -> {

				String customerName = nameTextField.getText();
				String customerMobile = mobileTextField.getText();
				String orderDateText = orderDateTextField.getText();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate orderDate = LocalDate.parse(orderDateText, formatter);
				String orderStatus = "InProcess";

				// Create a new order
				Brand b = new Brand(selectedBrandLabel.getText());
				Order order = new Order(customerName, customerMobile, b, selectedCar, orderDate, orderStatus);

				// Add the order to the cart
				Main.carsList.getLast().inProcessOrders.enqueue(order);
				Main.carsList.getLast().inProcessOrders.printList();

				addCartStage.close();

				// Show a confirmation message
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Car Added");
				alert.setHeaderText(null);
				alert.setContentText("Car added to cart: " + selectedCar);
				alert.showAndWait();
			});

			GridPane gridPane = new GridPane();
			gridPane.setPadding(new Insets(10));
			gridPane.setHgap(10);
			gridPane.setVgap(10);
			gridPane.add(nameLabel, 0, 0);
			gridPane.add(nameTextField, 1, 0);
			gridPane.add(mobileLabel, 0, 1);
			gridPane.add(mobileTextField, 1, 1);
			gridPane.add(brandLabel, 0, 2);
			gridPane.add(selectedBrandLabel, 1, 2);
			gridPane.add(carLabel, 0, 3);
			gridPane.add(selectedCarLabel, 1, 3);
			gridPane.add(orderDateLabel, 0, 4);
			gridPane.add(orderDateTextField, 1, 4);
			gridPane.add(orderStatusLabel, 0, 5);
			gridPane.add(new Label("InProcess"), 1, 5);
			gridPane.add(addToCartButton, 0, 6, 2, 1);
			gridPane.setAlignment(Pos.CENTER);
			// Create a scene and set it in the stage
			Scene scene = new Scene(gridPane, 320, 300);
			addCartStage.setScene(scene);

			// Show the stage
			addCartStage.show();
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("No Car Selected");
			alert.setHeaderText(null);
			alert.setContentText("No car selected.");
			alert.showAndWait();
		}
	}

}