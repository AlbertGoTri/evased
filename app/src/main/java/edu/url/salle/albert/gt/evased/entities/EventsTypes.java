package edu.url.salle.albert.gt.evased.entities;

import android.widget.Button;

public class EventsTypes {
    private Button btn_art;
    private Button btn_sport;
    private Button btn_music;
    private Button btn_celebrity;

    public EventsTypes(Button btn_art, Button btn_sport, Button btn_music, Button btn_celebrity) {
        this.btn_art = btn_art;
        this.btn_sport = btn_sport;
        this.btn_music = btn_music;
        this.btn_celebrity = btn_celebrity;
    }

    public Button getBtn_art() {
        return btn_art;
    }

    public Button getBtn_sport() {
        return btn_sport;
    }

    public Button getBtn_music() {
        return btn_music;
    }

    public Button getBtn_celebrity() {
        return btn_celebrity;
    }
}
