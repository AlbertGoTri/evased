package edu.url.salle.albert.gt.evased;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import edu.url.salle.albert.gt.evased.Adapters.EventsTypeAdapter;
import edu.url.salle.albert.gt.evased.Adapters.LocationsAdapter;
import edu.url.salle.albert.gt.evased.databinding.ActivityEventsScreenBinding;
import edu.url.salle.albert.gt.evased.databinding.ActivityMyMessagesBinding;
import edu.url.salle.albert.gt.evased.entities.EventsTypes;
import edu.url.salle.albert.gt.evased.entities.Location;

public class Events_screen_activity extends DrawerActivity {

    ActivityEventsScreenBinding activityEventsScreenBinding;


    private LocationsAdapter locationsAdapter;
    private EventsTypeAdapter eventsTypeAdapter;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEventsScreenBinding = ActivityEventsScreenBinding.inflate(getLayoutInflater());
        setContentView(activityEventsScreenBinding.getRoot());
        allocateActivityTitle("Events");
        //setContentView(R.layout.activity_events_screen);

        List<Location> locations = new ArrayList<>();
        ImageButton imageButton = null;
        locations.add(new Location(imageButton, "ana 3yiit"));
        locations.add(new Location(imageButton, "chari3 zbi"));
        locations.add(new Location(imageButton, "wa l9lawi"));


        ArrayList<EventsTypes> buttons_types = new ArrayList<>();
        Button button = null;
        buttons_types.add(new EventsTypes(button, "art"));
        buttons_types.add(new EventsTypes(button, "sport"));
        buttons_types.add(new EventsTypes(button, "dance"));
        buttons_types.add(new EventsTypes(button, "music"));

        //set up the RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.task_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //< - - - WHY "this" ??
        locationsAdapter = new LocationsAdapter(this, locations);
        //locationsAdapter.
        recyclerView.setAdapter(locationsAdapter);


        //set up the RecyclerView
        RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.buttons_events_recycler_view);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        eventsTypeAdapter = new EventsTypeAdapter(this, buttons_types);
        recyclerView2.setAdapter(eventsTypeAdapter);

        }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    }