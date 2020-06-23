package com.example.prasanth.sqlitedatabase;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ForgotPassword extends AppCompatActivity {

    EditText mobile;
    Button verify;
    Sqlitedatabase sqlitedatabase;
    public ListView listView;
    private List<MyList> listitems;
    private MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mobile = findViewById(R.id.mobileid);
        verify = findViewById(R.id.verifyid);
        listView = findViewById(R.id.listview);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MyList selected = listitems.get(position);
                Intent intent = new Intent(ForgotPassword.this, ChangePassword.class);
                intent.putExtra("key 1", selected.emailid);
                startActivity(intent);

            }
        });


        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mobile.getText().toString().isEmpty()) {

                    Toast.makeText(ForgotPassword.this, "Enter mobile number", Toast.LENGTH_SHORT).show();

                } else {

                    sqlitedatabase = new Sqlitedatabase(ForgotPassword.this);

                    Cursor c;
                    c = sqlitedatabase.getMobilenumber(mobile.getText().toString());

                    if (c.getCount() > 0) {

                        if (c.moveToFirst()) {

                            listitems = new ArrayList<>();

                            do {

                                String s_email = c.getString(c.getColumnIndex("Emailid"));
                                listitems.add(new MyList(s_email));

                            } while (c.moveToNext());

                        }

                    } else {
                        Toast.makeText(ForgotPassword.this, "Invalid Mobile Number", Toast.LENGTH_SHORT).show();
                    }
                }
                adapter = new MyAdapter(getApplicationContext(), listitems);
                listView.setAdapter(adapter);
            }
        });
    }
}
