# Week 10 Final Project: University Management System

## Architecture

```
User clicks a button (event)
   -> Controller (StudentController) handles the event
   -> Task runs on a background thread
   -> DatabaseUtil connects to MySQL
   -> Result returns to the UI thread (setOnSucceeded)
   -> ObservableList updates -> TableView refreshes automatically
```

## How the Project Meets the Learning Outcomes

| Outcome | Where I used it |
|---|---|
| LO1: EDA principles | The UI never talks to the database directly. The UI produces events, and DatabaseUtil, the Tasks and MySQL are the consumers. |
| LO2: Producer-consumer | LoadStudentsTask and the save Task do the database work on a background thread, so the UI never freezes. |
| LO3: Scheduled tasks | A ScheduledExecutorService prints a heartbeat every 5 seconds from a background thread. |
| LO4: Event communication | Button clicks fire events, and bindings link the fields to the Save button, so the parts are not tightly coupled. |

## Which Earlier Week Each Part Uses

| Part | Week |
|---|---|
| FXML views and controllers | Week 6 |
| onAction events and navigation between views | Week 7 |
| Student properties and the disabled Save button binding | Week 8 |
| Task, setOnSucceeded and setOnFailed | Week 9 |
| ObservableList and TableView | Weeks 5 and 8 |

## Design Decisions

- **Password:** My code reads the MySQL password from an environment variable, UNI_DB_PASSWORD, so it is never saved in the code or pushed to GitHub.
- **PreparedStatement:** I use ? placeholders so user input cannot cause SQL injection.
- **Daemon threads:** The scheduler and the Tasks run on daemon threads, so the program ends when the window closes.
- **setOnFailed:** Database errors show in the status label instead of failing silently.
- **try-with-resources:** The connection, statement and result set are closed automatically.

## Questions I Can Answer

1. **Why does the UI never talk to the database directly?** A database call can be slow, and it would freeze the UI thread. Separating them also makes the code easier to change and test.
2. **Why use a Task for the database?** The Task runs call() on a background thread, and the result comes back on the UI thread through setOnSucceeded.
3. **What does the scheduler do?** It runs the heartbeat every 5 seconds on its own thread, without any user action.
4. **Why disable the Save button with a binding?** The button is disabled automatically while any field is empty, with no extra if statements.

## Test Results

- [x] The app starts and shows the sidebar.
- [x] The Save button is disabled until all three fields are filled.
- [x] A saved student appears in the table and in MySQL.
- [x] The heartbeat prints every 5 seconds.