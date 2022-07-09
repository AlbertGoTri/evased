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

import edu.url.salle.albert.gt.evased.Adapters.MyRecyclerViewWhatsappAdapter;
import edu.url.salle.albert.gt.evased.Managers.UserConvEventManager;
import edu.url.salle.albert.gt.evased.databinding.ActivityMessagesBetweenTwoUsersBinding;
import edu.url.salle.albert.gt.evased.entities.Conversation;
import edu.url.salle.albert.gt.evased.entities.User;

public class messages_between_two_users_activity extends DrawerActivity implements MyRecyclerViewWhatsappAdapter.ItemClickListener {

    ActivityMessagesBetweenTwoUsersBinding activityMessagesBetweenTwoUsersBinding;



    private int index;
    private RecyclerView recyclerView;

    private TextView nameOnDisplay;
    private MyRecyclerViewWhatsappAdapter mAdapter;

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

        System.out.println(" \n\n\n\n\n\n\n\n\n CUANDOI \n\n\n\n\n ");
        for(Conversation conv : SignInUser.getConvs()){
            System.out.println("MESSSS-> Name of the receiver: " + conv.getName() +" " + conv.getLast_name() + ", ID: " + conv.getId() + ", mail: " + conv.getEmail());
        }
        //setContentView(R.layout.activity_messages_between_two_users);

        //-----------------------------------------------------------------------GET THE MESSAGES FROM THE MYMESSAGES
        Intent intent = this.getIntent();

        this.index = (int) intent.getIntExtra("index", 0);


        //-----------------------------------------------------------------------NAME ON DISPLAY
        this.nameOnDisplay = findViewById(R.id.name_messages_receiver);
        nameOnDisplay.setText(SignInUser.getConvs().get(index).getName());

        //-----------------------------------------------------------------------POPULATE THE RECYCLER VIEW
        this.recyclerView = (RecyclerView) findViewById(R.id.messages_recycler_view_23);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        updateSharedPreferences();


        //-----------------------------------------------------------------------SEND A MESSAGE
        sendButton = findViewById(R.id.sendmessagebutton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(messages_between_two_users_activity.this, "MESSAGE SENT", Toast.LENGTH_SHORT).show();
                messageToSend = findViewById(R.id.newMessageEdittext);
                //TODO I CHNAGED THis
                //manager.addMessage(messageToSend.getText().toString(), signInUser, otherUser);
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
        Toast.makeText(this, "You clicked " + mAdapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }

    private void updateSharedPreferences(){
        //TODO I CHNAGED THis
        mAdapter = new MyRecyclerViewWhatsappAdapter(this, SignInUser.getConvs().get(index), SignInUser);
        mAdapter.setClickListener(this);
        recyclerView.setAdapter(mAdapter);
    }
}