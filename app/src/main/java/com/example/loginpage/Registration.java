package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Registration extends AppCompatActivity implements View.OnClickListener {
    private EditText signUpEmailEditText,signUpUserNameEditText,signUpPassword1EditText;
    private Button signUpButton;
    private RadioGroup genderRadioGroup;
    private RadioButton  genderRadioButton;
    UsersDetails usersDetails;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        signUpEmailEditText = findViewById(R.id.signUpEmailEditText);
        signUpUserNameEditText = findViewById(R.id.signUpUserNameEditText);
        signUpPassword1EditText = findViewById(R.id.signUpPassword1EditText);

        genderRadioGroup = findViewById(R.id.signUpGenderRadioGroup);

        //database Connection
        dbHelper = new DBHelper(this);
        usersDetails = new UsersDetails();


        signUpButton = findViewById(R.id.signUpButtonId);

        signUpButton.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        //getting data from Users

        String email = signUpEmailEditText.getText().toString();
        String userName = signUpUserNameEditText.getText().toString();

        int selectedId = genderRadioGroup.getCheckedRadioButtonId();
        genderRadioButton = findViewById(selectedId);
        String gender = genderRadioButton.getText().toString();

        String password1 = signUpPassword1EditText.getText().toString();



        usersDetails.setEmail(email);
        usersDetails.setUserName(userName);
        usersDetails.setGender(gender);
        usersDetails.setPassword1(password1);


        long rowId = dbHelper.insertData(usersDetails);

        if(rowId>0){
            Toast.makeText(getApplicationContext(), "SIGN UP SUCCESSFULLY", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "SIGN UP FAILED", Toast.LENGTH_SHORT).show();
        }

    }


}