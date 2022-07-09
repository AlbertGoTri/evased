package edu.url.salle.albert.gt.evased;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import Persistence.MySingleton;
import edu.url.salle.albert.gt.evased.Managers.UserConvEventManager;
import edu.url.salle.albert.gt.evased.entities.Event;
import edu.url.salle.albert.gt.evased.entities.User;


public class MainActivity extends AppCompatActivity {


    public EditText username;
    public EditText password;

    private RequestQueue request;
    private String userToken;
    String email_ ;
    String password_;
    String name;
    int id;
    String last_name;
    String image;



    //check before continue to the next page
    boolean error_detected;

    //----------------------------------------------------------------------------------CREATE ALL DATA (EVENTS, USERS, CONVERSATIONS)
    UserConvEventManager manager = new UserConvEventManager();

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        request = Volley.newRequestQueue(this);

        //preferences = getSharedPreferences("Userinfo", 0);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);





        Button noAccountBtn = findViewById(R.id.noAccountBtn);
        noAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "SWITCHING TO SIGN UP", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });




        String url_LOGIN = "http://puigmal.salle.url.edu/api/v2/users/login";



        Button signInBtn = findViewById(R.id.signInBtn);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get the MAIL AND PASSWORD from the screen
                email_ = username.getText().toString();
                password_ = password.getText().toString();


                JSONObject jsonBodyLOGIN = new JSONObject();
                try {
                    jsonBodyLOGIN.put("email", email_);
                    jsonBodyLOGIN.put("password", password_);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest jsonObjectRequestLOGIN = new JsonObjectRequest(Request.Method.POST, url_LOGIN, jsonBodyLOGIN, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        try {
                            userToken = response.getString("accessToken");
                            System.out.println("Token: " + userToken);
                            getUserinfo();

                            ArrayList<Event> events = getEvents();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }}, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("ERRRROSSSSSS");
                    }
                });

                request.add(jsonObjectRequestLOGIN);


                Intent intent = new Intent(getApplicationContext(), Events_screen_activity.class);
                startActivity(intent);
            }

        });



    }


    public void getUserinfo(){

        String url_GETUSERSSTRING = "http://puigmal.salle.url.edu/api/v2/users/search?s=";
        //---------------------------------------------------------GET ALL THE INFO
        JSONObject jsonBodyUSERSEARCH = new JSONObject();
        try {
            jsonBodyUSERSEARCH.put("s", email_);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray jsonArrayUSERSEARCH = new JSONArray();
        jsonArrayUSERSEARCH.put(jsonBodyUSERSEARCH);

        final JSONObject[] user = new JSONObject[1];
        JsonArrayRequest jsonObjectRequestGETUSERSSTRING = new JsonArrayRequest(Request.Method.GET,
                url_GETUSERSSTRING + email_,
                jsonArrayUSERSEARCH,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            user[0] = response.getJSONObject(0);
                            name = user[0].getString("name");
                            id = user[0].getInt("id");
                            last_name = user[0].getString("last_name");
                            image = user[0].getString("image");
                            System.out.println("\n\n"+id + " " + name + " " + last_name + " " + email_);
                            saveShared();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("no coge bien la info del user ");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("EORRRORE ALANTIGUA");
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> heather = new HashMap<>();
                System.out.println("User Token: " + userToken);
                heather.put("Authorization","Bearer " + userToken);
                return heather;
            }
        };

        request.add(jsonObjectRequestGETUSERSSTRING);
    }

    public ArrayList<Event> getEvents(){
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        int id_used = sh.getInt("id", 1);

        ArrayList<Event> events = new ArrayList<>();

        String url_ALLEVENTS = "http://puigmal.salle.url.edu/api/v2/events";

        JsonArrayRequest jsonObjectRequestGETEVENTS = new JsonArrayRequest(Request.Method.GET, url_ALLEVENTS, null,  new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println(response);
                for (int i = 0 ; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        System.out.println(id + " == " + obj.getInt("owner_id"));
                        if(obj.getInt("owner_id") == id){


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

        request.add(jsonObjectRequestGETEVENTS);
        return events;
    }

    public void saveShared(){

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        System.out.println("Te voy a coger de los pelos: "+id + " " + name + " " + last_name + " " + email_ + " " + password_ + " " + image);
        myEdit.putInt("id", id);
        myEdit.putString("name", name);
        myEdit.putString("last_name", last_name);
        myEdit.putString("email", email_);
        myEdit.putString("password", password_);
        myEdit.putString("image", image);
        myEdit.putString("token", userToken);

        myEdit.apply();
    }


}