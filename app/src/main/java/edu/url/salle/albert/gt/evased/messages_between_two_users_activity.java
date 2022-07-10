package edu.url.salle.albert.gt.evased;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
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

import edu.url.salle.albert.gt.evased.Adapters.MyRecyclerViewWhatsappAdapter;
import edu.url.salle.albert.gt.evased.Managers.UserConvEventManager;
import edu.url.salle.albert.gt.evased.databinding.ActivityMessagesBetweenTwoUsersBinding;
import edu.url.salle.albert.gt.evased.entities.Conversation;
import edu.url.salle.albert.gt.evased.entities.Message;
import edu.url.salle.albert.gt.evased.entities.User;

public class messages_between_two_users_activity extends DrawerActivity implements MyRecyclerViewWhatsappAdapter.ItemClickListener {

    ActivityMessagesBetweenTwoUsersBinding activityMessagesBetweenTwoUsersBinding;



    private int index;
    private RecyclerView recyclerView;
    private RequestQueue request1;
    private TextView nameOnDisplay;
    private MyRecyclerViewWhatsappAdapter mAdapter;
    private ArrayList<Message> messs;
    private User user;
    private String other_user_name;

    //--------------------------------------------------------------------------SEND MESSAGES VARIABLES
    private EditText messageToSend;
    private ImageButton sendButton;
    private Button goBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMessagesBetweenTwoUsersBinding = ActivityMessagesBetweenTwoUsersBinding.inflate(getLayoutInflater());
        setContentView(activityMessagesBetweenTwoUsersBinding.getRoot());
        allocateActivityTitle("My messages");

        messs = new ArrayList<>();

        request1 = Volley.newRequestQueue(this);


        System.out.println(" \n\n\n\n\n\n\n\n\n CUANDOI \n\n\n\n\n ");

        //setContentView(R.layout.activity_messages_between_two_users);

        //-----------------------------------------------------------------------GET THE MESSAGES FROM THE MYMESSAGES
        Intent intent = this.getIntent();

        this.index = intent.getIntExtra("index", 0);
        this.user = (User) intent.getSerializableExtra("otherUser");
        this.other_user_name = intent.getStringExtra("otherUser_name");
        System.out.println("\n\n\n\nUSER FROM INTENT: " + user.getUserID() + ", " + user.getName()+ "\n\n\n\n");

        //-----------------------------------------------------------------------NAME ON DISPLAY
        this.nameOnDisplay = findViewById(R.id.name_messages_receiver);
        nameOnDisplay.setText(other_user_name);

        //-----------------------------------------------------------------------POPULATE THE RECYCLER VIEW
        this.recyclerView = (RecyclerView) findViewById(R.id.messages_recycler_view_23);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        saveAllMessages(index);


        //-----------------------------------------------------------------------SEND A MESSAGE
        sendButton = findViewById(R.id.sendmessagebutton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(messages_between_two_users_activity.this, "MESSAGE SENT", Toast.LENGTH_SHORT).show();
                messageToSend = findViewById(R.id.newMessageEdittext);
                //TODO I CHNAGED THis
                sendMessage(messageToSend.getText().toString());

                updateSharedPreferences();

            }
        });
        //------------------------------------------------------------------------GO BACK TO ALL CONVERSATIONS
        goBackButton = findViewById(R.id.go_back_to_all_conv_button);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.putExtra("managerV2", manager);
                setResult(RESULT_OK, intent);
                finish();

            }
        });



    }
    @Override
    public void onItemClick(View view, int position) {
        //Toast.makeText(this, "You clicked " + mAdapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }

    private void updateSharedPreferences(){
        //TODO I CHNAGED THis
        mAdapter = new MyRecyclerViewWhatsappAdapter(this, messs, SignInUser, index, other_user_name);
        mAdapter.setClickListener(this);
        recyclerView.setAdapter(mAdapter);
    }

    public void sendMessage(String contentt){
        String url_SENDMESSAGE = "http://puigmal.salle.url.edu/api/v2/messages";
        JSONObject jsonBodyMESSAGE = new JSONObject();
        //1866 nosotros, 1607 bonilla  //1034 , 884 , 1329 , 752, 993
        try {
            jsonBodyMESSAGE.put("content", contentt);
            jsonBodyMESSAGE.put("user_id_send", SignInUser.getUserID());
            jsonBodyMESSAGE.put("user_id_recived", index);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        saveAllMessages(SignInUser.getUserID());


        JsonObjectRequest jsonObjectRequestSENDMESSAGE = new JsonObjectRequest(Request.Method.POST, url_SENDMESSAGE, jsonBodyMESSAGE, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR SENDING MESSAGE");
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> heather = new HashMap<>();
                heather.put("Authorization","Bearer " + userToken);
                return heather;
            }
        };

        request1.add(jsonObjectRequestSENDMESSAGE);

    }
    private void saveAllMessages(int ide){
        String url_GETMESSAGES = "http://puigmal.salle.url.edu/api/v2/messages" + "/" + ide;
        //messs = new ArrayList<>();

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


                System.out.println("\n\nTHIS SEQUENCE HAS " + response.length() + " Messages");
                for (int i = 0 ; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);

                        messs.add(new Message(
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
                updateSharedPreferences();
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


        request1.add(jsonObjectRequestUSERMESSAGES);
    }
}