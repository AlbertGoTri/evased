package edu.url.salle.albert.gt.evased.entities;

import android.widget.ImageButton;

public class Location {
    private ImageButton location_image;
    private String location_description;

    public Location(ImageButton location_image, String location_description) {
        this.location_image = location_image;
        this.location_description = location_description;
    }

    public ImageButton getLocation_image() {
        return location_image;
    }

    public String getLocation_description() {
        return location_description;
    }
}
