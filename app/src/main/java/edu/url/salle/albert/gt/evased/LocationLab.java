package edu.url.salle.albert.gt.evased;

import android.content.Context;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import edu.url.salle.albert.gt.evased.entities.Location;

public class LocationLab {
    private static LocationLab sLocationLab;

    private List<Location> mLocations;

    public static LocationLab get(Context context) {
        if (sLocationLab == null) {
            sLocationLab = new LocationLab(context);
        }

        return sLocationLab;
    }

    ImageButton imageButton;
    private LocationLab(Context context) {
        mLocations = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Location location = new Location(imageButton, "test test ");

            mLocations.add(location);
        }
    }

    public List<Location> getmLocations() {
        return mLocations;
    }

    public Location getLocation(String location_description) {
        for (Location single_location : mLocations) {
            if (single_location.getLocation_description().equals(location_description)) {
                return single_location;
            }
        }

        return null;
    }


}
