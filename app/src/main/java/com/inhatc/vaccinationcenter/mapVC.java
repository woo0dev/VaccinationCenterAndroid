package com.inhatc.vaccinationcenter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lakue.lakuepopupactivity.PopupActivity;
import com.lakue.lakuepopupactivity.PopupGravity;
import com.lakue.lakuepopupactivity.PopupResult;
import com.lakue.lakuepopupactivity.PopupType;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.CircleOverlay;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.util.FusedLocationSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.inhatc.vaccinationcenter.Sign.data;

public class mapVC extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private FusedLocationSource locationSource;
    private NaverMap naverMap;
    private MapView mapView;
    private Marker marker1 = new Marker();
    Button btnInfo, btnPeople, btnMapPeople, btnMap;

    String imageUrl = "https://ncv.kdca.go.kr/kor/img/contents/plan/allview_step.jpg";

    List<Marker> markers = new ArrayList<>();
    List<CircleOverlay> circleList = new ArrayList<>();
    // 서울, 경기도, 인천, 강원도, 충북, 충남, 전북, 전남, 경북, 경남, 제주, 세종, 부산, 대구, 대전, 광주, 울산
    double center[][] = {{37.566380,126.977902},{37.263201,127.028574},{37.455791,126.705401},{37.881671,127.732695},{36.641669,127.488753},{36.815116,127.113745},{35.824080,127.147958},{34.810890,126.391914},{36.019013,129.343626},{35.227597,128.682366},{33.495155,126.537470},{36.480862,127.028574},{35.179572,129.075577},{35.871269,128.601734},{36.349637,127.383316},{35.160015,127.383316},{35.538756,129.311327}};

    public mapVC() throws MalformedURLException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_v_c);

        mapView = (MapView) findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);

        btnInfo = findViewById(R.id.btnInfo);
        btnInfo.setOnClickListener(this);

        btnPeople = findViewById(R.id.btnPeople);
        btnPeople.setOnClickListener(this);

        btnMapPeople = findViewById(R.id.btnMapPeople);
        btnMapPeople.setOnClickListener(this);

        btnMap = findViewById(R.id.btnMap);
        btnMap.setOnClickListener(this);

        Intent intent = new Intent(getBaseContext(), PopupActivity.class);
        intent.putExtra("type", PopupType.IMAGE);
        intent.putExtra("title", imageUrl); //Image
        intent.putExtra("buttonLeft", "종료");
        intent.putExtra("buttonRight", "바로가기");
        startActivityForResult(intent, 4);

    }

    public void onClick(View v) {
        if ( v == btnInfo ) {
            Intent infoIntent = new Intent(mapVC.this, infoVC.class);
            startActivity(infoIntent);
        } else if ( v == btnPeople ) {
            Intent PeopleIntent = new Intent(mapVC.this, PeopleVC.class);
            startActivity(PeopleIntent);
        } else if ( v == btnMapPeople ) {
            for (int i=0; i<markers.size(); i++) {
                markers.get(i).setMap(null);
            }
            for (int i=0; i<17; i++) {
                CircleOverlay circle = new CircleOverlay();
                circle.setCenter(new LatLng(center[i][0], center[i][1]));
                circle.setColor(-16711936);
                circle.setRadius(10000);
                circleList.add(circle);
            }
            for (int i=0; i<17; i++) {
                circleList.get(i).setMap(naverMap);
            }
        } else if( v == btnMap) {
            for (int i=0; i<circleList.size(); i++) {
                circleList.get(i).setMap(null);
            }
            for (int i = 0; i < Sign.data.length; i++) {
                if(Sign.data[i] != null) {
                    String name = Sign.data[i].getCenterName();
                    String add = Sign.data[i].getAddress();
                    String phone = Sign.data[i].getPhoneNumber();
                    Double lat = Double.parseDouble(Sign.data[i].getLat());
                    Double lng = Double.parseDouble(Sign.data[i].getLng());

                    Marker marker = new Marker();
                    marker.setWidth(50);
                    marker.setHeight(65);
                    marker.setCaptionText(name);
                    marker.setCaptionRequestedWidth(200);
                    marker.setCaptionMinZoom(10);
                    marker.setCaptionMaxZoom(21);
                    marker.setHideCollidedSymbols(true);
                    marker.setPosition(new LatLng(lat, lng));
                    markers.add(marker);

                    marker.setOnClickListener(overlay -> {
                        InfoWindow infoWindow = new InfoWindow();
                        infoWindow.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
                            @NonNull
                            @Override
                            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                                String result = "센터이름 : " + name + "\n주소 : " + add + "\n 전화번호 : " + phone;
                                return result;
                            }
                        });
                        infoWindow.open(marker);
                        return true;
                    });
                }
            }
            for (int i=0; i<markers.size(); i++) {
                markers.get(i).setMap(naverMap);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,  @NonNull int[] grantResults) {
        if (locationSource.onRequestPermissionsResult(
                requestCode, permissions, grantResults)) {
            if (!locationSource.isActivated()) {
                naverMap.setLocationTrackingMode(LocationTrackingMode.None);
            }
            return;
        }
        super.onRequestPermissionsResult(
                requestCode, permissions, grantResults);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;

        naverMap.setLocationSource(locationSource);

        naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);

        for (int i = 0; i < Sign.data.length; i++) {
            if(Sign.data[i] != null) {
                String name = Sign.data[i].getCenterName();
                String add = Sign.data[i].getAddress();
                String phone = Sign.data[i].getPhoneNumber();
                Double lat = Double.parseDouble(Sign.data[i].getLat());
                Double lng = Double.parseDouble(Sign.data[i].getLng());

                Marker marker = new Marker();
                marker.setWidth(50);
                marker.setHeight(65);
                marker.setCaptionText(name);
                marker.setCaptionRequestedWidth(200);
                marker.setCaptionMinZoom(10);
                marker.setCaptionMaxZoom(21);
                marker.setHideCollidedSymbols(true);
                marker.setPosition(new LatLng(lat, lng));
                markers.add(marker);

                marker.setOnClickListener(overlay -> {
                    InfoWindow infoWindow = new InfoWindow();
                    infoWindow.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
                        @NonNull
                        @Override
                        public CharSequence getText(@NonNull InfoWindow infoWindow) {
                            String result = "센터이름 : " + name + "\n주소 : " + add + "\n 전화번호 : " + phone;
                            return result;
                        }
                    });
                    infoWindow.open(marker);
                    return true;
                });
            }
        }

        for (Marker marker : markers) {
            marker.setMap(naverMap);
        }

    }


    @Override
    public void onStart()
    {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop()
    {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if(requestCode == 4){
                PopupResult result = (PopupResult) data.getSerializableExtra("result");
                if(result == PopupResult.LEFT){

                } else if(result == PopupResult.RIGHT){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    Uri uri = Uri.parse("https://ncvr.kdca.go.kr/cobk/index.html");
                    intent.setData(uri);
                    startActivity(intent);
                } else if(result == PopupResult.IMAGE){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    Uri uri = Uri.parse("https://ncv.kdca.go.kr/menu.es?mid=a10117040000");
                    intent.setData(uri);
                    startActivity(intent);
                }
            }
        }
    }

}