package com.project.notend.notend.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.project.notend.notend.R;
import com.project.notend.notend.adapter.SimpleFragmentPagerAdapter;
import com.project.notend.notend.data.remote.APIService;
import com.project.notend.notend.data.remote.ApiUtils;
import com.project.notend.notend.data.storage_share.SharedPrefs;
import com.project.notend.notend.entities.Account;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.project.notend.notend.data.config.config.CURRENT_TOKEN_ID;

public class Content extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    private APIService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        ButterKnife.bind(this);
        mAPIService = ApiUtils.getApiService();
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
        ImageView _menu =  findViewById(R.id.menu);
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
        Context wrapper = new ContextThemeWrapper(this, R.style.PopupMenu);
        PopupMenu popup = new PopupMenu(wrapper, v, Gravity.BOTTOM);
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
        String token = SharedPrefs.getInstance().get(CURRENT_TOKEN_ID,String.class);
        mAPIService.getAccountInfo("Bearer "+token).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful()){
//                    Toast.makeText(Content.this, String.valueOf(response.body().getId())
//                            , Toast.LENGTH_SHORT).show();
                    if(response.body().getPaidUser()){
                        Intent intent = new Intent(Content.this, EditProfile.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(Content.this, "Only members can edit their profile"
                                , Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {}
        });

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
