
## Activity 3: FXML and Scene Builder

- **FXML** is an XML file that describes the layout of the UI. Scene Builder lets me design it by dragging components, and it writes the XML for me.
- The fx:id attribute gives a component a name, so the controller can find it.
- The fx:controller attribute links the FXML file to its controller class.

## Activity 4: Self-Check

1. **What does @FXML do?** It tells JavaFX to inject the component from the FXML file into the Java variable. For example, @FXML private Button loginButton; picks up the button with fx:id="loginButton".
2. **Why use FXML instead of writing everything in Java?** It separates design from logic. A designer can change the FXML in Scene Builder without touching the Java code, and a developer can focus on the logic. The code becomes cleaner and easier to maintain.

## MVC in My Login App

- **View:** login.fxml
- **Controller:** LoginController.java, where the button click and the validation happen





## Activity 5: Capstone Dashboard

- **Layout used:** BorderPane for the whole window, with an HBox for the top bar, a VBox for the sidebar and a VBox for the centre. The cards sit in an HBox, and each card is a VBox.
- **Styling:** I used setStyle() with CSS, like -fx-background-color and -fx-background-radius, to colour the bars and round the cards.
- **Helper method:** createCard() builds each card, so I do not repeat the same code three times.
- This top bar, sidebar and centre layout is used by real dashboards like AWS and Shopify.

## Final Self-Assessment

**Learning Outcome 1: Create a basic JavaFX application**
- [x] I can create a Stage, Scene and root Node from memory.
- [x] I can compile and run the app from the terminal.

**Learning Outcome 2: Design UI layouts (programmatic and FXML)**
- [x] I understand the difference between VBox, HBox and BorderPane.
- [x] I can write an FXML file and link it to a controller.

**Learning Outcome 3: JavaFX architecture and threading**
- [x] The JavaFX UI thread is called the "JavaFX Application Thread".
- [x] Long operations must not block this thread, or the UI freezes, as in Swing.

**Learning Outcome 4: Build a functional application**
- [x] I wired a button in FXML to a method in a controller using onAction.
- [x] I completed the capstone dashboard.

## Swing vs JavaFX

| Feature | Swing | JavaFX |
|---|---|---|
| UI thread | Event Dispatch Thread (EDT) | JavaFX Application Thread |
| Main window | JFrame | Stage |
| Content | Content pane | Scene |
| Styling | Limited | CSS |
| Design tool | NetBeans Matisse | Scene Builder and FXML |
| Status | Maintenance mode | Actively developed |