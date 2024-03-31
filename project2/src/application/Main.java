package application;
	


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import javax.swing.filechooser.FileNameExtensionFilter;

import application.Main.myEvent;

import application.circularDLLSort.brandNode;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;


public class Main extends Application {
	Button reClient = new Button("Client");
	Button reAdmin = new Button("Admin");
	Stage s = new Stage();
	Stage s2 = new Stage();
	static circularDLLSort carsList;
	static ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList());

	adminTab tab;

	@Override
	public void start(Stage primaryStage) {
		try {
			// Create the main page layout
			Scene scene = new Scene(getMainPage(), 700, 700);

			primaryStage.setScene(scene);
			primaryStage.setTitle("Car Agency System");
			primaryStage.show();
			// Add event handlers for the buttons
			reClient.addEventHandler(ActionEvent.ACTION, new myEvent());
			reAdmin.addEventHandler(ActionEvent.ACTION, new myEvent());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method to create the main page layout
	private VBox getMainPage() {
		HBox h = new HBox(15);
		VBox spane = new VBox(15);
		Label title = new Label("Car Agency System");// The Main Screen title

		Font f = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 35);// type of The Text Main Screen
																						// title
		title.setFont(f);
		title.setPadding(new Insets(15, 15, 15, 15));
		title.setAlignment(Pos.TOP_CENTER);// to put title in Top of Center
		ImageView image2 = new ImageView(new Image("car2.png"));

		// Font fonts = Font.font(25);
		Color color = new Color(0.75, 0.125, 0.50, 1);

		reClient.setFont(f);
		reAdmin.setFont(f);
		title.setTextFill(color);
		reClient.setTextFill(color);
		reAdmin.setTextFill(color);

		reClient.setMaxWidth(500);
		reAdmin.setMaxWidth(500);

		image2.setFitWidth(500);
		image2.setFitHeight(500);

		h.setAlignment(Pos.CENTER);
		reClient.setStyle("-fx-background-color: white");// To determine the Color background for every button
		reAdmin.setStyle("-fx-background-color: white");// To determine the Color background for every button

		spane.setStyle("-fx-background-color:#D3D3D3");// To determine the Color background
		h.getChildren().addAll(reClient, reAdmin);
		spane.setAlignment(Pos.CENTER);
		spane.getChildren().addAll(title, image2, h);
		spane.setPadding(new Insets(15, 15, 15, 15));
		return spane;
	}

	public void readCarsFromFile5() {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Read Cars From File");
		File file = chooser.showOpenDialog(s);
		if (file != null) {
			try (Scanner scanner = new Scanner(file)) {
				carsList = new circularDLLSort();
				// Read each line of the file
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					String[] carData = line.split(",");
					// Check if the line has at least 5 elements
					if (carData.length >= 5) {
						String brand = carData[0].trim();
						String model = carData[1].trim();
						String yearString = carData[2].trim();
						String color = carData[3].trim();
						String price = carData[4].trim();

						SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
						Date year = yearFormat.parse(yearString);

						car car = new car(model, year, color, price);
						// Check if the brand is already present in the carsList
						if (Main.carsList.search3(brand) == null) {
							comboBox.getItems().add(brand);

							Brand b = new Brand(brand);
							carsList.insert2(b);
							carsList.search3(brand).st.insert(model, year, color, price);
						} else {
							carsList.search3(brand).st.insert(model, year, color, price);
						}
					}
				}
				// Print the single linked list and the entire circularDLLSort list
				carsList.getLast().st.printList2(); // Print the single linked list
				System.out.println(carsList.getLast().st.toString());
				carsList.printList2();

			} catch (FileNotFoundException e) {
				// Display an error message if the file is not found
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("File Not Found");
				alert.setHeaderText(null);
				alert.setContentText("The selected file was not found.");
				alert.showAndWait();
			} catch (IOException e) {
				// Display an error message if there is an error reading the file
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Reading File");
				alert.setHeaderText(null);
				alert.setContentText("An error occurred while reading the file.");
				alert.showAndWait();
			} catch (ParseException e) {
				// Display an error message if there is an error parsing the date
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Invalid Data");
				alert.setHeaderText(null);
				alert.setContentText("Invalid date format in the file.");
				alert.showAndWait();
			}
		}
	}

	class myEvent implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			if (((Button) event.getSource()).getText().equals("Client")) {
				readCarsFromFile5();
				clientTab tab = new clientTab();
				tab.getClientTab();

			} else if (((Button) event.getSource()).getText().equals("Admin")) {
				readOrdersFromFile3();

			}

		}

	}

	public void readOrdersFromFile3() {
		Platform.runLater(() -> {
			FileChooser chooser = new FileChooser();
			chooser.setTitle("Read Orders From File");
			File file = chooser.showOpenDialog(s);
			if (file != null) {
				try (Scanner scanner = new Scanner(file)) {
					int lineNumber = 1; // To track the line number
					while (scanner.hasNextLine()) {
						String line = scanner.nextLine();
						String[] orderData = line.split(",");

						try {
							if (orderData.length >= 9) { // Check if orderData has at least 9 elements
								String customerName = orderData[0].trim();
								String customerMobile = orderData[1].trim();
								String carBrand = orderData[2].trim();
								String carModel = orderData[3].trim();

								SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
								Date year = yearFormat.parse(orderData[4].trim());

								String color = orderData[5].trim();
								String price = orderData[6].trim();
								String orderDateString = orderData[7].trim();
								String orderStatus = orderData[8].trim();

								SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
								Date orderDate = dateFormat.parse(orderDateString);

								Brand b = new Brand(carBrand);
								Main.carsList.insert(carBrand);
								car carInstance = new car(carModel, year, color, price);

								LocalDate localOrderDate = orderDate.toInstant().atZone(ZoneId.systemDefault())
										.toLocalDate();

								Order order = new Order(customerName, customerMobile, b, carInstance, localOrderDate,
										orderStatus);
								circularDLLSort.brandNode brandNodeInstance = Main.carsList.getFirst(); // Get the first
																										// brandNode
								Stack finishedOrders = brandNodeInstance.finishedOrders;
								if (orderStatus.equals("Finished")) {
									finishedOrders.push(order);
								} else if (orderStatus.equals("InProcess")) {
									carsList.getLast().getInProcessOrders().enqueue(order);
								}
							} else {
								// Handle the case when orderData does not have enough elements
								// Display an error message or take appropriate action
								System.out.println("Invalid order data on line " + lineNumber + ": " + line);
							}

						} catch (ParseException e) {
							// Display an error message with the line causing the error
							Alert alert = new Alert(Alert.AlertType.ERROR);
							alert.setTitle("Invalid Data");
							alert.setHeaderText(null);
							alert.setContentText(
									"Invalid date format in the file. Line: " + lineNumber + "\n\n" + line);
							alert.showAndWait();
						}

						lineNumber++;
					}
					carsList.getLast().inProcessOrders.printList();
					carsList.getLast().finishedOrders.printStack();
					tab = new adminTab();
					tab.getTabe();
				} catch (FileNotFoundException e) {
					// Display an error message if the file is not found
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("File Not Found");
					alert.setHeaderText(null);
					alert.setContentText("The selected file was not found.");
					alert.showAndWait();
				} catch (IOException e) {
					// Display an error message if there is an error reading the file
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error Reading File");
					alert.setHeaderText(null);
					alert.setContentText("An error occurred while reading the file.");
					alert.showAndWait();
				}
			}
		});
	}

	public static void main(String[] args) {
		launch(args);

	}
}
