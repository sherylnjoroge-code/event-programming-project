import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class FileManager {

    private JFrame frame;
    private JTree folderTree;
    private JTable fileTable;
    private DefaultTableModel tableModel;
    private JLabel statusBar;
    private File currentFolder;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FileManager().createAndShow());
    }

    private void createAndShow() {
        frame = new JFrame("File Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);

        // ============================================================
        // 1. MENU BAR
        // ============================================================
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem refreshItem = new JMenuItem("Refresh");
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(refreshItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        frame.setJMenuBar(menuBar);

        // ============================================================
        // 2. TOOLBAR
        // ============================================================
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);

        JButton refreshBtn = new JButton("Refresh");
        JButton upBtn = new JButton("Up Folder");
        JButton homeBtn = new JButton("Home");

        toolbar.add(homeBtn);
        toolbar.add(upBtn);
        toolbar.addSeparator();
        toolbar.add(refreshBtn);
        frame.add(toolbar, BorderLayout.NORTH);

        // ============================================================
        // 3. TREE (left)
        // ============================================================
        DefaultMutableTreeNode root =
                createNode(new File(System.getProperty("user.home")));
        folderTree = new JTree(new DefaultTreeModel(root));
        folderTree.setFont(new Font("Arial", Font.PLAIN, 14));
        folderTree.setRowHeight(22);

        // Custom renderer: show only the folder name, not the full path
        folderTree.setCellRenderer(new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(
                    JTree tree, Object value, boolean sel, boolean expanded,
                    boolean leaf, int row, boolean hasFocus) {
                super.getTreeCellRendererComponent(
                        tree, value, sel, expanded, leaf, row, hasFocus);
                Object obj = ((DefaultMutableTreeNode) value).getUserObject();
                if (obj instanceof File) {
                    File f = (File) obj;
                    setText(f.getName().isEmpty() ? f.getPath() : f.getName());
                }
                return this;
            }
        });

        // LAZY LOADING: load a folder's sub-folders only when it is expanded
        folderTree.addTreeWillExpandListener(new TreeWillExpandListener() {
            @Override
            public void treeWillExpand(TreeExpansionEvent event) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                        event.getPath().getLastPathComponent();
                loadChildren(node);
            }

            @Override
            public void treeWillCollapse(TreeExpansionEvent event) { }
        });

        folderTree.expandRow(0);

        // The tree talks to the table through this listener
        folderTree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode selected = (DefaultMutableTreeNode)
                    folderTree.getLastSelectedPathComponent();
            if (selected != null && selected.getUserObject() instanceof File) {
                File folder = (File) selected.getUserObject();
                if (folder.isDirectory()) {
                    loadFolderContents(folder);
                }
            }
        });

        // ============================================================
        // 4. TABLE (right)
        // ============================================================
        String[] columns = {"Name", "Type", "Size", "Modified"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        fileTable = new JTable(tableModel);
        fileTable.setRowHeight(22);
        fileTable.setFont(new Font("Arial", Font.PLAIN, 13));
        fileTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));

        // SplitPane between the tree and the table
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(folderTree),
                new JScrollPane(fileTable));
        splitPane.setDividerLocation(280);
        frame.add(splitPane, BorderLayout.CENTER);

        // ============================================================
        // 5. STATUS BAR
        // ============================================================
        statusBar = new JLabel("Ready");
        statusBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        frame.add(statusBar, BorderLayout.SOUTH);

        // ============================================================
        // 6. EVENT HANDLERS
        // ============================================================

        // Load the home directory first
        loadFolderContents(new File(System.getProperty("user.home")));

        homeBtn.addActionListener(e -> {
            File home = new File(System.getProperty("user.home"));
            loadFolderContents(home);
        });

        upBtn.addActionListener(e -> {
            if (currentFolder != null && currentFolder.getParentFile() != null) {
                loadFolderContents(currentFolder.getParentFile());
            }
        });

        refreshBtn.addActionListener(e -> {
            if (currentFolder != null) {
                loadFolderContents(currentFolder);
            }
        });

        refreshItem.addActionListener(e -> refreshBtn.doClick());
        exitItem.addActionListener(e -> System.exit(0));

        aboutItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame,
                    "File Manager v1.0\nBuilt with Swing\nWeek 5 Capstone Project",
                    "About", JOptionPane.INFORMATION_MESSAGE);
        });

        // Double-click a row: open the folder, or show the file path
        fileTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = fileTable.getSelectedRow();
                    if (row >= 0) {
                        String fileName = (String) tableModel.getValueAt(row, 0);
                        File file = new File(currentFolder, fileName);
                        if (file.isDirectory()) {
                            loadFolderContents(file);
                        } else {
                            JOptionPane.showMessageDialog(frame,
                                    "Would open: " + file.getAbsolutePath(),
                                    "File Selected",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        });

        frame.setVisible(true);
    }

    // Create a tree node for a folder, with a placeholder child
    // so that the node shows an expand arrow
    private DefaultMutableTreeNode createNode(File folder) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(folder);
        node.add(new DefaultMutableTreeNode("Loading..."));
        return node;
    }

    // Replace the placeholder with the real sub-folders (one level only)
    private void loadChildren(DefaultMutableTreeNode node) {
        if (node.getChildCount() == 0) return;

        DefaultMutableTreeNode first =
                (DefaultMutableTreeNode) node.getFirstChild();
        if (!(first.getUserObject() instanceof String)) return; // already loaded

        node.removeAllChildren();
        File folder = (File) node.getUserObject();
        File[] children = folder.listFiles();

        if (children != null) {
            Arrays.sort(children);
            for (File child : children) {
                if (child.isDirectory() && !child.isHidden()) {
                    node.add(createNode(child));
                }
            }
        }
        ((DefaultTreeModel) folderTree.getModel()).nodeStructureChanged(node);
    }

    // Load the contents of a folder into the table
    private void loadFolderContents(File folder) {
        if (!folder.isDirectory()) return;

        currentFolder = folder;
        tableModel.setRowCount(0); // Clear the old rows first

        File[] files = folder.listFiles();
        if (files != null) {
            // Folders first, then files, each sorted by name
            Arrays.sort(files, (a, b) -> {
                if (a.isDirectory() != b.isDirectory()) {
                    return a.isDirectory() ? -1 : 1;
                }
                return a.getName().compareToIgnoreCase(b.getName());
            });

            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
            for (File file : files) {
                String type = file.isDirectory()
                        ? "Folder" : getExtension(file.getName());
                String size = file.isDirectory()
                        ? "-" : formatSize(file.length());
                String modified = sdf.format(file.lastModified());
                tableModel.addRow(new Object[]{
                        file.getName(), type, size, modified
                });
            }
        }

        frame.setTitle("File Manager - " + folder.getAbsolutePath());
        statusBar.setText(files != null
                ? files.length + " items in " + folder.getName()
                : "Could not read folder");
    }

    private String getExtension(String name) {
        int dot = name.lastIndexOf('.');
        return dot >= 0 ? name.substring(dot + 1).toUpperCase() : "File";
    }

    private String formatSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        if (bytes < 1024 * 1024) return String.format("%.1f KB", bytes / 1024.0);
        return String.format("%.1f MB", bytes / (1024.0 * 1024.0));
    }
}