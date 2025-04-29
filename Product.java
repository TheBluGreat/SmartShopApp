// Product.java
// This class represents a product in the shop
// It contains the name, price, and quantity of the product

public class Product {
    // The name of the product (e.g., "Apple")
    private String name;

    // The price of a single unit of this product (e.g., 1.99)
    private double price;

    // The quantity of this product available in stock
    private int quantity;

    // Constructor that creates a new product with given name, price, and quantity
    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getter method to retrieve the product's name
    public String getName() {
        return name;
    }

    // Getter method to retrieve the product's price
    public double getPrice() {
        return price;
    }

    // Getter method to retrieve how many units are in stock
    public int getQuantity() {
        return quantity;
    }

    // Setter method to update the price of the product
    public void setPrice(double price) {
        this.price = price;
    }

    // Setter method to update the quantity of the product
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Converts the product details into a readable string format
    @Override
    public String toString() {
        return "Product Name: " + name + " | Price= £" + price + " | QTY= " + quantity;
    }
}