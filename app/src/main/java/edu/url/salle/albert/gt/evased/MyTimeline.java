package edu.url.salle.albert.gt.evased;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import edu.url.salle.albert.gt.evased.Adapters.MyRecyclerViewEvents_TimelineAdapter;
import edu.url.salle.albert.gt.evased.databinding.ActivityTimelineBinding;
import edu.url.salle.albert.gt.evased.entities.Event;

public class


MyTimeline extends DrawerActivity implements MyRecyclerViewEvents_TimelineAdapter.ItemClickListener {

    ActivityTimelineBinding activityTimelineBinding;


    private MyRecyclerViewEvents_TimelineAdapter mAdapter;

    private ArrayList<Event> eventos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        this.eventos = new ArrayList<>();
        for(int i = 0; i < intent.getIntExtra("indexx",0); i ++){
            eventos.add((Event)intent.getSerializableExtra("i"));
            System.out.println(i + " ) " + ((Event) intent.getSerializableExtra("i")).getName());
        }
        System.out.println("JODERRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR\n\nnn\\n\n\n\n\\n\n\n\n\n\n");
        activityTimelineBinding = ActivityTimelineBinding.inflate(getLayoutInflater());
        setContentView(activityTimelineBinding.getRoot());
        allocateActivityTitle("Timeline");


        //System.out.println(eventos.get(0).getName() + "11111111111111111111111111");

        //TODO:(THERE IS NOTHING TO DO) SINCE THIS CLASS EXTENDS FROM DRAWER ACTIVITY (the sidebar) THERE IS NO NEED TO INTENT THINGS



        //set up the RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.task_recycler_view_23);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyRecyclerViewEvents_TimelineAdapter(this, eventos);
        mAdapter.setClickListener(this);
        recyclerView.setAdapter(mAdapter);


    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + mAdapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}