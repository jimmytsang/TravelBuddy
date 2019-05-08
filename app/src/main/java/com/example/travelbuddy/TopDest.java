package com.example.travelbuddy;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class TopDest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_dest);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Popular Destinations");
        setSupportActionBar(toolbar);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("itinerary");

        FloatingActionButton f1 = findViewById(R.id.destination_add_fab_1);
        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.child("Eiffel Tower").setValue("The Eiffel Tower is a wrought-iron lattice tower on the Champ de Mars in Paris, France.");
            }
        });

        FloatingActionButton f2 = findViewById(R.id.destination_add_fab_2);
        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.child("Machu Picchu").setValue("Machu Picchu is an Incan citadel set high in the Andes Mountains in Peru, above the Urubamba River valley.");
            }
        });

        FloatingActionButton f3 = findViewById(R.id.destination_add_fab_3);
        f3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.child("Sydney Opera House").setValue("The Sydney Opera House is a performing arts centre at Sydney Harbour in Sydney, Australia.");
            }
        });

        FloatingActionButton f4 = findViewById(R.id.destination_add_fab_4);
        f4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.child("Yellowstone National Park").setValue("Yellowstone National Park is a 3,500-sq.-mile wilderness area atop a volcanic hot spot.");
            }
        });

        FloatingActionButton f5 = findViewById(R.id.destination_add_fab_5);
        f5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.child("Colosseum").setValue("The Colosseum or Coliseum, is an oval amphitheatre in the centre of the city of Rome, Italy.");
            }
        });

        FloatingActionButton f6 = findViewById(R.id.destination_add_fab_6);
        f6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.child("Grand Canyon").setValue("National park with layered bands of red rock revealing millions of years of geological history");
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
            Intent intent = new Intent(TopDest.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
