package com.inhatc.vaccinationcenter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.Query;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    TextView txtIdView;
    EditText editId, editPassword, editAge, editInfection, editInoculation;
    Button btnSignUp;
    String id, password, age, infaction, inoculation = null;
    String userId = null;
    boolean bId = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editId = findViewById(R.id.editId);
        editPassword = findViewById(R.id.editPassword);
        editAge = findViewById(R.id.editAge);
        editInfection = findViewById(R.id.editInfection);
        editInoculation = findViewById(R.id.editInoculation);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);

        DBHelper helper;
        SQLiteDatabase db;
        helper = new DBHelper(SignUp.this, "userInfo.db", null, 1);
        db = helper.getWritableDatabase();
        helper.onCreate(db);

    }

    public void onClick(View v) {
        if (v == btnSignUp) {
            try {
                DBHelper helper;
                SQLiteDatabase db;
                helper = new DBHelper(SignUp.this, "userInfo.db", null, 1);
                db = helper.getWritableDatabase();
                helper.onCreate(db);

                Cursor c = db.query("userInfo", null, null, null, null, null, null);
                c.moveToFirst();
                while (c.moveToNext()) {
                    String tempId = c.getString(c.getColumnIndex("userId"));
                    if (editId.getText().toString().equals(tempId)) {
                        bId = true;
                    }
                }
                if (!bId) {
                    String sql = "INSERT INTO userInfo('userId', 'password', 'age', 'infection', 'inoculation') values('" + editId.getText().toString() + "', '" + editPassword.getText().toString() + "','" + editAge.getText().toString() + "','" + editInfection.getText().toString() + "','" + editInoculation.getText().toString() + "');";
                    db.execSQL(sql);
                    Toast myToast = Toast.makeText(getApplicationContext(), "회원가입 완료", Toast.LENGTH_LONG);
                    myToast.show();
                } else {
                    Toast myToast = Toast.makeText(getApplicationContext(), "중복된 아이디입니다.", Toast.LENGTH_LONG);
                    myToast.show();
                }
            } catch (Exception e) {
                System.out.println("insert error" + e);
            }
        }
    }
}