package edu.url.salle.albert.gt.evased;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import java.util.Map;

import edu.url.salle.albert.gt.evased.Adapters.MyRecyclerViewEvents_TimelineAdapter;
import edu.url.salle.albert.gt.evased.databinding.ActivityTimelineBinding;
import edu.url.salle.albert.gt.evased.entities.Event;

public class


MyTimeline extends DrawerActivity implements MyRecyclerViewEvents_TimelineAdapter.ItemClickListener {

    ActivityTimelineBinding activityTimelineBinding;


    private MyRecyclerViewEvents_TimelineAdapter mAdapter;
    private RequestQueue request6;
    private ArrayList<Event> eventos;
    RecyclerView recyclerView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        request6 = Volley.newRequestQueue(this);
        this.eventos = new ArrayList<>();
        /*
        for(int i = 0; i < intent.getIntExtra("indexx",0); i ++){
            eventos.add((Event)intent.getSerializableExtra("i"));
            System.out.println(i + " ) " + ((Event) intent.getSerializableExtra("i")).getName());
        }

         */




        System.out.println("JODERRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR\n\nnn\\n\n\n\n\\n\n\n\n\n\n");
        activityTimelineBinding = ActivityTimelineBinding.inflate(getLayoutInflater());
        setContentView(activityTimelineBinding.getRoot());
        allocateActivityTitle("Timeline");

        getEvents();

        //set up the RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.task_recycler_view_23);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + mAdapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }

    public void getEvents(){

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
                            eventos.add(new Event(
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
                            System.out.println("name: " + eventos.get(eventos.size() - 1).getName());

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
                            eventos.add(new Event(
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
                            System.out.println("name: " + eventos.get(eventos.size() - 1).getName());

                            System.out.println();
                        }catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("no guarda bien el event");
                        }
                    }
                }
                mAdapter = new MyRecyclerViewEvents_TimelineAdapter(MyTimeline.this, eventos);
                mAdapter.setClickListener(MyTimeline.this);
                recyclerView.setAdapter(mAdapter);
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

        request6.add(jsonObjectRequestGETEVENTS);

    }

    private void updateShared(){

    }
}