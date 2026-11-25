import javax.swing.*;
import java.awt.*;

public class FixedDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("EDT Demo - GOOD Practice");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new FlowLayout());

            JLabel status = new JLabel("Ready");
            JButton button = new JButton("Do Long Operation");

            // GOOD: run the long operation in a background thread
            button.addActionListener(e -> {
                status.setText("Working...");
                button.setEnabled(false);

                // Start a background thread
                new Thread(() -> {
                    try {
                        Thread.sleep(5000); // blocks THIS thread, not the EDT
                    } catch (InterruptedException ex) { }

                    System.out.println("Background work on: "
                            + Thread.currentThread().getName());

                    // CRITICAL: update the GUI on the EDT
                    SwingUtilities.invokeLater(() -> {
                        System.out.println("GUI update on: "
                                + Thread.currentThread().getName());
                        status.setText("Done!");
                        button.setEnabled(true);
                    });
                }).start();
            });

            frame.add(button);
            frame.add(status);
            frame.setVisible(true);
        });
    }
}