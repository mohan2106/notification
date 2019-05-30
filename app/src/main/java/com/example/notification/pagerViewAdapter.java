package com.example.notification;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class pagerViewAdapter extends FragmentPagerAdapter {
    public pagerViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                profileFragment mProfile=new profileFragment();
                return mProfile;
            case 1:
                AllUserFragment ne=new AllUserFragment();
                return ne;
            case 2:
                NotificationFragment nf=new NotificationFragment();
                return nf;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
