package view.mainframe;

import controller.ActionManager;
import controller.diagramActions.DoubleClickAction;
import core.AppCore;
import model.event.ISubscriber;
import model.event.Notification;
import model.event.NotificationType;
import model.tree.ClassyTreeImplementation;
import model.tree.MyNodeMutable;
import view.dialogs.MessagePane;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame implements ISubscriber {

    private static MainFrame instance = null;
    private static ToolBar toolBar;
    private ClassyTreeImplementation classyTree;
    private JPanel leftPanel = new JPanel();
    private MyNodeMutable selectedNode;
    private ActionManager actionManager;
    private List<MyNodeMutable> myNodeMutables;
    private Menu menu;

    public static MainFrame getInstance() {

        if (instance == null) {
            instance = new MainFrame();
            instance.instilize();

        }
        return instance;
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

    private void instilize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        actionManager = new ActionManager();
        myNodeMutables = new ArrayList<MyNodeMutable>();
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

        JLabel autor = new JLabel();
        JLabel projekat = new JLabel();
        autor.setText("Autor");
        projekat.setText("Projekat");
        add(autor);
        add(projekat);


        add(leftPanel, BorderLayout.WEST);
        leftPanel.add(new JScrollPane(projectExplorer));

        revalidate();

        menu = new Menu();
        setJMenuBar(menu);


        toolBar = new ToolBar();
        toolBar.setSize(100, 50);
        add(toolBar, BorderLayout.NORTH);

        add(new PackageView());

        projectExplorer.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {

                selectedNode = (MyNodeMutable) projectExplorer.getLastSelectedPathComponent();
                classyTree.getTreeView().notifySubscribers(new Notification(NotificationType.NODE_SELECTION_CHANGED, selectedNode));
            }
        });


        projectExplorer.addMouseListener(new DoubleClickAction());
        setIconImage(new ImageIcon(getClass().getResource("/images/ClassyCrafT.png")).getImage());
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

    public List<MyNodeMutable> getMyNodeMutables() {
        return myNodeMutables;
    }

    public void setMyNodeMutables(List<MyNodeMutable> myNodeMutables) {
        this.myNodeMutables = myNodeMutables;
    }

    public ClassyTreeImplementation getClassyTree() {
        return classyTree;
    }

    public void setClassyTree(ClassyTreeImplementation classyTree) {
        this.classyTree = classyTree;
    }
}
