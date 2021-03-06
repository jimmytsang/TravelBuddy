package com.example.travelbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

public class CountryPage extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    CountryResult country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country);

        Bundle extras = getIntent().getExtras();
        country = new CountryResult((String) extras.get("name"),
                (String) extras.get("desc"),
                (String) extras.get("text1"),
                (String) extras.get("text2"));

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(country.name);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager.setAdapter(new CountryTabAdapter(getSupportFragmentManager(), country));
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.default_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_bar_home_button) {
            Intent intent = new Intent(CountryPage.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}