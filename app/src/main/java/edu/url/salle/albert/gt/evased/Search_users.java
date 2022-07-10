package edu.url.salle.albert.gt.evased;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
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

import edu.url.salle.albert.gt.evased.Adapters.MyRecyclerViewNewUsersAdapter;
import edu.url.salle.albert.gt.evased.databinding.ActivitySearchUsersBinding;
import edu.url.salle.albert.gt.evased.entities.Conversation;
import edu.url.salle.albert.gt.evased.entities.User;

public class Search_users extends DrawerActivity implements MyRecyclerViewNewUsersAdapter.ItemClickListener,SearchView.OnQueryTextListener  {

    //search User option
    SearchView searchView;
    ActivitySearchUsersBinding activitySearchUsersBinding;

    //recyclerview
    private MyRecyclerViewNewUsersAdapter mAdapter;
    private RecyclerView recyclerView;

    //get all users variables
    ArrayList<User> all_users;

    //requestQueue
    RequestQueue request7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySearchUsersBinding = ActivitySearchUsersBinding.inflate(getLayoutInflater());
        setContentView(activitySearchUsersBinding.getRoot());
        allocateActivityTitle("Users");

        request7 = Volley.newRequestQueue(this);
        all_users = new ArrayList<>();

        //recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.message_recycler_view_453);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getAllUsers();

        searchView = findViewById(R.id.searchUser_searchView);
        searchView.setOnQueryTextListener(this);

        Button myRequests = findViewById(R.id.buttonGoToRequests);
        myRequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Search_users.this, FriendRequest.class);
                intent.putExtra("mode", 2);
                intent.putExtra("token", userToken);
                intent.putExtra("actual", SignInUser.getUserID());

                startActivity(intent);
            }
        });

    }

    private void updateRecyclerView(ArrayList<User> users3) {
        mAdapter = new MyRecyclerViewNewUsersAdapter(this, users3);
        mAdapter.setClickListener(this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "FRIEND REQUEST SENT!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Search_users.this, FriendRequest.class);
        intent.putExtra("mode", 1);
        intent.putExtra("token", userToken);
        intent.putExtra("id", all_users.get(position).getUserID());
        intent.putExtra("actual", SignInUser.getUserID());
        System.out.println("\n\n\n\n\n\n----------------------------------------" +
                "   " + "id: " + all_users.get(position).getUserID() + "--------------------------------------" +
                "\n\n\n\n\n\n\n\n\n");
        startActivity(intent);

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        mAdapter.filtrado(s);
        return false;
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


        request7.add(jsonObjectRequestGETUSERS);


    }
}