package com.project.notend.notend.activity.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;

import com.project.notend.notend.R;
import com.project.notend.notend.activity.GetkeyPasswordActivity;
import com.project.notend.notend.activity.LoginActivity;
import com.project.notend.notend.activity.ResetPasswordActivity;
import android.app.DatePickerDialog.OnDateSetListener;
import com.project.notend.notend.data.remote.APIService;
import com.project.notend.notend.data.remote.ApiUtils;
import com.project.notend.notend.entities.Account;
import android.app.DatePickerDialog;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.project.notend.notend.data.config.config.CURRENT_TOKEN_ID;
import static com.project.notend.notend.data.remote.ApiUtils.SERVER_URL_ACCOUNT;

import com.project.notend.notend.data.storage_share.SharedPrefs;

import org.w3c.dom.Text;

import java.time.Instant;
import java.util.Calendar;
import java.util.List;

public class YourSelfFragment extends Fragment {

    private static final String TAG = "EditActivity";
    private static int id;
    private static Account acc;
    private static String token ;
    private static ProgressDialog progressDialog;
    private static Calendar cal = Calendar.getInstance();
    private APIService mAPIService;
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
    @BindView(R.id.tvCity)
    EditText tvAddress;
    @BindView(R.id.myCountry)
    EditText tvCountry;
    @BindView(R.id.myJob)
    EditText tvJob;
    @BindView(R.id.myName)
    EditText tvName;
    @BindView(R.id.myNameLast)
    EditText tvNameLast;
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
    @BindView(R.id.txtdate)
    Button txtDate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
//        SharedPrefs.getInstance().clear();
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
        btn_edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                edit();
            }
        });
        txtDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
//        Toast.makeText(context, acc.getEmail(), Toast.LENGTH_SHORT).show();
        return rootView;

    }

    public void showDatePickerDialog(){
        OnDateSetListener callback = new OnDateSetListener() {
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear,
                                  int dayOfMonth) {
                //Mỗi lần thay đổi ngày tháng năm thì cập nhật lại TextView Date
                txtDate.setText(
                        (dayOfMonth) +"/"+(monthOfYear+1)+"/"+year);
                //Lưu vết lại biến ngày hoàn thành
                cal.set(year, monthOfYear, dayOfMonth);
//                Toast.makeText(context, cal.YEAR, Toast.LENGTH_SHORT).show();
            }
        };
        //các lệnh dưới này xử lý ngày giờ trong DatePickerDialog
        //sẽ giống với trên TextView khi mở nó lên
        String s = txtDate.getText()+"";
        if(s.equalsIgnoreCase("choose")){
            s = "1/1/2000";
        }
        String strArrtmp[]=s.split("/");
        int ngay=Integer.parseInt(strArrtmp[0]);
        int thang=Integer.parseInt(strArrtmp[1])-1;
        int nam=Integer.parseInt(strArrtmp[2]);
        DatePickerDialog pic=new DatePickerDialog(
                context,
                callback, nam, thang, ngay);
        pic.show();
    }

    public boolean validate() {
        boolean valid = true;
        String name = etLogin.getText().toString();
        String email = etEmail.getText().toString();
        String height = tvHeight.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            etLogin.setError("at least 3 characters");
            valid = false;
        } else {
            etLogin.setError(null);
        }
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("enter a valid email address");
            valid = false;
        } else {
            etEmail.setError(null);
        }

        if (height.isEmpty() || height.length() < 2) {
            tvHeight.setError("enter a valid height");
            valid = false;
        } else {
            tvHeight.setError(null);
        }
        if (valid == false) {
            Toast.makeText(context, "Please finish your form !!!", Toast.LENGTH_SHORT).show();
        }
        return valid;
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
                    acc = a;
                    fillData(a);
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {}
        });
    }

    private void fillData(Account a) {
        tvName.setText(a.getFirstName());
        tvNameLast.setText(a.getLastName());
        etEmail.setText(a.getEmail());
        etLogin.setText(a.getLogin());
        String s = a.getDateOfBirth();
        if(s.length() < 1){
            txtDate.setText("Choose");
        } else {
            txtDate.setText(a.getDateOfBirth());
        }
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

        if (a.getMarriedStatus() == null){
            return;
        } else if (a.getMarriedStatus() == 0){
            no.setChecked(true);
            yes.setChecked(false);
        } else {
            yes.setChecked(true);
            no.setChecked(false);
        }

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
        if(!validate()){
            return;
        }

        progressDialog = new ProgressDialog(context, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();


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
        final String lastName = tvNameLast.getText().toString();
        final String country1 = tvCountry.getText().toString();
        final String dob1 = txtDate.getText().toString();
        final String family1 = tvFamily.getText().toString();
        final int height1 = Integer.parseInt(tvHeight.getText().toString());
        final String login1 = etLogin.getText().toString();
        final String tongue1 = tvLang.getText().toString();
        final String religion1 = tvReligion.getText().toString();
        final String working = tvJob.getText().toString();
        final String city1 = tvAddress.getText().toString();
        final String hobbies = tvHobbies.getText().toString();
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

        final boolean paid = acc.getPaidUser();
        final List<String> authorize = acc.getAuthorities();
        final String avatar = acc.getImageUrl();
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        callApiEdit(new Account(country1, dob1, email1, family1, firstName, height1,
                                user_id, login1, tongue1, religion1, working, city1, gender, status,
                                lastName, paid, authorize, avatar, hobbies));
                    }
                }, 3000);

    }

    public void callApiEdit(final Account account){
        String token = SharedPrefs.getInstance().get(CURRENT_TOKEN_ID,String.class);
        mAPIService.editAccount(account, "Bearer "+token).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "onResponse: " + account.toString());
                Log.d(TAG, "onResponse: " + response.isSuccessful());
                Log.d(TAG, "onResponse:, responebody--- " + response.code());
                if(response.code() == 200){
                    progressDialog.dismiss();
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Something went wrong :(", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                progressDialog.dismiss();
                Log.d(TAG, "Failed");
            }
        });
    }
}
