package app.view.mainframe;

import app.model.diagcomposite.DiagramElement;
import app.model.event.ISubscriber;
import app.model.event.Notification;
import app.model.event.NotificationType;
import app.model.implementation.DiagramNode;
import app.controller.state.State;
import app.controller.state.StateManager;
import app.model.tree.MyNodeMutable;
import app.model.composite.AbstractClassyNode;
import app.model.implementation.PackageNode;
import app.model.implementation.ProjectExplorer;
import app.model.implementation.ProjectNode;
import app.view.tabs.TabbedPane;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class  PackageView extends JPanel implements ISubscriber {
    JLabel autorJe = new JLabel();
    JLabel projekatJe = new JLabel();
    JLabel pathJe = new JLabel();
    JLabel imeAutora = new JLabel("NoInput");
    JLabel path = new JLabel("NoInput");
    JLabel imeProjekta = new JLabel("No Project Selected");
    ProjectNode projectNode;
    PackageNode packageNode;
    StateManager stateManager;

    public PackageView() {
        projectNode = new ProjectNode();
        autorJe.setText("Autor: ");
        projekatJe.setText("Projekat: ");
        pathJe.setText("Path: ");
        stateManager = new StateManager();

        MainFrame.getInstance().getClassyTree().getTreeView().addSubscriber(this);
        JPanel containerPanel = new JPanel();
        containerPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        containerPanel.setSize(100, 50);
        startState(stateManager.getSelectionState());

        containerPanel.add(autorJe);
        containerPanel.add(imeAutora);
        containerPanel.add(Box.createRigidArea(new Dimension(100, 0)));
        containerPanel.add(projekatJe);
        containerPanel.add(imeProjekta);
        containerPanel.add(Box.createRigidArea(new Dimension(100, 0)));
        containerPanel.add(pathJe);
        containerPanel.add(path);
        MainFrame.getInstance().setPackageView(this);
        setLayout(new BorderLayout());
        add(containerPanel, BorderLayout.PAGE_START);
        add(TabbedPane.getInstance(), BorderLayout.CENTER);
    }

    @Override
    public void update(Notification notification) {
        if (notification.getType().equals(NotificationType.CHANGE_AUTHOR)) {
            projectNode.setAuthor(notification.getTitle());
            imeAutora.setText(projectNode.getAuthor());
        } else if (notification.getType().equals(NotificationType.NODE_SELECTION_CHANGED)) {
            MyNodeMutable selected = notification.getNode();
            if (!(selected.getClassyNode() instanceof DiagramElement)) {
                if (selected != null) {
                    if (!(selected.getClassyNode() instanceof ProjectExplorer)) {
                        if (selected.getClassyNode() instanceof ProjectNode) {
                            projectNode = (ProjectNode) selected.getClassyNode();
                            if (projectNode.getAuthor() != null) {
                                imeAutora.setText(projectNode.getAuthor());
                            } else {
                                imeAutora.setText("NoInput");
                            }
                            if (projectNode.getPath() != null) {
                                path.setText(projectNode.getPath());
                            }
                            else {
                                path.setText("NoInput");
                            }

                        } else {
                            AbstractClassyNode currentNode = selected.getClassyNode();
                            if (!(selected.getClassyNode().getParent() instanceof DiagramNode) || currentNode==null) {
                                while (!(currentNode instanceof ProjectNode)) {
                                    AbstractClassyNode temp = currentNode;
                                    temp = currentNode.getParent();
                                    currentNode = temp;
                                }
                                projectNode = (ProjectNode) currentNode;
                                MainFrame.getInstance().setCurrentProject(projectNode);
                                if (projectNode.getPath()!=null) {
                                    path.setText(projectNode.getPath());
                                }
                                if (projectNode.getAuthor()!=null) {
                                    imeAutora.setText(projectNode.getAuthor());
                                }
                            }

                        }
                        imeProjekta.setText(projectNode.getName());
                    }
                } else {
                    imeProjekta.setText("No Project Selected");
                    imeAutora.setText("NoInput");
                    path.setText("NoInput");
                }
            }

        } else if (notification.getType().equals(NotificationType.PACKAGE_SELECTED)) {
            TabbedPane.getInstance().setTrenutniPaket((PackageNode) notification.getNode().getClassyNode());
            MainFrame.getInstance().setPackageView(this);
            packageNode = (PackageNode) notification.getNode().getClassyNode();

        } else if (notification.getType().equals(NotificationType.RENAME)) {
            if (projectNode.getId().equals(notification.getId())) {
                projectNode.setName(notification.getTitle());
                imeProjekta.setText(projectNode.getName());
            }
        } else if (notification.getType().equals(NotificationType.PATH_CHANGED)) {
            if (projectNode.getId().equals(notification.getId())) {
                projectNode.setPath(notification.getTitle());
                path.setText(projectNode.getPath());
            }
        }

    }

    public void startState(State state) {
        stateManager.setCurrentState(state);
    }

    public StateManager getStateManager() {
        return stateManager;
    }

    public void setStateManager(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public void misKliknut(int x, int y, DiagramView diagramView) {
        stateManager.getCurrentState().misKliknut(x,y,diagramView);
    }

    public void misPovucen(int x, int y, DiagramView diagramView) {
        stateManager.getCurrentState().misPovucen(x,y,diagramView);
    }
    public void misOtpusten(int x, int y, DiagramView diagramView) {
        stateManager.getCurrentState().misOtpusten(x,y,diagramView);
    }


    public PackageNode getPackageNode() {
        return packageNode;
    }

    public void setPackageNode(PackageNode packageNode) {
        this.packageNode = packageNode;
    }
}
