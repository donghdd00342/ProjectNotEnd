package com.project.notend.notend.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.project.notend.notend.R;
import com.project.notend.notend.entities.Account;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        ButterKnife.bind(this);
        Bundle getBundle = this.getIntent().getExtras();
        Account account = getBundle.getParcelable("data");
        String url = SERVER_URL_ACCOUNT + account.getImageUrl();
        Glide.with(this).load(url).into(imgProfile);
        actionBar.setTitle(account.getLastName() + " " + account.getFirstName());
        tvMyName.setText(account.getLastName() + " " + account.getFirstName());

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
