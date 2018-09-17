package com.project.notend.notend.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.notend.notend.R;
import com.project.notend.notend.activity.editprofilefragment.EditFragment;
import com.project.notend.notend.activity.editprofilefragment.HobbyFragment;

import java.util.ArrayList;
import java.util.List;


public class ProfileFragmentAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private static int count;
    private String tabTitles[] = new String[] { "Profile", "Interest"};
    private int[] imageResId = { R.drawable.administrator ,R.drawable.communication};
    private List<Fragment> fragmentList;

    public ProfileFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        fragmentList = new ArrayList<>();
        fragmentList.add(new EditFragment());
        fragmentList.add(new HobbyFragment());
        count = fragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return (Fragment)fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return count;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    public String[] getTabTitles() {
        return tabTitles;
    }

    public View getTabView(int position) {
        // Given you have a custom layout in `res/layout/custom_tab.xml` with a TextView and ImageView
        View v = LayoutInflater.from(mContext).inflate(R.layout.custom_tab, null);
        TextView tv = (TextView) v.findViewById(R.id.tvNameIcon);
        tv.setText(tabTitles[position]);
        ImageView img = (ImageView) v.findViewById(R.id.imgIcon);
        img.setImageResource(imageResId[position]);
        return v;
    }
    public int getTabIcon(int position){
        return imageResId[position];
    }

}
