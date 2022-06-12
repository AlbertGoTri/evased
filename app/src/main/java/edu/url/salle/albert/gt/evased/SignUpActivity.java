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

public class SignUpActivity extends AppCompatActivity {
    private Button signUpBtn;
    private TextView name;
    private TextView surname;
    private TextView email;
    private TextView password;
    private TextView confirmPassword;

    SharedPreferences preferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.passwordSignUp);
        confirmPassword = findViewById(R.id.passwordConfirm);

        preferences = getSharedPreferences("Userinfo", 0);


        signUpBtn = findViewById(R.id.signUpBtn);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //save the new User information
                String name_str = name.getText().toString();
                String surname_str = surname.getText().toString();
                String email_str = email.getText().toString();
                String password_str = password.getText().toString();
                String confirmPassword_str = confirmPassword.getText().toString();

                if(password_str.equals(confirmPassword_str) && name_str.length()>1 && surname_str.length()>1 && email_str.length()>1) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("name", name_str);
                    editor.putString("surname", surname_str);
                    editor.putString("email", email_str);
                    editor.putString("password", password_str);
                    editor.apply();
                    Toast.makeText(SignUpActivity.this, "Signed up successfully!", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(SignUpActivity.this, "Please, check the input data", Toast.LENGTH_SHORT).show();
                }

            }
        });
        }
}
