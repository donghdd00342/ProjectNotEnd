package com.project.notend.notend.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.project.notend.notend.R;

public class Content extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    private int[] tabIcons = {
            R.drawable.human,
            R.drawable.human,
            R.drawable.human
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        viewPager= (ViewPager) findViewById(R.id.viewpager);
        set(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

    }
}
