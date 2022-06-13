package edu.url.salle.albert.gt.evased;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.url.salle.albert.gt.evased.Adapters.MyRecyclerViewMessagesAdapter;
import edu.url.salle.albert.gt.evased.Adapters.MyRecyclerViewWhatsappAdapter;
import edu.url.salle.albert.gt.evased.Managers.UserConvEventManager;
import edu.url.salle.albert.gt.evased.entities.User;

public class messages_between_two_users_activity extends AppCompatActivity implements MyRecyclerViewWhatsappAdapter.ItemClickListener {


    private TextView nameOnDisplay;
    private MyRecyclerViewWhatsappAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages_between_two_users);

        //-----------------------------------------------------------------------GET THE MESSAGES FROM THE MYMESSAGES
        Intent intent = this.getIntent();
        UserConvEventManager manager = (UserConvEventManager) intent.getSerializableExtra("manager");
        User signInUser = (User) intent.getSerializableExtra("actualUser");
        User otherUser = (User) intent.getSerializableExtra("otherUser");

        //-----------------------------------------------------------------------NAME ON DISPLAY
        this.nameOnDisplay = findViewById(R.id.name_messages_receiver);
        nameOnDisplay.setText(otherUser.getName());

        //-----------------------------------------------------------------------POPULATE THE RECYCLER VIEW
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.messages_recycler_view_23);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new MyRecyclerViewWhatsappAdapter(this, manager.getOneConveration(signInUser, otherUser), signInUser, otherUser );
        mAdapter.setClickListener(this);
        recyclerView.setAdapter(mAdapter);



    }
    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + mAdapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}