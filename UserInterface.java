import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class UserInterface extends JFrame {
    private ProductManager productManager;
    private SalesManager salesManager;

    public UserInterface() {
        productManager = new ProductManager();
        salesManager = new SalesManager(productManager);

        setTitle("Smart Shop Management System");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Smart Shop Management System", JLabel.CENTER);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton manageProductsBtn = createStyledButton("Manage Products");
        JButton recordSalesBtn = createStyledButton("Record Sales");
        JButton generateReportsBtn = createStyledButton("Generate Reports");
        JButton exitBtn = createStyledButton("Exit");

        addButton(panel, manageProductsBtn);
        addButton(panel, recordSalesBtn);
        addButton(panel, generateReportsBtn);
        addButton(panel, exitBtn);

        manageProductsBtn.addActionListener(e -> showProductMenu());
        recordSalesBtn.addActionListener(e -> salesManager.recordSale());
        generateReportsBtn.addActionListener(e -> generateReportsToFile());
        exitBtn.addActionListener(e -> System.exit(0));

        add(panel);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button.setMaximumSize(new Dimension(200, 30));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    private void addButton(JPanel panel, JButton button) {
        panel.add(button);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    private void showProductMenu() {
        String[] options = {"Add Product", "Update Product", "Remove Product", "Display Products", "Back"};
        int choice = JOptionPane.showOptionDialog(this, "Choose an action:", "Product Management",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);

        switch (choice) {
            case 0 -> productManager.addProduct();
            case 1 -> productManager.updateProduct();
            case 2 -> productManager.removeProduct();
            case 3 -> productManager.displayProducts();
        }
    }

    private void generateReportsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("report.txt"))) {
            writer.write("----- Product List -----\n");
            int i = 1;
            for (Product p : productManager.getProducts()) {
                writer.write(i++ + ". " + p + "\n");
            }

            writer.write("\n----- Sales Records -----\n");
            List<String> sales = salesManager.getSales();
            if (sales.isEmpty()) {
                writer.write("No sales recorded.\n");
            } else {
                for (String sale : sales) {
                    writer.write(sale + "\n");
                }
                writer.write("\nTotal Sales Revenue: £" + String.format("%.2f", salesManager.getTotalSalesAmount()));
            }

            writer.write("\n\n----- Low Stock (under 5) -----\n");
            for (Product p : productManager.getLowStockProducts(5)) {
                writer.write(p + "\n");
            }

            JOptionPane.showMessageDialog(null, "Report saved to report.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error writing report: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserInterface().setVisible(true));
    }
}
