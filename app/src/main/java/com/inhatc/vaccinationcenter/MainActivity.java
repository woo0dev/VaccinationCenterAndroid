package com.inhatc.vaccinationcenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSign, btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSign = findViewById(R.id.btnSign);
        btnSign.setOnClickListener(this);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);

    }

    public void onClick(View v) {
        if ( v == btnSign ) {
            Intent signIntent = new Intent(MainActivity.this, Sign.class);
            startActivity(signIntent);
        } else if (v == btnSignUp) {
            Intent signUpIntent = new Intent(MainActivity.this, SignUp.class);
            startActivity(signUpIntent);
        }
    }


}