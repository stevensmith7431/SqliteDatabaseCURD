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

public class ChangePassword extends AppCompatActivity {

    EditText password;
    EditText confirmpassword;
    Button save;
    String email;
    Sqlitedatabase sqlitedatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        password=findViewById(R.id.passwordid);
        confirmpassword=findViewById(R.id.confirmpassid);
        save=findViewById(R.id.saveid);

        sqlitedatabase = new Sqlitedatabase(ChangePassword.this);

        email = getIntent().getStringExtra("key 1");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (password.getText().toString().isEmpty()){
                    password.setError("Enter password");
                } else if (confirmpassword.getText().toString().isEmpty()){
                    confirmpassword.setError("Enter confirmpassword");
                } else if (!isValidPassword(password.getText().toString())){
                    Toast.makeText(ChangePassword.this, "Password is week", Toast.LENGTH_SHORT).show();
                } else if (!password.getText().toString().equals(confirmpassword.getText().toString())) {

                    Toast.makeText(ChangePassword.this, "Passwords does not match", Toast.LENGTH_SHORT).show();
                    password.setText("");
                    confirmpassword.setText("");

                } else {

                    Intent intent = new Intent(ChangePassword.this,MainActivity.class);
                    sqlitedatabase.updatePassword(email,password.getText().toString());
                    Toast.makeText(ChangePassword.this, "Password change successfully", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
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

}
