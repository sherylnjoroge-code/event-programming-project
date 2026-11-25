# Week 2 Summary: The Observer Pattern

## What the Observer Pattern Is

The Observer pattern is a way for one object, the Subject, to automatically tell many other objects, the Observers, when its data changes. The subject keeps a list of observers. When something changes, it loops through the list and calls each observer's update() method. The subject does not need to know what the observers do.

## Key Parts

- **Subject:** holds the data and the list of observers.
- **Observer:** an interface with an update() method.
- **Concrete Subject:** the real class, like WeatherData.
- **Concrete Observers:** the real displays, like CurrentConditionsDisplay.

## Why It Matters

- It gives **loose coupling**. The subject only knows the Observer interface, not the real classes.
- I can **add or remove observers** without changing the subject. In Activity 3, I added TextLogger without touching DataModel.
- It is easy to test the subject on its own.

## Push vs Pull

- **Push:** the subject sends the data with the notification. My WeatherData did this.
- **Pull:** the subject only says something changed, and the observer asks for the data. The legacy Observable did this.

## Four Mechanisms in Java

| Mechanism | Status |
|---|---|
| Custom Observer | Recommended |
| java.util.Observer | Deprecated since Java 9 |
| PropertyChangeListener | Supported, for JavaBeans and Swing |
| JavaFX Bindings | Recommended for JavaFX |

## Example From My Code

```java
for (Observer o : observers) {
    o.update(temperature, humidity, pressure);
}
```

## Final Reflection

The Observer pattern lets one object, the subject, tell many other objects when its data changes, without knowing who they are. It is important because it keeps the code loosely coupled, so I can add or remove observers without changing the subject. It relates to event-driven programming because the subject is like an event source and the observers are like listeners that react when an event happens. A button click in Swing works the same way, with the button as the source and the ActionListener as the observer. For my own project I would choose a custom observer, because it is not deprecated, needs no inheritance and gives me full control. An example is a Kenyan marketplace app where a new M-Pesa payment tells the order page, the seller's SMS and the stock count at the same time.