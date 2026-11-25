# Week 3 Notes: Swing GUI

## Activity 1: Self-Check

1. **Difference between JFrame and the content pane:** The JFrame is the top-level window, with the title bar and the minimise, maximise and close buttons. The content pane is the invisible area inside the JFrame where components are added. In modern Swing, frame.add(component) adds to the content pane automatically.
2. **What does EXIT_ON_CLOSE do?** It decides what happens when the user clicks the X button. With EXIT_ON_CLOSE the whole program ends. Other options are DO_NOTHING_ON_CLOSE, HIDE_ON_CLOSE and DISPOSE_ON_CLOSE.
3. **Why wrap the code in SwingUtilities.invokeLater()?** All Swing GUI creation and changes must happen on the EDT. invokeLater() schedules the code to run on the EDT, which keeps the GUI thread-safe.