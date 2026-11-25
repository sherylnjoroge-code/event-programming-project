# Week 2 Notes: Observer Pattern

## Activity 1: Weather Station Self-Check

1. **Role of the Subject interface:**  It says the subject must be able to register, remove and notify observers.
2. **notifyObservers() with no observers:** Nothing happens. The loop has nothing to go through, and there is no error.
3. **register vs remove:** registerObserver adds an observer to the list so it gets updates. removeObserver takes it out so it stops getting them.
4. **Why the list is an instance variable:** The list must stay alive between calls. If it were created inside a method, it would be lost each time and the subject would forget its observers.

## Activity 2: Legacy Observer Self-Check

1. **Why can't you extend another class if you already extend Observable?** Java has single inheritance, so a class can have only one parent class. Observable uses up that one place.
2. **Push vs pull model:** In the push model, the subject sends the data inside the notification, like update(temperature, humidity, pressure). In the pull model, the subject only says something changed and the observer asks for the data itself with getters.
3. **Why did Java deprecate these classes?** Observable is a class, notification order is not guaranteed, setChanged() is easy to forget, the pull model is clumsy, and the API is not thread-safe or extensible.

## Activity 3: Coupling Questions

1. **Adding a third display class?** Yes, I have to open DataModel and add another line to call it.
2. **If LabelDisplay is deleted or renamed?** DataModel stops compiling, because it names that class directly. I would have to fix it.
3. **Testing DataModel in isolation?** No. It creates the display classes inside itself, so I cannot test it without them.

## Activity 3: Comparison

| Aspect | Tightly Coupled | Observer Pattern |
|---|---|---|
| Adding a new display | Modify DataModel | Just register a new observer |
| Testing DataModel in isolation | Hard, it needs the display classes | Easy, no display classes required |
| Removing a display | Modify DataModel | Just don't register it |