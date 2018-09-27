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
    private TextView tvAge;
    private TextView tvEdu;
    private TextView tvSalary;
    private TextView tvHaveChildren;
    private TextView tvDesireChildren;
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
    @BindView(R.id.btn_Edit)
    Button _edit;
    @BindView(R.id.btn_paypal)
    Button _paypal;
    @BindView(R.id.btn_logout)
    Button _logout;
    @BindView(R.id.btn_history)
    Button _history;

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
        Button _changePassButton = (Button) rootView.findViewById(R.id.btn_changepassview);
        Button btEdit = (Button) rootView.findViewById(R.id.btn_Edit);
        _changePassButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent i = new Intent(context, ChangePasswordActivity.class);
                startActivity(i);
            }
        });
        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditProfile.class);
                startActivity(intent);
            }
        });
        _paypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PaypalActivity.class);
                startActivity(intent);
            }
        });
        _history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HistoryActivity.class);
                startActivity(intent);
            }
        });
        _logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });

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
//        tvAge = (TextView) rootView.findViewById(R.id.myAge);
        tvHeight = (TextView) rootView.findViewById(R.id.myHeight);
        tvAddress = (TextView) rootView.findViewById(R.id.myAddress);
        tvCountry = (TextView) rootView.findViewById(R.id.myCountry);
//        tvEdu = (TextView) rootView.findViewById(R.id.myEdu);
        tvJob = (TextView) rootView.findViewById(R.id.myJob);
//        tvSalary = (TextView) rootView.findViewById(R.id.mySalary);
//        tvHaveChildren = (TextView) rootView.findViewById(R.id.myHaveChildren);
//        tvDesireChildren = (TextView) rootView.findViewById(R.id.myDesireChildren);
        tvStatusLife = (TextView) rootView.findViewById(R.id.myStatusLife);
    }

    private void getAccountInfo(){
        mAPIService.getAccountInfo("Bearer "+token).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful()){
                    Account a = response.body();
                    fillData(a);
//                    Toast.makeText(getContext(),a.getFirstName(),Toast.LENGTH_LONG).show();
                    if(!a.getPaidUser()){
                        _edit.setEnabled(false);
                    } else{
                        _paypal.setText("Extend your payment");
                    }
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {}
        });
    }

    private void fillData(Account a) {
        tvName.setText(a.getFirstName() + " " + a.getLastName());
//        tvAge.setText();
        tvHeight.setText(String.valueOf(a.getHeightCm()));
        tvAddress.setText(a.getCity());
        tvCountry.setText(a.getCountryLiving());
//        tvEdu.setText();
        tvJob.setText(a.getWorkingAt());
//        tvSalary.setText();
//        tvHaveChildren.setText();
//        tvDesireChildren.setText();
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
