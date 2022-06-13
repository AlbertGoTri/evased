package edu.url.salle.albert.gt.evased;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import edu.url.salle.albert.gt.evased.Adapters.MyRecyclerViewEvents_TimelineAdapter;
import edu.url.salle.albert.gt.evased.Adapters.MyRecyclerViewMessagesAdapter;
import edu.url.salle.albert.gt.evased.Managers.UserConvEventManager;
import edu.url.salle.albert.gt.evased.entities.Conversation;
import edu.url.salle.albert.gt.evased.entities.Event;
import edu.url.salle.albert.gt.evased.entities.User;
import edu.url.salle.albert.gt.evased.lab.EventLab;
import edu.url.salle.albert.gt.evased.lab.UserLab;

public class


MyTimeline extends AppCompatActivity implements MyRecyclerViewEvents_TimelineAdapter.ItemClickListener {

    private MyRecyclerViewEvents_TimelineAdapter mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);




        //populate the recycler view
        Intent intent = this.getIntent();
        UserConvEventManager manager = (UserConvEventManager) intent.getSerializableExtra("manager");



        //set up the RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.task_recycler_view_23);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyRecyclerViewEvents_TimelineAdapter(this, manager.getEvents());
        mAdapter.setClickListener(this);
        recyclerView.setAdapter(mAdapter);


    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + mAdapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}