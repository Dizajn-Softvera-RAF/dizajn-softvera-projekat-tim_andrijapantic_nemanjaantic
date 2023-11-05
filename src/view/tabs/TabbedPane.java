package view.tabs;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.UUID;

public class TabbedPane  extends JTabbedPane {

    private static TabbedPane instance = null;
    private ArrayList<Tab> listaTabova = new ArrayList<>();

    private Tab selectedTab;

    public TabbedPane() {

        addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                TabbedPane sourceTabbedPane = (TabbedPane) e.getSource();
                String name = sourceTabbedPane.getTitleAt(sourceTabbedPane.getSelectedIndex());
                int index = indexOfTab(name);
                for (Tab tab : listaTabova) {

                    if (tab.getTitle().equals(name)) {

                        selectedTab = tab;
                    }
                }

            }
        });
    }

    public void addNewPane(String projekat, UUID id, MyNodeMutable nodeMutable) {
        addTab(new Tab(this, projekat, id), nodeMutable);
    }

    public static TabbedPane getInstance() {
        if (instance == null) {
            instance = new TabbedPane();
        }
        return instance;
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

    private void addTab(Tab tab, MyNodeMutable nodeMutable) {
        if (selectedTab == null) {
            selectedTab = tab;
        }
        MapView mapView = new MapView(tab);
        if (nodeMutable != null) {
            MapNode mapNode = (MapNode) nodeMutable.getMapNode();
            for (ElementNode e: mapNode.getChildren()) {
                if(e.element instanceof Theme){

                    mapView.getM().getElementArrayList().add((Theme) e.element);
                    mapView.getElementPainters().add(new ThemePainter((Theme) e.element, null));
                } else if(e.element instanceof Link){

                    mapView.getM().getElementArrayList().add((Link) e.element);
                    mapView.getElementPainters().add(new LinkPainter((Link) e.element, null));
                }
            }
        }
        mapView.repaint();
        tab.setMapView(mapView);
        this.addTab(tab.getTitle(), mapView);
        this.setTabComponentAt(indexOfTab(tab.getTitle()), tab.getHeader());
        MainFrame.getInstance().getMapTree().getTreeView().addSubscriber(tab);
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

    public void removeTab(String tabName, UUID id) {

        if (tabName != null) {
            int index = TabbedPane.getInstance().indexOfTab(tabName);
            if (index >= 0) {


                TabbedPane.getInstance().remove(index);

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
}
