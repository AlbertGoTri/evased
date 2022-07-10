package edu.url.salle.albert.gt.evased;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.url.salle.albert.gt.evased.Adapters.MyRecyclerViewNewUsersAdapter;
import edu.url.salle.albert.gt.evased.Adapters.MyRecyclerViewRequestsAdapter;
import edu.url.salle.albert.gt.evased.entities.User;

public class FriendRequest extends AppCompatActivity implements MyRecyclerViewRequestsAdapter.ItemClickListener{
    private int modee;
    private int ider;
    RequestQueue request9;
    private String userToken;

    //recyclerview
    private MyRecyclerViewRequestsAdapter mAdapter;
    private RecyclerView recyclerView;

    //get all users variables
    ArrayList<User> all_users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_request);

        all_users = new ArrayList<>();
        request9 = Volley.newRequestQueue(this);

        Intent intent = this.getIntent();
        userToken = intent.getStringExtra("token");
        ider = intent.getIntExtra("id",456);

        modee = intent.getIntExtra("mode", 0);
        if(modee == 0){
            System.out.println("ERRRRROOOOOR WITH THE MODE");
        }else if(modee == 1){

            sendRequest();


        }else if(modee == 2){

        }

        //recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.recyclerOfRequesters34);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getAllUsers();
    }

    private void updateRecyclerView(ArrayList<User> users3) {
        mAdapter = new MyRecyclerViewRequestsAdapter(this, users3);
        mAdapter.setClickListener(this);
        recyclerView.setAdapter(mAdapter);
    }
    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "FRIEND REQUEST SENT!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(FriendRequest.this, FriendRequest.class);
        intent.putExtra("mode", 1);
        intent.putExtra("token", userToken);
        intent.getIntExtra("id", all_users.get(position).getUserID());
        startActivity(intent);

    }
    private void getAllUsers(){
        String url_SIGNIN = "http://puigmal.salle.url.edu/api/v2/users";

        JsonArrayRequest jsonObjectRequestGETUSERS = new JsonArrayRequest(Request.Method.GET, url_SIGNIN, null,  new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0 ; i < response.length(); i++) {
                    System.out.println("\nENTRAMOS loop");
                    try {
                        System.out.println("\nENTRAMOS EN EL TRYY");
                        JSONObject obj = response.getJSONObject(i);
                        //System.out.println(SignInUser.getUserID() + " == " + obj.getInt("id"));
                        //if(obj.getInt("id") == SignInUser.getUserID()){


                        all_users.add(new User(
                                        obj.getInt("id"),
                                        obj.getString("name"),
                                        obj.getString("last_name"),
                                        obj.getString("email"),
                                        obj.getString("image")
                                )
                        );
                        System.out.println("USER-> " + all_users.get(all_users.size() - 1).getName());

                        //}

                        System.out.println();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        System.out.println("no gurada bien los users-> allusers");
                    }
                }

                updateRecyclerView(all_users);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("not getting the users correctly");
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> heather = new HashMap<>();
                heather.put("Authorization","Bearer " + userToken);
                return heather;
            }
        };


        request9.add(jsonObjectRequestGETUSERS);


    }

    private void sendRequest(){
        String url_ADDFRIEND = "http://puigmal.salle.url.edu/api/v2/friends/" + ider;

        JSONObject jsonBodySEARCH = new JSONObject();
        try {
            jsonBodySEARCH.put("id", ider);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequestSENDFRIENDREQ = new JsonObjectRequest(Request.Method.POST, url_ADDFRIEND, jsonBodySEARCH,  new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR SENDING ");
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> heather = new HashMap<>();
                heather.put("Authorization","Bearer " + userToken);
                return heather;
            }
        };
        request9.add(jsonObjectRequestSENDFRIENDREQ);

    }
}