# Week 2 Self-Assessment

## Learning Outcome 1: Design and Implement the Observer Pattern

1. **UML diagram:**

```
   <<interface>>                      <<interface>>
     Subject          notifies          Observer
 ------------------  ------------>  ------------------
 + registerObserver()                + update()
 + removeObserver()                        ^
 + notifyObservers()                       | implements
        ^                                  |
        | implements             --------------------------
        |                        | ConcreteObserver       |
   WeatherData                   | (CurrentConditions,    |
 (ConcreteSubject)               |  Statistics, Forecast) |
                                 --------------------------
```

2. **Subject interface from memory:**

```java
public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
```

3. **Why does the Subject need a list of observers?** It needs to know who to tell when its data changes. The list is what it loops through to call update() on each observer.

## Learning Outcome 2: Evaluate Legacy java.util.Observer

4. **Three problems with java.util.Observable:** It is a class, so I cannot extend anything else. The notification order is not guaranteed. setChanged() is easy to forget, and then nothing is sent.
5. **Why was the API deprecated?** Because of those problems, and also because it is not thread-safe, not extensible, and uses the clumsy pull model.
6. **Pull model and why it is harder to use:** The observer is only told that something changed, and then it has to call getters to fetch the data. That means extra code in every observer, and it is less efficient than the push model.

## Learning Outcome 3: Create a Custom Event Notification System

7. **Methods a custom Subject must provide:** registerObserver (or addObserver), removeObserver and notifyObservers.
8. **How does the Subject notify all observers?** It loops through its list of observers and calls each one's update() method, passing the new data.
9. **How to prevent a memory leak when observers are no longer needed:** Call removeObserver() so the subject no longer holds a reference to them. Otherwise they stay in the list and Java cannot free them.

## Learning Outcome 4: Compare Different Mechanisms

10. **Four mechanisms:** Custom Observer, java.util.Observer, PropertyChangeListener, JavaFX Properties/Bindings.
11. **Best for JavaFX:** JavaFX Bindings.
12. **Deprecated:** java.util.Observer and Observable.

## Score

I answered __12_ out of 12 correctly.