import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

// user interface class 
public class UserInterface extends JFrame {
    private ProductManager productManager;
    private SalesManager salesManager;

    
    public UserInterface() {
        productManager = new ProductManager();
        salesManager = new SalesManager(productManager);

        // main page setup
        setTitle("Smart Shop Management System");
        setSize(800, 400); // size of the main program window
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centers the window for any res

        // main panel config
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // vertical layout
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);

        // label for the 'Smart Shop Management System'
        JLabel titleLabel = new JLabel("Smart Shop Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 30)); 
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // whitespace below

        // main menu buttons
        JButton manageProductsBtn = createStyledButton("Manage Products");
        JButton recordSalesBtn = createStyledButton("Record Sales");
        JButton generateReportsBtn = createStyledButton("Generate Reports");
        JButton exitBtn = createStyledButton("Exit");

        // add buttons to panel
        addButton(panel, manageProductsBtn);
        addButton(panel, recordSalesBtn);
        addButton(panel, generateReportsBtn);
        addButton(panel, exitBtn);

        // button behaviors
        manageProductsBtn.addActionListener(e -> showProductMenu());
        recordSalesBtn.addActionListener(e -> salesManager.recordSale());
        generateReportsBtn.addActionListener(e -> generateReportsToFile());
        exitBtn.addActionListener(e -> System.exit(0));

     
        add(panel);
    }

    // styled button customisation
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button.setMaximumSize(new Dimension(400, 50)); // set button size
        button.setAlignmentX(Component.CENTER_ALIGNMENT); 
        return button;
    }

    // adds a button and spacing below it to a panel
    private void addButton(JPanel panel, JButton button) {
        panel.add(button);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // vertical spacing
    }

    // product management menu
    private void showProductMenu() {
        JPanel productMenuPanel = new JPanel();
        productMenuPanel.setLayout(new BoxLayout(productMenuPanel, BoxLayout.Y_AXIS));
        productMenuPanel.setBackground(Color.WHITE);

        // buttons for customisation for product management buttons on page
        JButton addProductBtn = createStyledButton("Add Product");
        JButton updateProductBtn = createStyledButton("Update Product");
        JButton removeProductBtn = createStyledButton("Remove Product");
        JButton displayProductsBtn = createStyledButton("Display Products");
        JButton backBtn = createStyledButton("Back");

        // set buttons height to 40
        Dimension buttonSize = new Dimension(addProductBtn.getPreferredSize().width, 40);
        addProductBtn.setPreferredSize(buttonSize);
        updateProductBtn.setPreferredSize(buttonSize);
        removeProductBtn.setPreferredSize(buttonSize);
        displayProductsBtn.setPreferredSize(buttonSize);
        backBtn.setPreferredSize(buttonSize);

        // actions to menu buttons
        addProductBtn.addActionListener(e -> productManager.addProduct());
        updateProductBtn.addActionListener(e -> productManager.updateProduct());
        removeProductBtn.addActionListener(e -> productManager.removeProduct());
        displayProductsBtn.addActionListener(e -> productManager.displayProducts());
        backBtn.addActionListener(e -> productMenuPanel.setVisible(false)); // hide dialog

        // add buttons with spacing to the product menu panel
        productMenuPanel.add(addProductBtn);
        productMenuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        productMenuPanel.add(updateProductBtn);
        productMenuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        productMenuPanel.add(removeProductBtn);
        productMenuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        productMenuPanel.add(displayProductsBtn);
        productMenuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        productMenuPanel.add(backBtn);

        
        JOptionPane.showOptionDialog(this, productMenuPanel, "Product Management",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
    }

    // generate and save product and sales report to a text file
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
                writer.write("\nTotal Sales Revenue: Â£" + String.format("%.2f", salesManager.getTotalSalesAmount()));
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

    // main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserInterface().setVisible(true));
    }
}
