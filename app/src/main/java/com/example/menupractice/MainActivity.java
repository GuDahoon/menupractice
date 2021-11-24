package com.example.menupractice;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity {
    final String TAG = this.getClass().getSimpleName();
    LinearLayout home_ly;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        SettingListener();
        bottomNavigationView.setSelectedItemId(R.id.tab_food);
    }
    private void init() {
        home_ly = findViewById(R.id.home_ly);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }
    private void SettingListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new TabSelectedListener());
    }

    class TabSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch(menuItem.getItemId()) {
                case R.id.tab_food :
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_ly, new foodfragment()).commit();
                    break;
                case R.id.tab_camera :
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_ly, new scrabfragment()).commit();
                    break;
                case R.id.tab_view :
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_ly, new userfragment()).commit();
                    break;
            }

            return true;
        }
    }
}