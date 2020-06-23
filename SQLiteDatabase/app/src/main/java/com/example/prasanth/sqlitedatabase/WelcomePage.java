package com.example.prasanth.sqlitedatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomePage extends AppCompatActivity {

    TextView username;
    TextView emailid;
    TextView mobilenumber;
    TextView gender;
    TextView age;
    TextView city;
    String user,email,mobnum,genders,ages,cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        username=findViewById(R.id.usernameid);
        emailid=findViewById(R.id.emailid);
        mobilenumber=findViewById(R.id.mobilenumberid);
        gender=findViewById(R.id.genderid);
        age=findViewById(R.id.ageid);
        city=findViewById(R.id.city);


        Intent intent = getIntent();
        user = intent.getStringExtra("user");
        email = intent.getStringExtra("emailid");
        mobnum = intent.getStringExtra("mobilenumber");
        genders = intent.getStringExtra("gender");
        ages = intent.getStringExtra("age");
        cities = intent.getStringExtra("city");

        username.setText(user);
        emailid.setText(email);
        mobilenumber.setText(mobnum);
        gender.setText(genders);
        age.setText(ages);
        city.setText(cities);

    }
}
