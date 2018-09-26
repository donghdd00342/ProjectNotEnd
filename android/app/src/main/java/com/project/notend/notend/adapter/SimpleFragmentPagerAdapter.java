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
import com.project.notend.notend.activity.fragment.FriendsFragment;
import com.project.notend.notend.activity.fragment.ListUserFragment;
import com.project.notend.notend.activity.fragment.MessengerFragment;
import com.project.notend.notend.activity.fragment.YourSelfFragment;

import java.util.ArrayList;
import java.util.List;


public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private static int count;
    private String tabTitles[] = new String[] { "List", "Friend","Messenger","YourSelf"};
    private int[] imageResId = { R.drawable.find_user, R.drawable.friend ,R.drawable.communication,
            R.drawable.administrator};
    private List<Fragment> fragmentList;

    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        fragmentList = new ArrayList<>();
        fragmentList.add(new ListUserFragment());
        fragmentList.add(new FriendsFragment());
        fragmentList.add(new MessengerFragment());
        fragmentList.add(new YourSelfFragment());
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
