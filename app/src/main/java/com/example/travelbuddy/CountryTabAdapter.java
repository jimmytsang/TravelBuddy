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
                GenInfoFragment gen_rv = new GenInfoFragment();
                Bundle gen_args = new Bundle();
                gen_args.putString("text2", country.text2);
                gen_args.putString("name", country.name);
                gen_rv.setArguments(gen_args);
                return gen_rv;
            case 1:
                VisaFragment rv = new VisaFragment();
                Bundle args = new Bundle();
                args.putString("text1", country.text1);
                args.putString("name", country.name);
                rv.setArguments(args);
                return rv;
            case 2:
                TipsFragment tips_rv = new TipsFragment();
                Bundle tips_args = new Bundle();
                tips_args.putString("name", country.name);
                tips_rv.setArguments(tips_args);
                return tips_rv;
            case 3: return new TopDestinationsFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    /**
     * This method returns the title of the tab according to the position.
     */
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0 :
                return "General";
            case 1 :
                return "Visa Info";
            case 2 :
                return "Travel Tips";
            case 3:
                return "Top Sites";
        }
        return null;
    }
}
