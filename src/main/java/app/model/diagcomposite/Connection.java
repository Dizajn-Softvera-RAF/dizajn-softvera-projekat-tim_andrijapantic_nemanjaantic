package app.model.diagcomposite;

public abstract class Connection extends DiagramElement {

    private Interclass fromInterclass;
    private Interclass toInterclass;

    public Interclass getFromInterclass() {
        return fromInterclass;
    }

    public void setFromInterclass(Interclass fromInterclass) {
        this.fromInterclass = fromInterclass;
    }

    public Interclass getToInterclass() {
        return toInterclass;
    }

    public void setToInterclass(Interclass toInterclass) {
        this.toInterclass = toInterclass;
    }
}
