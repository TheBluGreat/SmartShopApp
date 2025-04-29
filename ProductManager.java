import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private List<Product> products = new ArrayList<>();

    public void addProduct() {
        String name = JOptionPane.showInputDialog("Enter product name:");
        if (name == null) return;

        String priceInput = JOptionPane.showInputDialog("Enter product price:");
        String quantityInput = JOptionPane.showInputDialog("Enter product quantity:");
        try {
            double price = Double.parseDouble(priceInput);
            int quantity = Integer.parseInt(quantityInput);
            products.add(new Product(name, price, quantity));
            JOptionPane.showMessageDialog(null, "Product added successfully.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Product not added.");
        }
    }

    public void updateProduct() {
        displayProducts();
        if (products.isEmpty()) return;

        String indexInput = JOptionPane.showInputDialog("Enter product number to update:");
        try {
            int index = Integer.parseInt(indexInput) - 1;
            if (index >= 0 && index < products.size()) {
                Product product = products.get(index);
                String priceInput = JOptionPane.showInputDialog("Enter new price:");
                String quantityInput = JOptionPane.showInputDialog("Enter new QTY:");
                double newPrice = Double.parseDouble(priceInput);
                int newQuantity = Integer.parseInt(quantityInput);
                product.setPrice(newPrice);
                product.setQuantity(newQuantity);
                JOptionPane.showMessageDialog(null, "Product updated.");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid product number.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Update failed.");
        }
    }

    public void removeProduct() {
        displayProducts();
        if (products.isEmpty()) return;

        String indexInput = JOptionPane.showInputDialog("Enter product number to remove:");
        try {
            int index = Integer.parseInt(indexInput) - 1;
            if (index >= 0 && index < products.size()) {
                products.remove(index);
                JOptionPane.showMessageDialog(null, "Product removed.");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid product number.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Removal failed.");
        }
    }

    public void displayProducts() {
        if (products.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No products available.");
            return;
        }
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < products.size(); i++) {
            list.append((i + 1)).append(". ").append(products.get(i)).append("\n");
        }
        JOptionPane.showMessageDialog(null, list.toString(), "Product List", JOptionPane.INFORMATION_MESSAGE);
    }

    public List<Product> getProducts() {
        return products;
    }

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
