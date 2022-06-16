package edu.url.salle.albert.gt.evased;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.url.salle.albert.gt.evased.Adapters.MyRecyclerViewNewUsersAdapter;
import edu.url.salle.albert.gt.evased.databinding.ActivitySearchUsersBinding;
import edu.url.salle.albert.gt.evased.entities.User;

public class Search_users extends DrawerActivity implements MyRecyclerViewNewUsersAdapter.ItemClickListener,SearchView.OnQueryTextListener  {

    //search User option
    SearchView searchView;
    ActivitySearchUsersBinding activitySearchUsersBinding;

    //recyclerview
    private MyRecyclerViewNewUsersAdapter mAdapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySearchUsersBinding = ActivitySearchUsersBinding.inflate(getLayoutInflater());
        setContentView(activitySearchUsersBinding.getRoot());
        allocateActivityTitle("Users");

        //recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.message_recycler_view_453);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        updateRecyclerView(manager.getUsers());

        searchView = findViewById(R.id.searchUser_searchView);
        searchView.setOnQueryTextListener(this);

    }

    private void updateRecyclerView(ArrayList<User> users) {
        mAdapter = new MyRecyclerViewNewUsersAdapter(this, manager.getUsers());
        mAdapter.setClickListener(this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + mAdapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();

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
}