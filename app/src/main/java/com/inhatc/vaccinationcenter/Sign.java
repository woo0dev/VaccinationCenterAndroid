package com.inhatc.vaccinationcenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Sign extends AppCompatActivity implements View.OnClickListener {

    EditText editId, editPassword;
    Button btnSign;

    static String id = null;
    boolean bId = false;

    static Datum[] data = new Datum[1000];
    ArrayList<PeopleDatum> peopleData = new ArrayList<PeopleDatum>();
    static PeopleDatum[] peopleInfoData = new PeopleDatum[18];

    String urlStr = "https://api.odcloud.kr/api/15077586/v1/centers?page=1&perPage=10000&serviceKey=g4CVdFveh5eMEiCUuZtycJ2FiYeYVhwfFXURiUZ2Kex4vl4MemZRTtifCWHhwXy59GzlUQj9ICPvXYIgCkn7Dg%3D%3D";
    String peopleUrlStr = "https://api.odcloud.kr/api/15077756/v1/vaccine-stat?page=1&perPage=10000&serviceKey=g4CVdFveh5eMEiCUuZtycJ2FiYeYVhwfFXURiUZ2Kex4vl4MemZRTtifCWHhwXy59GzlUQj9ICPvXYIgCkn7Dg%3D%3D";

    URL url = new URL(urlStr);
    URL peopleUrl = new URL(peopleUrlStr);

    public Sign() throws MalformedURLException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        if (android.os.Build.VERSION.SDK_INT > 9) { StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); StrictMode.setThreadPolicy(policy); }

        editId = findViewById(R.id.editId);
        editPassword = findViewById(R.id.editPassword);

        btnSign = findViewById(R.id.btnSign);
        btnSign.setOnClickListener(this);


        try {
            String line = "";
            String result = "";

            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            while ((line = br.readLine()) != null) {
                result = result.concat(line);
                System.out.println(line);
            }

            JSONParser parser = new JSONParser();

            org.json.simple.JSONObject obj = (org.json.simple.JSONObject)parser.parse(result);

            org.json.simple.JSONArray parse_listArr = (org.json.simple.JSONArray)obj.get("data");

            for (int i=0;i< parse_listArr.size();i++) {
                org.json.simple.JSONObject weather = (org.json.simple.JSONObject) parse_listArr.get(i);

                String address = (String) weather.get("address");
                String centerName = (String) weather.get("centerName");
                String lat = (String) weather.get("lat");
                String lng = (String) weather.get("lng");
                String phoneNumber = (String) weather.get("phoneNumber");


                data[i] = new Datum(address, centerName, phoneNumber, lat, lng);

                StringBuffer sb = new StringBuffer();
                sb.append("주소 : " + address + ", 센터이름 : " + centerName + ", " + "위도 : "+ lat + ", 경도 : " + lng + ", 전화번호 : " + phoneNumber);
                //System.out.println(sb.toString());

            }
            br.close();
        } catch (Exception e) {
            System.out.println("json 파싱 실패 : "+e);
        }

        try {
            String line = "";
            String result = "";

            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(peopleUrl.openStream()));
            while ((line = br.readLine()) != null) {
                result = result.concat(line);
                System.out.println(line);
            }

            JSONParser parser = new JSONParser();

            org.json.simple.JSONObject obj = (org.json.simple.JSONObject)parser.parse(result);

            org.json.simple.JSONArray parse_listArr = (org.json.simple.JSONArray)obj.get("data");

            for (int i=0;i< parse_listArr.size();i++) {
                org.json.simple.JSONObject weather = (org.json.simple.JSONObject) parse_listArr.get(i);

                String sido = (String) weather.get("sido");
                long totalFirstCnt = (long) weather.get("totalFirstCnt");
                long totalSecondCnt = (long) weather.get("totalSecondCnt");
                long firstCnt = (long) weather.get("firstCnt");
                long secondCnt = (long) weather.get("secondCnt");

                peopleData.add(new PeopleDatum(sido, totalFirstCnt, totalSecondCnt, firstCnt, secondCnt));

                StringBuffer sb = new StringBuffer();
                sb.append("지역 : " + sido + "전체 누적 통계(1차) : " + totalFirstCnt + ", 전체 누적 통계(2차) : " + totalSecondCnt + ", " + "당일 통계(1차) : "+ firstCnt + ", 당일 통계(2차) : " + secondCnt );
                System.out.println(sb.toString());

            }

            for (int j=0; j<18; j++) {
                peopleInfoData[j] = peopleData.get(peopleData.size()-1-j);
                System.out.println(peopleInfoData[j].getSido());
            }

            br.close();
        } catch (Exception e) {
            System.out.println("json 파싱 실패 : "+e);
        }

    }

    public void onClick(View v) {
        if ( v == btnSign ) {
            try {
                DBHelper helper;
                SQLiteDatabase db;
                helper = new DBHelper(Sign.this, "userInfo.db", null, 1);
                db = helper.getWritableDatabase();
                helper.onCreate(db);

                Cursor c = db.query("userInfo",null,null,null,null,null,null);
                c.moveToFirst();
                while(c.moveToNext()){
                    String tempId = c.getString(c.getColumnIndex("userId"));
                    String tempPw = c.getString(c.getColumnIndex("password"));
                    if(editId.getText().toString().equals(tempId)) {
                        if(editPassword.getText().toString().equals(tempPw)) {
                            id = c.getString(c.getColumnIndex("_id"));
                            bId = true;
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("select error" + e);
            }

            if(bId) {
                Intent mapVCIntent = new Intent(Sign.this, mapVC.class);
                startActivity(mapVCIntent);
            } else {
                Toast myToast = Toast.makeText(getApplicationContext(), "아이디 및 비밀번호가 틀립니다.", Toast.LENGTH_LONG);
                myToast.show();
            }
        }
    }

}