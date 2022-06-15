package edu.url.salle.albert.gt.evased;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationView;

import edu.url.salle.albert.gt.evased.Managers.UserConvEventManager;
import edu.url.salle.albert.gt.evased.entities.User;

public class DrawerActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    public UserConvEventManager manager = new UserConvEventManager();

    //TODO: instead of getting the user 0, get the user that has signed in the app.
    public User SignInUser = manager.getUsers().get(0);


    @Override
    public void setContentView(View view) {
        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer, null);
        FrameLayout container=  drawerLayout.findViewById(R.id.activityContainer);
        container.addView(view);
        super.setContentView(drawerLayout);

        Toolbar toolbar = drawerLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawer(GravityCompat.START);

                switch (item.getItemId()) {
                    case R.id.nav_timeline :
                        Intent timeline_intent =new Intent(getApplicationContext(), MyTimeline.class);
                        timeline_intent.putExtra("manager", manager);
                        startActivity(timeline_intent);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_messages :
                        Intent messages_intent = new Intent(getApplicationContext(), MyMessages.class);
                        User actualUser = manager.getUsers().get(0);
                        messages_intent.putExtra("actualUser", actualUser);
                        messages_intent.putExtra("manager", manager);
                        startActivity(messages_intent);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_events:
                        startActivity(new Intent(getApplicationContext(), Events_screen_activity.class));
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_search_users:
                        startActivity(new Intent(getApplicationContext(), Search_users.class));
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_map:
                        startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_account:
                        startActivity(new Intent(getApplicationContext(), MyAccountActivity.class));
                        overridePendingTransition(0, 0);
                        break;
                }
                return false;
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

protected void allocateActivityTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
}

}