package com.example.travelbuddy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CountryTabAdapter extends FragmentPagerAdapter {

    CountryResult country;

    CountryTabAdapter(FragmentManager fm, CountryResult country) {
        super(fm);
        this.country = country;
    }

    /**
     * Return fragment with respect to Position .
     */
    @Override
    public Fragment getItem(int position)
    {
        switch (position){
            case 0:
                VisaFragment rv = new VisaFragment();
                Bundle args = new Bundle();
                args.putString("text1", country.text1);
                args.putString("name", country.name);
                rv.setArguments(args);
                return rv;
            case 1 : return new TipsFragment();
            case 2 : return new TopDestinationsFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    /**
     * This method returns the title of the tab according to the position.
     */
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0 :
                return "Visa Information";
            case 1 :
                return "Travel Tips";
            case 2 :
                return "Top Destinations";
        }
        return null;
    }
}
