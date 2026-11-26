package com.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TaskDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label statusLabel = new Label("Ready");
        Button startButton = new Button("Start Background Task");

        startButton.setOnAction(e -> {
            statusLabel.setText("Processing...");
            startButton.setDisable(true); // prevent double-clicking

            // 1. Create a background Task
            Task<Void> task = new Task<>() {
                @Override
                protected Void call() throws Exception {
                    // THIS RUNS IN THE BACKGROUND (off the UI thread)
                    System.out.println("call() runs on: "
                            + Thread.currentThread().getName());
                    Thread.sleep(2500);

                    // We are on a background thread here, so we MUST use
                    // Platform.runLater() to touch the UI.
                    Platform.runLater(() -> statusLabel.setText("Halfway there..."));

                    Thread.sleep(2500);
                    return null;
                }
            };

            // 2. What to do when the task succeeds.
            // This handler already runs on the UI thread, so it is safe
            // to update the UI directly.
            task.setOnSucceeded(event -> {
                System.out.println("onSucceeded runs on: "
                        + Thread.currentThread().getName());
                statusLabel.setText("Task Complete!");
                startButton.setDisable(false);
            });

            // 3. Start the task on a new thread
            Thread thread = new Thread(task);
            thread.setDaemon(true);
            thread.start();
        });

        VBox root = new VBox(20, startButton, statusLabel);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(root, 400, 200);
        primaryStage.setTitle("Task Demo - The Fix");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}