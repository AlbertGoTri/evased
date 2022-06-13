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
import edu.url.salle.albert.gt.evased.Managers.UserConvEventManager;
import edu.url.salle.albert.gt.evased.entities.Conversation;
import edu.url.salle.albert.gt.evased.entities.Event;
import edu.url.salle.albert.gt.evased.entities.User;
import edu.url.salle.albert.gt.evased.lab.EventLab;
import edu.url.salle.albert.gt.evased.lab.UserLab;


public class MyMessages extends AppCompatActivity implements MyRecyclerViewMessagesAdapter.ItemClickListener {

    private MyRecyclerViewMessagesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_messages);

        //-----------------------------------------------------------------------GET THE CONVERSATIONS FROM THE MAIN

        
        //intent get information
        Intent intent = this.getIntent();
        UserConvEventManager manager = (UserConvEventManager) intent.getSerializableExtra("manager");

        User signInUser = (User) intent.getSerializableExtra("actualUser");


        //-----------------------------------------------------------------------POPULATE THE RECYCLER VIEW
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.message_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyRecyclerViewMessagesAdapter(this, manager.getRelatedConversations(signInUser), signInUser);
        mAdapter.setClickListener(this);
        recyclerView.setAdapter(mAdapter);
        
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + mAdapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}