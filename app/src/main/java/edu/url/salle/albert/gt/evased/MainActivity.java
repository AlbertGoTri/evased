package edu.url.salle.albert.gt.evased;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.url.salle.albert.gt.evased.entities.Conversation;
import edu.url.salle.albert.gt.evased.entities.Event;
import edu.url.salle.albert.gt.evased.entities.User;
import edu.url.salle.albert.gt.evased.lab.EventLab;
import edu.url.salle.albert.gt.evased.lab.UserLab;


public class MainActivity extends AppCompatActivity {


    private Button noAccountBtn;
    private Button signInBtn;
    private TextView username;
    private TextView password;

    //---------------------------------------------------------------------------------EXTRA BUTTONS
    private Button MyMessagesBTN;
    private Button MyTimelineBTN;

    //---------------------------------------------------------------------------------INITIALIZE USERS + CONVERSATIONS
    UserLab userlab = new UserLab();
    ArrayList<User> users = userlab.getUsers();
    ArrayList<Conversation> conversations = userlab.getConversations();

    //---------------------------------------------------------------------------------INITIALIZE EVENTS
    EventLab eventlab = new EventLab( users);
    ArrayList<Event> events = eventlab.getEvents();



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
        //--------------------------------------------------------------------MY_MESSAGES_BUTTON----------------------------------------------------------------------------
        MyMessagesBTN = findViewById(R.id.MyMessages_Button);
        MyMessagesBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get the user that has the sign in
                User actualUser = users.get(0);

                //arraylist with the converations with the user participates
                ArrayList<Conversation> final_conversations = new ArrayList<>();

                //get only the conversations where user participates
                for(Conversation conv: conversations){
                    if(conv.getReceiver() == users.get(0) || conv.getSender() == users.get(0) ){
                        final_conversations.add(conv);
                    }
                }

                Intent intent = new Intent(getApplicationContext(), MyMessages.class);
                for(int i = 0; i < final_conversations.size(); i++){
                    intent.putExtra("conv" + i, final_conversations.get(i));
                }
                intent.putExtra("actualUser", actualUser);
                intent.putExtra("NumOfConv", final_conversations.size());




                startActivity(intent);
            }
        });

        //--------------------------------------------------------------------MY_TIMELINE_BUTTON----------------------------------------------------------------------------
        MyTimelineBTN = findViewById(R.id.MyTimeline_Button);
        MyTimelineBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyTimeline.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("events", events);
                intent.putExtras(bundle);

                startActivity(intent);
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