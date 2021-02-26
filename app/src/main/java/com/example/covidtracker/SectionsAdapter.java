package com.example.covidtracker;

import android.content.Context;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.covidtracker.Fragment.HelpFragment;
import com.example.covidtracker.Fragment.MapsFragment;
import com.example.covidtracker.Fragment.OverviewFragment;
import com.example.covidtracker.Fragment.ProfileFragment;
import com.example.covidtracker.Fragment.StatisticsFragment;

import java.util.Map;

public class SectionsAdapter extends FragmentPagerAdapter {

    private final Context mContext;

    public SectionsAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return HelpFragment.newInstance();
            case 1:
                return OverviewFragment.newInstance();
            case 2:
                 return MapsFragment.newInstance();
            case 3:
                return StatisticsFragment.newInstance();
            case 4:
                return ProfileFragment.newInstance();
            default:
                return MapsFragment.newInstance();
        }
    }


    @Override
    public int getCount() {
        return 5;
    }
}