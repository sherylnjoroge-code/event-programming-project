# Week 1 Self-Assessment

## Learning Outcome 1: Define EDP Components

1. **What is an event?** Something that happens that the program can react to. Examples: a button click, a key press.
2. **What is an event source?** The object that creates the event. Examples: a button, a card slot on an ATM.
3. **What is an event listener?** The object that waits for an event and reacts when it happens. Examples: an ActionListener on a button, a card reader waiting for a card.
4. **What is an event handler?** The code that runs when the event happens. Examples: the code that verifies a PIN, the code that adds a number to the calculator display.
5. **Diagram:**

```
Event Source  --->  Event  --->  Listener  --->  Handler
 (button)        (click)       (waits)        (runs code)
```

## Learning Outcome 2: Compare Paradigms

6. **Procedural vs event-driven:** In procedural programming, the program controls the flow and runs steps in a fixed order from top to bottom. In event-driven programming, the user's actions control the flow and the program waits and reacts.
7. **Main advantage for GUIs:** The user can click anything in any order, and the program keeps running and responds each time.
8. **When to choose procedural:** For simple tasks with no user interaction, like a script that calculates monthly payroll and prints the result.

## Learning Outcome 3: Event Loop and Queue

9. **Event loop:** A loop that keeps checking the event queue, takes the next event, runs its handler and repeats.
10. **Event queue:** A waiting line where events are stored in the order they arrive. The event loop takes them one at a time from the front.
11. **Why never block the loop:** While one handler is stuck, all other events wait in the queue, so the app freezes and the user cannot click anything.
12. **EDT:** The Event Dispatch Thread. It is the one thread in Java Swing that handles all events and updates the GUI.

## Learning Outcome 4: Real-World Examples

13. **Five real-world systems:** ATM, microwave oven, elevator, M-Pesa app, Jumia website.
14. **One system, all four components (ATM):**
    - Event source: card slot
    - Event: card inserted
    - Listener: card reader
    - Handler: read card data
15. **Why modern apps are event-driven:** Users expect to interact freely and get instant responses. Phones, websites and apps all wait for taps, clicks and messages instead of following one fixed path.

## Score

I answered __15_ out of 15 correctly.