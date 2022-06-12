package edu.url.salle.albert.gt.evased;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import edu.url.salle.albert.gt.evased.Adapters.MyRecyclerViewMessagesAdapter;
import edu.url.salle.albert.gt.evased.entities.Conversation;
import edu.url.salle.albert.gt.evased.entities.Event;
import edu.url.salle.albert.gt.evased.entities.User;
import edu.url.salle.albert.gt.evased.lab.EventLab;
import edu.url.salle.albert.gt.evased.lab.UserLab;


public class MyMessages extends AppCompatActivity implements MyRecyclerViewMessagesAdapter.ItemClickListener {

    //---------------------------------------------------------------------------------INITIALIZE USERS + CONVERSATIONS
    UserLab userlab = new UserLab();
    ArrayList<User> users = userlab.getUsers();
    ArrayList<Conversation> conversations = userlab.getConversations();

    //---------------------------------------------------------------------------------INITIALIZE EVENTS
    EventLab eventlab = new EventLab( users);
    ArrayList<Event> events = eventlab.getEvents();

    private MyRecyclerViewMessagesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_messages);
        
        //intent get information
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        //ArrayList<Conversation> conversations = (ArrayList<Conversation>) bundle.getSerializable("conversations");
        //User user = (User) getIntent().getSerializableExtra("user");

        //arraylist with the converations with the user participates
        ArrayList<Conversation> final_conversations = new ArrayList<>();

        //get only the conversations where user participates
        for(Conversation conv: conversations){
            if(conv.getReceiver() == users.get(0) || conv.getSender() == users.get(0) ){
                final_conversations.add(conv);

            }
        }

        //set up the RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.message_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyRecyclerViewMessagesAdapter(this, final_conversations);
        mAdapter.setClickListener(this);
        recyclerView.setAdapter(mAdapter);
        
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + mAdapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}