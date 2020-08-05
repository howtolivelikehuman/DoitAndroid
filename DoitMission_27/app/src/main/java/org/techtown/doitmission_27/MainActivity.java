package org.techtown.doitmission_27;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    SupportMapFragment mapFragment;
    GoogleMap map;
    GoogleMap.OnMarkerClickListener listener;
    MarkerOptions friend1;
    MarkerOptions friend2;
    Drawable pic1;
    Drawable pic2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AutoPermissions.Companion.loadAllPermissions(this, 101);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d("Map", "지도 준비됨");
                map = googleMap;
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.5696934357,126.90220851),16));
                addMarkers();
                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Toast.makeText(getApplicationContext(), marker.getTitle() + ": " +marker.getSnippet(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
            }
        });
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);						// 태스크를 백그라운드로 이동
                finishAndRemoveTask();                              // 액티비티 종료 + 태스크 리
                android.os.Process.killProcess(android.os.Process.myPid()); // 앱 프로세스 종료
            }
        });
    }

    public void addMarkers(){
        LatLng friendpos = new LatLng(37.5696934357,126.90220851);
        friend1 = new MarkerOptions();
        friend1.position(friendpos);
        friend1.title("대머리왕");
        friend1.snippet("머리카락이 하나도 없음");

        pic1 = getResources().getDrawable(R.drawable.birdmissile1);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.birdmissile1);
        bitmap = Bitmap.createScaledBitmap(bitmap,300,250,true);
        friend1.icon(BitmapDescriptorFactory.fromBitmap(bitmap));

        map.addMarker(friend1);

        friendpos = new LatLng(37.5725019461, 126.90442929);
        friend2 = new MarkerOptions();
        friend2.position(friendpos);
        friend2.title("풍성머리왕");
        friend2.snippet("머리카락이 풍성함");
        pic2 = getResources().getDrawable(R.drawable.birdmissile5);
        bitmap = ((BitmapDrawable)pic2).getBitmap();
        bitmap = Bitmap.createScaledBitmap(bitmap,300,250,true);

        friend2.icon(BitmapDescriptorFactory.fromBitmap(bitmap));
        map.addMarker(friend2);
    }


    @SuppressLint("MissingPermission")
    public void onResume() {
        super.onResume();
        if (map != null) {
            map.setMyLocationEnabled(true);
        }
    }
    @SuppressLint("MissingPermission")
    public void onPause() {
        super.onPause();
        if (map != null) {
            map.setMyLocationEnabled(true);
        }
    }
}