package com.petus.findyourpet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity{
    static final LatLng map = new LatLng(25.041350, 121.566357);
    private GoogleMap mMap;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private NavigationView.OnNavigationItemSelectedListener navigationView_selected = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.action_home:
                    Toast.makeText(MainActivity.this,"首頁",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.action_about:
                    Toast.makeText(MainActivity.this,"關於",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.action_help:
                    Toast.makeText(MainActivity.this,"使用說明",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.action_settings:
                    Toast.makeText(MainActivity.this,"設定",Toast.LENGTH_SHORT).show();
                    break;
            }
            menuItem.setChecked(true);//點選了把它設為選中狀態
            drawerLayout.closeDrawers();//關閉抽屜
            return true;
        }
    };

    private OnMapReadyCallback map_ready = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;

            mMap.addMarker(new MarkerOptions().position(map).title("你的狗"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(map,16));
            mMap.getUiSettings().setCompassEnabled(true);       // 左上角的指南針，要兩指旋轉才會出現
            mMap.getUiSettings().setMapToolbarEnabled(true);    // 右下角的導覽及開啟 Google Map功能
            mMap.getUiSettings().setZoomControlsEnabled(true);
            setMapStyle();
        }
    };

    private void setMapStyle() {
        MapStyleOptions style = new MapStyleOptions(
                "[" +
                        "  {" +
                        "    \"featureType\":\"poi.business\"," +
                        "    \"elementType\":\"all\"," +
                        "    \"stylers\":[" +
                        "      {" +
                        "        \"visibility\":\"off\"" +
                        "      }" +
                        "    ]" +
                        "  }," +
                        "  {" +
                        "    \"featureType\":\"transit\"," +
                        "    \"elementType\":\"all\"," +
                        "    \"stylers\":[" +
                        "      {" +
                        "        \"visibility\":\"off\"" +
                        "      }" +
                        "    ]" +
                        "  }" +
                        "]"
        );
        mMap.setMapStyle(style);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Error","onViewCreate");
        setContentView(R.layout.activity_main);
        Log.e("Error","onCreate");

        ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMapAsync(map_ready);

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(navigationView_selected);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        actionBarDrawerToggle.syncState();//初始化狀態
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

    }
}