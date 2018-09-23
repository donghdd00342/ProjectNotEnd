package com.project.notend.notend.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.project.notend.notend.R;
import com.project.notend.notend.data.remote.APIService;
import com.project.notend.notend.data.remote.ApiUtils;
import com.project.notend.notend.data.storage_share.SharedPrefs;
import com.project.notend.notend.entities.Account;
import com.project.notend.notend.entities.Friend;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.project.notend.notend.data.config.config.CURRENT_ID;
import static com.project.notend.notend.data.config.config.CURRENT_TOKEN_ID;
import static com.project.notend.notend.data.remote.ApiUtils.SERVER_URL_ACCOUNT;

public class DetailUser extends AppCompatActivity {

    @BindView(R.id.imgProfile)
    ImageView imgProfile;
    @BindView(R.id.tvMyName)
    TextView tvMyName;
    @BindView(R.id.tvMyAge)
    TextView tvMyAge;
    @BindView(R.id.tvMyHeight)
    TextView tvMyheigh;
    @BindView(R.id.tvMyAddress)
    TextView tvMyAddress;
    @BindView(R.id.tvMyCountry)
    TextView tvMyCountry;
    private APIService mAPIService;
    @BindView(R.id.btn_addFriend)
    Button btn_addFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        ButterKnife.bind(this);
        mAPIService = ApiUtils.getApiService();
        Bundle getBundle = this.getIntent().getExtras();
        final Account account = getBundle.getParcelable("data");
        initData(account.getLogin());


    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initData(String login){
        mAPIService.getDetailUser(login, "Bearer "+ SharedPrefs.getInstance()
                .get(CURRENT_TOKEN_ID, String.class).toString())
                .enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful()){
                    final Account account = response.body();
                    String url = SERVER_URL_ACCOUNT + account.getImageUrl();
                    Glide.with(getBaseContext()).load(url).into(imgProfile);
                    getSupportActionBar().setTitle(account.getLastName() + " " + account.getFirstName());
                    tvMyName.setText(account.getLastName() + " " + account.getFirstName());
                    btn_addFriend.setVisibility(View.VISIBLE);
                    if (null == account.getFriendStatus() || account.getFriendStatus() == 10){
                        btn_addFriend.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Friend friend = new Friend();
                                friend.setFriendFirstName(account.getFirstName().toString());
                                friend.setFriendLastName(account.getLastName());
                                friend.setFriendImageUrl(account.getImageUrl().toString());
                                friend.setFriendLogin(account.getLogin());
                                friend.setFriendId(account.getId());
                                friend.setOwnerId(SharedPrefs.getInstance()
                                        .get(CURRENT_ID, Integer.class));
                                sendAddFriend(friend);
                            }
                        });
                    }else {
                        if ( account.getFriendStatus()== 11 ){
                            btn_addFriend.setEnabled(false);
                            btn_addFriend.setText("đã gửi lời mời kết bạn");
                        }else {
                            btn_addFriend.setVisibility(View.GONE);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {

            }
        });
    }

    private void sendAddFriend(Friend paramObject){
        mAPIService.askFriend(paramObject,
                "Bearer "+ SharedPrefs.getInstance().get(CURRENT_TOKEN_ID, String.class).toString())
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()){
                            btn_addFriend.setEnabled(false);
                            btn_addFriend.setText("Đã gửi");
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
