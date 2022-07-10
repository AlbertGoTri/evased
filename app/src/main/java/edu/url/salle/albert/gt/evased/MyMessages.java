package edu.url.salle.albert.gt.evased;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import edu.url.salle.albert.gt.evased.Adapters.MyRecyclerViewMessagesAdapter;
import edu.url.salle.albert.gt.evased.Managers.UserConvEventManager;
import edu.url.salle.albert.gt.evased.databinding.ActivityMyMessagesBinding;
import edu.url.salle.albert.gt.evased.entities.Conversation;
import edu.url.salle.albert.gt.evased.entities.Event;
import edu.url.salle.albert.gt.evased.entities.Message;


public class MyMessages extends DrawerActivity implements MyRecyclerViewMessagesAdapter.ItemClickListener  {

    ActivityMyMessagesBinding activityMyMessagesBinding;

    private MyRecyclerViewMessagesAdapter mAdapter;
    //private User SignInUser;
    //private UserConvEventManager managerGeneral;
    private RequestQueue request;


private ArrayList<Conversation> convs;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMyMessagesBinding = ActivityMyMessagesBinding.inflate(getLayoutInflater());
        setContentView(activityMyMessagesBinding.getRoot());
        allocateActivityTitle("My messages");


        recyclerView = (RecyclerView) findViewById(R.id.message_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        request = Volley.newRequestQueue(this);

        // setContentView(R.layout.activity_my_messages);

        //-----------------------------------------------------------------------GET THE CONVERSATIONS FROM THE MAIN

        String url_GETALLMESSAGES = "http://puigmal.salle.url.edu/api/v2/messages/users";
        JsonArrayRequest jsonObjectRequestGETMESSAGES = new JsonArrayRequest(
                Request.Method.GET,
                url_GETALLMESSAGES,
                null,
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                convs = new ArrayList<>();
                System.out.println("\nHay " + response.length() + " conversas");
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putInt("x_index", response.length());
                num_conv =response.length();
                myEdit.apply();

                for (int i = 0 ; i < response.length(); i++) {
                    System.out.println("\nENTRAMOS loop");
                    try {
                        System.out.println("\nENTRAMOS EN EL TRYY");
                        JSONObject obj = response.getJSONObject(i);
                        //System.out.println(SignInUser.getUserID() + " == " + obj.getInt("id"));
                        //if(obj.getInt("id") == SignInUser.getUserID()){


                            convs.add(new Conversation(
                                            obj.getInt("id"),
                                            obj.getString("name"),
                                            obj.getString("last_name"),
                                            obj.getString("email"),
                                            obj.getString("image")
                                    )
                            );
                            System.out.println("conversation with: " + convs.get(convs.size() - 1).getName());

                        //}

                        System.out.println();
                    }catch (JSONException e) {
                        e.printStackTrace();
                        System.out.println("no gurada bien las conversas");
                    }

                    //saveAllMessages(SignInUser.getLastConv().getId());
                    saveAllMessages(convs.get(convs.size() - 1).getId());
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("errorrrr in showing conversations");
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> heather = new HashMap<>();
                heather.put("Authorization","Bearer " + userToken);
                return heather;
            }
        };
        request.add(jsonObjectRequestGETMESSAGES);






        //-----------------------------------------------------------------------POPULATE THE RECYCLER VIEW


        
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + mAdapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), messages_between_two_users_activity.class);
        intent.putExtra("index", convs.get(position).getId());
        intent.putExtra("otherUser", SignInUser);
        intent.putExtra("otherUser_name", convs.get(position).getName());

        startActivityForResult(intent, 1);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                this.manager = (UserConvEventManager) data.getSerializableExtra("managerV2");
               // updateSharedPreferences();

            }
        }
    }


    private void updateSharedPreferences(ArrayList<Conversation> convs){
        System.out.println("AHORA LO HAGO - -- - ");
        for(Conversation conv : convs){
            System.out.println("CONV-> Name of the receiver: " + conv.getName() +" " + conv.getLast_name() + ", ID: " + conv.getId() + ", mail: " + conv.getEmail());
        }
        mAdapter = new MyRecyclerViewMessagesAdapter(this, convs, SignInUser);
        mAdapter.setClickListener(this);
        recyclerView.setAdapter(mAdapter);
    }

    private void saveAllMessages(int ide){
        String url_GETMESSAGES = "http://puigmal.salle.url.edu/api/v2/messages" + "/" + ide;


        JSONObject jsonBodySEARCH = new JSONObject();
        try {
            jsonBodySEARCH.put("id", ide);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray jsonArrayUSERID = new JSONArray();
        jsonArrayUSERID.put(jsonBodySEARCH);

        JsonArrayRequest jsonObjectRequestUSERMESSAGES = new JsonArrayRequest(Request.Method.GET, url_GETMESSAGES, jsonArrayUSERID,  new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int index = 0;
                int index_2 = 0;
                for(Conversation single_conv : convs){
                    if(single_conv.getId() == ide){
                        index = index_2;
                    }
                    index_2++;
                }
                System.out.println("\n\nTHIS SEQUENCE HAS " + response.length() + " Messages");
                for (int i = 0 ; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);

                        convs.get(index).addMess(new Message(
                                        obj.getInt("id"),
                                        obj.getString("content"),
                                        obj.getInt("user_id_send"),
                                        obj.getInt("user_id_recived"),
                                        obj.getString("timeStamp")
                                )
                        );
                        //System.out.println(obj.getInt("user_id_send") + "sent message: " + obj.getString("content") + ", sended to " + obj.getInt("user_id_recived")  );
                        //System.out.println("message to: " + SignInUser.getConvs().get(index).getLastMessage().getUser_id_recived() + ", content: " + SignInUser.getConvs().get(index).getLastMessage().getContent() );

                        System.out.println();
                    }catch (JSONException e) {
                        e.printStackTrace();
                        System.out.println("no gurada bien los mensajes");
                    }
                }
                updateSharedPreferences(convs);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error with getting messages from conversations");
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> heather = new HashMap<>();
                heather.put("Authorization","Bearer " + userToken);
                return heather;
            }
        };


        request.add(jsonObjectRequestUSERMESSAGES);
    }

    public void uploadMessages(){
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        System.out.println("\n\n\n\n\nHAY " + SignInUser.getConvs().size() + " Conversas\n\n\n");
        for(int x = 0; x < num_conv; x++){
            myEdit.putInt("conv" + x + "id", SignInUser.getConvs().get(x).getId());
            myEdit.putString("conv" + x + "name", SignInUser.getConvs().get(x).getName());
            myEdit.putString("conv" + x + "email", SignInUser.getConvs().get(x).getEmail());
            myEdit.putString("conv" + x + "last_name", SignInUser.getConvs().get(x).getLast_name());
            myEdit.putString("conv" + x + "image", SignInUser.getConvs().get(x).getImage());
            myEdit.putInt("y_index" + x, SignInUser.getConvs().get(x).getMessages().size());
            for(int y =0; y< SignInUser.getConvs().get(x).getMessages().size(); y++){
                myEdit.putInt("conv" + x + "mess" + y + "id", SignInUser.getConvs().get(x).getMessages().get(y).getId());
                myEdit.putInt("conv" + x + "mess" + y + "user_id_recived", SignInUser.getConvs().get(x).getMessages().get(y).getUser_id_recived());
                myEdit.putInt("conv" + x + "mess" + y + "getUser_id_sent", SignInUser.getConvs().get(x).getMessages().get(y).getUser_id_sent());
                myEdit.putString("conv" + x + "mess" + y + "content", SignInUser.getConvs().get(x).getMessages().get(y).getContent());
                myEdit.putString("conv" + x + "mess" + y + "timestamp", SignInUser.getConvs().get(x).getMessages().get(y).getTimeStamp());

            }
        }

        myEdit.apply();
    }
}