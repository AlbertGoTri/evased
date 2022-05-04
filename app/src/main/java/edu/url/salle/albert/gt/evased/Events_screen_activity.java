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

import java.util.ArrayList;

import edu.url.salle.albert.gt.evased.Adapters.EventsTypeAdapter;

public class Events_screen_activity extends AppCompatActivity {

    private Button btn_art;
    private Button btn_sport;
    private Button btn_music;
    private Button btn_celebrity;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_screen);

        //drawerLayout = findViewById(R.id.my_drawer_layout);
        //actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        //drawerLayout.addDrawerListener(actionBarDrawerToggle);
        //actionBarDrawerToggle.syncState();

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*ArrayList<Button> buttonArrayList = new ArrayList<>();
        buttonArrayList.add(btn_art);
        buttonArrayList.add(btn_celebrity);
        buttonArrayList.add(btn_music);
        buttonArrayList.add(btn_sport);

        RecyclerView recyclerView = null;
        EventsTypeAdapter adapter = new EventsTypeAdapter(buttonArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
         */
        }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    }