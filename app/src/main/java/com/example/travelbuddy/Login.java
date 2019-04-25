package com.example.travelbuddy;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

public class Login extends AppCompatActivity {

    Button login_button;
    TextView username_input;
    TextView password_input;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        login_button = findViewById(R.id.button_login);
        username_input = findViewById(R.id.input_username);
        password_input = findViewById(R.id.input_password);

        // clicking 'login' takes you to Main Activity
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username_string = username_input.getText().toString();
                Intent intent = new Intent(Login.this, MainActivity.class);
                intent.putExtra("username", username_string);
                startActivity(intent);
                setResult(LoginActivity.LOGIN_RESULT);
                finish();
            }
        });

        // back button on toolbar
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Login.this, LoginActivity.class);
                        Login.this.startActivity(intent);
                    }
                }
        );

    }
}
