package com.example.travelbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItineraryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Current Itinerary");
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.itinerary_recyclerview);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        final List<Destination> myDataset = new ArrayList<>();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("itinerary");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    Map<String, Object> result = (Map<String, Object>) dataSnapshot.getValue();
                    for (Map.Entry<String, Object> item : result.entrySet()) {
                        myDataset.add(new Destination(item.getKey(), (String) item.getValue()));
                        Log.d("Hey", "found destination entry: " + item.getKey());
                    }
                    mAdapter = new MyAdapter(myDataset);
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // PASS
            }
        });
    }
}

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Destination> mDataset;
    private Map<String, Integer> imageMap;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView cardView;
        public MyViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Destination> myDataset) {
        mDataset = myDataset;
        imageMap = new HashMap<>();
        imageMap.put("Grand Canyon", R.mipmap.grandcanyon);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.destination_card_layout_in_itinerary, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
//        Log.d("hey", "lol ?");
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Destination thisDest = mDataset.get(position);
        TextView title = holder.cardView.findViewById(R.id.destination_title);
        title.setText(thisDest.name);
        TextView desc = holder.cardView.findViewById(R.id.destination_desc);
        desc.setText(thisDest.desc);
        ImageView img = holder.cardView.findViewById(R.id.destination_pic);
        img.setImageResource(imageMap.get(thisDest.name));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

class Destination {
    String name;
    String desc;

    public Destination(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
}