package com.zybooks.finalprojectmod5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button loginButton;
    Button createUserButton;
    DatabaseHelper dbHelper;
    SharedPreferences sharedPreferences;

    public void setUsername(String username) {
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new DatabaseHelper(this);

        // Click on login button
        loginButton = findViewById(R.id.button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                var username = ((EditText)findViewById(R.id.editTextText)).getText().toString();
                var password = ((EditText)findViewById(R.id.editTextText2)).getText().toString();
                if (dbHelper.checkLogin(username, password)) {
                    setUsername(username);
                    Intent intent = new Intent(MainActivity.this, Home.class);
                    startActivity(intent);
                }
            }
        });

        // Click on create user button
        createUserButton = findViewById(R.id.button2);
        createUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                var username = ((EditText)findViewById(R.id.editTextText)).getText().toString();
                var password = ((EditText)findViewById(R.id.editTextText2)).getText().toString();
                var result = dbHelper.createUser(username, password);
                if (result != -1) {
                    setUsername(username);
                    Intent intent = new Intent(MainActivity.this, Home.class);
                    startActivity(intent);
                }
            }
        });
    }

}