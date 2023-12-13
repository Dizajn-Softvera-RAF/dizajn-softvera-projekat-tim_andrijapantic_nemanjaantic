package app.controller.state;

import app.controller.state.states.*;

public class StateManager {
    private AddInterclassState addInterclassState;
    private AddConnectionState addConnectionState;
    private SelectionState selectionState;
    private EditState editState;
    private DeleteState deleteState;
    private MoveState moveState;
    private ZoomInState zoomInState;
    private ZoomOutState zoomOutState;
    private DuplicationState duplicationState;
    private ZoomToFitState zoomToFitState;
    private State currentState;

    public StateManager() {
        initializeStates();
    }

    private void initializeStates() {
        addInterclassState = new AddInterclassState();
        addConnectionState = new AddConnectionState();
        selectionState = new SelectionState();
        editState = new EditState();
        deleteState = new DeleteState();
        moveState = new MoveState();
        zoomInState = new ZoomInState();
        zoomOutState = new ZoomOutState();
        duplicationState = new DuplicationState();
        zoomToFitState = new ZoomToFitState();
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public AddInterclassState getAddInterclassState() {
        return addInterclassState;
    }

    public void setAddInterclassState(AddInterclassState addInterclassState) {
        this.addInterclassState = addInterclassState;
    }

    public AddConnectionState getAddConnectionState() {
        return addConnectionState;
    }

    public void setAddConnectionState(AddConnectionState addConnectionState) {
        this.addConnectionState = addConnectionState;
    }

    public SelectionState getSelectionState() {
        return selectionState;
    }

    public void setSelectionState(SelectionState selectionState) {
        this.selectionState = selectionState;
    }

    public EditState getEditState() {
        return editState;
    }

    public void setEditState(EditState editState) {
        this.editState = editState;
    }

    public DeleteState getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(DeleteState deleteState) {
        this.deleteState = deleteState;
    }

    public MoveState getMoveState() {
        return moveState;
    }

    public void setMoveState(MoveState moveState) {
        this.moveState = moveState;
    }

    public ZoomInState getZoomInState() {
        return zoomInState;
    }

    public void setZoomInState(ZoomInState zoomInState) {
        this.zoomInState = zoomInState;
    }

    public ZoomOutState getZoomOutState() {
        return zoomOutState;
    }

    public void setZoomOutState(ZoomOutState zoomOutState) {
        this.zoomOutState = zoomOutState;
    }

    public DuplicationState getDuplicationState() {
        return duplicationState;
    }

    public void setDuplicationState(DuplicationState duplicationState) {
        this.duplicationState = duplicationState;
    }

    public ZoomToFitState getZoomToFitState() {
        return zoomToFitState;
    }

    public void setZoomToFitState(ZoomToFitState zoomToFitState) {
        this.zoomToFitState = zoomToFitState;
    }
}
