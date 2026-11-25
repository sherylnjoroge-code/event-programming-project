package com.example;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {

    // 1. Use Properties instead of standard String and int
    private final StringProperty name = new SimpleStringProperty("");
    private final IntegerProperty age = new SimpleIntegerProperty(0);

    // 2. Standard getters and setters
    public String getName() { return name.get(); }
    public void setName(String value) { name.set(value); }

    public int getAge() { return age.get(); }
    public void setAge(int value) { age.set(value); }

    // 3. Property getters (needed for bindings)
    // These let other components bind to this property.
    public StringProperty nameProperty() { return name; }
    public IntegerProperty ageProperty() { return age; }
}