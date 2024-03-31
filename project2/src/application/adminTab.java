package application;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import application.Order;
import application.SLLSort.Node;
import application.circularDLLSort.brandNode;
import application.circularDLLSort.brandNode;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class adminTab {
	TabPane tabPane = new TabPane();
	Tab adminTab = new Tab("Admin");
	Tab informat = new Tab("Information Brand");
	Tab summaryReportTab = new Tab("Summary Report"); // New tab for summary report
	static TableView<Order> queueTableView;
	TableView<Order> stackTableView = new TableView<>();
	static ObservableList<Order> q = FXCollections.observableArrayList();
	private ObservableList<Order> stack = FXCollections.observableArrayList();

	Button last10Button = new Button("Last10"); // Button to show last 10 sold cars
	Button pro = new Button("InProcess"); // Button to show last 10 sold cars
	Button cancelButton = new Button("Cancel");
	Button requeueButton = new Button("Requeue");
	Button ng4 = new Button();
	Stage s;
	Stack finishedOrdersStack = new Stack(); // Create a new stack to store the orders

	public Stage getTabe() {

		q.clear();
		// Clear the q list to start with an empty list

		Queue inProcessOrders = Main.carsList.getLast().inProcessOrders;
		// Get the in-process orders queue from the last brand in the carsList

		int size = inProcessOrders.getSize();
		// Get the size of the in-process orders queue

		for (int i = 0; i < size; i++) {
			// Iterate through the in-process orders queue
			Order order = (Order) inProcessOrders.dequeue();
			// Dequeue an order from the in-process orders queue
			q.add(order);
			// Add the order to the q list
			inProcessOrders.enqueue(order);
			// Re-enqueue the order to maintain the queue order
		}

		// The above loop dequeues and re-enqueues the orders from the in-process orders
		// queue to the q list
		TableColumn<Order, String> customerName = new TableColumn<>("Customer Name");
		customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
		TableColumn<Order, String> customerMobile = new TableColumn<>("Customer Mobile");
		customerMobile.setCellValueFactory(new PropertyValueFactory<>("customerMobile"));
		TableColumn<Order, String> brandColumn = new TableColumn<>("Brand");
		brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
		TableColumn<Order, String> modelColumn = new TableColumn<>("Model");
		modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
		TableColumn<Order, String> yearColumn = new TableColumn<>("Year");
		yearColumn.setCellValueFactory(cellData -> {
			Order car = cellData.getValue();
			Date year = car.getYear();
			DateFormat formatter = new SimpleDateFormat("yyyy");
			String yearString = formatter.format(year);
			return new SimpleStringProperty(yearString);
		});
		TableColumn<Order, String> colorColumn = new TableColumn<>("Color");
		colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
		TableColumn<Order, String> priceColumn = new TableColumn<>("Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		TableColumn<Order, String> dateColumn = new TableColumn<>("Order Date");
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
		TableColumn<Order, String> order7 = new TableColumn<>("Order Status");
		order7.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));

		queueTableView = new TableView<>();
		queueTableView.setItems(q);
		stackTableView.setItems(stack);
		// Create table view and set columns

		queueTableView.getColumns().addAll(customerName, customerMobile, brandColumn, modelColumn, yearColumn,
				colorColumn, priceColumn, dateColumn, order7);
		queueTableView.setItems(q);

		adminTab.setClosable(false);

		tabPane.getTabs().add(adminTab);

		// Create the layout for the Admin tab
		VBox adminLayout = new VBox();
		adminTab.setContent(adminLayout);
		// brandTab .setContent(brand.r());
		TableColumn<Order, String> customerName2 = new TableColumn<>("Customer Name");
		customerName2.setCellValueFactory(new PropertyValueFactory<>("customerName"));
		TableColumn<Order, String> customerMobile2 = new TableColumn<>("Customer Mobile");
		customerMobile2.setCellValueFactory(new PropertyValueFactory<>("customerMobile"));
		TableColumn<Order, String> brandColumn2 = new TableColumn<>("Brand");
		brandColumn2.setCellValueFactory(new PropertyValueFactory<>("brand"));
		TableColumn<Order, String> modelColumn2 = new TableColumn<>("Model");
		modelColumn2.setCellValueFactory(new PropertyValueFactory<>("model"));
		TableColumn<Order, String> yearColumn2 = new TableColumn<>("Year");
		yearColumn2.setCellValueFactory(cellData -> {
			Order order2 = cellData.getValue();
			Date year = order2.getYear();
			DateFormat formatter = new SimpleDateFormat("yyyy");
			String yearString = formatter.format(year);
			return new SimpleStringProperty(yearString);
		});
		TableColumn<Order, String> colorColumn2 = new TableColumn<>("Color");
		colorColumn2.setCellValueFactory(new PropertyValueFactory<>("color"));
		TableColumn<Order, String> priceColumn2 = new TableColumn<>("Price");
		priceColumn2.setCellValueFactory(new PropertyValueFactory<>("price"));
		TableColumn<Order, String> dateColumn2 = new TableColumn<>("Order Date");
		dateColumn2.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
		TableColumn<Order, LocalDate> statusColumn2 = new TableColumn<>("Order Status");
		statusColumn2.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));

		stackTableView = new TableView<>();
		// Create a new TableView for the stack
		stack.clear();
		// Clear the stack to start with an empty stack

		while (!Main.carsList.getLast().finishedOrders.isEmpty()) {
			// Iterate until the finished orders stack is empty
			Order soldCar = (Order) Main.carsList.getLast().finishedOrders.pop();
			// Pop an order from the finished orders stack
			stack.add(soldCar);
			// Add the popped order to the stack list
			finishedOrdersStack.push(soldCar);
			// Push the order onto the new stack
		}

		// The above loop pops orders from the finished orders stack, adds them to the
		// stack list,
		// and pushes them onto the new stack (finishedOrdersStack)

		while (!finishedOrdersStack.isEmpty()) {
			// Iterate until the new stack (finishedOrdersStack) is empty
			Order soldCar = (Order) finishedOrdersStack.pop();
			// Pop an order from the new stack
			Main.carsList.getLast().finishedOrders.push(soldCar);
			// Push the order back to the original finished orders stack
		}

		stackTableView.setItems(stack);
		// Set the items of the stackTableView to the stack list
		stackTableView.getColumns().addAll(customerName2, customerMobile2, brandColumn2, modelColumn2, yearColumn2,
				colorColumn2, priceColumn2, dateColumn2, statusColumn2);

		cancelButton.setOnAction(event -> {
			if (!inProcessOrders.isEmpty()) {
				Order order3 = (Order) inProcessOrders.dequeue();
				cancelOrder(order3);
				q.remove(order3); // Remove the canceled order from the q list
				queueTableView.setItems(q); // Update the table view data
			}

		});

		pro.setOnAction(event -> {

			if (!inProcessOrders.isEmpty()) {
				Order order1 = (Order) inProcessOrders.dequeue();
				Main.carsList.getLast().finishedOrders.push(order1);
				order1.setOrderStatus("Finished");
				q.remove(order1); // Remove the processed order from the q list
				stack.add(order1);
				queueTableView.setItems(q); // Update the table view data
				stackTableView.setItems(stack);
			}
		});

		requeueButton.setOnAction(e -> {
			if (!inProcessOrders.isEmpty()) {
				Order order2 = (Order) inProcessOrders.dequeue();
				q.remove(order2); // Remove the processed order from the q list
				inProcessOrders.enqueue(order2);
				q.add(order2); // Remove the processed order from the q list
				queueTableView.setItems(q); // Update the table view data
			}
		});

		last10Button.setOnAction(e -> {
			reportLastTenSoldCars();
		});
		VBox summaryReportLayout = new VBox(10);
		summaryReportTab.setContent(summaryReportLayout);
		TableView<summaryReport> summaryTableView = new TableView<>();

		TableColumn<summaryReport, String> brandColumnSummary = new TableColumn<>("Brand");
		brandColumnSummary.setCellValueFactory(new PropertyValueFactory<>("brand"));

		TableColumn<summaryReport, Double> pricehColumnSummary = new TableColumn<>("Higher Price");
		pricehColumnSummary.setCellValueFactory(new PropertyValueFactory<>("higherPrice"));
		TableColumn<summaryReport, Double> pricelColumnSummary = new TableColumn<>("Lower Price");
		pricelColumnSummary.setCellValueFactory(new PropertyValueFactory<>("lowerPrice"));

		TableColumn<summaryReport, String> higherModelColumnSummary = new TableColumn<>("Higher Model");
		higherModelColumnSummary.setCellValueFactory(new PropertyValueFactory<>("higherModelNumber"));

		TableColumn<summaryReport, String> lowerModelColumnSummary = new TableColumn<>("Lower Model");
		lowerModelColumnSummary.setCellValueFactory(new PropertyValueFactory<>("lowerModelNumber"));

		// Add the columns to the summary table view
		summaryTableView.getColumns().addAll(brandColumnSummary, pricehColumnSummary, pricelColumnSummary,
				higherModelColumnSummary, lowerModelColumnSummary);

		ObservableList<summaryReport> summaryData = FXCollections.observableArrayList();
		// Create an ObservableList to store the summary reports



		
		Set<String> processedBrands = new HashSet<>();
		// Create a set to keep track of processed brands

		

		brandNode currentBrandNode = Main.carsList.getFirst();
		// Start from the first brand node in the circular linked list

		while (currentBrandNode != null) {
		    String brand = currentBrandNode.getData().getBrand();

		    SLLSort currentSLL = currentBrandNode.getSt();
		    // Get the SLLSort associated with the current brand node

		    SLLSort.Node currentCarNode = currentSLL.getHead();
		    // Start from the head of the circular linked list of cars

		    while (currentCarNode != null) {
		        // Iterate through each car node in the circular linked list of cars

		        summaryReport report = new summaryReport(currentBrandNode);
		        // Create a new summary report for the current brand node
		        report.setBrand(brand);
		        report.setHigherPrice(Main.carsList.getHighestPrice(brand));
		        report.setLowerPrice(Main.carsList.getLowestPrice(brand));
		        report.setHigherModelNumber(currentSLL.getHighestModelNumber());
		        report.setLowerModelNumber(currentSLL.getLowestModelNumber());
		        // Set the summary report data based on the current brand node and its associated SLLSort

		        if (!summaryDataContainsBrand(summaryData, brand)) {
		            // Check if the summary data already contains a report with the same brand
		            summaryData.add(report);
		        }

		        currentCarNode = currentCarNode.getNext();
		        // Move to the next car node in the circular linked list

		        if (currentCarNode == currentSLL.getHead()) {
		            break;
		            // Exit the inner loop if we reach the end of the circular linked list
		        }
		    }

		    processedBrands.add(brand);
		    // Add the brand to the set of processed brands

		    currentBrandNode = currentBrandNode.getNext();
		    // Move to the next brand node in the circular linked list

		    if (currentBrandNode == Main.carsList.getFirst()) {
		        break;
		        // Exit the loop if we reach the first brand node in the circular linked list
		    }
		}
		
		// Set the data to the summary table view
		summaryTableView.setItems(summaryData);
		// Add the summary table view to the summary report layout
		Label summary = new Label("Summary Report");
		summary.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 20));// type of The Text

		summary.setPadding(new Insets(10, 15, 10, 10));// To determine the dimensions of the sides
		Button bye = new Button();
		bye.setOnAction(e -> {
			System.exit(0);
		});
		bye.setAlignment(Pos.CENTER);
		Image image10 = new Image("photo-time-to-say-goodbye-message.jpg");
		ImageView imageView10 = new ImageView(image10);
		bye.setGraphic(imageView10);
		imageView10.setFitWidth(200);
		imageView10.setFitHeight(180);
		summaryReportLayout.setPadding(new Insets(10, 15, 10, 10));// To determine the dimensions of the sides
		summaryReportLayout.setAlignment(Pos.CENTER);
		summaryReportLayout.getChildren().addAll(summary, summaryTableView, bye);
		getBrandScreen();
		getInformationCar();
		// tabPane.getTabs().add(brandTab);
		tabPane.getTabs().add(summaryReportTab);
		HBox h = new HBox(10);

		ng4.setOnAction(e -> {
			Main.carsList.saveOrdersToFile2(Main.carsList.getFirst().getFinishedOrders(),
					Main.carsList.getFirst().getInProcessOrders());
		});
		Image image6 = new Image("saveFile.png");
		ImageView imageView4 = new ImageView(image6);
		ng4.setGraphic(imageView4);
		imageView4.setFitWidth(60);
		imageView4.setFitHeight(60);
		requeueButton.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 16));// type of The Text
		pro.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 16));// type of The Text
		cancelButton.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 16));// type of The Text
		last10Button.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 16));// type of The Text

		Label qulab = new Label("Queue Information");
		Label staLab = new Label("Stack Information");
		Color color = new Color(0.75, 0.125, 0.50, 1);

		qulab.setTextFill(color);
		staLab.setTextFill(color);
		qulab.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 20));// type of The Text
		staLab.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 20));// type of The Text

		requeueButton.setPadding(new Insets(10, 15, 10, 10));// To determine the dimensions of the sides
		pro.setPadding(new Insets(10, 15, 10, 10));// To determine the dimensions of the sides
		cancelButton.setPadding(new Insets(10, 15, 10, 10));// To determine the dimensions of the sides
		last10Button.setPadding(new Insets(10, 15, 10, 10));// To determine the dimensions of the sides
		qulab.setPadding(new Insets(10, 15, 10, 10));// To determine the dimensions of the sides
		staLab.setPadding(new Insets(10, 15, 10, 10));// To determine the dimensions of the sides
		staLab.setAlignment(Pos.CENTER);
		qulab.setAlignment(Pos.CENTER);
		requeueButton.setStyle("-fx-background-color: white");// To determine the Color background for Button
		pro.setStyle("-fx-background-color: white");// To determine the Color background for Button
		cancelButton.setStyle("-fx-background-color: white");// To determine the Color background for Button
		deleteBrandButton.setStyle("-fx-background-color: white");// To determine the Color background for Button
		last10Button.setStyle("-fx-background-color: white");// To determine the Color background for Button
		requeueButton.setMaxWidth(120);
		requeueButton.setMaxHeight(50);
		pro.setMaxWidth(120);
		pro.setMaxHeight(50);
		cancelButton.setMaxWidth(120);
		cancelButton.setMaxHeight(50);
		last10Button.setMaxWidth(120);
		last10Button.setMaxHeight(40);
		queueTableView.setPadding(new Insets(10, 15, 10, 10));// To determine the dimensions of the sides
		stackTableView.setPadding(new Insets(10, 15, 10, 10));// To determine the dimensions of the sides
		h.setAlignment(Pos.CENTER);
		Button cancel2 = new Button();
		Image image = new Image("homePage.jpg");
		ImageView imageView = new ImageView(image);
		cancel2.setGraphic(imageView);
		imageView.setFitWidth(140);
		imageView.setFitHeight(70);
		cancel2.setMaxWidth(100);
		cancel2.setMaxHeight(100);
		cancel2.setStyle("-fx-background-color: white;");
		ng4.setStyle("-fx-background-color: white;");
h.setPadding(new Insets(10, 15, 10, 10));
		
		cancel2.setOnAction(e->{
			s.close();
		});
		h.getChildren().addAll(requeueButton, pro, cancelButton, last10Button, ng4,cancel2);
		adminLayout.getChildren().addAll(qulab, queueTableView, staLab, stackTableView, h);
		adminLayout.setPadding(new Insets(10, 15, 10, 10));// To determine the dimensions of the sides
		Scene scene = new Scene(tabPane, 850, 700);
		s = new Stage();
		// Set the Scene to the primaryStage
		s.setScene(scene);
		s.show();
		return s;
	}
	private boolean summaryDataContainsBrand(List<summaryReport> summaryData, String brand) {
	    for (summaryReport report : summaryData) {
	        if (report.getBrand().equals(brand)) {
	            return true;
	        }
	    }
	    return false;
	}

	public void cancelOrder(Order order) {
		if (!Main.carsList.getLast().getInProcessOrders().isEmpty()) {
			Main.carsList.getLast().getInProcessOrders().dequeue(); // Remove from the queue

			// Show alert dialog
			String message = "Order canceled: " + order.toString();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Order Canceled");
			alert.setHeaderText(null);
			alert.setContentText(message);
			alert.showAndWait();

			// Perform any necessary actions for a canceled order
			// For example, you can remove the order from the TableView and update the UI
			q.remove(order); // Remove from the queueTableView
			queueTableView.setItems(q); // Update the queueTableView
		}

	}

	public void reportLastTenSoldCars() {
		Stage stage = new Stage();
		TableView<Order> tableView2 = new TableView<>();

		TableColumn<Order, String> customerName3 = new TableColumn<>("Customer Name");
		customerName3.setCellValueFactory(new PropertyValueFactory<>("customerName"));
		TableColumn<Order, String> customerMobile3 = new TableColumn<>("Customer Mobile");
		customerMobile3.setCellValueFactory(new PropertyValueFactory<>("customerMobile"));
		TableColumn<Order, String> brandColumn3 = new TableColumn<>("Brand");
		brandColumn3.setCellValueFactory(new PropertyValueFactory<>("brand"));
		TableColumn<Order, String> modelColumn3 = new TableColumn<>("Model");
		modelColumn3.setCellValueFactory(new PropertyValueFactory<>("model"));
		TableColumn<Order, String> yearColumn3 = new TableColumn<>("Year");
		yearColumn3.setCellValueFactory(cellData -> {
			Order order = cellData.getValue();
			Date year = order.getYear();
			DateFormat formatter = new SimpleDateFormat("yyyy");
			String yearString = formatter.format(year);
			return new SimpleStringProperty(yearString);
		});
		TableColumn<Order, String> colorColumn3 = new TableColumn<>("Color");
		colorColumn3.setCellValueFactory(new PropertyValueFactory<>("color"));
		TableColumn<Order, String> priceColumn3 = new TableColumn<>("Price");
		priceColumn3.setCellValueFactory(new PropertyValueFactory<>("price"));
		TableColumn<Order, String> dateColumn3 = new TableColumn<>("Order Date");
		dateColumn3.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
		TableColumn<Order, LocalDate> statusColumn3 = new TableColumn<>("Order Status");
		statusColumn3.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));

		// Add other columns as needed

		// Set the table columns to the table view
		tableView2.getColumns().addAll(customerName3, customerMobile3, brandColumn3, modelColumn3, yearColumn3,
				colorColumn3, priceColumn3, dateColumn3, statusColumn3);

		// Create an observable list to store the sold cars
		ObservableList<Order> soldCars = FXCollections.observableArrayList();
		// Create an ObservableList to store the sold cars

		Stack last10 = new Stack();
		// Create a stack to store the last 10 finished orders

		for (int i = 0; i < 10; i++) {
			if (!Main.carsList.getLast().finishedOrders.isEmpty()) {
				// Check if there are remaining finished orders in the last brand node's
				// finished orders stack

				Order o = Main.carsList.getLast().finishedOrders.pop();
				// Pop the topmost finished order from the stack

				last10.push(o);
				// Push the finished order onto the last10 stack

				soldCars.add(o);
				// Add the finished order to the sold cars list
			}
		}

		while (!last10.isEmpty()) {
			// Re-add the finished orders back to the last brand node's finished orders
			// stack

			Order o = last10.pop();
			// Pop the topmost finished order from the last10 stack

			Main.carsList.getLast().finishedOrders.push(o);
			// Push the finished order back onto the last brand node's finished orders stack
		}
		// Set the sold cars to the table view
		tableView2.setItems(soldCars);
		Label lastLab = new Label("Last 10");
		lastLab.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 20));// type of The Text

		lastLab.setPadding(new Insets(10, 15, 10, 10));// To determine the dimensions of the sides
		// Create a layout for the stage
		VBox layout = new VBox(10);
		Button lastStage = new Button("Cancel");
		lastStage.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 18));// type of The Text
		layout.setPadding(new Insets(10, 15, 10, 10));// To determine the dimensions of the sides
		lastStage.setPadding(new Insets(10, 15, 10, 10));// To determine the dimensions of the sides
		lastStage.setMaxHeight(100);
		lastStage.setMaxWidth(100);
		lastStage.setOnAction(e -> {
			stage.close();
		});
		layout.setAlignment(Pos.CENTER);
		layout.getChildren().addAll(lastLab, tableView2, lastStage);

		// Set the layout to the stage
		stage.setScene(new Scene(layout));
		stage.setTitle("Last 10");
		stage.show();
	}

	private Label BrandLabel, newBrand, addBrand;
	static TextField newBrandField, addL;
	static TextField BrandField;
	static Label massege;
	public static Button addBrandButton, updateBrandButton, deleteBrandButton, searchBrandButton;
	static Button ng, pg;
	public static brandNode searchednode;
	static Button next;
	private GridPane g = new GridPane();
	Stage location;
	ImageView imageView;
	Tab brandTab = new Tab("Brand");

	public void getBrandScreen() {
		tabPane.getTabs().add(brandTab);
		HBox h2 = new HBox(10);
		ng = new Button();
		Image image2 = new Image("nextst.jpg");
		ImageView imageView2 = new ImageView(image2);
		ng.setGraphic(imageView2);
		pg = new Button();
		Image image3 = new Image("previosst.jpg");
		ImageView imageView3 = new ImageView(image3);
		pg.setGraphic(imageView3);
		// Image image = new Image("109-1090359_next-page-button.png");
		// ImageView imageView = new ImageView(image);

		h2.getChildren().addAll(pg, ng);
		pg.setOnAction(e -> {
			Main.carsList.backwardButtonActionPerformed();

		});
		ng.setOnAction(e -> {
			Main.carsList.forward();
		});
		h2.setAlignment(Pos.CENTER);
		// create a button and set its graphic to the image view

		BrandLabel = new Label("Brand:");
		newBrand = new Label("New Brand");
		addBrand = new Label("Add Brand:");
		addL = new TextField();
		BrandField = new TextField();
		Button add = new Button("Click Add");
		add.setVisible(false);
		Button up = new Button("click Updat");
		up.setVisible(false);
		newBrandField = new TextField();
		addBrandButton = new Button("Add Brand");
		updateBrandButton = new Button("Update Brand");
		deleteBrandButton = new Button("Delete Brand");
		searchBrandButton = new Button("Search Brand");
		addL.setVisible(false);
		addBrand.setVisible(false);

		newBrand.setVisible(false);
		newBrandField.setVisible(false);

		massege = new Label("");
		massege.setStyle("-fx-text-fill:red");

		HBox h = new HBox(5);
		h.setAlignment(Pos.CENTER);

		g.add(BrandLabel, 0, 1);
		g.add(BrandField, 1, 1);
		g.add(newBrand, 0, 2);
		g.add(newBrandField, 1, 2);
		g.add(up, 2, 2);
		g.add(add, 2, 3);
		g.add(addL, 1, 3);
		g.add(addBrand, 0, 3);

		g.add(searchBrandButton, 1, 4);

		g.add(addBrandButton, 1, 5);

		g.add(updateBrandButton, 1, 6);
		g.add(deleteBrandButton, 1, 7);
		g.add(h2, 1, 8);

		h.setAlignment(Pos.BOTTOM_LEFT);
		h.getChildren().addAll(searchBrandButton, addBrandButton, updateBrandButton, deleteBrandButton);
		g.add(h, 1, 10);
		// g.add(next, 3, 12);

		g.setHgap(10);
		g.setVgap(10);

		addBrand.setVisible(false);
		addL.setVisible(false);

		searchBrandButton.setMaxWidth(190);
		searchBrandButton.setMaxHeight(100);
		addBrandButton.setMaxWidth(190);
		addBrandButton.setMaxHeight(100);
		updateBrandButton.setMaxWidth(190);
		updateBrandButton.setMaxHeight(100);
		deleteBrandButton.setMaxWidth(190);
		deleteBrandButton.setMaxHeight(100);

		imageView2.setFitWidth(60);
		imageView2.setFitHeight(60);
		imageView3.setFitWidth(60);
		imageView3.setFitHeight(60);

		BrandField.setText(Main.carsList.getFirst().data.getBrand());
		addBrandButton.setOnAction(e -> {
			addBrand.setVisible(true);
			addL.setVisible(true);
			add.setVisible(true);

			System.out.println(addL.getText());
			add.setOnAction(ev -> {
				Main.carsList.insert(addL.getText());

			});

		});
		updateBrandButton.setOnAction(e -> {
			newBrand.setVisible(true);
			newBrandField.setVisible(true);
			up.setVisible(true);

			up.setOnAction(ev -> {
				Main.carsList.update(BrandField.getText(), newBrandField.getText());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Update Brand");
				alert.setHeaderText(null);
				alert.setContentText("Update Brand Succesfully");
				alert.showAndWait();

			});

		});
		deleteBrandButton.setOnAction(e -> {
			System.out.println(BrandField.getText());
			Main.carsList.delete(BrandField.getText());
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Delete Brand");
			alert.setHeaderText(null);
			alert.setContentText("Delete Brand Succesfully");
			alert.showAndWait();

		});
		searchBrandButton.setOnAction(e -> {
			Main.carsList.search3(BrandField.getText());
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Search Brand");
			alert.setHeaderText(null);
			alert.setContentText("Search Brand Succesfully");
			alert.showAndWait();

		});
		BrandLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 13));// type of The Text
		searchBrandButton.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));// type of The Text
		newBrand.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));// type of The Text
		addBrandButton.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));// type of The Text
		addBrand.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));// type of The Text
		updateBrandButton.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));// type of The Text

		deleteBrandButton.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));// type of The Text
		BrandLabel.setPadding(new Insets(10, 15, 10, 10));// To determine the dimensions of the sides

		updateBrandButton.setStyle("-fx-background-color: white");// To determine the Color background for Button
		searchBrandButton.setStyle("-fx-background-color: white");// To determine the Color background for Button
		addBrandButton.setStyle("-fx-background-color: white");// To determine the Color background for Button
		deleteBrandButton.setStyle("-fx-background-color: white");// To determine the Color background for Button

		updateBrandButton.setDisable(false);// to be able to press it

		g.setStyle("-fx-background-color:#D3D3D3");// To determine the Color background
		g.setAlignment(Pos.CENTER);
		brandTab.setClosable(false);
		brandTab.setContent(g);

	}

	public static Button getNg() {
		return ng;
	}

	public static TextField getBrandField() {
		return BrandField;
	}

	public static void setBrandField(TextField brandField) {
		BrandField = brandField;
	}

	public void setNg(Button ng) {

		this.ng = ng;

	}

	public static Button getPg() {
		return pg;
	}

	public void setPg(Button pg) {
		this.pg = pg;
	}

	public static TextField getBrandField1() {
		// TODO Auto-generated method stub
		return BrandField;
	}

	private Button addCarButton, updateCarButton, deleteCarButton, searchCarButton, ng2;
	private Label carLabel, modelLabel, yearLabel, colorLabel, priceLabel;
	static TextField modelField, yearField, colorField, priceField;
	private GridPane g2 = new GridPane();
	private Button next2;
	Stage martry;
	Scene martyrs;

	public void getInformationCar() {
		tabPane.getTabs().add(informat);

		ng2 = new Button();
		Image image2 = new Image("nextst.jpg");
		ImageView imageView2 = new ImageView(image2);
		ng2.setGraphic(imageView2);

		ng2.setOnAction(e -> {
			Main.carsList.search3(BrandField.getText()).st.nextSLL();
		});

		carLabel = new Label("Car Record:");
		modelLabel = new Label("Model:");
		modelField = new TextField();
		yearLabel = new Label("Year:");
		yearField = new TextField();
		colorLabel = new Label("Color:");
		colorField = new TextField();
		priceLabel = new Label("Price:");
		priceField = new TextField();

		addCarButton = new Button("Add Car");
		updateCarButton = new Button("Update Car");
		deleteCarButton = new Button("Delete Car");
		searchCarButton = new Button("Search Car");
		modelField.setText(Main.carsList.getFirst().st.getHead().carobj.model);

		HBox h = new HBox(5);
		g2.add(carLabel, 1, 0);

		g2.add(modelLabel, 0, 1);
		g2.add(modelField, 1, 1);

		g2.add(yearLabel, 0, 2);
		g2.add(yearField, 1, 2);

		g2.add(colorLabel, 0, 3);
		g2.add(colorField, 1, 3);

		g2.add(priceLabel, 0, 4);
		g2.add(priceField, 1, 4);

		h.setAlignment(Pos.BOTTOM_LEFT);
		h.getChildren().addAll(searchCarButton, addCarButton, updateCarButton, deleteCarButton);
		g2.add(ng2, 1, 8);
		g2.add(h, 1, 10);

		g2.setHgap(10);
		g2.setVgap(10);

		modelField.setPrefSize(2, 2);
		colorField.setPrefSize(2, 2);
		priceField.setPrefSize(2, 2);
		yearField.setPrefSize(2, 2);

		searchCarButton.setMaxWidth(180);
		searchCarButton.setMaxHeight(100);
		updateCarButton.setMaxWidth(180);
		updateCarButton.setMaxHeight(100);
		addCarButton.setMaxWidth(180);
		addCarButton.setMaxHeight(100);
		deleteCarButton.setMaxWidth(180);
		deleteCarButton.setMaxHeight(100);

		priceField.setVisible(false);
		colorField.setVisible(false);
		yearField.setVisible(false);

		deleteCarButton.setOnAction(e -> {

			if (modelField.getText().equals("") || modelField.getText() == null) {
				// Check if the model field is empty or null
				// No action required if the model field is empty
			} else {
				SLLSort.Node now = Main.carsList.search3(BrandField.getText()).getSt().searchByModel2(modelField.getText());
				// Search for the node with the given model in the first brand node's linked
				// list

				if (now == null) {
					// If the node is not found, no action required
				} else {
					modelField.clear();
					// Clear the model field

					colorField.clear();
					// Clear the color field

					yearField.clear();
					// Clear the year field

					priceField.clear();
					// Clear the price field

					Main.carsList.getFirst().st.delete(now.carobj.model);
					// Delete the node with the given model from the first brand node's linked list
					System.out.println("Car successfully removed.");
					// Print a success message to the console
				}
			}

		});

		searchCarButton.setOnAction(e -> {
			String model = modelField.getText();
			Main.carsList.printList2();
			Node node = Main.carsList.search3(BrandField.getText()).st.searchByModel2(model);
			colorField.setVisible(true);
			yearField.setVisible(true);
			priceField.setVisible(true);

			if (node != null) {
				colorField.setText(node.carobj.getColor());
				modelField.setText(node.carobj.model);
				// Extract only the year from the date
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
				String formattedYear = dateFormat.format(node.carobj.getYear());
				yearField.setText(formattedYear);

				// Set the price field
				priceField.setText(node.carobj.getPrice());
				System.out.println("Done");
			}

			System.out.println("Done");

		});
		updateCarButton.setOnAction(e -> {
			getupdate();
		});
		addCarButton.setOnAction(e -> {
			getAdd();
		});
		cancel = new Button();
		carLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 18));// type of The Text
		modelLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 14));// type of The Text
		yearLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 14));// type of The Text
		priceLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 14));// type of The Text
		colorLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 14));
		searchCarButton.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));// type of The Text
		addCarButton.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));// type of The Text
		updateCarButton.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));// type of The Text
		deleteCarButton.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));
		cancel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));

		carLabel.setPadding(new Insets(10, 15, 10, 10));// To determine the dimensions of the sides
		modelLabel.setPadding(new Insets(10, 15, 10, 10));
		colorLabel.setPadding(new Insets(10, 15, 10, 10));
		yearLabel.setPadding(new Insets(10, 15, 10, 10));
		priceLabel.setPadding(new Insets(10, 15, 10, 10));// To determine the dimensions of the sides

		imageView2.setFitWidth(60);
		imageView2.setFitHeight(60);
		// imageView2.setFitHeight(60);

		searchCarButton.setStyle("-fx-background-color: white");// To determine the Color background for Button
		cancel.setStyle("-fx-background-color: white");// To determine the Color background for Button
		addCarButton.setStyle("-fx-background-color: white");// To determine the Color background for Button
		updateCarButton.setStyle("-fx-background-color: white");// To determine the Color background for Button
		deleteCarButton.setStyle("-fx-background-color: white");// To determine the Color background for Button

		g2.setStyle("-fx-background-color:#D3D3D3");// To determine the Color background
		g2.setAlignment(Pos.CENTER);
		informat.setContent(g2);

	}

	static TextField modelField2, colorField2, yearField2, priceField2;
	private Label modellab2, colorlab2, yearlab2, pricelab2;

	static TextField modelField3, colorField3, yearField3, priceField3;
	private Label modellab3, colorlab3, yearlab3, pricelab3;

	private Label addLabel, updateLabel, deleteLabel;
	private Stage add, delete, update;
	private Button cancel, cancel2;
	private GridPane g3 = new GridPane();
	private GridPane g0 = new GridPane();

	public void getAdd() {
		Button added = new Button("Add");
		addLabel = new Label("Add Car");
		modellab2 = new Label("Model:");
		modelField2 = new TextField();
		colorlab2 = new Label("Color:");
		colorField2 = new TextField();
		yearlab2 = new Label("Year:");
		yearField2 = new TextField();
		pricelab2 = new Label("Price:");
		priceField2 = new TextField();

		cancel = new Button("Cancel");

		g3.add(addLabel, 1, 0);

		g3.add(modellab2, 0, 1);
		g3.add(modelField2, 1, 1);

		g3.add(colorlab2, 0, 2);
		g3.add(colorField2, 1, 2);

		g3.add(yearlab2, 0, 3);
		g3.add(yearField2, 1, 3);

		g3.add(pricelab2, 0, 4);
		g3.add(priceField2, 1, 4);

		g3.add(cancel, 1, 8);
		g3.add(added, 2, 8);
		g3.setHgap(5);
		g3.setVgap(5);

		cancel.setMaxWidth(80);
		cancel.setMaxHeight(80);
		addLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 18));// type of The Text
		modelField2.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 14));// type of The Text
		colorField2.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 14));// type of The Text
		yearField2.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 14));// type of The Text
		priceField2.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 14));// type of The Text
		cancel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));

		modellab2.setPadding(new Insets(10, 15, 10, 10));// To determine the dimensions of the sides
		colorField2.setPadding(new Insets(10, 15, 10, 10));
		yearField2.setPadding(new Insets(10, 15, 10, 10));
		priceField2.setPadding(new Insets(10, 15, 10, 10));

		cancel.setStyle("-fx-background-color: white");// To determine the Color background for Button
		cancel.setOnAction(e -> {
			add.close();
		});
		added.setOnAction(e -> {
			SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
			String formattedYear = yearField2.getText();
			Date year = null;
			try {
				year = yearFormat.parse(formattedYear);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}

			Main.carsList.search3(BrandField.getText()).st.insert(modelField2.getText(), year, colorField2.getText(),
					priceField2.getText());
			System.out.println("SUCCESS");
		});
		g3.setStyle("-fx-background-color:#D3D3D3");// To determine the Color background
		g3.setAlignment(Pos.CENTER);
		Scene s = new Scene(g3, 350, 300);
		add = new Stage();
		add.setTitle("Add Car Screen");// There is a title for the window
		add.setScene(s);
		add.show();

	}

	public void getupdate() {
		updateLabel = new Label("UpdateCar");
		modellab3 = new Label("Model:");
		modelField3 = new TextField();
		colorlab3 = new Label("Color:");
		colorField3 = new TextField();
		yearlab3 = new Label("Year:");
		yearField3 = new TextField();
		pricelab3 = new Label("Price:");
		priceField3 = new TextField();

		cancel2 = new Button("Cancel");
		Button upd = new Button("Update");
		g0.add(updateLabel, 1, 0);

		g0.add(modellab3, 0, 1);
		g0.add(modelField3, 1, 1);

		g0.add(colorlab3, 0, 2);
		g0.add(colorField3, 1, 2);

		g0.add(yearlab3, 0, 3);
		g0.add(yearField3, 1, 3);

		g0.add(pricelab3, 0, 4);
		g0.add(priceField3, 1, 4);

		g0.add(cancel2, 1, 8);
		g0.add(upd, 2, 8);
		g0.setHgap(5);
		g0.setVgap(5);

		cancel2.setMaxWidth(70);
		cancel2.setMaxHeight(70);
		updateLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 18));// type of The Text
		modelField3.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 14));// type of The Text
		colorField3.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 14));// type of The Text
		yearField3.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 14));// type of The Text
		priceField3.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 14));// type of The Text
		cancel2.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));

		modellab3.setPadding(new Insets(10, 15, 10, 10));// To determine the dimensions of the sides
		colorField3.setPadding(new Insets(10, 15, 10, 10));
		yearField3.setPadding(new Insets(10, 15, 10, 10));
		priceField3.setPadding(new Insets(10, 15, 10, 10));

		cancel2.setStyle("-fx-background-color: white");// To determine the Color background for Button
		cancel2.setOnAction(e -> {

			update.close();
		});
		upd.setOnAction(e -> {
			String formattedYear2 = yearField3.getText();
			Date year = null;
			try {
				SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
				year = yearFormat.parse(formattedYear2);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}

			Main.carsList.search3(BrandField.getText()).st.updateByModel(modelField.getText(),
					new car(modelField3.getText(), year, colorField3.getText(), priceField3.getText()));

			System.out.println("Successfully");
		});
		g0.setStyle("-fx-background-color:#D3D3D3");// To determine the Color background
		g0.setAlignment(Pos.CENTER);
		Scene s = new Scene(g0, 370, 300);
		update = new Stage();
		update.setTitle("UpdateCar Screen");// There is a title for the window
		update.setScene(s);
		update.show();

	}

}
