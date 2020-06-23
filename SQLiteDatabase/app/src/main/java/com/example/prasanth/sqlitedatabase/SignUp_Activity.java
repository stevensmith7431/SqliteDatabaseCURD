package com.example.prasanth.sqlitedatabase;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp_Activity extends AppCompatActivity {

    EditText username;
    EditText emailid;
    EditText password;
    EditText confirmpassword;
    EditText mobilenumber;
    EditText gender;
    EditText age;
    EditText city;
    Button signup;
    Sqlitedatabase sqlitedatabase;
    String s_username, s_emailid, s_password, s_mobilenumber, s_gender, s_age, s_city;
    String MobilePattern = "[0-8]{9}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);

        username = findViewById(R.id.userid);
        emailid = findViewById(R.id.emailid);
        password = findViewById(R.id.passwordid);
        confirmpassword = findViewById(R.id.confirmid);
        mobilenumber = findViewById(R.id.mobilenumberid);
        gender = findViewById(R.id.genderid);
        age = findViewById(R.id.ageid);
        city = findViewById(R.id.cityid);
        signup = findViewById(R.id.signupid);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                s_username = username.getText().toString();
                s_emailid = emailid.getText().toString();
                s_password = password.getText().toString();
                s_mobilenumber = mobilenumber.getText().toString();
                s_gender = gender.getText().toString();
                s_age = age.getText().toString();
                s_city = city.getText().toString();

                if (s_username.isEmpty()) {
                    username.setError("Username should not empty");
                } else if (s_emailid.isEmpty()) {
                    emailid.setError("Emailid should not empty");
                } else if (s_password.isEmpty()) {
                    password.setError("Password shpuld not empty");
                } else if (confirmpassword.getText().toString().isEmpty()) {
                    confirmpassword.setError("Confirm Password shpuld not empty");
                } else if (s_mobilenumber.isEmpty()) {
                    mobilenumber.setError("Mobile number should not empty");
                } else if (s_gender.isEmpty()) {
                    gender.setError("Gender Should not empty");
                } else if (s_age.isEmpty()) {
                    age.setError("Age should not empty");
                } else if (s_city.isEmpty()) {
                    city.setError("City should not empty");
                } else if (!s_password.equals(confirmpassword.getText().toString())) {
                    Toast.makeText(SignUp_Activity.this, "Password not matching", Toast.LENGTH_SHORT).show();

                } else if (!emailValidator(s_emailid)) {

                    Toast.makeText(SignUp_Activity.this, "Invalid email", Toast.LENGTH_SHORT).show();

                } else if (!isValidPassword(s_password)) {

                    Toast.makeText(SignUp_Activity.this, "Password is week", Toast.LENGTH_SHORT).show();

                } else if (mobilenumber.getText().toString().matches(MobilePattern)) {

                    Toast.makeText(SignUp_Activity.this, "Enter valid mobile number", Toast.LENGTH_SHORT).show();

                } else if (!s_password.equals(confirmpassword.getText().toString())) {

                    Toast.makeText(SignUp_Activity.this, "Passwords does not match", Toast.LENGTH_SHORT).show();
                    password.setText("");
                    confirmpassword.setText("");

                } else {

                    sqlitedatabase = new Sqlitedatabase(SignUp_Activity.this);

                    Cursor c;
                    c = sqlitedatabase.validateSignup(emailid.getText().toString());

                    if (c.getCount()>0){

                        Toast.makeText(SignUp_Activity.this, "user already exist", Toast.LENGTH_SHORT).show();

                    } else {

                        Intent intent = new Intent(SignUp_Activity.this, WelcomePage.class);
                        sqlitedatabase = new Sqlitedatabase(SignUp_Activity.this);
                        sqlitedatabase.insertStudentDetail(s_username, s_emailid, s_password, s_mobilenumber, s_age, s_gender, s_city);
                        intent.putExtra("user", s_username);
                        intent.putExtra("emailid", s_emailid);
                        intent.putExtra("mobilenumber", s_mobilenumber);
                        intent.putExtra("gender", s_gender);
                        intent.putExtra("age", s_age);
                        intent.putExtra("city", s_city);
                        Toast.makeText(SignUp_Activity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(intent);

                    }
                }
            }
        });
    }

    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean emailValidator(String email) {

        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
