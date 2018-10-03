package com.project.notend.notend.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.project.notend.notend.activity.fragment.FriendsFragment1;
import com.project.notend.notend.activity.fragment.FriendsFragment2;

import java.util.ArrayList;
import java.util.List;

public class FriendsFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private static int count;
    private String tabTitles[] = new String[] { "Friend","Request"};
    private List<Fragment> fragmentList;
    public FriendsFragmentPagerAdapter( FragmentManager fm) {
        super(fm);
        fragmentList = new ArrayList<>();
        fragmentList.add(new FriendsFragment1());
        fragmentList.add(new FriendsFragment2());
        count = fragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return  (Fragment)fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return count;
    }

    public String[] getTabTitles() {
        return tabTitles;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
