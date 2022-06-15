package edu.url.salle.albert.gt.evased;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import edu.url.salle.albert.gt.evased.Adapters.MyRecyclerViewEvents_TimelineAdapter;
import edu.url.salle.albert.gt.evased.databinding.ActivityTimelineBinding;

public class


MyTimeline extends DrawerActivity implements MyRecyclerViewEvents_TimelineAdapter.ItemClickListener {

    ActivityTimelineBinding activityTimelineBinding;


    private MyRecyclerViewEvents_TimelineAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTimelineBinding = ActivityTimelineBinding.inflate(getLayoutInflater());
        setContentView(activityTimelineBinding.getRoot());
        allocateActivityTitle("Timeline");



        //TODO:(THERE IS NOTHING TO DO) SINCE THIS CLASS EXTENDS FROM DRAWER ACTIVITY (the sidebar) THERE IS NO NEED TO INTENT THINGS
        //populate the recycler view
        //Intent intent = this.getIntent();
        //UserConvEventManager manager = (UserConvEventManager) intent.getSerializableExtra("manager");



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