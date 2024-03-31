package application;

import java.time.LocalDate;
import java.util.Date;

import javafx.scene.control.TableView;

public class Order {
	String customerName;// Holds the name of the customer
	String customerMobile;// Holds the mobile number of the customer
	Brand brand;// Represents the brand of the car
	car car;// Represents the car object associated with the order
	LocalDate orderDate;// Represents the date of the order
	String orderStatus;// Holds the status of the order

	public Order(String customerName, String customerMobile, Brand b, application.car car, LocalDate orderDate,
			String orderStatus) {
		super();
		this.customerName = customerName;
		this.customerMobile = customerMobile;
		this.brand = b;
		this.car = car;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return customerName + "," + customerMobile + "," + brand + "," + car + "," + orderDate + "," + orderStatus;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public car getCar() {
		return car;
	}

	public void setCar(car car) {
		this.car = car;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getModel() {
		return car.getModel(); // Retrieves the model of the car associated with the order
	}

	public String getColor() {
		return car.getColor();// Retrieves the color of the car associated with the order
	}

	public String getPrice() {
		return car.getPrice(); // Retrieves the price of the car associated with the order
	}

	public Date getYear() {
		return car.getYear();// Retrieves the year of the car associated with the order
	}

}
