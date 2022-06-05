package edu.url.salle.albert.gt.evased;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private Button noAccountBtn;
    private Button signInBtn;
    private TextView username;
    private TextView password;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences("Userinfo", 0);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        noAccountBtn = findViewById(R.id.noAccountBtn);
        noAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "SWITCHING TO SIGN UP", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });

        signInBtn = findViewById(R.id.signInBtn);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check the user data
                String usernameValue = username.getText().toString();
                String passwordValue = password.getText().toString();

                String registeredUsername = preferences.getString("name", "");
                String registeredPassword = preferences.getString("password", "");

                if(usernameValue.equals(registeredUsername) && passwordValue.equals(registeredPassword)) {
                    Toast.makeText(MainActivity.this, "LOGIN SUCCESSFUL" + usernameValue + passwordValue, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, Events_screen_activity.class));
                }else{
                    Toast.makeText(MainActivity.this, "LOGIN FAILED -> " + usernameValue + " != " + registeredUsername + ", " + passwordValue + " != " + registeredPassword, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    /*
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private Button map_button;
    private Button events_maps_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        System.out.println("MAIN ACTIVITY");

        /*lateral_menu();

        map_button = findViewById(R.id.button_map);
        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        events_maps_btn = findViewById(R.id.button_test_events_location);
        events_maps_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Events_screen_activity.class);
                startActivity(intent);
                finish();
            }
        });


    }




    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            if(item.getItemId() == R.id.nav_events) {
                Intent intent = new Intent(MainActivity.this, Events_screen_activity.class);
                startActivity(intent);
                finish();
            }
            if(item.getItemId() == R.id.nav_map) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
                finish();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.lateral_menu, menu);
        return true;
    }

    public void lateral_menu() {
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

         */
}