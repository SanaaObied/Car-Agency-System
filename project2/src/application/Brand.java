package application;

public class Brand {
	String brand;// Represents the brand name

	public Brand(String brand) {
		super();
		this.brand = brand;

	}

	public String getBrand() {
		return brand;// Retrieves the brand name
	}

	public void setBrand(String brand) {
		this.brand = brand;// Sets the brand name
	}

	public boolean equalsIgnoreCase(String str) {
		if (str == null) {
			return false;// If the provided string is null, returns false
		}
		return brand.equalsIgnoreCase(str.trim());// Compares the brand name with the provided string (ignoring case and leading/trailing whitespace)
	}

	@Override
	public String toString() {
		return brand;// Returns the brand name as a string
	}

}
