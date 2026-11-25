# Week 7 Notes: Event Handling in JavaFX

## Activity 1: Filters vs Handlers

| | Event Filter | Event Handler |
|---|---|---|
| Phase | Capturing (top down, root to target) | Bubbling (bottom up, target to root) |
| Registered with | addEventFilter() | addEventHandler() or setOnXxx() |
| Runs | Before the target handles the event | After the target handles the event |

1. **Which phase does an Event Filter belong to?** The capturing phase. It travels from the root node down to the target node.
2. **What does e.consume() do?** It stops the event from going any further. If called in a filter, the event never reaches the target or any handlers.
3. **How do I register an Event Handler?** With addEventHandler(), or with convenience methods like setOnMouseClicked() and setOnAction().

**Order I saw:** filter on the root, then the handler on the button, then the handler on the root.

## Activity 2: Mouse and Key Events

1. **Which method tracks the mouse position without clicking?** setOnMouseMoved().
2. **Why might a KeyEvent not fire?** The node does not have focus. It must be focus traversable and must request focus, with root.requestFocus().
3. **How do I check which key was pressed?** With getCode() on the event, for example e.getCode() == KeyCode.UP.

## Activity 3: Drag and Drop

1. **Which handler starts a drag on the source?** setOnDragDetected().
2. **Purpose of event.acceptTransferModes(TransferMode.MOVE)?** It tells JavaFX that the target is willing to accept the dragged data. Without it the drop is rejected.
3. **How do I get the data when it is dropped?** Call event.getDragboard() on the drop event, then read the content, for example db.getString().

**Drag and drop steps:** setOnDragDetected on the source, setOnDragOver on the target to accept, setOnDragDropped on the target to receive the data.

## Activity 4: Custom Events

1. **Why use a custom event instead of calling a method directly?** It gives loose coupling. The part that fires the event does not need to know which classes are listening, or how many. The code is easier to reuse and maintain.
2. **Which class must a custom event extend?** javafx.event.Event.
3. **How do I define the type of a custom event?** With a static EventType field, for example new EventType<>(Event.ANY, "LOGIN_SUCCESS").

**Example:** In a Kenyan shop app, a PaymentSuccessEvent fired after an M-Pesa payment could trigger the SMS receipt, the stock update and the sales report at the same time, without the pay button knowing about any of them.

## Final Self-Assessment

**Learning Outcome 1: Handle action, key and mouse events**
- [x] I can use setOnMouseMoved to track X and Y coordinates.
- [x] I can use setOnKeyPressed and KeyCode to move an object.

**Learning Outcome 2: Differentiate between filters and handlers**
- [x] Filters capture events top down, and handlers bubble events bottom up.
- [x] I know how to use e.consume() to stop propagation.

**Learning Outcome 3: Implement drag and drop**
- [x] I can create a drag source with setOnDragDetected and a Dragboard.
- [x] I can create a drop target with setOnDragOver and setOnDragDropped.

**Learning Outcome 4: Create and fire custom events**
- [x] I can create a custom Event class and an EventType.
- [x] I can fire the event with fireEvent() and listen for it with addEventHandler().