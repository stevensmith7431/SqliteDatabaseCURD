package com.example.prasanth.sqlitedatabase;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    TextView forgotpassword;
    TextView signup;
    Button b_login;
    Sqlitedatabase sqlitedatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.userid);
        password = findViewById(R.id.passid);
        b_login = findViewById(R.id.loginid);
        forgotpassword = findViewById(R.id.forgotpasswordid);
        signup = findViewById(R.id.signupid);

        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (username.getText().toString().isEmpty()) {
                    username.setError("username should not empty");
                } else if (password.getText().toString().isEmpty()) {
                    password.setError("password should not empty");
                } else {

                    sqlitedatabase = new Sqlitedatabase(MainActivity.this);

                    Cursor c;
                    c = sqlitedatabase.CheckLogin(username.getText().toString(), password.getText().toString());

                    if (c.getCount() == 0) {

                        Toast.makeText(MainActivity.this, "Invalid username", Toast.LENGTH_SHORT).show();

                    } else {

                        if (c.moveToFirst()) {

                            for (int i = 0; i < c.getCount(); i++) {

                                String s_email = c.getString(c.getColumnIndex("Emailid"));
                                String s_pass = c.getString(c.getColumnIndex("Password"));

                                if (s_email.equals(username.getText().toString()) && s_pass.equals(password.getText().toString())) {

                                    String s_gender = c.getString(c.getColumnIndex("Age"));
                                    String s_age = c.getString(c.getColumnIndex("Gender"));
                                    String s_city = c.getString(c.getColumnIndex("City"));
                                    String s_username = c.getString(c.getColumnIndex("Username"));
                                    String s_mobilenumber = c.getString(c.getColumnIndex("MobileNumber"));

                                    Intent intent = new Intent(MainActivity.this, WelcomePage.class);
                                    intent.putExtra("user", s_username);
                                    intent.putExtra("mobilenumber", s_mobilenumber);
                                    intent.putExtra("gender", s_gender);
                                    intent.putExtra("age", s_age);
                                    intent.putExtra("city", s_city);
                                    intent.putExtra("emailid", s_email);
                                    Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                }
                            }
                        } else {

                            Toast.makeText(MainActivity.this, "Invalid username and password", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ForgotPassword.class);
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SignUp_Activity.class);
                startActivity(intent);
            }
        });
    }
}
