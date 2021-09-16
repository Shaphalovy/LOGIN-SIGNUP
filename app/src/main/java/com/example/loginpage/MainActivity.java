package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText usernameEditText,passwordEditText;
    private TextView signUpHereTextView;
    private Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.usernameEditTextId);
        passwordEditText = findViewById(R.id.passwordEditTextId);
        signUpHereTextView = findViewById(R.id.signUpHereTextViewId);
        loginButton = findViewById(R.id.loginButtonId);
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase sqLiteDatabase =  dbHelper.getWritableDatabase();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                Boolean result = dbHelper.findPassword(username,password);

                if (result == true){
                    Intent intent = new Intent(MainActivity.this,Profile.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "username and Password didn't Match", Toast.LENGTH_SHORT).show();
                }


            }
        });

        signUpHereTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Registration.class);
                startActivity(intent);
            }
        });
    }

}