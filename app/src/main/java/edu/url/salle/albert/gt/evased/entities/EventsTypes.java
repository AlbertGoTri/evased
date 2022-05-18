package edu.url.salle.albert.gt.evased.entities;

import android.widget.Button;

public class EventsTypes {
    private Button button;
    private String string;

    public EventsTypes(Button button, String string) {
        this.button = button;
        this.string = string;
    }

    public Button getButton() {
        return button;
    }

    public String getString() {
        return string;
    }
}
