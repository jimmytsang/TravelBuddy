package com.example.travelbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class CountrySearchActivity extends AppCompatActivity {

    private static final String LOG_TAG = "CountrySearch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Destination Search");
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CardView cardView = findViewById(R.id.card_view_1);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CountrySearchActivity.this, CountryPage.class);
                startActivity(intent);
            }
        });

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("countries");

        // Get the SearchView and set the searchable configuration
        SearchView searchView = (SearchView) findViewById(R.id.country_searchview);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.v(LOG_TAG, "search query updated: " + newText);
                Query queryRef = myRef.orderByKey()
                        .startAt("")
                        .endAt("" + "\uf8ff");
                ValueEventListener queryListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Log.d(LOG_TAG, "search query RECEIVED DATA FROM FIREBASE REALTIME DATABASE: " + dataSnapshot.getValue().toString());
                        populateResults((Map<String, Object>) dataSnapshot.getValue());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };
                queryRef.addListenerForSingleValueEvent(queryListener);

                return false;
            }
        });
    }

    public void populateResults(Map<String, Object> data) {
        for (Map.Entry<String, Object> entry : data.entrySet()){
            Map singleCountry = (Map) entry.getValue();
            Log.d(LOG_TAG, "search query desc for " + entry.getKey() + " is " + singleCountry.get("desc"));
        }
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
            Intent intent = new Intent(CountrySearchActivity.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
