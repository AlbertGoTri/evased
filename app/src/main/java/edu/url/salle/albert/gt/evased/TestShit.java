package edu.url.salle.albert.gt.evased;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.url.salle.albert.gt.evased.databinding.ActivityDrawerBinding;

public class TestShit extends DrawerActivity {

    ActivityDrawerBinding activityDrawerBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDrawerBinding = ActivityDrawerBinding.inflate(getLayoutInflater());
        setContentView(activityDrawerBinding.getRoot());
    }
}