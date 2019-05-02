package com.example.travelbuddy;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CountryTabAdapter extends FragmentPagerAdapter {

    CountryTabAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Return fragment with respect to Position .
     */
    @Override
    public Fragment getItem(int position)
    {
        switch (position){
            case 0 : return new TransportationFragment();
            case 1 : return new TransportationFragment();
            case 2 : return new TransportationFragment();
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
                return "Tab 1";
            case 1 :
                return "Tab 2";
            case 2 :
                return "Tab 3";
        }
        return null;
    }
}