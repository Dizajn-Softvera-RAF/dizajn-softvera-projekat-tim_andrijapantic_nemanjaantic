package message;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    ErrType type;
    String stringContent;
    PossibleErr content;
    LocalDateTime time;
    DateTimeFormatter formatter;

    public Message(PossibleErr err) {
        if (err.equals(PossibleErr.PROJECT_MUST_BE_SELECTED_TO_CREATE_A_PACKAGE)) {
            stringContent = "Ne mozes praviti pakete ako nisi selektovao projekat.";
            this.type = ErrType.ERROR;
        } else if (err.equals(PossibleErr.PACKAGE_MUST_BE_SELECTED_TO_CREATE_A_COMPONENT)) {
            stringContent = "Ne mozes praviti komponente ako nisi selektovao paket.";
            this.type = ErrType.ERROR;
        } else if (err.equals(PossibleErr.PROJECT_EXPLORER_MUST_BE_SELECTED_TO_CREATE_A_PROJECT)) {
            stringContent = "Moras selektovati Project Explorer da bi pravio nove projekte.";
            this.type = ErrType.ERROR;
        } else if (err.equals(PossibleErr.NAME_CANNOT_BE_EMPTY)) {
            stringContent = "Ime ne sme biti prazno.";
            this.type = ErrType.ERROR;
        } else if (err.equals(PossibleErr.NAME_CANNOT_BE_CHANGED)) {
            stringContent = "Ne mozes promeniti ime ovom elementu.";
            this.type = ErrType.ERROR;
        } else if (err.equals(PossibleErr.UNABLE_TO_ADD_PROJECT)) {
            stringContent = "Ne mozes dodati projekat.";
            this.type = ErrType.ERROR;
        } else if (err.equals(PossibleErr.NODE_CANNOT_BE_DELETED)) {
            stringContent = "Ne mozes obrisati selektovani node.";
            this.type = ErrType.ERROR;
        } else if (err.equals(PossibleErr.UNABLE_TO_ADD_PACKAGE)) {
            stringContent = "Ne mozes dodati paket.";
            this.type = ErrType.ERROR;
        } else if (err.equals(PossibleErr.PROJECT_CANNOT_BE_DELETED)) {
            stringContent = "Ne mozes obrisati projekat.";
            this.type = ErrType.ERROR;
        } else if (err.equals(PossibleErr.UNABLE_TO_ADD_COMPONENT)) {
            stringContent = "Ne mozes dodati komponentu.";
            this.type = ErrType.ERROR;
        } else if (err.equals(PossibleErr.CANNOT_DELETE_ELEMENT)) {
            stringContent = "Moras selektovati neki element da bi ga obrisao.";
            this.type = ErrType.WARNING;
        } else if (err.equals(PossibleErr.ONLY_USE_DOUBLE_CLICK_ON_NODES)) {
            stringContent = "Dupli klik koristiti samo na node-ovima.";
            this.type = ErrType.INFO;
        }
        time = LocalDateTime.now();
        formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    }

    public ErrType getType() {
        return type;
    }

    public void setType(ErrType type) {
        this.type = type;
    }

    public String getStringContent() {
        return stringContent;
    }

    public void setStringContent(String stringContent) {
        this.stringContent = stringContent;
    }

    public PossibleErr getContent() {
        return content;
    }

    public void setContent(PossibleErr content) {
        this.content = content;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
