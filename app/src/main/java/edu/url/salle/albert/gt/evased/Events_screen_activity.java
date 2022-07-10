package edu.url.salle.albert.gt.evased;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.databinding.tool.util.StringUtils;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.url.salle.albert.gt.evased.Adapters.EventsTypeAdapter;
import edu.url.salle.albert.gt.evased.Adapters.LocationsAdapter;
import edu.url.salle.albert.gt.evased.Adapters.MyRecyclerViewMessagesAdapter;
import edu.url.salle.albert.gt.evased.databinding.ActivityEventsScreenBinding;
import edu.url.salle.albert.gt.evased.databinding.ActivityMyMessagesBinding;
import edu.url.salle.albert.gt.evased.entities.Conversation;
import edu.url.salle.albert.gt.evased.entities.Event;
import edu.url.salle.albert.gt.evased.entities.EventsTypes;
import edu.url.salle.albert.gt.evased.entities.Location;

public class Events_screen_activity extends DrawerActivity {

    ActivityEventsScreenBinding activityEventsScreenBinding;


    private LocationsAdapter locationsAdapter;
    RecyclerView recyclerView;
    private EventsTypeAdapter eventsTypeAdapter;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private RequestQueue request2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEventsScreenBinding = ActivityEventsScreenBinding.inflate(getLayoutInflater());

        request2 = Volley.newRequestQueue(this);
        setContentView(activityEventsScreenBinding.getRoot());
        allocateActivityTitle("Events");
        //setContentView(R.layout.activity_events_screen);





        ArrayList<EventsTypes> buttons_types = new ArrayList<>();
        Button button = null;
        buttons_types.add(new EventsTypes(button, "art"));
        buttons_types.add(new EventsTypes(button, "sport"));
        buttons_types.add(new EventsTypes(button, "dance"));
        buttons_types.add(new EventsTypes(button, "music"));

        //set up the RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.task_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //< - - - WHY "this" ??

        events = getEvents();


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

    public ArrayList<Event> getEvents(){

        ArrayList<Event> events2 = new ArrayList<>();

        String url_ALLEVENTS = "http://puigmal.salle.url.edu/api/v2/events";

        JsonArrayRequest jsonObjectRequestGETEVENTS = new JsonArrayRequest(Request.Method.GET, url_ALLEVENTS, null,  new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int counter = 0;
                System.out.println(response);
                for (int i = 0 ; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        System.out.println(SignInUser.getUserID() + " == " + obj.getInt("owner_id"));
                        if(obj.getInt("owner_id") == SignInUser.getUserID()){

                            counter++;
                            events.add(new Event(
                                            obj.getInt("id"),
                                            obj.getString("name"),
                                            obj.getInt("owner_id"),
                                            obj.getString("date"),
                                            obj.getString("image"),
                                            obj.getString("location"),
                                            obj.getString("description"),
                                            obj.getString("eventStart_date"),
                                            obj.getString("eventEnd_date"),
                                            obj.getInt("n_participators"),
                                            obj.getString("type")

                                    )
                            );
                            System.out.println("name: " + events.get(events.size() - 1).getName());

                        }

                        System.out.println();
                    }catch (JSONException e) {
                        e.printStackTrace();
                        System.out.println("no guarda bien el event");
                    }
                }
                if(counter == 0){
                    System.out.println("\n\n\n\nNO EVENTS HAVE BEEN FOUND FOR OUR USER LEST SHOW OTHER EVENTS\n\n\n\n\n" );
                    for (int i = 0 ; i < response.length(); i++) {
                        try {
                            JSONObject obj = response.getJSONObject(i);

                            counter++;
                            events.add(new Event(
                                            obj.getInt("id"),
                                            obj.getString("name"),
                                            obj.getInt("owner_id"),
                                            obj.getString("date"),
                                            obj.getString("image"),
                                            obj.getString("location"),
                                            obj.getString("description"),
                                            obj.getString("eventStart_date"),
                                            obj.getString("eventEnd_date"),
                                            obj.getInt("n_participators"),
                                            obj.getString("type")

                                    )
                            );
                            System.out.println("name: " + events.get(events.size() - 1).getName());

                            System.out.println();
                        }catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("no guarda bien el event");
                        }
                    }
                }
                locationsAdapter = new LocationsAdapter(Events_screen_activity.this, getLocations());
                //locationsAdapter.
                recyclerView.setAdapter(locationsAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("errorororororo con los eventos");
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> heather = new HashMap<>();
                heather.put("Authorization","Bearer " + userToken);
                return heather;
            }
        };

        request2.add(jsonObjectRequestGETEVENTS);
        return events2;
    }

    public ArrayList<Location> getLocations(){
        ArrayList<Location> ret_loc =new ArrayList<>();
        for(Event event :events){
            String upToNCharacters = event.getImage().substring(0, Math.min(event.getImage().length(), 3));
            if(upToNCharacters.equals("htt")) {
                System.out.println("link: " + event.getImage());
                ret_loc.add(new Location(event.getImage(), event.getName()));
            }
        }
        return ret_loc;

    }

    private void updateShared(){



    }
    }