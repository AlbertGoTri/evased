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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import Persistence.MySingleton;
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

                //TODO CAMBIAR
                String url = "http://puigmal.salle.url.edu/api/v2/login";

                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("email", "mf@gmail.com");
                    jsonBody.put("password", "12345678");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (url, jsonBody, response -> {
                            try {
                                String token = response.getString("accessToken");
                                System.out.println(token);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }, error -> {
                            System.out.println("error");

                        });

                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);

            }
        });

    }
}