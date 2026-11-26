package com.university;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 1. Load the main FXML file
        Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));

        // 2. Set up the Scene and Stage
        primaryStage.setTitle("University Management System");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

        // 3. Schedule a background task to run every 5 seconds.
        // The thread is a daemon, so it does not keep the program alive
        // after the window is closed.
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1, r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });

        Runnable heartbeat = () -> {
            System.out.println("System Heartbeat: Checking for updates at "
                    + new java.util.Date());
            // To update the UI from here, use Platform.runLater()
        };

        // Run after 0 seconds, then every 5 seconds
        scheduler.scheduleAtFixedRate(heartbeat, 0, 5, TimeUnit.SECONDS);

        // Stop the scheduler when the window closes
        primaryStage.setOnCloseRequest(e -> scheduler.shutdownNow());
    }

    public static void main(String[] args) {
        launch(args);
    }
}