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

        String sido[] = new String[18];
        long totalFirst[] = new long[18];
        long totalSecond[] = new long[18];
        long first[] = new long[18];
        long second[] = new long[18];

        String x = "";

        for(int i=0; i<18; i++) {
            sido[i] = Sign.peopleInfoData[17-i].getSido();
            totalFirst[i] = Sign.peopleInfoData[17-i].getTotalFirstCnt();
            totalSecond[i] = Sign.peopleInfoData[17-i].getTotalSecondCnt();
            first[i] = Sign.peopleInfoData[17-i].getFirstCnt();
            second[i] = Sign.peopleInfoData[17-i].getSecondCnt();
        }

        for(int i=0; i<18; i++) {
            x += "지역 : " + sido[i] + "전체 누적 통계(1차) : " + totalFirst[i] + "전체 누적 통계(2차) : " + totalSecond[i] + "당일 통계(1차) : " + first[i] + "당일 통계(2차) : " + second[i] + "\n";
        }

        txt.setText(x);
    }
}