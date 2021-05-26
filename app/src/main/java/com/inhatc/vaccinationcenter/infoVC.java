package com.inhatc.vaccinationcenter;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class infoVC extends AppCompatActivity {

    String myInfo = "";

    TextView txtMyInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_v_c);

        txtMyInfo = findViewById(R.id.txtMyInfo);

        try {
            DBHelper helper;
            SQLiteDatabase db;
            helper = new DBHelper(infoVC.this, "userInfo.db", null, 1);
            db = helper.getWritableDatabase();
            helper.onCreate(db);

            Cursor c = db.query("userInfo", null, null, null, null, null, null);
            c.moveToFirst();
            while (c.moveToNext()) {
                if (Sign.id.equals(c.getString(c.getColumnIndex("_id")))) {
                    myInfo += "No : " + c.getString(c.getColumnIndex("_id")) + "\n";
                    myInfo += "ID : " + c.getString(c.getColumnIndex("userId")) + "\n";
                    myInfo += "감영여부 : " + c.getString(c.getColumnIndex("infection")) + "\n";
                    myInfo += "예방접종여부 : " + c.getString(c.getColumnIndex("inoculation")) + "\n";
                    txtMyInfo.setText(myInfo);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}