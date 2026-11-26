# Week 5 Notes: Advanced Swing Components

## Which Component for Which Data

| Component | Purpose | Real-world example |
|---|---|---|
| JList | A flat list of selectable items | WhatsApp chat list, Spotify playlist |
| JTable | Rows and columns of data, can be edited | Bank statement, inventory system |
| JTree | Hierarchical data, items inside items | Windows File Explorer, IDE project sidebar |
| JTabbedPane | Groups related content into tabs | Chrome settings (General / Advanced) |
| JMenuBar, JMenu, JMenuItem | Standard menu navigation | Notepad, Word, VS Code |
| JToolBar | Quick buttons for common actions | New, Open, Save buttons |
| JFileChooser | Lets the user pick a file to open or save | Save As dialog |
| JOptionPane | Simple popup dialogs | Are you sure? and error messages |
| JSplitPane | Divides the window into two resizable parts | Tree on the left, table on the right |

## Activity 1: JList and Custom Renderers

- **setCellRenderer()** installs my custom renderer.
- **getListCellRendererComponent()** is called by Swing for each visible row. I return a Component, usually a JLabel, and Swing draws it.
- In my contact list the renderer showed a green, orange or grey dot depending on the status (Online, Away, Offline).

1. **What does a custom renderer do?** It controls how each item looks. Instead of plain text I can add icons, colours, fonts or several labels in one row.
2. **How often is the renderer called?** For every visible row, possibly hundreds of times a second while scrolling. So the code must be fast and must not create heavy objects.
3. **Why does !e.getValueIsAdjusting() matter?** Without it, the listener fires more than once during a single click as the selection changes. The check makes it run only when the selection is final.

## Activity 2: JTable

| Class | Purpose |
|---|---|
| DefaultTableModel | Stores the rows and columns, with addRow() and removeRow() |
| JTable | The visual component that displays the model |
| JScrollPane | Gives scrollbars and shows the column headers |
| TableCellRenderer | Customises how a cell looks |
| TableCellEditor | Customises how a cell is edited |

- My renderer on the GPA column showed values below 3.0 in red.

1. **TableModel vs JTable:** The TableModel holds the data. The JTable only displays it. When I change the model, the view updates automatically.
2. **Why wrap a JTable in a JScrollPane?** Without it the column headers do not show and there are no scrollbars.
3. **How do I make a column non-editable?** Override isCellEditable(row, column) in the table model and return false for that column.

## Activity 3: JTree and JTabbedPane

| JTree concept | Description |
|---|---|
| DefaultMutableTreeNode | A single node in the tree |
| root | The top node |
| add(child) | Adds a child node |
| DefaultTreeModel | Manages the tree structure |
| getLastSelectedPathComponent() | Returns the selected node |
| TreeSelectionListener | Fires when the selection changes |

| JTabbedPane concept | Description |
|---|---|
| addTab(title, component) | Adds a tab with its content |
| getSelectedIndex() | Returns the active tab |
| ChangeListener | Fires when the active tab changes |

1. **JTree vs JList:** A JList shows a flat list. A JTree shows hierarchical data, like folders inside folders.
2. **What does addTab() do?** It adds a new tab with a title and a content component. The user clicks the tab to switch views.
3. **What is DefaultMutableTreeNode?** A tree node that can hold child nodes. Mutable means I can add or remove children after creating it.

## Activity 4: Menus, Toolbars and Dialogs

```
JMenuBar (the whole bar at the top)
 +-- JMenu ("File")
      +-- JMenuItem ("New")
      +-- JMenuItem ("Open")
      +-- JMenuItem ("Save")
      +-- separator
      +-- JMenuItem ("Exit")
 +-- JMenu ("Edit")
```

| Dialog | Purpose |
|---|---|
| JOptionPane.showMessageDialog | Show information |
| JOptionPane.showConfirmDialog | Ask a yes or no question |
| JOptionPane.showInputDialog | Ask the user to type something |
| JFileChooser.showOpenDialog | Pick an existing file to open |
| JFileChooser.showSaveDialog | Choose where to save a file |

1. **JMenu vs JMenuItem:** A JMenu is a top-level menu in the bar, like File. A JMenuItem is a clickable item inside a menu, like Save.
2. **showOpenDialog vs showSaveDialog:** showOpenDialog is for choosing an existing file to read. showSaveDialog is for choosing where to save a file.
3. **What does showConfirmDialog return?** An int constant: YES_OPTION, NO_OPTION, CANCEL_OPTION or CLOSED_OPTION. I compare it with == to see the user's choice.

- I used one Runnable for each action (new, open, save), so the menu item and the toolbar button share the same code.
- The word count uses a DocumentListener, which fires every time the text changes.

## Activity 5: Capstone File Manager

- The window is split with a JSplitPane. The folder tree is on the left and a table of files is on the right.
- Extras: a toolbar with Home and Up Folder, a menu, and a status bar showing the item count.
- Double-clicking a folder in the table opens it.

1. **How do the tree and the table communicate?** Through a TreeSelectionListener on the tree. When a folder is selected, the listener calls loadFolderContents(folder), which updates the table model.
2. **Why call tableModel.setRowCount(0) before loading files?** To clear the table first. Otherwise the files from the previous folder stay on screen.
3. **What does JSplitPane do?** It divides the window into two resizable sections. The user drags the divider to change their sizes.

## Final Self-Assessment

**Learning Outcome 1: Create and use advanced Swing components.** Rating (1-5): ___

**Learning Outcome 2: Implement custom renderers.** Rating (1-5): ___

How do I customise a JList row? I call setCellRenderer() with a subclass of DefaultListCellRenderer that overrides getListCellRendererComponent(). For a JTable, I call setCellRenderer() on a column of the column model, using a subclass of DefaultTableCellRenderer.

**Learning Outcome 3: Create dialogs, menus and toolbars.** Rating (1-5): ___

Four common JOptionPane methods:
1. showMessageDialog
2. showConfirmDialog
3. showInputDialog
4. showOptionDialog

**Learning Outcome 4: Build a multi-pane application.** Rating (1-5): ___

Did my capstone FileManager run successfully?
- [x ] Yes
- [ ] No