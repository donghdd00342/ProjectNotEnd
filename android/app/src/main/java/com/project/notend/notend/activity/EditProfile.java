package com.project.notend.notend.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.project.notend.notend.R;
import com.project.notend.notend.adapter.ProfileFragmentAdapter;
import com.project.notend.notend.adapter.SimpleFragmentPagerAdapter;

public class EditProfile extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        ProfileFragmentAdapter adapter = new ProfileFragmentAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            //tab.setCustomView(adapter.getTabView(i));
            tab.setIcon(adapter.getTabIcon(i));
        }

    }
}
