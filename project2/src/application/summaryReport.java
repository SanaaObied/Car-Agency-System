package application;

import application.circularDLLSort.brandNode;

//Represents a summary report for a brand.
public class summaryReport {
	private String brand;
	private String higherPrice;
	private String lowerPrice;
	private String higherModelNumber;
	private String lowerModelNumber;

// Constructs a summary report for the given brandNode.
	public summaryReport(brandNode brandNode) {
		super();
		this.brand = brandNode.data.brand;
		this.higherPrice = Main.carsList.getHighestPrice(brand);
		this.lowerPrice = Main.carsList.getLowestPrice(brand);
		this.higherModelNumber = Main.carsList.getHighestModelNumber(brand);
		this.lowerModelNumber = Main.carsList.getLowestModelNumber(brand);
	}

//Returns the brand name.
	public String getBrand() {
		return brand;
	}

// Sets the brand name
	public void setBrand(String brand2) {
		this.brand = brand2;
	}


	public String getHigherModelNumber() {
		return higherModelNumber;
	}

	
	public String getHigherPrice() {
		return higherPrice;
	}

	public void setHigherPrice(String higherPrice) {
		this.higherPrice = higherPrice;
	}

	public String getLowerPrice() {
		return lowerPrice;
	}

	public void setLowerPrice(String lowerPrice) {
		this.lowerPrice = lowerPrice;
	}

	//	Sets the higher model number for the brand.
	public void setHigherModelNumber(String higherModelNumber) {
		this.higherModelNumber = higherModelNumber;
	}

//Returns the lower model number for the brand
	public String getLowerModelNumber() {
		return lowerModelNumber;
	}

//Sets the lower model number for the brand.
	public void setLowerModelNumber(String lowerModelNumber) {
		this.lowerModelNumber = lowerModelNumber;
	}

}
