package com.project.notend.notend.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.notend.notend.R;
import com.project.notend.notend.data.remote.APIService;
import com.project.notend.notend.data.remote.ApiUtils;
import com.project.notend.notend.data.storage_share.SharedPrefs;
import com.project.notend.notend.entities.Account;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.project.notend.notend.data.config.config.CURRENT_TOKEN_ID;
import static com.project.notend.notend.data.remote.ApiUtils.SERVER_URL_ACCOUNT;

public class FriendDetailUser extends AppCompatActivity{
    @BindView(R.id.imgProfile)
    ImageView imgProfile;
    @BindView(R.id.tvMyName)
    TextView tvMyName;
//    @BindView(R.id.tvMyAge)
//    TextView tvMyAge;
//    @BindView(R.id.tvMyHeight)
//    TextView tvMyheigh;
//    @BindView(R.id.tvMyAddress)
//    TextView tvMyAddress;
//    @BindView(R.id.tvMyCountry)
//    TextView tvMyCountry;
    private APIService mAPIService;
    private static String token = SharedPrefs.getInstance().get(CURRENT_TOKEN_ID, String.class).toString();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_detail_user);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        ButterKnife.bind(this);
        mAPIService = ApiUtils.getApiService();
        String friendLogin;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                friendLogin= null;
            } else {
                friendLogin= extras.getString("friendLogin");
            }
        } else {
            friendLogin = (String) savedInstanceState.getSerializable("friendLogin");
        }
        initData(friendLogin);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initData(String login){
        Log.e("login",""+login);
        mAPIService.getDetailUser(login, "Bearer "+ token).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful()){
                    Account account = response.body();
                    String url = SERVER_URL_ACCOUNT + account.getImageUrl();
                    Glide.with(getBaseContext()).load(url).into(imgProfile);
                    getSupportActionBar().setTitle(account.getFirstName() + " " + account.getLastName());
                    tvMyName.setText(account.getFirstName() + " " + account.getLastName());
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {

            }
        });
    }
}
