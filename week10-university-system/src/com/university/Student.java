package com.university;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// One row of the students table. It uses JavaFX properties (Week 8)
// so the TableView can observe the values.
public class Student {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty regNumber = new SimpleStringProperty();
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();

    public Student(int id, String regNumber, String firstName, String lastName) {
        this.id.set(id);
        this.regNumber.set(regNumber);
        this.firstName.set(firstName);
        this.lastName.set(lastName);
    }

    // Property getters (needed for the TableView bindings)
    public IntegerProperty idProperty() { return id; }
    public StringProperty regNumberProperty() { return regNumber; }
    public StringProperty firstNameProperty() { return firstName; }
    public StringProperty lastNameProperty() { return lastName; }

    // Standard getter
    public String getRegNumber() { return regNumber.get(); }
}