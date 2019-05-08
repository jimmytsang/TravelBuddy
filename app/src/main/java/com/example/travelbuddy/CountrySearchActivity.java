package com.example.travelbuddy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CountrySearchActivity extends AppCompatActivity {

    private static final String LOG_TAG = "CountrySearch";
    private RecyclerView countryResults;
    private CountryCardViewAdapter adapter;
    private List<CountryResult> countries;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Destination Search");
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        CardView cardView = findViewById(R.id.card_view_1);
//        cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(CountrySearchActivity.this, CountryPage.class);
//                startActivity(intent);
//            }
//        });

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("countries");

        countries = new ArrayList<>();
        countryResults = findViewById(R.id.country_search_results);

        linearLayoutManager = new LinearLayoutManager(this);
        countryResults.setLayoutManager(linearLayoutManager);

        adapter = new CountryCardViewAdapter(countries, this);
        countryResults.setAdapter(adapter);

        // Get the SearchView and set the searchable configuration
        SearchView searchView = (SearchView) findViewById(R.id.country_searchview);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {
                Log.v(LOG_TAG, "search query updated: " + newText);
                Query queryRef = myRef.orderByKey()
                        .startAt("")
                        .endAt("" + "\uf8ff");
                ValueEventListener queryListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        Log.d(LOG_TAG, "search query RECEIVED DATA FROM FIREBASE REALTIME DATABASE: " + dataSnapshot.getValue().toString());
                        if (dataSnapshot.getValue() != null) {
                            populateResults((Map<String, Object>) dataSnapshot.getValue(), newText);
                        }
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

    public void populateResults(Map<String, Object> data, String query) {
        Log.d(LOG_TAG, query);
        countries.clear();
        adapter.notifyDataSetChanged();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            if (!entry.getKey().toLowerCase().contains(query.toLowerCase())) {
                continue;
            }
            Map singleCountry = (Map) entry.getValue();
            Log.d(LOG_TAG, "search query desc for " + entry.getKey() + " is " + singleCountry.get("desc"));
            CountryResult thisResult = new CountryResult(entry.getKey(), (String) singleCountry.get("desc"));
            Log.d(LOG_TAG, "adding a country " + entry.getKey());
            countries.add(thisResult);
        }
        adapter.notifyDataSetChanged();
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

class CountryResult {
    public String name;
    public String desc;
    public int imageId;

    private Map<String, Integer> idMap;

    CountryResult(String name, String desc) {
        this.name = name;
        this.desc = desc;
        initializeIdMap();
        this.imageId = idMap.get(name);
    }

    private void initializeIdMap() {
        idMap = new HashMap<>();
        idMap.put("Brazil", R.mipmap.brazil_small);
        idMap.put("United States of America", R.drawable.sf);
    }
}

class CountryCardViewAdapter extends RecyclerView.Adapter<CountryCardViewAdapter.CountryCardViewHolder>{

    Context context;

    public static class CountryCardViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView name;
        TextView desc;
        ImageView image;

        CountryCardViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.country_default_card_view);
            name = (TextView)itemView.findViewById(R.id.country_name);
            desc = (TextView)itemView.findViewById(R.id.country_desc);
            image = (ImageView)itemView.findViewById(R.id.country_img);
        }
    }

    List<CountryResult> countries;

    CountryCardViewAdapter(List<CountryResult> countries, Context context) {
        this.countries = countries;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    @Override
    public CountryCardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_card, viewGroup, false);
        CountryCardViewHolder pvh = new CountryCardViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(CountryCardViewHolder personViewHolder, int i) {
        final CountryResult thisCountry = countries.get(i);
        personViewHolder.name.setText(thisCountry.name);
        personViewHolder.desc.setText(thisCountry.desc);
        personViewHolder.image.setImageResource(thisCountry.imageId);
        personViewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CountryPage.class);
                intent.putExtra("name", thisCountry.name);
                intent.putExtra("desc", thisCountry.desc);
                intent.putExtra("imageId", thisCountry.imageId);
                context.startActivity(intent);
            }
        });
    }
}