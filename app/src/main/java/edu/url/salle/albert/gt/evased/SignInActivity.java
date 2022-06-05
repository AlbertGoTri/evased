package edu.url.salle.albert.gt.evased;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {
    private Button noAccountBtn;
    private Button signInBtn;
    private TextView username;
    private TextView password;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_signin);

        noAccountBtn = findViewById(R.id.noAccountBtn);
        noAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            }
        });

        signInBtn = findViewById(R.id.signInBtn);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check the user data
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    Toast.makeText(SignInActivity.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SignInActivity.this, "LOGIN FAILED", Toast.LENGTH_SHORT).show();
                }
                System.out.println("Checking the user data...");
            }
        });

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
    }

}
