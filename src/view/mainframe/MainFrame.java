package view.mainframe;

import controller.ActionManager;
import diagramActions.DoubleClickAction;
import model.core.AppCore;
import model.event.ISubscriber;
import model.event.Notification;
import model.tree.ClassyTreeImplementation;
import model.tree.MyNodeMutable;
import view.dialogs.MessagePane;
import view.tabs.TabbedPane;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.awt.*;

public class MainFrame extends JFrame implements ISubscriber {

    private static MainFrame instance = null;

    private ClassyTreeImplementation classyTree;

    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();

    private MyNodeMutable selectedNode;

    private static ToolBar toolBar;

    private ActionManager actionManager;
    private Menu menu;

    public static MainFrame getInstance() {

        if (instance == null) {
            instance = new MainFrame();
            instance.instilize();

        }
        return instance;
    }

    private void instilize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        actionManager = new ActionManager();

        classyTree = new ClassyTreeImplementation();
        JTree projectExplorer = classyTree.generateTree(AppCore.getInstance().getClassyRepository().getProjectExplorer());

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screensize = kit.getScreenSize();
        int screenHeight = screensize.height;
        int screenWidth = screensize.width;

        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ClassyCrafT");

        setLayout(new BorderLayout());

        leftPanel.setLayout(new BorderLayout());


        leftPanel.setSize(100, 50);

        projectExplorer.setBorder(new EmptyBorder(15, 15, 15, 15));
        projectExplorer.setEditable(true);

        leftPanel.add(projectExplorer, BorderLayout.CENTER);
        leftPanel.add(new JSeparator(1), BorderLayout.EAST);


        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        revalidate();

        menu = new Menu();
        setJMenuBar(menu);


        toolBar = new ToolBar();
        toolBar.setSize(100, 50);
        add(toolBar, BorderLayout.NORTH);

        add(TabbedPane.getInstance());

        projectExplorer.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {

                selectedNode = (MyNodeMutable) projectExplorer.getLastSelectedPathComponent();

            }
        });


        projectExplorer.addMouseListener(new DoubleClickAction());

    }




    public static void setInstance(MainFrame instance) {
        MainFrame.instance = instance;
    }

    public static ToolBar getToolBar() {
        return toolBar;
    }

    public static void setToolBar(ToolBar toolBar) {
        MainFrame.toolBar = toolBar;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public void setActionManager(ActionManager actionManager) {
        this.actionManager = actionManager;
    }

    @Override
    public void update(Notification notification) {
        new MessagePane(notification.getMessage());
    }

    public MyNodeMutable getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(MyNodeMutable selectedNode) {
        this.selectedNode = selectedNode;
    }

    public ClassyTreeImplementation getClassyTree() {
        return classyTree;
    }

    public void setClassyTree(ClassyTreeImplementation classyTree) {
        this.classyTree = classyTree;
    }
}
