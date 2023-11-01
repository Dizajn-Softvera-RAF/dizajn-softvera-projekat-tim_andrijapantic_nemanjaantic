package controller;

public class ActionManager {

    private  ExitAction exitAction;
    private NewProjectAction newProjectAction;
    private NewPackageAction newPackageAction;
    private NewComponentAction newComponentAction;
    private InfoAction infoAction;

    public ActionManager() {initilizeActions();}

    public void initilizeActions() {
        exitAction = new ExitAction();
        newProjectAction = new NewProjectAction();
        newPackageAction = new NewPackageAction();
        newComponentAction = new NewComponentAction();
        infoAction = new InfoAction();
    }

    public ExitAction getExitAction() {
        return exitAction;
    }

    public void setExitAction(ExitAction exitAction) {
        this.exitAction = exitAction;
    }

    public NewProjectAction getNewProjectAction() {
        return newProjectAction;
    }

    public void setNewProjectAction(NewProjectAction newProjectAction) {
        this.newProjectAction = newProjectAction;
    }

    public NewPackageAction getNewPackageAction() {
        return newPackageAction;
    }

    public void setNewPackageAction(NewPackageAction newPackageAction) {
        this.newPackageAction = newPackageAction;
    }

    public NewComponentAction getNewComponentAction() {
        return newComponentAction;
    }

    public void setNewComponentAction(NewComponentAction newComponentAction) {
        this.newComponentAction = newComponentAction;
    }

    public InfoAction getInfoAction() {
        return infoAction;
    }

    public void setInfoAction(InfoAction infoAction) {
        this.infoAction = infoAction;
    }
}


