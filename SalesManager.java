import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SalesManager {
    private ProductManager productManager;
    private List<String> sales = new ArrayList<>();
    private double totalSalesAmount = 0.0;

    public SalesManager(ProductManager productManager) {
        this.productManager = productManager;
    }

    public void recordSale() {
        List<Product> products = productManager.getProducts();
        if (products.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No products available.");
            return;
        }

        StringBuilder productList = new StringBuilder();
        for (int i = 0; i < products.size(); i++) {
            productList.append((i + 1)).append(". ").append(products.get(i)).append("\n");
        }

        String indexInput = JOptionPane.showInputDialog("Choose a product:\n" + productList);
        try {
            int index = Integer.parseInt(indexInput) - 1;
            Product product = products.get(index);

            String quantityInput = JOptionPane.showInputDialog("Enter quantity to sell:");
            int quantity = Integer.parseInt(quantityInput);

            if (quantity <= product.getQuantity()) {
                product.setQuantity(product.getQuantity() - quantity);
                double total = quantity * product.getPrice();
                totalSalesAmount += total;

                String record = "Product: " + product.getName() +
                        "| Quantity Sold: " + quantity +
                        "|  Total: £" + total +
                        "| Date: " + LocalDate.now();
                sales.add(record);

                JOptionPane.showMessageDialog(null, "Sale recorded.");
            } else {
                JOptionPane.showMessageDialog(null, "Not enough stock.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Sale not recorded.");
        }
    }

    public void displaySales() {
        if (sales.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No sales recorded.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (String s : sales) {
            sb.append(s).append("\n");
        }
        sb.append("\nTotal Sales Revenue: £").append(String.format("%.2f", totalSalesAmount));
        JOptionPane.showMessageDialog(null, sb.toString(), "Sales Records", JOptionPane.INFORMATION_MESSAGE);
    }

    public List<String> getSales() {
        return sales;
    }

    public double getTotalSalesAmount() {
        return totalSalesAmount;
    }
}
