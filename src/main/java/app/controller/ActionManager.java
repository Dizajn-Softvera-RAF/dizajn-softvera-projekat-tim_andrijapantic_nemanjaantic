package app.controller;

import app.controller.stateActions.*;

public class ActionManager {

    private ExitAction exitAction;
    private NewTreeChildAction newTreeChildAction;
    private NewPackageAction newPackageAction;
    private NewComponentAction newComponentAction;
    private InfoAction infoAction;
    private DeleteAction deleteAction;
    private NewDiagramAction newDiagramAction;
    private ChangeAuthorAction changeAuthorAction;
    private ChangePathAction changePathAction;
    private IntoAddInterclassState intoAddInterclassState;
    private IntoAddConnectionState intoAddConnectionState;
    private IntoSelectionState intoSelectionState;
    private IntoDeleteState intoDeleteState;
    private IntoEditState intoEditState;

    public ActionManager() {
        initilizeActions();
    }

    public void initilizeActions() {
        exitAction = new ExitAction();
        newTreeChildAction = new NewTreeChildAction();
        newPackageAction = new NewPackageAction();
        newComponentAction = new NewComponentAction();
        infoAction = new InfoAction();
        deleteAction = new DeleteAction();
        newDiagramAction = new NewDiagramAction();
        changeAuthorAction = new ChangeAuthorAction();
        changePathAction = new ChangePathAction();
        intoAddInterclassState = new IntoAddInterclassState();
        intoAddConnectionState = new IntoAddConnectionState();
        intoDeleteState = new IntoDeleteState();
        intoEditState = new IntoEditState();
        intoSelectionState = new IntoSelectionState();
    }

    public ExitAction getExitAction() {
        return exitAction;
    }

    public void setExitAction(ExitAction exitAction) {
        this.exitAction = exitAction;
    }

    public NewTreeChildAction getNewProjectAction() {
        return newTreeChildAction;
    }

    public void setNewProjectAction(NewTreeChildAction newTreeChildAction) {
        this.newTreeChildAction = newTreeChildAction;
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

    public DeleteAction getDeleteAction() {
        return deleteAction;
    }

    public void setDeleteAction(DeleteAction deleteAction) {
        this.deleteAction = deleteAction;
    }

    public NewDiagramAction getNewDiagramAction() {
        return newDiagramAction;
    }

    public void setNewDiagramAction(NewDiagramAction newDiagramAction) {
        this.newDiagramAction = newDiagramAction;
    }

    public ChangeAuthorAction getChangeAuthorAction() {
        return changeAuthorAction;
    }

    public void setChangeAuthorAction(ChangeAuthorAction changeAuthorAction) {
        this.changeAuthorAction = changeAuthorAction;
    }

    public ChangePathAction getChangePathAction() {
        return changePathAction;
    }

    public void setChangePathAction(ChangePathAction changePathAction) {
        this.changePathAction = changePathAction;
    }

    public IntoAddInterclassState getIntoAddInterclassState() {
        return intoAddInterclassState;
    }

    public void setIntoAddInterclassState(IntoAddInterclassState intoAddInterclassState) {
        this.intoAddInterclassState = intoAddInterclassState;
    }

    public IntoAddConnectionState getIntoAddConnectionState() {
        return intoAddConnectionState;
    }

    public void setIntoAddConnectionState(IntoAddConnectionState intoAddConnectionState) {
        this.intoAddConnectionState = intoAddConnectionState;
    }

    public IntoSelectionState getIntoSelectionState() {
        return intoSelectionState;
    }

    public void setIntoSelectionState(IntoSelectionState intoSelectionState) {
        this.intoSelectionState = intoSelectionState;
    }

    public IntoDeleteState getIntoDeleteState() {
        return intoDeleteState;
    }

    public void setIntoDeleteState(IntoDeleteState intoDeleteState) {
        this.intoDeleteState = intoDeleteState;
    }

    public IntoEditState getIntoEditState() {
        return intoEditState;
    }

    public void setIntoEditState(IntoEditState intoEditState) {
        this.intoEditState = intoEditState;
    }
}


