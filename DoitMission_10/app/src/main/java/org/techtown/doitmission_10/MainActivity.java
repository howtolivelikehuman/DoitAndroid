package org.techtown.doitmission_10;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Adapter;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    MainFragment mainFragment;
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    Toolbar toolbar;
    DrawerLayout drawer;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = new MainFragment();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,mainFragment).commit();

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu1 :
                        Toast.makeText(getApplicationContext(),"1번째", Toast.LENGTH_LONG).show();
                        toolbar.setTitle("첫 번째 화면");
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,mainFragment).commit();
                        return true;
                    case R.id.menu2 :
                        Toast.makeText(getApplicationContext(),"2번째", Toast.LENGTH_LONG).show();
                        toolbar.setTitle("두 번째 화면");
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment2).commit();
                        return true;
                    case R.id.menu3 :
                        Toast.makeText(getApplicationContext(),"3번째", Toast.LENGTH_LONG).show();
                        toolbar.setTitle("세 번째 화면");
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment3).commit();
                        return true;

                }
                return false;
            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu1){
            toolbar.setTitle("첫 번째 화면");
            getSupportFragmentManager().beginTransaction().replace(R.id.container,mainFragment).commit();
    }
        else if(id == R.id.menu2){
            toolbar.setTitle("두 번째 화면");
            getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment2).commit();
        }
        else if(id == R.id.menu3){
            toolbar.setTitle("세 번째 화면");
            getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment3).commit();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}