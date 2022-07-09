package edu.url.salle.albert.gt.evased.entities;

import android.widget.ImageButton;

public class Location {
    private String link;
    private String location_description;

    public Location(String link, String location_description) {
        this.link = link;
        this.location_description = location_description;
    }

    public String getLocation_image() {
        return link;
    }

    public String getLocation_description() {
        return location_description;
    }
}
