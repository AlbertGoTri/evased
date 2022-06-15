package edu.url.salle.albert.gt.evased;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.url.salle.albert.gt.evased.Adapters.MyRecyclerViewMessagesAdapter;
import edu.url.salle.albert.gt.evased.Managers.UserConvEventManager;
import edu.url.salle.albert.gt.evased.databinding.ActivityMyMessagesBinding;


public class MyMessages extends DrawerActivity implements MyRecyclerViewMessagesAdapter.ItemClickListener {

    ActivityMyMessagesBinding activityMyMessagesBinding;

    private MyRecyclerViewMessagesAdapter mAdapter;
    //private User SignInUser;
    //private UserConvEventManager managerGeneral;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMyMessagesBinding = ActivityMyMessagesBinding.inflate(getLayoutInflater());
        setContentView(activityMyMessagesBinding.getRoot());
        allocateActivityTitle("My messages");
        // setContentView(R.layout.activity_my_messages);

        //-----------------------------------------------------------------------GET THE CONVERSATIONS FROM THE MAIN

        //TODO:(THERE IS NOTHING TO DO) SINCE THIS CLASS EXTENDS FROM DRAWER ACTIVITY (the sidebar) THERE IS NO NEED TO INTENT THINGS

        //intent get information
        //Intent intent = this.getIntent();
        //UserConvEventManager manager = (UserConvEventManager) intent.getSerializableExtra("manager");
        //this.managerGeneral = manager;

        //User signInUser = (User) intent.getSerializableExtra("actualUser");
        //this.SignInUser= signInUser;




        //-----------------------------------------------------------------------POPULATE THE RECYCLER VIEW
        recyclerView = (RecyclerView) findViewById(R.id.message_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        updateSharedPreferences();

        
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + mAdapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), messages_between_two_users_activity.class);
        intent.putExtra("actualUser", SignInUser);
        intent.putExtra("manager", manager);
        intent.putExtra("otherUser", manager.getTheOtherUser(mAdapter.getItem(position), SignInUser));

        startActivityForResult(intent, 1);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                this.manager = (UserConvEventManager) data.getSerializableExtra("managerV2");
                updateSharedPreferences();

            }
        }
    }
    private void updateSharedPreferences(){
        mAdapter = new MyRecyclerViewMessagesAdapter(this, manager.getRelatedConversations(SignInUser), SignInUser);
        mAdapter.setClickListener(this);
        recyclerView.setAdapter(mAdapter);
    }
}