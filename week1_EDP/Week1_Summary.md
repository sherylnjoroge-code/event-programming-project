# Week 1 Summary: Event-Driven Programming

## Key Definitions

- **Event:** something that happens, like a click or key press.
- **Event source:** the object that creates the event.
- **Event listener:** waits for the event and reacts.
- **Event handler:** the code that runs when the event happens.
- **Event queue:** a waiting line that stores events in order.
- **Event loop:** takes events from the queue and runs their handlers, again and again.
- **EDT:** the Event Dispatch Thread in Swing, which handles all GUI work.

## Important Diagram

```
Event Source -> Event -> Event Queue -> Event Loop -> Handler -> GUI Update
```

## Procedural vs Event-Driven

| Aspect | Procedural | Event-Driven |
|---|---|---|
| Who controls the flow? | The program | The user |
| Order of steps | Fixed | Any order |
| Recovering from mistakes | Restart the program | Press clear and continue |
| Best for | Simple scripts | GUIs and interactive apps |

## Code Example I Found Helpful

This handler is from my calculator. When the button is clicked, the code runs:

```java
btn.addActionListener(e -> {
    display.setText(display.getText() + text);
});
```

## Key Lessons From the Bug Fixes

- Never run slow work on the EDT, or the window freezes. Use a background thread.
- Always add a listener, or the button does nothing.
- Only update the GUI from the EDT, using `SwingUtilities.invokeLater()`.
- Clear lists when removing objects, or memory is wasted.

## Questions I Still Have

- How does the event loop decide the order when two events arrive at the same time?
- How is Week 2's Observer Pattern different from a normal listener?