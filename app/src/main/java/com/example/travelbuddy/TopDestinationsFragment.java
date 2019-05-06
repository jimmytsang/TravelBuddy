package com.example.travelbuddy;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TopDestinationsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TopDestinationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopDestinationsFragment extends Fragment {

    public TopDestinationsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_destinations, container, false);
    }
}
