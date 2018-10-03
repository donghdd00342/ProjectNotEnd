package com.project.notend.notend.activity.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;

import com.project.notend.notend.R;
import com.project.notend.notend.data.remote.APIService;
import com.project.notend.notend.data.remote.ApiUtils;
import com.project.notend.notend.entities.Account;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.project.notend.notend.data.config.config.CURRENT_TOKEN_ID;
import static com.project.notend.notend.data.remote.ApiUtils.SERVER_URL_ACCOUNT;

import com.project.notend.notend.data.storage_share.SharedPrefs;

public class YourSelfFragment extends Fragment {
    private static int id;
    private static String token ;
    private APIService mAPIService;
    @BindView(R.id.myAge)
    EditText tvAge;
    @BindView(R.id.myReligion)
    EditText tvReligion;
    @BindView(R.id.myLang)
    EditText tvLang;
    @BindView(R.id.myHobbies)
    EditText tvHobbies;
    @BindView(R.id.myFamily)
    EditText tvFamily;
    Context context;
    @BindView(R.id.imgProfile)
    ImageView imgProfile;
    @BindView(R.id.myAddress)
    EditText tvAddress;
    @BindView(R.id.myCountry)
    EditText tvCountry;
    @BindView(R.id.myJob)
    EditText tvJob;
    @BindView(R.id.myName)
    EditText tvName;
    @BindView(R.id.myHeight)
    EditText tvHeight;
    @BindView(R.id.edLogin)
    EditText etLogin;
    @BindView(R.id.edEmail)
    EditText etEmail;
    @BindView(R.id.btn_Edit)
    Button btn_edit;
    @BindView(R.id.radioButton_male)
    RadioButton male;
    @BindView(R.id.radioButton_female)
    RadioButton female;
    @BindView(R.id.radioButton_yes)
    RadioButton yes;
    @BindView(R.id.radioButton_no)
    RadioButton no;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAPIService = ApiUtils.getApiServiceAccount();
        token = SharedPrefs.getInstance().get(CURRENT_TOKEN_ID,String.class);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_yourself, container, false);
        initView(rootView);
        context = rootView.getContext();
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    public void initView(View rootView){
        getAccountInfo();
//        tvName = (TextView) rootView.findViewById(R.id.myName);
//        tvGender = (TextView) rootView.findViewById(R.id.myGender);
//        tvAge = (TextView) rootView.findViewById(R.id.myAge);
//        tvHeight = (TextView) rootView.findViewById(R.id.myHeight);
//        tvAddress = (TextView) rootView.findViewById(R.id.myAddress);
//        tvCountry = (TextView) rootView.findViewById(R.id.myCountry);
//        tvReligion = (TextView) rootView.findViewById(R.id.myReligion);
//        tvJob = (TextView) rootView.findViewById(R.id.myJob);
//        tvLang = (TextView) rootView.findViewById(R.id.myLang);
//        tvHobbies = (TextView) rootView.findViewById(R.id.myHobbies);
//        tvFamily = (TextView) rootView.findViewById(R.id.myFamily);
//        tvStatusLife = (TextView) rootView.findViewById(R.id.myStatusLife);
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
        etEmail.setText(a.getEmail());
        etLogin.setText(a.getLogin());
        tvAge.setText(a.getDateOfBirth());
        if (a.getHeightCm() == null){
            tvHeight.setText("");
        } else {
            tvHeight.setText(String.valueOf(a.getHeightCm()));
        }
        tvAddress.setText(a.getCity());
        tvCountry.setText(a.getCountryLiving());
        tvReligion.setText(a.getReligion());
        tvJob.setText(a.getWorkingAt());
        tvLang.setText(a.getMotherTongue());
        tvHobbies.setText(a.getHobbies());
        tvFamily.setText(a.getFamilyDetails());
//        tvStatusLife.setText(String.valueOf(a.getMarriedStatus()));
        if (a.getImageUrl().length()>1){
            String url = SERVER_URL_ACCOUNT + a.getImageUrl();
            Glide.with(getContext()).load(url).into(imgProfile);
        }else {
            imgProfile.setImageResource(R.drawable.avata_demo_1);
        }
        if (a.getGender() == 0){
            female.setChecked(true);
            male.setChecked(false);
        } else {
            male.setChecked(true);
            female.setChecked(false);
        }

//        if (a.getMarriedStatus() == 0){
//            no.setChecked(true);
//            yes.setChecked(false);
//        } else {
//            yes.setChecked(true);
//            no.setChecked(false);
//        }

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(this).attach(this).commit();
        }
    }

    public void edit(){
        btn_edit.setEnabled(false);

        String token = SharedPrefs.getInstance().get(CURRENT_TOKEN_ID,String.class);
        mAPIService.getAccountInfo("Bearer "+token).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful()){
                    id = response.body().getId();
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {}
        });


        final int user_id = id;
        final String firstName = tvName.getText().toString();
        final String country1 = tvCountry.getText().toString();
        final String dob1 = tvAge.getText().toString();
        final String family1 = tvFamily.getText().toString();
        final int height1 = Integer.parseInt(tvHeight.getText().toString());
        final String login1 = etLogin.getText().toString();
        final String tongue1 = tvLang.getText().toString();
        final String religion1 = tvReligion.getText().toString();
        final String working = tvJob.getText().toString();
        final String address1 = tvAddress.getText().toString();
        final String email1 = etEmail.getText().toString();
        int tgender = 0;
        if (male.isChecked()){
            tgender = 1;
        }
        final int gender = tgender;
        int tstatus = 0;
        if (yes.isChecked()){
            tstatus = 1;
        }
        final int status = tstatus;
//
//        // TODO: Implement your own signup logic here.
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
//                        callApi(new Account(self1, city1, contact1, country1, dob1, mail, family1,
//                                firstName, gender, height1, lastName, login1, status, middle, tongue1, qualify,
//                                religion1, working));
                    }
                }, 3000);

    }
}
