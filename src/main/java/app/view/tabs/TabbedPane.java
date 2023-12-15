package app.view.tabs;

import app.model.implementation.DiagramNode;
import app.model.implementation.PackageNode;
import app.model.tree.MyNodeMutable;
import app.view.mainframe.DiagramView;
import app.view.mainframe.MainFrame;
import app.view.mainframe.PackageView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;

public class TabbedPane extends JTabbedPane {

    private static TabbedPane instance = null;
    private ArrayList<Tab> listaTabova = new ArrayList<>();
    private ArrayList<Tab> trenutniTaboviZaBrisanje = new ArrayList<>();

    private Tab selectedTab;
    private PackageNode trenutniPaket = null;

    private ArrayList<UUID> blockedTaboviId;

    public TabbedPane() {

        addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                TabbedPane sourceTabbedPane = (TabbedPane) e.getSource();
                try {
                    String name = sourceTabbedPane.getTitleAt(sourceTabbedPane.getSelectedIndex());
                    for (Tab tab : listaTabova) {

                        if (tab.getTitle().equals(name)) {

                            selectedTab = tab;
                        }
                    }
                } catch (IndexOutOfBoundsException exception) {
                }

            }
        });
    }

    public static TabbedPane getInstance() {
        if (instance == null) {
            instance = new TabbedPane();
        }
        return instance;
    }

    public void addNewPane(String ime, UUID id, DiagramNode node, MyNodeMutable nodeMutable) {
        addTab(new Tab(this, ime, id), node, nodeMutable);
    }

    public ArrayList<Tab> getListaTabova() {
        return listaTabova;
    }

    public Tab getSelectedTab() {
        return selectedTab;
    }

    public void setSelectedTab(Tab selectedTab) {
        this.selectedTab = selectedTab;
    }


    public void addTab(Tab tab, DiagramNode node, MyNodeMutable nodeMutable) {
        if (selectedTab == null) {
            selectedTab = tab;
        }
        DiagramView diagramView = new DiagramView(tab);
        diagramView.setDiagramNode(node);
        diagramView.setMyNodeMutable(nodeMutable);
        node.addSubscriber(diagramView);

        tab.setDiagramView(diagramView);
        this.addTab(tab.getTitle(), diagramView);
        this.setTabComponentAt(indexOfTab(tab.getTitle()), tab.getHeader());
        MainFrame.getInstance().getClassyTree().getTreeView().addSubscriber(tab);
        listaTabova.add(tab);

    }

    public boolean isTabPresent(String title) {

        int count = getTabCount();

        for (int i = 0; i < count; i++) {

            if (getTitleAt(i).equals(title)) {
                return true;
            }
        }
        return false;
    }

    public int getIndexOfTab(String title, UUID id) {
        int counter = this.getTabCount();
        for (int i = 0; i < counter; i++) {
            if (this.getListaTabova().get(i).getId().equals(id) && this.getListaTabova().get(i).getTitle().equals(title)) {
                return i;
            }
        }
        return -1;
    }

    public void closeTab(String tabName, UUID id) {

        if (tabName != null) {
            int index = TabbedPane.getInstance().indexOfTab(tabName);
            if (index >= 0) {


                TabbedPane.getInstance().remove(index);
                System.out.println("Closed tab: " + tabName);
            }
        } else {
            int counter = this.getTabCount();
            for (int i = 0; i < counter; i++) {
                if (this.getListaTabova().get(i).getId().equals(id)) {
                    TabbedPane.getInstance().removeTabAt(i);
                }
            }
        }
    }

    public void removeTab(String tabName, UUID id) {

        if (tabName != null) {
            int index = TabbedPane.getInstance().indexOfTab(tabName);
            if (index >= 0) {

                TabbedPane.getInstance().remove(index);
                System.out.println("Removan tab: " + tabName);
            }
        } else {
            int counter = this.getTabCount();
            for (int i = 0; i < counter; i++) {
                if (this.getListaTabova().get(i).getId().equals(id)) {
                    TabbedPane.getInstance().remove(i);
                }
            }
        }
    }

    public void closeAllTabs() {
        int tabCount = this.getTabCount();
        for (int i = tabCount - 1; i >= 0; i--) {
            this.remove(i);
        }
    }

    public Tab findTab(String title, UUID id) {
        for (Tab tab : this.getListaTabova()) {
            if (tab.getTitle().equals(title) && tab.getId().equals(id)) {
                return tab;
            }
        }
        return null;
    }

    public ArrayList<Tab> getTrenutniTaboviZaBrisanje() {
        return trenutniTaboviZaBrisanje;
    }

    public void setTrenutniTaboviZaBrisanje(ArrayList<Tab> trenutniTaboviZaBrisanje) {
        this.trenutniTaboviZaBrisanje = trenutniTaboviZaBrisanje;
    }

    public PackageNode getTrenutniPaket() {
        return trenutniPaket;
    }

    public void setTrenutniPaket(PackageNode trenutniPaket) {
        this.trenutniPaket = trenutniPaket;
    }


    public ArrayList<UUID> getBlockedTaboviId() {
        return blockedTaboviId;
    }

    public void setBlockedTaboviId(ArrayList<UUID> blockedTaboviId) {
        this.blockedTaboviId = blockedTaboviId;
    }
}
