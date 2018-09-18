package com.project.notend.notend.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.project.notend.notend.R;
import com.project.notend.notend.data.remote.APIService;
import com.project.notend.notend.data.remote.ApiUtils;
import com.project.notend.notend.dialog.CustomListener;
import com.project.notend.notend.entities.Account;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
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
    Button _signupButton;
    @BindView(R.id.link_login)
    TextView _loginLink;
    @BindView(R.id.rgSex)
    RadioGroup _radioSexGroup;
    private APIService mAPIService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        //API-ACCOUNT
        mAPIService = ApiUtils.getApiServiceAccount();
        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void signup() {
        Log.d(TAG, "Signup");
        if (!validate()) {
            onSignupFailed();
            return;
        }
        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        final String fistName = _nameText.getText().toString();
        final String lastName = _lastNameText.getText().toString();
        final String email = _emailText.getText().toString();
        final String password = _passwordText.getText().toString();
        final String loginName = _loginName.getText().toString().trim();
        final int gender;
        switch (_radioSexGroup.getCheckedRadioButtonId()) {
            case R.id.rbFemale:
                gender = 2;
                break;

            case R.id.rbMale:
                gender = 1;
                break;
            default:
                gender = 3;
                break;
        }
        // TODO: Implement your own signup logic here.
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        // onSignupFailed();
                        //dialogSuccess(Register.this);
                        progressDialog.dismiss();
                        createAccount(new Account(email, fistName, lastName, loginName, password, gender));

                    }
                }, 3000);
    }

    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        dialogSuccess(Register.this);

    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        _signupButton.setEnabled(true);
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
            _rePasswordText.setError("Password Not matching");
            valid = false;
        } else {
            _rePasswordText.setError(null);
        }
        return valid;
    }

    public void dialogSuccess(final Activity activity) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AppTheme_Dark_Dialog));
        dialogBuilder.setMessage("Creat Account Success");
        dialogBuilder.setNegativeButton("ok", null);
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
        Button theButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        theButton.setText("ok");
        theButton.setOnClickListener(new CustomListener(dialog, activity));
    }

    public void createAccount(Account account) {
        mAPIService.createAccount(account).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                //Toast.makeText(Register.this,response.body().toString(),Toast.LENGTH_SHORT).show();
                // Log.i(TAG, "post submitted to API." + response.body().toString());
                Log.d(TAG, "onResponse: " + response.isSuccessful());
                Log.d(TAG, "onResponse:, responebody--- " + response.body());
                onSignupSuccess();
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
                Log.e(TAG, "onFailure: message" + t.getMessage());
                t.printStackTrace();
                Toast.makeText(Register.this, "something went wrong", Toast.LENGTH_SHORT).show();
                onSignupFailed();
                //dialogSuccess(Register.this);
            }
        });
    }
}
