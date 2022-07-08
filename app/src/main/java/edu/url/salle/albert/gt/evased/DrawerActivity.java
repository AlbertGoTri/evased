package edu.url.salle.albert.gt.evased;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.SearchView;

import com.google.android.material.navigation.NavigationView;

import edu.url.salle.albert.gt.evased.Managers.UserConvEventManager;
import edu.url.salle.albert.gt.evased.entities.User;

public class DrawerActivity extends AppCompatActivity{


    DrawerLayout drawerLayout;
    public UserConvEventManager manager = new UserConvEventManager();


    public User SignInUser;


    @Override
    public void setContentView(View view) {
        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer, null);
        FrameLayout container=  drawerLayout.findViewById(R.id.activityContainer);
        container.addView(view);
        super.setContentView(drawerLayout);

        SignInUser = getUser();

        Toolbar toolbar = drawerLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawer(GravityCompat.START);

                switch (item.getItemId()) {
                    case R.id.nav_timeline :
                        /*
                        Intent timeline_intent =new Intent(getApplicationContext(), MyTimeline.class);
                        timeline_intent.putExtra("manager", manager);
                        startActivity(timeline_intent);
                        overridePendingTransition(0, 0);

                         */
                        break;
                    case R.id.nav_messages :
                        /*
                        Intent messages_intent = new Intent(getApplicationContext(), MyMessages.class);
                        User actualUser = manager.getUsers().get(0);
                        messages_intent.putExtra("actualUser", actualUser);
                        messages_intent.putExtra("manager", manager);
                        startActivity(messages_intent);
                        overridePendingTransition(0, 0);

                         */
                        break;
                    case R.id.nav_events:
                        /*
                        startActivity(new Intent(getApplicationContext(), Events_screen_activity.class));
                        overridePendingTransition(0, 0);

                         */
                        break;
                    case R.id.nav_search_users:
                        /*
                        startActivity(new Intent(getApplicationContext(), Search_users.class));
                        overridePendingTransition(0, 0);

                         */
                        break;
                    case R.id.nav_map:
                        /*
                        startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                        overridePendingTransition(0, 0);

                         */
                        break;
                    case R.id.nav_account:
                        /*
                        startActivity(new Intent(getApplicationContext(), MyAccountActivity.class));
                        overridePendingTransition(0, 0);

                         */
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

    public User getUser(){

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        int s1 = sh.getInt("id", 999999);
        String s2 = sh.getString("name", "no encontrado");
        String s3 = sh.getString("last_name", "no encontrado");
        String s4 = sh.getString("email", "no encontrado");
        String s5 = sh.getString("password", "no encontrado");
        String s6 = sh.getString("image", "no encontrado");
        System.out.println("nameeeeeeeeeeee: " + s1 + ", " + s2 + ", " + s3 + ", " + s4 + ", " + s5 + ", " + s6);

        return new User(s1,s2,s3,s4,s5,s6);
    }


}