package com.example.travelbuddy;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private static final String LOG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    public void toSearch(View view) {
        Intent intent = new Intent(MainActivity.this, CountrySearchActivity.class);
        MainActivity.this.startActivity(intent);
    }

    public void toDest(View view) {
        Intent intent = new Intent(MainActivity.this, TopDest.class);
        MainActivity.this.startActivity(intent);
    }

    public void newTrip(View view) {
        Intent intent = new Intent(MainActivity.this, ItineraryActivity.class);
        MainActivity.this.startActivity(intent);
    }

    public void toProfile(View view) {
        Intent intent = new Intent(MainActivity.this, MyProfile.class);
        MainActivity.this.startActivity(intent);
    }
}
