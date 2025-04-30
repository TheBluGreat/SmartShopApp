import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    //Sets up the array list to store the produt information
    private List<Product> products = new ArrayList<>();

    //method for adding products to teh system
    public void addProduct() {
        String name = JOptionPane.showInputDialog("Enter product name:");
        //Exception handling for the product name, creating an error message
        if (name == null|| name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "You cannot leave the name empty", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Exception handling for the product price, creating an error message
        String priceInput = JOptionPane.showInputDialog("Enter product price:");
        if (priceInput == null || priceInput.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "You cannot leave the price empty", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

            //Exception handling for quantity, creating an error message
            String quantityInput = JOptionPane.showInputDialog("Enter product quantity:");
            if (quantityInput == null || quantityInput.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "You cannot leave the quantity empty", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
        try {
            //Exception handling for price, creating an error message for negative values
            double price = Double.parseDouble(priceInput);
            if (price <0) {
                JOptionPane.showMessageDialog(null, "Price cannot be a negative", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            //Exception handling for quantity, creating an error message for negative values
            int quantity = Integer.parseInt(quantityInput);
            if (quantity <0) {
                JOptionPane.showMessageDialog(null,  "Quantity cannot be a negative", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Responsive messages to indicate function output
            products.add(new Product(name, price, quantity));
            JOptionPane.showMessageDialog(null, "Product added successfully.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Product not added.");
        }
    }

    //method for updating product information
    public void updateProduct() {
        //display list of products
        displayProducts();
        //Exception handling for if product is empty, error message will show
        if (products.isEmpty()) return;

        String indexInput = JOptionPane.showInputDialog("Enter product number to update:");
        try {
            int index = Integer.parseInt(indexInput) - 1;
            if (index >= 0 && index < products.size()) {
                Product product = products.get(index);
                String priceInput = JOptionPane.showInputDialog("Enter new price:");
                //exception handling for empty price input
                if (priceInput == null || priceInput.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "You cannot leave the price empty", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //excpetion handling for quantity
                String quantityInput = JOptionPane.showInputDialog("Enter new QTY:");
                if (quantityInput == null || quantityInput.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "You cannot leave the quantity empty", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //exception handling for negative price
                double newPrice = Double.parseDouble(priceInput);
                if (newPrice <0) {
                    JOptionPane.showMessageDialog(null, "Price cannot be a negative", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //exception handling for negative quantity
                int newQuantity = Integer.parseInt(quantityInput);
                if (newQuantity <0) {
                    JOptionPane.showMessageDialog(null, "Quantity cannot be a negative", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                product.setPrice(newPrice);
                product.setQuantity(newQuantity);
                //responsive feedback to indicate function completition
                JOptionPane.showMessageDialog(null, "Product updated.");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid product number.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Update failed.");
        }
    }

    //method for removing products
    public void removeProduct() {
        displayProducts();
        if (products.isEmpty()) return;

        String indexInput = JOptionPane.showInputDialog("Enter product number to remove:");
        try {
            int index = Integer.parseInt(indexInput) - 1;
            //Implimentation of HCI feedback to get confirmation of removal of products
            if (index >= 0 && index < products.size()) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this product?", "Remove Product", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    products.remove(index);
                    JOptionPane.showMessageDialog(null, "Product removed.");
                } else {
                    JOptionPane.showMessageDialog(null, "Removal cancelled.");
                }

            //responsive feedback
            } else {
                JOptionPane.showMessageDialog(null, "Invalid product number.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Removal failed.");
        }
    }

    //method to display products
    public void displayProducts() {
        //exception handling for if products list is empty
        if (products.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No products available.");
            return;
        }

        //StringBuilder stores the list of products
        StringBuilder list = new StringBuilder();

        //loop through the product list and format with numbers
        for (int i = 0; i < products.size(); i++) {
            list.append((i + 1)).append(". ").append(products.get(i)).append("\n");
        }
        //displaying the formatted list in message dialog box
        JOptionPane.showMessageDialog(null, list.toString(), "Product List", JOptionPane.INFORMATION_MESSAGE);
    }

    //method to return the list of products
    public List<Product> getProducts() {
        return products;
    }

    //method to create the report for low stock items
    public List<Product> getLowStockProducts(int threshold) {
        List<Product> lowStock = new ArrayList<>();
        for (Product p : products) {
            if (p.getQuantity() < threshold) {
                lowStock.add(p);
            }
        }
        return lowStock;
    }
}
