package com.project.notend.notend.activity.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.notend.notend.R;
import com.project.notend.notend.adapter.FriendsFragmentPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendsFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    public FriendsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friends, container, false);
        tabLayout = (TabLayout) rootView.findViewById(R.id.sliding_tabs);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        FriendsFragmentPagerAdapter adapter = new FriendsFragmentPagerAdapter(getFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return rootView;
    }
}
