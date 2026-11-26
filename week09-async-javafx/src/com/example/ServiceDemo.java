package com.example;

import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ServiceDemo extends Application {

    // 1. A Service that can be restarted
    private class DataSyncService extends Service<String> {
        @Override
        protected Task<String> createTask() {
            return new Task<>() {
                @Override
                protected String call() throws Exception {
                    // Simulate a long download or query
                    for (int i = 0; i <= 10; i++) {
                        if (isCancelled()) break; // allow cancellation
                        Thread.sleep(500);
                    }
                    return "Data Synced Successfully!";
                }
            };
        }
    }

    @Override
    public void start(Stage primaryStage) {
        Label statusLabel = new Label("Ready");

        // Indeterminate progress spinner (infinite loading circle)
        ProgressIndicator spinner = new ProgressIndicator();
        spinner.setVisible(false);

        Button syncBtn = new Button("Sync Data");
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setDisable(true);

        DataSyncService service = new DataSyncService();

        syncBtn.setOnAction(e -> {
            service.restart(); // starts or restarts the service
            statusLabel.setText("Syncing...");
            spinner.setVisible(true);
            syncBtn.setDisable(true);
            cancelBtn.setDisable(false);
        });

        cancelBtn.setOnAction(e -> {
            service.cancel(); // abort the task
            statusLabel.setText("Sync Cancelled.");
            spinner.setVisible(false);
            syncBtn.setDisable(false);
            cancelBtn.setDisable(true);
        });

        // Handle success
        service.setOnSucceeded(e -> {
            statusLabel.setText(service.getValue()); // the returned result
            spinner.setVisible(false);
            syncBtn.setDisable(false);
            cancelBtn.setDisable(true);
        });

        // Handle failure
        service.setOnFailed(e -> {
            statusLabel.setText("Sync Failed: " + service.getException().getMessage());
            spinner.setVisible(false);
            syncBtn.setDisable(false);
            cancelBtn.setDisable(true);
        });

        HBox buttons = new HBox(10, syncBtn, cancelBtn);
        VBox root = new VBox(20, buttons, spinner, statusLabel);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-alignment: center;");

        Scene scene = new Scene(root, 400, 250);
        primaryStage.setTitle("Service Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}