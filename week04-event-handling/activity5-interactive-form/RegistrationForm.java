import javax.swing.*;
import java.awt.*;

public class RegistrationForm extends JFrame {

    // ---- Components (same names as the handbook's table) ----
    private JLabel titleLabel, nameLabel, emailLabel, passwordLabel, genderLabel;
    private JLabel countryLabel, commentsLabel, statusLabel;
    private JTextField nameField, emailField;
    private JPasswordField passwordField;
    private JRadioButton maleRadio, femaleRadio, otherRadio;
    private ButtonGroup genderGroup;
    private JCheckBox sportsCheck, musicCheck, readingCheck;
    private JComboBox<String> countryBox;
    private JTextArea commentsArea;
    private JButton submitButton, clearButton, exitButton;

    public RegistrationForm() {
        setTitle("Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // ---- Title ----
        titleLabel = new JLabel("Registration Form", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        add(titleLabel, BorderLayout.NORTH);

        // ---- Form panel (GridBagLayout) ----
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Row 0: Name
        nameLabel = new JLabel("Full Name:");
        nameField = new JTextField(30);
        addRow(form, gbc, 0, nameLabel, nameField);

        // Row 1: Email
        emailLabel = new JLabel("Email:");
        emailField = new JTextField(30);
        addRow(form, gbc, 1, emailLabel, emailField);

        // Row 2: Password
        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(30);
        addRow(form, gbc, 2, passwordLabel, passwordField);

        // Row 3: Gender (radio buttons in a ButtonGroup)
        genderLabel = new JLabel("Gender:");
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        otherRadio = new JRadioButton("Other");
        maleRadio.setSelected(true);
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderGroup.add(otherRadio);
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        genderPanel.add(otherRadio);
        addRow(form, gbc, 3, genderLabel, genderPanel);

        // Row 4: Interests (checkboxes)
        JLabel interestsLabel = new JLabel("Interests:");
        sportsCheck = new JCheckBox("Sports");
        musicCheck = new JCheckBox("Music");
        readingCheck = new JCheckBox("Reading");
        JPanel interestsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        interestsPanel.add(sportsCheck);
        interestsPanel.add(musicCheck);
        interestsPanel.add(readingCheck);
        addRow(form, gbc, 4, interestsLabel, interestsPanel);

        // Row 5: Country (combo box)
        countryLabel = new JLabel("Country:");
        countryBox = new JComboBox<>(new String[]{"Kenya", "Tanzania", "Uganda", "Rwanda"});
        addRow(form, gbc, 5, countryLabel, countryBox);

        // Row 6: Comments (text area inside a scroll pane)
        commentsLabel = new JLabel("Comments:");
        commentsArea = new JTextArea(3, 30);
        commentsArea.setLineWrap(true);
        commentsArea.setWrapStyleWord(true);
        addRow(form, gbc, 6, commentsLabel, new JScrollPane(commentsArea));

        add(form, BorderLayout.CENTER);

        // ---- Bottom: status label + buttons ----
        JPanel bottom = new JPanel(new BorderLayout());
        statusLabel = new JLabel("Status: Ready", SwingConstants.CENTER);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));
        bottom.add(statusLabel, BorderLayout.NORTH);

        submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(0, 160, 0));
        submitButton.setForeground(Color.WHITE);
        clearButton = new JButton("Clear");
        exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(200, 50, 50));
        exitButton.setForeground(Color.WHITE);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttons.add(submitButton);
        buttons.add(clearButton);
        buttons.add(exitButton);
        bottom.add(buttons, BorderLayout.SOUTH);
        add(bottom, BorderLayout.SOUTH);

        // ---- EVENT HANDLERS ----
        submitButton.addActionListener(e -> submitClicked());
        clearButton.addActionListener(e -> clearClicked());
        exitButton.addActionListener(e -> exitClicked());
    }

    // Helper: puts a label in column 0 and a component in column 1
    private void addRow(JPanel panel, GridBagConstraints gbc, int row,
                        JComponent label, JComponent field) {
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(field, gbc);
    }

    // ---- SUBMIT ----
    private void submitClicked() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        // Validation
        if (name.isEmpty()) {
            showError("Error: Please enter your name");
            return;
        }
        if (email.isEmpty() || !email.contains("@")) {
            showError("Error: Please enter a valid email");
            return;
        }
        if (password.length() < 6) {
            showError("Error: Password must be at least 6 characters");
            return;
        }

        // Gather gender
        String gender = "Not specified";
        if (maleRadio.isSelected()) gender = "Male";
        else if (femaleRadio.isSelected()) gender = "Female";
        else if (otherRadio.isSelected()) gender = "Other";

        // Gather interests
        StringBuilder interests = new StringBuilder();
        if (sportsCheck.isSelected()) interests.append("Sports ");
        if (musicCheck.isSelected()) interests.append("Music ");
        if (readingCheck.isSelected()) interests.append("Reading ");

        // Gather country and comments
        String country = (String) countryBox.getSelectedItem();
        String comments = commentsArea.getText().trim();

        // Show confirmation
        String message = "Registration Successful!\n\n" +
                "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Gender: " + gender + "\n" +
                "Interests: " + (interests.length() > 0 ? interests.toString() : "None") + "\n" +
                "Country: " + country + "\n" +
                "Comments: " + (comments.isEmpty() ? "None" : comments);

        JOptionPane.showMessageDialog(this, message,
                "Registration Complete", JOptionPane.INFORMATION_MESSAGE);

        statusLabel.setText("Registration submitted successfully!");
        statusLabel.setForeground(new Color(0, 128, 0));
    }

    private void showError(String text) {
        statusLabel.setText(text);
        statusLabel.setForeground(Color.RED);
    }

    // ---- CLEAR ----
    private void clearClicked() {
        nameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        genderGroup.clearSelection();
        sportsCheck.setSelected(false);
        musicCheck.setSelected(false);
        readingCheck.setSelected(false);
        countryBox.setSelectedIndex(0);
        commentsArea.setText("");
        statusLabel.setText("Status: Form cleared");
        statusLabel.setForeground(Color.BLACK);
    }

    // ---- EXIT ----
    private void exitClicked() {
        int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to exit?",
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrationForm().setVisible(true));
    }
}