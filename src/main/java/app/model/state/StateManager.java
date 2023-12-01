package app.model.state;

import app.model.state.states.*;

public class StateManager {
    private AddInterclassState addInterclassState;
    private AddConnectionState addConnectionState;
    private SelectionState selectionState;
    private EditState editState;
    private DeleteState deleteState;
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
}
