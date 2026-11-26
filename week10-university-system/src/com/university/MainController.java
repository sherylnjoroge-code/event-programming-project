package com.university;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainController {

    // Connects to the StackPane in MainView.fxml
    @FXML private StackPane centerPane;

    // Triggered when the Dashboard button is clicked
    @FXML
    private void showDashboard() {
        centerPane.getChildren().setAll(new Label("Welcome to the University Dashboard!"));
    }

    // Triggered when the Students button is clicked
    @FXML
    private void showStudents() {
        try {
            Parent studentView = FXMLLoader.load(getClass().getResource("StudentView.fxml"));
            centerPane.getChildren().setAll(studentView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}