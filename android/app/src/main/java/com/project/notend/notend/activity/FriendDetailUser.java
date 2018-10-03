package com.project.notend.notend.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.project.notend.notend.R;
import com.project.notend.notend.activity.fragment.FriendsFragment1;
import com.project.notend.notend.data.remote.APIService;
import com.project.notend.notend.data.remote.ApiUtils;
import com.project.notend.notend.data.storage_share.SharedPrefs;
import com.project.notend.notend.entities.Account;
import com.project.notend.notend.entities.Friend;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.myName)
    TextView tvMyName;
    @BindView(R.id.myGender)
    TextView tvMyGender;
    @BindView(R.id.btn_delFriend)
    Button btn_delFriend;
//    @BindView(R.id.tvMyAge)
//    TextView tvMyAge;
//    @BindView(R.id.tvMyHeight)
//    TextView tvMyheigh;
//    @BindView(R.id.tvMyAddress)
//    TextView tvMyAddress;
//    @BindView(R.id.tvMyCountry)
//    TextView tvMyCountry;
    private APIService mAPIService;
    private static String token;

    List<Friend> friendList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_detail_user);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        ButterKnife.bind(this);
        mAPIService = ApiUtils.getApiService();
        token = SharedPrefs.getInstance().get(CURRENT_TOKEN_ID, String.class).toString();
        String friendLogin;
        Integer friendId;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                friendLogin = null;
                friendId = null;
            } else {
                friendLogin = extras.getString("friendLogin");
                friendId = extras.getInt("friendId");
            }
        } else {
            friendLogin = (String) savedInstanceState.getSerializable("friendLogin");
            friendId = (Integer) savedInstanceState.getSerializable("friendId");
        }
        initData(friendLogin, friendId);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initData(String login, final Integer friendId){
        token = SharedPrefs.getInstance().get(CURRENT_TOKEN_ID, String.class).toString();
        mAPIService.getDetailUser(login, "Bearer "+ token).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful()){
                    final Account account = response.body();
                    String url = SERVER_URL_ACCOUNT + account.getImageUrl();
                    Glide.with(getBaseContext()).load(url).into(imgProfile);
                    getSupportActionBar().setTitle(account.getFirstName() + " " + account.getLastName());
                    tvMyName.setText(account.getFirstName() + " " + account.getLastName());
                    String gender;
                    if(account.getGender()==0){
                        gender = "Female";
                    }else if(account.getGender()==1){
                        gender = "Male";
                    }else{
                        gender = "Unknown";
                    }
                    tvMyGender.setText(gender);
                    btn_delFriend.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showAlert(friendId);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {

            }
        });
    }

    private void showAlert(final Integer id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(FriendDetailUser.this);
        builder.setMessage("You want to delete your friend?");
        builder.setCancelable(false);
        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                senđDelFriend(id);
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void senđDelFriend(Integer id){
        mAPIService.deleteRequestFriend(id, "Bearer "+ token).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.e("resDel_FriendDetailUser",""+response);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                    }
                });
    }
}
