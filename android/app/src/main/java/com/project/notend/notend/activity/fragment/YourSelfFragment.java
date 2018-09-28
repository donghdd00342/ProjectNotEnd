package com.project.notend.notend.activity.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.project.notend.notend.activity.ChangePasswordActivity;
import com.project.notend.notend.activity.Content;
import com.project.notend.notend.activity.EditProfile;

import butterknife.BindView;

import com.project.notend.notend.MainActivity;
import com.project.notend.notend.R;
import com.project.notend.notend.activity.HistoryActivity;
import com.project.notend.notend.activity.LoginActivity;
import com.project.notend.notend.activity.PaypalActivity;
import com.project.notend.notend.data.remote.APIService;
import com.project.notend.notend.data.remote.ApiUtils;
import com.project.notend.notend.entities.Account;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.project.notend.notend.data.config.config.CURRENT_ID;
import static com.project.notend.notend.data.config.config.CURRENT_TOKEN_ID;
import static com.project.notend.notend.data.remote.ApiUtils.SERVER_URL_ACCOUNT;

import com.project.notend.notend.data.storage_share.SharedPrefs;

public class YourSelfFragment extends Fragment {
    private static String token ;
    private APIService mAPIService;
    @BindView(R.id.myAge)
    TextView tvAge;
    @BindView(R.id.myReligion)
    TextView tvReligion;
    @BindView(R.id.myLang)
    TextView tvLang;
    @BindView(R.id.myHobbies)
    TextView tvHobbies;
    @BindView(R.id.myFamily)
    TextView tvFamily;
    Context context;
    @BindView(R.id.imgProfile)
    ImageView imgProfile;
    @BindView(R.id.myAddress)
    TextView tvAddress;
    @BindView(R.id.myCountry)
    TextView tvCountry;
    @BindView(R.id.myJob)
    TextView tvJob;
    @BindView(R.id.myStatusLife)
    TextView tvStatusLife;
    @BindView(R.id.myName)
    TextView tvName;
    @BindView(R.id.myHeight)
    TextView tvHeight;
    @BindView(R.id.myGender)
    TextView tvGender;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAPIService = ApiUtils.getApiServiceAccount();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_yourself, container, false);
        token = SharedPrefs.getInstance().get(CURRENT_TOKEN_ID,String.class);
        initView(rootView);
        context = rootView.getContext();
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    public void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
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
                String token = SharedPrefs.getInstance().get(CURRENT_TOKEN_ID,String.class);
                SharedPrefs.getInstance().clear();
                Content content = (Content) getActivity();
                content.finish();
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void initView(View rootView){
        getAccountInfo();
        tvName = (TextView) rootView.findViewById(R.id.myName);
        tvGender = (TextView) rootView.findViewById(R.id.myGender);
        tvAge = (TextView) rootView.findViewById(R.id.myAge);
        tvHeight = (TextView) rootView.findViewById(R.id.myHeight);
        tvAddress = (TextView) rootView.findViewById(R.id.myAddress);
        tvCountry = (TextView) rootView.findViewById(R.id.myCountry);
        tvReligion = (TextView) rootView.findViewById(R.id.myReligion);
        tvJob = (TextView) rootView.findViewById(R.id.myJob);
        tvLang = (TextView) rootView.findViewById(R.id.myLang);
        tvHobbies = (TextView) rootView.findViewById(R.id.myHobbies);
        tvFamily = (TextView) rootView.findViewById(R.id.myFamily);
        tvStatusLife = (TextView) rootView.findViewById(R.id.myStatusLife);
    }

    private void getAccountInfo(){
        mAPIService.getAccountInfo("Bearer "+token).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful()){
                    Account a = response.body();
                    fillData(a);
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {}
        });
    }

    private void fillData(Account a) {
        tvName.setText(a.getFirstName() + " " + a.getLastName());
        String gender;
        if(a.getGender()==0){
            gender = "Female";
        }else if(a.getGender()==1){
            gender = "Male";
        }else{
            gender = "Unknown";
        }
        tvGender.setText(gender);
        tvAge.setText(a.getDateOfBirth());
        tvHeight.setText(String.valueOf(a.getHeightCm()));
        tvAddress.setText(a.getCity());
        tvCountry.setText(a.getCountryLiving());
        tvReligion.setText(a.getReligion());
        tvJob.setText(a.getWorkingAt());
        tvLang.setText(a.getMotherTongue());
        tvHobbies.setText(a.getHobbies());
        tvFamily.setText(a.getFamilyDetails());
        tvStatusLife.setText(String.valueOf(a.getMarriedStatus()));
        String url = SERVER_URL_ACCOUNT + a.getImageUrl();
        Glide.with(getContext()).load(url).into(imgProfile);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(this).attach(this).commit();
        }
    }
}
