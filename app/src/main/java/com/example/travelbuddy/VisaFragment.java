package com.example.travelbuddy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import androidx.fragment.app.Fragment;

public class VisaFragment extends Fragment {
    String text1;
    Map<String, Integer> imageMap;

    public VisaFragment() {
        // Required empty public constructor
    }

    private void initImageMap() {
        imageMap = new HashMap<>();
        imageMap.put("United States of America", R.mipmap.esta_big);
        imageMap.put("Brazil", R.mipmap.brazil_travel_visa);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rv = inflater.inflate(R.layout.visa_info_fragment, container, false);

        initImageMap();

        Bundle args = getArguments();
        text1 = args.getString("text1");
        TextView textView1 = rv.findViewById(R.id.visa_text_1);
        textView1.setText(text1);

        ImageView imageView1 = rv.findViewById(R.id.visa_image_1);
        imageView1.setImageResource(imageMap.get(args.getString("name")));

        return rv;
    }
}
