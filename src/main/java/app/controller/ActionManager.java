package app.controller;

import app.controller.serializerActions.OpenProjectAction;
import app.controller.serializerActions.SaveProjectAction;
import app.controller.serializerActions.SaveProjectAsAction;
import app.controller.serializerActions.SaveTemplateAction;
import app.controller.state.stateActions.*;

public class ActionManager {

    private ExitAction exitAction;
    private NewTreeChildAction newTreeChildAction;
    private NewPackageAction newPackageAction;
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
    private IntoMoveState intoMoveState;
    private IntoZoomInState intoZoomInState;
    private IntoZoomOutState intoZoomOutState;
    private IntoDuplicationState intoDuplicationState;
    private IntoZoomToFitState intoZoomToFitState;
    private ScreenshotAction screenshotAction;
    private SaveProjectAction saveProjectAction;
    private OpenProjectAction openAction;
    private SaveProjectAsAction saveProjectAsAction;
    private SaveTemplateAction saveTemplateAction;

    public ActionManager() {
        initilizeActions();
    }

    public void initilizeActions() {
        exitAction = new ExitAction();
        newTreeChildAction = new NewTreeChildAction();
        newPackageAction = new NewPackageAction();
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
        intoMoveState = new IntoMoveState();
        intoZoomInState = new IntoZoomInState();
        intoZoomOutState = new IntoZoomOutState();
        intoDuplicationState = new IntoDuplicationState();
        intoZoomToFitState = new IntoZoomToFitState();
        screenshotAction = new ScreenshotAction();
        saveProjectAction = new SaveProjectAction();
        openAction = new OpenProjectAction();
        saveProjectAsAction = new SaveProjectAsAction();
        saveTemplateAction = new SaveTemplateAction();
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

    public IntoMoveState getIntoMoveState() {
        return intoMoveState;
    }

    public void setIntoMoveState(IntoMoveState intoMoveState) {
        this.intoMoveState = intoMoveState;
    }

    public IntoZoomInState getIntoZoomInState() {
        return intoZoomInState;
    }

    public void setIntoZoomInState(IntoZoomInState intoZoomInState) {
        this.intoZoomInState = intoZoomInState;
    }

    public IntoZoomOutState getIntoZoomOutState() {
        return intoZoomOutState;
    }

    public void setIntoZoomOutState(IntoZoomOutState intoZoomOutState) {
        this.intoZoomOutState = intoZoomOutState;
    }

    public IntoDuplicationState getIntoDuplicationState() {
        return intoDuplicationState;
    }

    public void setIntoDuplicationState(IntoDuplicationState intoDuplicationState) {
        this.intoDuplicationState = intoDuplicationState;
    }

    public IntoZoomToFitState getIntoZoomToFitState() {
        return intoZoomToFitState;
    }

    public void setIntoZoomToFitState(IntoZoomToFitState intoZoomToFitState) {
        this.intoZoomToFitState = intoZoomToFitState;
    }

    public ScreenshotAction getScreenshotAction() {
        return screenshotAction;
    }

    public void setScreenshotAction(ScreenshotAction screenshotAction) {
        this.screenshotAction = screenshotAction;
    }

    public SaveProjectAction getSaveAction() {
        return saveProjectAction;
    }

    public void setSaveAction(SaveProjectAction saveProjectAction) {
        this.saveProjectAction = saveProjectAction;
    }

    public OpenProjectAction getOpenAction() {
        return openAction;
    }

    public void setOpenAction(OpenProjectAction openAction) {
        this.openAction = openAction;
    }

    public SaveProjectAction getSaveProjectAction() {
        return saveProjectAction;
    }

    public void setSaveProjectAction(SaveProjectAction saveProjectAction) {
        this.saveProjectAction = saveProjectAction;
    }

    public SaveProjectAsAction getSaveProjectAsAction() {
        return saveProjectAsAction;
    }

    public void setSaveProjectAsAction(SaveProjectAsAction saveProjectAsAction) {
        this.saveProjectAsAction = saveProjectAsAction;
    }

    public SaveTemplateAction getSaveTemplateAction() {
        return saveTemplateAction;
    }

    public void setSaveTemplateAction(SaveTemplateAction saveTemplateAction) {
        this.saveTemplateAction = saveTemplateAction;
    }
}


