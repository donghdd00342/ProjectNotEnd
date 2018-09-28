package com.project.notend.notend.activity.editprofilefragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.project.notend.notend.R;
import com.project.notend.notend.activity.Content;
import com.project.notend.notend.activity.EditProfile;
import com.project.notend.notend.activity.Register;
import com.project.notend.notend.data.remote.APIService;
import com.project.notend.notend.data.remote.ApiUtils;
import com.project.notend.notend.data.storage_share.SharedPrefs;
import com.project.notend.notend.entities.Account;
import com.project.notend.notend.entities.TokenId;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.project.notend.notend.data.config.config.CURRENT_TOKEN_ID;

public class EditFragment extends Fragment {
    private Context context;
    static int id;

//    @BindView(R.id.btn_changepassview)
//    Button _changePassButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private static final String TAG = "EditActivity";
    @BindView(R.id.radioButton_yes)
    RadioButton yes;
    @BindView(R.id.radioButton_no)
    RadioButton no;
    @BindView(R.id.radioButton_male)
    RadioButton male;
    @BindView(R.id.radioButton_female)
    RadioButton female;
    @BindView(R.id.edHeight)
    EditText height;
    @BindView(R.id.edDob)
    EditText dob;
    @BindView(R.id.edLogin)
    EditText login;
    @BindView(R.id.edEmail)
    EditText email;
    @BindView(R.id.edName)
    EditText _firstNameText;
    @BindView(R.id.edLastName)
    EditText _lastNameText;
    @BindView(R.id.edMiddleName)
    EditText _middleName;
    @BindView(R.id.edSelf)
    EditText _self;
    @BindView(R.id.edFamily)
    EditText _family;
    @BindView(R.id.edCity)
    EditText _city;
    @BindView(R.id.edContactNumber)
    EditText contact;
    @BindView(R.id.edCountry)
    EditText country;
    @BindView(R.id.edMotherTongue)
    EditText tongue;
    @BindView(R.id.edQualification)
    EditText qualification;
    @BindView(R.id.edReligion)
    EditText religion;
    @BindView(R.id.edWorkingAt)
    EditText workingAt;
    @BindView(R.id.btn_Edit)
    Button _confirm;

    private APIService mAPIService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        mAPIService = ApiUtils.getApiServiceAccount();
        View rootView = inflater.inflate(R.layout.fragment_edit, container, false);
        context = rootView.getContext();
        ButterKnife.bind(this, rootView);

        String token = SharedPrefs.getInstance().get(CURRENT_TOKEN_ID,String.class);
        String header = "Bearer " + token;

//        Toast.makeText(context, header ,Toast.LENGTH_SHORT).show();
        mAPIService.getAccountInfo(header).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
//                Toast.makeText(EditActivity.this,response.body().toString(),Toast.LENGTH_SHORT).show();
                // Log.i(TAG, "post submitted to API." + response.body().toString());
                int status = 0;
                if (response.body().getMarriedStatus() == null){
                    status = 0;
                }
                if (status == 0){
                    no.setChecked(true);
                    yes.setChecked(false);
                } else {
                    yes.setChecked(true);
                    no.setChecked(false);
                }
                int gender = response.body().getGender();
                if (gender == 0){
                    female.setChecked(true);
                    male.setChecked(false);
                } else {
                    male.setChecked(true);
                    female.setChecked(false);
                }
                height.setText(String.valueOf(response.body().getHeightCm()));
                dob.setText(String.valueOf(response.body().getDateOfBirth()));
                login.setText(response.body().getLogin());
                email.setText(response.body().getEmail());
                _firstNameText.setText(response.body().getFirstName());
                _lastNameText.setText(response.body().getLastName());
                _middleName.setText(String.valueOf(response.body().getMiddleName()));
                _self.setText(String.valueOf(response.body().getAboutSelf()));
                _family.setText(String.valueOf(response.body().getFamilyDetails()));
                _city.setText(String.valueOf(response.body().getCity()));
                country.setText(String.valueOf(response.body().getCountryLiving()));
                contact.setText(String.valueOf(response.body().getContactNumber()));
                tongue.setText(String.valueOf(response.body().getMotherTongue()));
                qualification.setText(String.valueOf(response.body().getQualification()));
                religion.setText(String.valueOf(response.body().getReligion()));
                workingAt.setText(String.valueOf(response.body().getWorkingAt()));

                Log.d(TAG, "onResponse: "+response.isSuccessful());
                Log.d(TAG, "onResponse:, responebody--- "+response.body());
            }
            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
                Log.e(TAG, "onFailure: message"+t.getMessage() );
                t.printStackTrace();
                Toast.makeText(context, "something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

        _confirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                edit();
            }
        });

        return rootView;
    }

    public void edit(){
        Log.d(TAG, "Edit");
        if (!validate()) {
            return;
        }
        _confirm.setEnabled(false);

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
        final String firstName = _firstNameText.getText().toString();
        final String lastName = _lastNameText.getText().toString();
        final String middle = _middleName.getText().toString();
        final String mail = email.getText().toString();
        final String self1 = _self.getText().toString();
        final String city1 = _city.getText().toString().trim();
        final String contact1 = contact.getText().toString();
        final String country1 = country.getText().toString();
        final String dob1 = dob.getText().toString();
        final String family1 = _family.getText().toString();
        final int height1 = Integer.parseInt(height.getText().toString());
        final String login1 = login.getText().toString();
        final String tongue1 = tongue.getText().toString();
        final String qualify = qualification.getText().toString();
        final String religion1 = religion.getText().toString();
        final String working = workingAt.getText().toString();
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

        // TODO: Implement your own signup logic here.
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        callApi(new Account(self1, city1, contact1, country1, dob1, mail, family1,
                                firstName, gender, height1, lastName, login1, status, middle, tongue1, qualify,
                                religion1, working));
                    }
                }, 3000);

    }

    public boolean validate(){
        return true;
    }

    public void callApi(Account account){
        String token = SharedPrefs.getInstance().get(CURRENT_TOKEN_ID,String.class);
        String header = "Bearer " + token;
        mAPIService.editAccount(account, header).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                //Toast.makeText(Register.this,response.body().toString(),Toast.LENGTH_SHORT).show();
                // Log.i(TAG, "post submitted to API." + response.body().toString());
                boolean code = response.isSuccessful();
                Log.d(TAG, "onResponse: "+ code);
                Log.d(TAG, "onResponse:, responebody--- "+response.body());
                if(code){
                    Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                    _confirm.setEnabled(true);
                }
            }
            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
                Log.e(TAG, "onFailure: message"+t.getMessage() );
                t.printStackTrace();
                Toast.makeText(context, "something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
