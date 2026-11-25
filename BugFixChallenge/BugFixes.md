# Bug Fix Documentation

## Bug 1: Blocking the EDT
- **Symptom:** The window froze for 5 seconds after clicking Process. I could not move it or click anything.
- **Cause:** `Thread.sleep(5000)` ran inside the button's handler on the EDT, so the event loop was blocked and could not handle other events.
- **Fix:** Run the slow work in a background thread and update the GUI on the EDT.
- **Code Change:** I moved `Thread.sleep(5000)` into `new Thread(() -> {...}).start()`. After the wait, I used `SwingUtilities.invokeLater()` to set the label to "Done!". I also disabled the button while processing.

## Bug 2: Missing Listener
- **Symptom:** Clicking Increment did nothing. The count stayed at 0.
- **Cause:** No ActionListener was registered on the button, so the click event had no handler.
- **Fix:** Added an ActionListener to the increment button.
- **Code Change:** I added `incrementButton.addActionListener(e -> {...})`. Inside it, `count++` increases the number and `countLabel.setText("Count: " + count)` updates the label.

## Bug 3: Wrong Thread
- **Symptom:** The clock seemed to work, but it is unsafe and can cause erratic behavior or crashes.
- **Cause:** The label was updated from a background thread. Swing is not thread-safe, so only the EDT should change the GUI.
- **Fix:** Used `SwingUtilities.invokeLater()` for GUI updates.
- **Code Change:** I wrapped `timeLabel.setText(...)` inside `SwingUtilities.invokeLater(() -> {...})`. I also disabled the Start button after the first click so only one clock thread runs.

## Bug 4: Memory Leak
- **Symptom:** After Remove All, new buttons kept counting up (Button 3, not Button 0). Memory use grows over time.
- **Cause:** `removeAll()` only removed the buttons from the screen. The `createdButtons` list still held references to them, so Java could not free them.
- **Fix:** Clear the list when removing all buttons.
- **Code Change:** I added `createdButtons.clear();` inside the Remove All handler, right after `buttonPanel.removeAll();`.