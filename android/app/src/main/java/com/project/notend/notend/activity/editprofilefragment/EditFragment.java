package com.project.notend.notend.activity.editprofilefragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.notend.notend.R;
import com.project.notend.notend.activity.ChangePasswordActivity;
import com.project.notend.notend.activity.EditActivity;
import com.project.notend.notend.data.remote.APIService;
import com.project.notend.notend.dialog.CustomListener;
import com.project.notend.notend.entities.Account;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditFragment extends Fragment {
    private Context context;

//    @BindView(R.id.btn_changepassview)
//    Button _changePassButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private static final String TAG = "SignupActivity";
    @BindView(R.id.edName)
    EditText _nameText;
    @BindView(R.id.edLastName)
    EditText _lastNameText;
    @BindView(R.id.edEmail)
    EditText _emailText;
    @BindView(R.id.edPassword)
    EditText _passwordText;
    @BindView(R.id.edreEnterPassword)
    EditText _rePasswordText;
    @BindView(R.id.edLoginName)
    EditText _loginName;
    @BindView(R.id.btn_signup)
    Button _editButton;
    private APIService mAPIService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit, container, false);
        context = rootView.getContext();
        Button _confirm = (Button) rootView.findViewById(R.id.btn_Edit);
        _confirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });

        return rootView;
    }

    public void signup() {
        Log.d(TAG, "EditActivity");
        _editButton.setEnabled(false);
        if (!validate()) {
            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(context,R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        final String fistName = _nameText.getText().toString();
        final String lastName = _lastNameText.getText().toString();
        final String email = _emailText.getText().toString();
        final String password = _passwordText.getText().toString();
        final String loginName = _loginName.getText().toString().trim();
        // TODO: Implement your own signup logic here.
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        // onSignupFailed();
                        //dialogSuccess(EditActivity.this);
                        progressDialog.dismiss();
                        editAccount(new Account(email,fistName,lastName,loginName,password));

                    }
                }, 3000);
    }

    public boolean validate() {
        boolean valid = true;
        String name = _nameText.getText().toString();
        String lastName = _lastNameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String rePassword = _rePasswordText.getText().toString();
        String loginName = _loginName.getText().toString().trim();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }
        if (lastName.isEmpty() || name.length() < 3) {
            _lastNameText.setError("at least 3 characters");
            valid = false;
        } else {
            _lastNameText.setError(null);
        }
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }
        if (loginName.isEmpty() || name.length() < 3) {
            _loginName.setError("at least 3 characters");
            valid = false;
        } else {
            _loginName.setError(null);
        }
        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }
        if (!password.equals(rePassword)) {
            _rePasswordText.setError("Password not match");
            valid = false;
        } else {
            _rePasswordText.setError(null);
        }
        if(!valid){
            _editButton.setEnabled(true);
        }
        return valid;
    }

    public void editAccount(Account account) {
        mAPIService.createAccount(account).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                //Toast.makeText(EditActivity.this,response.body().toString(),Toast.LENGTH_SHORT).show();
                // Log.i(TAG, "post submitted to API." + response.body().toString());
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
    }
}
