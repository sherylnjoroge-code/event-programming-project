package com.university;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// A background Task that reads all students from the database.
// The UI (producer of the request) does not wait for the database (consumer).
public class LoadStudentsTask extends Task<ObservableList<Student>> {

    // This method runs entirely on a BACKGROUND THREAD
    @Override
    protected ObservableList<Student> call() throws Exception {
        ObservableList<Student> students = FXCollections.observableArrayList();

        String sql = "SELECT * FROM students";

        // try-with-resources closes the connection automatically
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("student_id"),
                        rs.getString("registration_number"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                );
                students.add(student);
            }
        }

        // The result goes back to the UI thread through getValue()
        return students;
    }
}