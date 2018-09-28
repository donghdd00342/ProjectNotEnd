package com.project.notend.notend.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.project.notend.notend.R;
import com.project.notend.notend.adapter.SimpleFragmentPagerAdapter;
import com.project.notend.notend.data.storage_share.SharedPrefs;

import butterknife.ButterKnife;

public class Content extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
//    @BindView(R.id.menu)
//    Button _menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        ButterKnife.bind(this);
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            //tab.setCustomView(adapter.getTabView(i));
            tab.setIcon(adapter.getTabIcon(i));
        }
        Button _menu = (Button) findViewById(R.id.menu);
        _menu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                showPopup(v);
            }
        });
    }
//    @Override
//    public void onBackPressed() {
//        // disable going back to the MainActivity
//        moveTaskToBack(true);
//    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v, Gravity.CENTER);
        popup.getMenuInflater().inflate(R.menu.menu_yourself, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
//                Toast.makeText(Content.this,"You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
//                return true;

                switch (item.getItemId()) {
                    case R.id.logout:
                        logout();
                        return true;
                    case R.id.history:
                        history();
                        return true;
                    case R.id.edit:
                        edit();
                        return true;
                    case R.id.changepass:
                        changePassword();
                        return true;
                    case R.id.paypal:
                        paypal();
                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.show();
    }

    public void logout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You want to logout?");
        builder.setCancelable(false);
        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(context, "Yes", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                SharedPrefs.getInstance().clear();
                Intent intent = new Intent(Content.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void history(){
        Intent intent = new Intent(Content.this, HistoryActivity.class);
        startActivity(intent);
    }

    public void edit(){
        Intent intent = new Intent(Content.this, EditProfile.class);
        startActivity(intent);
    }

    public void changePassword(){
        Intent i = new Intent(Content.this, ChangePasswordActivity.class);
        startActivity(i);
    }

    public void paypal(){
        Intent intent = new Intent(Content.this, PaypalActivity.class);
        startActivity(intent);
    }
}
