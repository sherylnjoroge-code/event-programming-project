# Week 3 Notes: Swing GUI

## Activity 1: Self-Check

1. **Difference between JFrame and the content pane:** The JFrame is the top-level window, with the title bar and the minimise, maximise and close buttons. The content pane is the invisible area inside the JFrame where components are added. In modern Swing, frame.add(component) adds to the content pane automatically.
2. **What does EXIT_ON_CLOSE do?** It decides what happens when the user clicks the X button. With EXIT_ON_CLOSE the whole program ends. Other options are DO_NOTHING_ON_CLOSE, HIDE_ON_CLOSE and DISPOSE_ON_CLOSE.
3. **Why wrap the code in SwingUtilities.invokeLater()?** All Swing GUI creation and changes must happen on the EDT. invokeLater() schedules the code to run on the EDT, which keeps the GUI thread-safe.







## Activity 4: What I Observed

- **FreezeDemo:** When I clicked the button, the whole window froze for 5 seconds. I could not move it, resize it or click anything. After 5 seconds it showed "Done!". The Thread.sleep(5000) ran on the EDT, so the event loop was blocked.
- **FixedDemo:** When I clicked the button, it showed "Working..." and the button was disabled, but I could still move and resize the window. After 5 seconds it showed "Done!". The slow work ran in a background thread, and only the label update went back to the EDT.

## Activity 4: Self-Check

1. **Why does Thread.sleep() on the EDT freeze the UI?** The EDT runs the event loop, which handles all events. If it is busy sleeping, it cannot process any other events, so the UI cannot repaint, respond to clicks or resize.
2. **What is the safe way to update a label from a background thread?** Use SwingUtilities.invokeLater(() -> label.setText("...")). This schedules the update to run on the EDT.
3. **What happens if you violate the EDT rule?** At best the UI freezes. At worst it glitches, shows old data or crashes. Swing is not thread-safe, so the rules exist to prevent bugs that are hard to find.

## The Two Golden Rules

1. All GUI creation and updates must happen on the EDT.
2. Never run long operations on the EDT.