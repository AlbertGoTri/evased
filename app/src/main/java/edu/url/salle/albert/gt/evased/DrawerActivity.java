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

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import edu.url.salle.albert.gt.evased.Managers.UserConvEventManager;
import edu.url.salle.albert.gt.evased.entities.Conversation;
import edu.url.salle.albert.gt.evased.entities.Event;
import edu.url.salle.albert.gt.evased.entities.Message;
import edu.url.salle.albert.gt.evased.entities.User;

public class DrawerActivity extends AppCompatActivity{


    DrawerLayout drawerLayout;
    public UserConvEventManager manager = new UserConvEventManager();
    String url_GETALLMESSAGES = "http://puigmal.salle.url.edu/api/v2/messages/users";

    public String userToken;
    public User SignInUser;
    ArrayList<Event> events;
    public int num_conv;



    @Override
    public void setContentView(View view) {
        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer, null);
        FrameLayout container=  drawerLayout.findViewById(R.id.activityContainer);
        container.addView(view);
        super.setContentView(drawerLayout);

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        num_conv = sh.getInt("x_index", 0);

        SignInUser = updateUser();

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


                        timeline_intent.putExtra("indexx", events.size());
                        for(int i = 0; i< events.size() ; i++){
                            timeline_intent.putExtra("i", events.get(i));
                            System.out.println("\nEVENT: " + events.get(i).getName() + "");

                        }
                        startActivity(timeline_intent);
                        overridePendingTransition(0, 0);


                        break;
                    case R.id.nav_messages :


                        Intent messages_intent = new Intent(getApplicationContext(), MyMessages.class);
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

    public User updateUser(){

        User ret_user;
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        int s1 = sh.getInt("id", 999999);
        String s2 = sh.getString("name", "no encontrado");
        String s3 = sh.getString("last_name", "no encontrado");
        String s4 = sh.getString("email", "no encontrado");
        String s5 = sh.getString("password", "no encontrado");
        String s6 = sh.getString("image", "no encontrado");


        System.out.println("drawerName: " + s1 + ", " + s2 + ", " + s3 + ", " + s4 + ", " + s5 + ", " + s6);

        System.out.println("\n\n\n\n NOSE QUEDAMOS SIN CONVS \n\n\n\n\n");
        this.userToken = sh.getString("token", "no encontrado");
        ret_user = new User(s1,s2,s3,s4,s5,s6);

        //messages--------------------------------------

        //int x_index = sh.getInt("x_index", 0);
        System.out.println("TENEMOS: " + num_conv + " CONVERSACIONS  - -- -");
        for(int x = 0; x < num_conv; x++){
            ret_user.addConv(
                    new Conversation(
                            sh.getInt("conv" + x + "id", 0),
                            sh.getString("conv" + x + "name",""),
                            sh.getString("conv" + x + "last_name", ""),
                            sh.getString("conv" + x + "email", ""),
                            sh.getString("conv" + x + "image", "")

                    )
            );
            int y_index = sh.getInt("y_index" + x, 0);
            for(int y =0; y< y_index; y++){
                ret_user.getConvs().get(x).addMess(
                        new Message(
                                sh.getInt("conv" + x + "mess" + y + "id", 0),
                                sh.getString("conv" + x + "mess" + y + "content", ""),
                                sh.getInt("conv" + x + "mess" + y + "getUser_id_sent", 0),
                                sh.getInt("conv" + x + "mess" + y + "user_id_recived", 0),
                                sh.getString("conv" + x + "mess" + y + "timestamp", "")


                        )
                );

            }
        }

        return ret_user;

    }

    public void updateMessages(){

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);




    }




}