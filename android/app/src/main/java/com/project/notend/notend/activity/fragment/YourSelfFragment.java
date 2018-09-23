package com.project.notend.notend.activity.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.project.notend.notend.activity.ChangePasswordActivity;
import com.project.notend.notend.activity.Content;
import com.project.notend.notend.activity.EditProfile;

import butterknife.BindView;

import com.project.notend.notend.MainActivity;
import com.project.notend.notend.R;
import com.project.notend.notend.activity.LoginActivity;
import com.project.notend.notend.activity.PaypalActivity;
import com.project.notend.notend.data.remote.APIService;
import com.project.notend.notend.data.remote.ApiUtils;
import com.project.notend.notend.entities.Account;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.project.notend.notend.data.config.config.CURRENT_TOKEN_ID;
import com.project.notend.notend.data.storage_share.SharedPrefs;

public class YourSelfFragment extends Fragment {
    private static String token = SharedPrefs.getInstance().get(CURRENT_TOKEN_ID,String.class);
    private APIService mAPIService;
    private TextView tvName;
    private TextView tvAge;
    private TextView tvHeight;
    private TextView tvAddress;
    private TextView tvCountry;
    private TextView tvEdu;
    private TextView tvJob;
    private TextView tvSalary;
    private TextView tvHaveChildren;
    private TextView tvDesireChildren;
    private TextView tvStatusLife;
    Context context;

    @BindView(R.id.btn_Edit)
    Button _edit;
    @BindView(R.id.btn_paypal)
    Button _paypal;
    @BindView(R.id.btn_logout)
    Button _logout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAPIService = ApiUtils.getApiServiceAccount();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_yourself, container, false);
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
                    if(!a.getPaidUser()){
                        _edit.setEnabled(true);
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
    }
}
