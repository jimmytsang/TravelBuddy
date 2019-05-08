package com.example.travelbuddy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
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

public class TopDestinationsFragment extends Fragment {

//    private static final String LOG_TAG = "CountrySearch";
//    private RecyclerView topDestinationResults;
//    private TopDestinationFragmentAdapter adapter;
//    private List<TopDestinationResult> topDestinations;
//    private LinearLayoutManager linearLayoutManager;
//
//    public TopDestinationsFragment() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.transportation_fragment);
//
//        final FirebaseDatabase database = FirebaseDatabase.getInstance();
//        final DatabaseReference myRef = database.getReference("countries");
//
//        topDestinations = new ArrayList<>();
//        topDestinationResults = findViewById(R.id.country_search_results);
//
//        linearLayoutManager = new LinearLayoutManager(this);
//        topDestinationResults.setLayoutManager(linearLayoutManager);
//
//        adapter = new TopDestinationFragmentAdapter(topDestinations, this);
//        topDestinationResults.setAdapter(adapter);
//
//        // Get the SearchView and set the searchable configuration
//        SearchView searchView = (SearchView) findViewById(R.id.country_searchview);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(final String newText) {
//                Log.v(LOG_TAG, "search query updated: " + newText);
//                Query queryRef = myRef.orderByKey()
//                        .startAt("")
//                        .endAt("" + "\uf8ff");
//                ValueEventListener queryListener = new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                        Log.d(LOG_TAG, "search query RECEIVED DATA FROM FIREBASE REALTIME DATABASE: " + dataSnapshot.getValue().toString());
//                        if (dataSnapshot.getValue() != null) {
//                            populateResults((Map<String, Object>) dataSnapshot.getValue(), newText);
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                };
//                queryRef.addListenerForSingleValueEvent(queryListener);
//
//                return false;
//            }
//        });
//    }
//
//    public void populateResults(Map<String, Object> data, String query) {
//        Log.d(LOG_TAG, query);
//        topDestinations.clear();
//        adapter.notifyDataSetChanged();
//        for (Map.Entry<String, Object> entry : data.entrySet()) {
//            if (!entry.getKey().toLowerCase().contains(query.toLowerCase())) {
//                continue;
//            }
//            Map singleCountry = (Map) entry.getValue();
//            Log.d(LOG_TAG, "search query desc for " + entry.getKey() + " is " + singleCountry.get("desc"));
//            TopDestinationResult thisResult = new TopDestinationResult(entry.getKey(),
//                    (String) singleCountry.get("desc"),
//                    (String) singleCountry.get("text1"));
//            Log.d(LOG_TAG, "adding a country " + entry.getKey());
//            topDestinations.add(thisResult);
//        }
//        adapter.notifyDataSetChanged();
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_destinations, container, false);
    }
}

class TopDestinationResult {
    public String name;
    public String desc;
    public int imageId;
    public String text1;

    private Map<String, Integer> idMap;

    TopDestinationResult(String name, String desc, String text1) {
        this.name = name;
        this.desc = desc;
        this.text1 = text1;
        initializeIdMap();
        this.imageId = idMap.get(name);
    }

    private void initializeIdMap() {
        idMap = new HashMap<>();
        idMap.put("Brazil", R.mipmap.brazil_small);
        idMap.put("United States of America", R.drawable.sf);
    }
}

class TopDestinationFragmentAdapter extends RecyclerView.Adapter<TopDestinationFragmentAdapter.CountryCardViewHolder>{

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

    List<TopDestinationResult> topDestinations;

    TopDestinationFragmentAdapter(List<TopDestinationResult> topDestinations, Context context) {
        this.topDestinations = topDestinations;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return topDestinations.size();
    }

    @Override
    public CountryCardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_card, viewGroup, false);
        CountryCardViewHolder pvh = new CountryCardViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(CountryCardViewHolder personViewHolder, int i) {
        final TopDestinationResult thisCountry = topDestinations.get(i);
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
                intent.putExtra("text1", thisCountry.text1);
                context.startActivity(intent);
            }
        });
    }
}
