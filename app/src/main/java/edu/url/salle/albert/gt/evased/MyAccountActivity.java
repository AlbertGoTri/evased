package edu.url.salle.albert.gt.evased;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.url.salle.albert.gt.evased.databinding.ActivityEventsScreenBinding;
import edu.url.salle.albert.gt.evased.databinding.ActivityMyAccountBinding;

public class MyAccountActivity extends DrawerActivity {

    ActivityMyAccountBinding activityMyAccountBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMyAccountBinding = ActivityMyAccountBinding.inflate(getLayoutInflater());
        setContentView(activityMyAccountBinding.getRoot());
        allocateActivityTitle("My account");
        //setContentView(R.layout.activity_my_account);
    }
}