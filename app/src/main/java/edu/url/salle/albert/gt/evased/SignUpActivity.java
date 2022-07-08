package edu.url.salle.albert.gt.evased;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {
    private Button signUpBtn;
    private TextView name;
    private TextView surname;
    private TextView email;
    private TextView password;
    private TextView confirmPassword;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        RequestQueue request = Volley.newRequestQueue(this);

        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.passwordSignUp);
        confirmPassword = findViewById(R.id.passwordConfirm);




        signUpBtn = findViewById(R.id.signUpBtn);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url_SIGNIN = "http://puigmal.salle.url.edu/api/v2/users";
                //save the new User information
                String name_str = name.getText().toString();
                String surname_str = surname.getText().toString();
                String email_str = email.getText().toString();
                String password_str = password.getText().toString();
                String confirmPassword_str = confirmPassword.getText().toString();

                if(password_str.equals(confirmPassword_str) && name_str.length()>1 && surname_str.length()>1 && email_str.length()>1) {

                    JSONObject jsonBodySIGNIN = new JSONObject();
                    try {
                        jsonBodySIGNIN.put("name", name_str);
                        jsonBodySIGNIN.put("last_name", surname_str);
                        jsonBodySIGNIN.put("email", email_str);
                        jsonBodySIGNIN.put("password", password_str);
                        jsonBodySIGNIN.put("image", "https://i.imgur.com/7eHZNEP.jpeg%22");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    JsonObjectRequest jsonObjectRequestSIGNIN = new JsonObjectRequest(Request.Method.POST, url_SIGNIN, jsonBodySIGNIN, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println(response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println("errorrrrrrr noii");
                        }
                    });

                    request.add(jsonObjectRequestSIGNIN);


                    Toast.makeText(SignUpActivity.this, "Signed up successfully!", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(SignUpActivity.this, "Please, check the input data", Toast.LENGTH_SHORT).show();
                }



            }
        });
        }


}
