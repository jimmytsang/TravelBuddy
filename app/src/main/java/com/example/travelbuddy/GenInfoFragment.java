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

public class GenInfoFragment extends Fragment {
    String text2;
    Map<String, Integer> imageMap;

    public GenInfoFragment() {
        // Required empty public constructor
    }

    private void initImageMap() {
        imageMap = new HashMap<>();
        imageMap.put("United States of America", R.drawable.us);
        imageMap.put("Brazil", R.drawable.brazil);
        imageMap.put("Taiwan", R.drawable.taiwan);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rv = inflater.inflate(R.layout.gen_info_fragment, container, false);

        initImageMap();

        Bundle args = getArguments();
        text2 = args.getString("text2").replace("\n", "\n\n");
        TextView textView1 = rv.findViewById(R.id.gen_text_1);
        textView1.setText(text2);

        ImageView imageView1 = rv.findViewById(R.id.gen_image_1);
        imageView1.setImageResource(imageMap.get(args.getString("name")));

        return rv;
    }
}
