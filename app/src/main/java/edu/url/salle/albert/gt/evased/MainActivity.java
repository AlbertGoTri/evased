package edu.url.salle.albert.gt.evased;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import edu.url.salle.albert.gt.evased.Managers.UserConvEventManager;
import edu.url.salle.albert.gt.evased.entities.User;


public class MainActivity extends AppCompatActivity {


    private Button noAccountBtn;
    private Button signInBtn;
    private TextView username;
    private TextView password;
    private String usernameValue;
    private String passwordValue;

    //---------------------------------------------------------------------------------EXTRA BUTTONS
    private Button MyMessagesBTN;
    private Button MyTimelineBTN;

    //----------------------------------------------------------------------------------CREATE ALL DATA (EVENTS, USERS, CONVERSATIONS)
    UserConvEventManager manager = new UserConvEventManager();

    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences("Userinfo", 0);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        String url = "http://puigmal.salle.url.edu/api/v2";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("SE VIENE HOSTIA");
                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                System.out.println("QUE VIENE QUE VIENE QUE VIENE\n\n\n" + response + "\n\n\nVINO VINO VINO");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR PRINGAO");
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        noAccountBtn = findViewById(R.id.noAccountBtn);
        noAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "SWITCHING TO SIGN UP", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });

        signInBtn = findViewById(R.id.signInBtn);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check the user data
                usernameValue = username.getText().toString();
                passwordValue = password.getText().toString();
                queue.add(stringRequest);
            }
        });
        //--------------------------------------------------------------------MY_MESSAGES_BUTTON----------------------------------------------------------------------------
        MyMessagesBTN = findViewById(R.id.MyMessages_Button);
        MyMessagesBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyMessages.class);

                //TODO: instead of getting the user 0, get the user that has signed in the app.
                edu.url.salle.albert.gt.evased.entities.User actualUser = manager.getUsers().get(0);
                intent.putExtra("actualUser", (Parcelable) actualUser);

                //-------------------------------------------PASS THE MANAGER THAT INCLUDES ALL THE INFO
                intent.putExtra("manager", manager);


                startActivity(intent);
            }
        });

        //--------------------------------------------------------------------MY_TIMELINE_BUTTON----------------------------------------------------------------------------
        MyTimelineBTN = findViewById(R.id.MyTimeline_Button);
        MyTimelineBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyTimeline.class);

                //-------------------------------------------PASS THE MANAGER THAT INCLUDES ALL THE INFO
                intent.putExtra("manager", manager);

                startActivity(intent);
            }
        });
    }
}