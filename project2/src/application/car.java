package application;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import application.SLLSort.Node;
import application.circularDLLSort.brandNode;

public class car {

	String model;// Represents the car model
	Date year;// Represents the manufacturing year of the car
	String color;// Represents the color of the car
	String price;// Represents the price of the car
	private String brand;// Represents the brand of the car

	public car(String model, Date year, String color, String price) {

		this.model = model;
		this.year = year;
		this.color = color;
		this.price = price;
		// this.Brand=Brand;
	}

	public car(String brand) {
		this.brand = brand;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	private Object getBrand2() {
		// TODO Auto-generated method stub
		return Main.carsList.getFirst().data;// Returns the brand of the first car in the carsList
	}

	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		String formattedYear = dateFormat.format(year);
		return model + "," + formattedYear + "," + color + "," + price;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Date getYear() {

		return year;// Retrieves the manufacturing year of the car
	}

	public void setYear(Date year) {
		this.year = year;// Sets the manufacturing year of the car
	}

	public String getColor() {
		return color;// Retrieves the color of the car
	}

	public void setColor(String color) {
		this.color = color;// Sets the color of the car
	}

	public String getPrice() {
		return price;// Retrieves the price of the car
	}

	public void setPrice(String price) {
		this.price = price;// Sets the price of the car
	}

	public static int compareModels(String model1, String model2) {
		return model1.compareToIgnoreCase(model2);// Compares two car models ignoring case sensitivity
	}

}
