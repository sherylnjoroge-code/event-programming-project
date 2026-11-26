package com.university;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class StudentController {

    // @FXML connects these variables to the fx:id values in StudentView.fxml
    @FXML private TextField regField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, String> colReg;
    @FXML private TableColumn<Student, String> colFirst;
    @FXML private TableColumn<Student, String> colLast;
    @FXML private Label statusLabel;
    @FXML private Button saveButton;

    // The list that the TableView displays
    private ObservableList<Student> studentList = FXCollections.observableArrayList();

    // Called automatically when the FXML loads
    @FXML
    public void initialize() {
        // 1. Connect the table columns to the Student properties (Week 8)
        colReg.setCellValueFactory(cellData -> cellData.getValue().regNumberProperty());
        colFirst.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        colLast.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        // 2. Tell the TableView to use our list
        studentTable.setItems(studentList);

        // 3. Disable the Save button while any field is empty (Week 8 binding)
        saveButton.disableProperty().bind(
                regField.textProperty().isEmpty()
                        .or(firstNameField.textProperty().isEmpty())
                        .or(lastNameField.textProperty().isEmpty())
        );

        // 4. Load the first data from the database
        loadStudents();
    }

    // Triggered when the user clicks "Save Student"
    @FXML
    private void handleSaveStudent() {
        String reg = regField.getText();
        String fName = firstNameField.getText();
        String lName = lastNameField.getText();

        // A background Task saves the data (producer-consumer)
        Task<Void> saveTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                String sql = "INSERT INTO students "
                        + "(registration_number, first_name, last_name, email) "
                        + "VALUES (?, ?, ?, ?)";

                try (Connection conn = DatabaseUtil.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, reg);
                    stmt.setString(2, fName);
                    stmt.setString(3, lName);
                    stmt.setString(4, reg + "@university.edu"); // generated email
                    stmt.executeUpdate();
                }
                return null;
            }
        };

        // When the task succeeds (this runs on the UI thread)
        saveTask.setOnSucceeded(e -> {
            statusLabel.setText("Student saved successfully!");
            regField.clear();
            firstNameField.clear();
            lastNameField.clear();
            loadStudents(); // refresh the table
        });

        // When the task fails, show the reason instead of failing silently
        saveTask.setOnFailed(e -> statusLabel.setText(
                "Could not save: " + saveTask.getException().getMessage()));

        Thread thread = new Thread(saveTask);
        thread.setDaemon(true);
        thread.start();
    }

    // Loads the students from the database in the background
    private void loadStudents() {
        LoadStudentsTask task = new LoadStudentsTask();

        task.setOnSucceeded(e -> {
            studentList.setAll(task.getValue());
            statusLabel.setText("Loaded " + studentList.size()
                    + " students from database.");
        });

        task.setOnFailed(e -> statusLabel.setText(
                "Database error: " + task.getException().getMessage()));

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }
}