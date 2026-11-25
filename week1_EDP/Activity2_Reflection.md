# Activity 2: Build the Event Loop

## Questions From Round 3

1. **What happened to the other events while I was stuck on the slow handler?** They had to wait in the queue. None of them was processed until the slow handler finished.
2. **What would a user see in a real application?** The app would freeze. Buttons would not respond, the window could not be moved, and Windows might say "Not responding".
3. **How could this problem be fixed?** Run the slow work in a background thread, so the event loop stays free. Only the small GUI update should go back to the EDT.

## Reflection

The event queue is a waiting line that stores events in the order they happen, like customers queuing at an M-Pesa shop. The event loop keeps checking the queue, takes the next event, runs its handler and then goes back to check again. Because it handles one event at a time, every event is processed in the right order. If a handler takes too long, the event loop is blocked and the other events just wait in the queue. In a real app this makes the window freeze, so the user cannot click, type or even move it. That is why the event loop should never be blocked, and slow work should run in a background thread. In Java Swing the event loop runs on the Event Dispatch Thread, so only quick code should run there.