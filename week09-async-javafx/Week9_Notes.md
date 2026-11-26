# Week 9 Notes: Asynchronous Programming in JavaFX

## The Golden Rule

Never run slow work on the JavaFX Application Thread. If I do, the whole window freezes, because that thread also draws the screen and handles every click. This is the same rule as the EDT in Swing.

## Activity 1: The Freeze

1. **Why did the window freeze for 5 seconds?** Thread.sleep(5000) ran on the JavaFX Application Thread. That thread could not process any other events, like moving the window or repainting, until the sleep finished.

## Activity 2: Task and Safe UI Updates

1. **Why use Platform.runLater()?** Code running on a background thread, like inside call(), is not allowed to change the UI. Platform.runLater() sends that code to the JavaFX Application Thread to run safely. In my TaskDemo I used it inside call() to set the "Halfway there..." message.

**What I saw in the terminal:** call() ran on Thread-0, but onSucceeded ran on the JavaFX Application Thread. So Task event handlers like setOnSucceeded are already on the UI thread, and I can update the UI there directly.

## Activity 3: Live Progress

1. **updateProgress() vs Platform.runLater():** updateProgress() is a built-in Task method that safely passes progress to the UI and batches the updates. Platform.runLater() sends any block of code to the UI thread. For progress, updateProgress() is better.
2. **Why unbind() at the end?** The label was bound to the task's message. A bound property cannot be set manually, so setText() would throw an exception. unbind() releases it first.

## Activity 4: Service

1. **Task vs Service:** A Task can run only once. A Service wraps a Task and can be started, restarted and cancelled many times, so it suits jobs like Refresh or Sync.
2. **How do I check for cancel inside call()?** Call isCancelled() inside the loop, so the background thread can break out and stop.

## Comparison

| | Task | Service |
|---|---|---|
| Runs | Once | Many times (restart) |
| Created with | new Task and a call() method | extend Service and write createTask() |
| Cancel | task.cancel() | service.cancel() |
| Good for | A single download | Refresh or Sync buttons |

## Three Safe Ways to Update the UI From Background Work

| Way | When I use it |
|---|---|
| updateProgress() and updateMessage() with bindings | Progress bars and status text |
| Platform.runLater() | Any other UI change from inside call() |
| setOnSucceeded or setOnFailed | When the task is finished (already on the UI thread) |

**Example:** A Kenyan app that loads M-Pesa transaction history should fetch it in a Task, show a spinner, and fill the list when it finishes, so the screen never freezes.

## Learning Outcomes

- [x] I can identify operations that freeze the UI.
- [x] I can implement background tasks with Task and Service.
- [x] I can update the UI safely from background threads.
- [x] I can show real-time progress to the user.