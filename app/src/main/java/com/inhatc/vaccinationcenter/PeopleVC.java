package com.inhatc.vaccinationcenter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PeopleVC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_v_c);

        TextView txt = findViewById(R.id.txt);

        String x = "";

        for(int i=0; i<18; i++) {
            x += "지역 : " + Sign.sido[i] + "전체 누적 통계(1차) : " + Sign.totalFirst[i] + "전체 누적 통계(2차) : " + Sign.totalSecond[i] + "당일 통계(1차) : " + Sign.first[i] + "당일 통계(2차) : " + Sign.second[i] + "\n";
        }

        txt.setText(x);
    }
}