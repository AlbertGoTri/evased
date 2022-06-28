package edu.url.salle.albert.gt.evased;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.Objects;

import Persistence.MySingleton;
import edu.url.salle.albert.gt.evased.Managers.UserConvEventManager;
import edu.url.salle.albert.gt.evased.entities.User;


public class MainActivity extends AppCompatActivity {


    private EditText username;
    private EditText password;

    private String userToken;

    //check before continue to the next page
    boolean error_detected;

    //----------------------------------------------------------------------------------CREATE ALL DATA (EVENTS, USERS, CONVERSATIONS)
    UserConvEventManager manager = new UserConvEventManager();

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //preferences = getSharedPreferences("Userinfo", 0);
        //username = findViewById(R.id.username);
        //password = findViewById(R.id.password);

/*
        Button noAccountBtn = findViewById(R.id.noAccountBtn);
        noAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "SWITCHING TO SIGN UP", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });

 */
        this.username = findViewById(R.id.username);
        this.password = findViewById(R.id.password);

        //we create a shared preferences to be always able to access the user that signed in
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();


        error_detected = false;

        final Button signInBtn = findViewById(R.id.signInBtn);
        signInBtn.setOnClickListener(v -> {

            String email = this.username.getText().toString();
            String password = this.password.getText().toString();
            String url = "http://puigmal.salle.url.edu/api/v2/users";
            JSONObject jsonBody = new JSONObject();
            try {
                jsonBody.put("email", email);
                jsonBody.put("password", password);
            } catch (JSONException e) {
                e.printStackTrace();
                error_detected = true;
            }
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (url, jsonBody, response -> {
                        try {
                            String token = response.getString("accessToken");
                            //setUser(token, email);
                            System.out.println(token);
                            this.userToken = token;
                        } catch (JSONException e) {
                            e.printStackTrace();
                            error_detected = true;
                        }
                    }, error -> {
                        System.out.println("erroooooor");
                        error_detected = true;
                    });

            MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
            Intent intent = new Intent(getApplicationContext(), Events_screen_activity.class);
            if(!error_detected){
                editor.putString("userToken",userToken);
                editor.putString("email",email);
                editor.putString("password",password);
                editor.apply();


            }else{
                editor.putString("userToken","userToken");
                editor.putString("email","jose@gmail.com");
                editor.putString("password","password");
                editor.apply();

            }

            startActivity(intent);

        });


    }
}