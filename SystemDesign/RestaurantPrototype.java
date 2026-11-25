import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RestaurantPrototype extends JFrame {

    private final String[] menu = {"Ugali and Beef Stew", "Chapati and Beans", "Pilau", "Tea"};
    private final int[] prices = {350, 150, 300, 50};

    private JComboBox<String> menuBox;
    private JLabel statusLabel;
    private JTextArea eventLog;
    private JButton orderButton;
    private JButton readyButton;
    private JButton payButton;

    private List<String> pendingOrders = new ArrayList<>();
    private List<String> readyOrders = new ArrayList<>();
    private int revenue = 0;

    public RestaurantPrototype() {
        setTitle("Restaurant Order System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Status Panel (NORTH)
        statusLabel = new JLabel();
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(statusLabel, BorderLayout.NORTH);

        // Event Log (CENTER)
        eventLog = new JTextArea(12, 40);
        eventLog.setEditable(false);
        add(new JScrollPane(eventLog), BorderLayout.CENTER);

        // Control Panel (SOUTH)
        JPanel controlPanel = new JPanel(new FlowLayout());
        menuBox = new JComboBox<>(menu);
        orderButton = new JButton("Waiter: Place Order");
        readyButton = new JButton("Kitchen: Mark Ready");
        payButton = new JButton("Cashier: Pay (M-Pesa)");

        // EVENT HANDLER 1: order placed
        orderButton.addActionListener(e -> {
            String item = (String) menuBox.getSelectedItem();
            pendingOrders.add(item);
            logEvent("ORDER PLACED: " + item);
            updateStatus();
        });

        // EVENT HANDLER 2: order ready
        readyButton.addActionListener(e -> {
            if (pendingOrders.isEmpty()) {
                logEvent("Kitchen: no orders waiting");
            } else {
                String item = pendingOrders.remove(0);
                readyOrders.add(item);
                logEvent("ORDER READY: " + item + " - waiter, please serve");
            }
            updateStatus();
        });

        // EVENT HANDLER 3: payment received
        payButton.addActionListener(e -> {
            if (readyOrders.isEmpty()) {
                logEvent("Cashier: nothing ready to pay for");
            } else {
                String item = readyOrders.remove(0);
                int price = getPrice(item);
                revenue += price;
                logEvent("PAYMENT RECEIVED via M-Pesa: " + item + " - KES " + price);
            }
            updateStatus();
        });

        controlPanel.add(menuBox);
        controlPanel.add(orderButton);
        controlPanel.add(readyButton);
        controlPanel.add(payButton);
        add(controlPanel, BorderLayout.SOUTH);

        updateStatus();
        setSize(750, 400);
        setLocationRelativeTo(null);
    }

    private int getPrice(String item) {
        for (int i = 0; i < menu.length; i++) {
            if (menu[i].equals(item)) {
                return prices[i];
            }
        }
        return 0;
    }

    private void updateStatus() {
        statusLabel.setText("Waiting in kitchen: " + pendingOrders.size()
                + "  |  Ready to serve: " + readyOrders.size()
                + "  |  Revenue: KES " + revenue);
    }

    private void logEvent(String event) {
        String timestamp = new Date().toString();
        eventLog.append("[" + timestamp + "] " + event + "\n");
        eventLog.setCaretPosition(eventLog.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RestaurantPrototype().setVisible(true);
        });
    }
}