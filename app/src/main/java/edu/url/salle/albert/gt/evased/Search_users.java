package edu.url.salle.albert.gt.evased;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.url.salle.albert.gt.evased.databinding.ActivitySearchUsersBinding;
import edu.url.salle.albert.gt.evased.databinding.ActivityTimelineBinding;

public class Search_users extends DrawerActivity {

    ActivitySearchUsersBinding activitySearchUsersBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySearchUsersBinding = ActivitySearchUsersBinding.inflate(getLayoutInflater());
        setContentView(activitySearchUsersBinding.getRoot());
        allocateActivityTitle("Users");
        //setContentView(R.layout.activity_search_users);
    }
}