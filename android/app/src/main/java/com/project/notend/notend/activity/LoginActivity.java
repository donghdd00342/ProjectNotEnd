package com.project.notend.notend.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.notend.notend.R;
import com.project.notend.notend.data.remote.APIService;
import com.project.notend.notend.data.remote.ApiUtils;
import com.project.notend.notend.data.storage_share.SharedPrefs;
import com.project.notend.notend.entities.Account;
import com.project.notend.notend.entities.TokenId;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.project.notend.notend.data.config.config.CURRENT_ID;
import static com.project.notend.notend.data.config.config.CURRENT_TOKEN_ID;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private static ProgressDialog progressDialog;
    private APIService mAPIService;

    @BindView(R.id.edUsername)
    EditText _UserText;
    @BindView(R.id.edPassword)
    EditText _passwordText;
    @BindView(R.id.btn_login)
    Button _loginButton;
    @BindView(R.id.link_signup)
    TextView _signupLink;
    @BindView(R.id.link_forgetPass)
    TextView forgetPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mAPIService = ApiUtils.getApiService();
        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });
        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
        forgetPass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), GetkeyPasswordActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            return;
        }

        _loginButton.setEnabled(false);

        progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        final String userName = _UserText.getText().toString();
        final String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        // onLoginFailed();
                        try {
                            JSONObject paramObject = new JSONObject();
                            paramObject.put("username", userName);
                            paramObject.put("password", password);
                            callAPI(paramObject.toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, 3000);
    }


//    @Override
//    public void onBackPressed() {
//        // disable going back to the MainActivity
//        moveTaskToBack(true);
//    }

    public boolean validate() {
        boolean valid = true;
        String userName = _UserText.getText().toString();
        String password = _passwordText.getText().toString();

        if (userName.isEmpty() || userName.length() < 3) {
            _UserText.setError("enter a valid name login");
            valid = false;
        } else {
            _UserText.setError(null);
        }
        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }
        return valid;
    }

    public void callAPI(String paramObject) {
        mAPIService.loginAccount(paramObject).enqueue(new Callback<TokenId>() {
            @Override
            public void onResponse(Call<TokenId> call, Response<TokenId> response) {
                if (response.isSuccessful()) {
                    TokenId resObj = response.body();
                    if (resObj.getIdToken().length() > 0) {
                        SharedPrefs.getInstance().put(CURRENT_TOKEN_ID, resObj.getIdToken().toString());
                        setIdAccount(resObj.getIdToken().toString());
                        Intent intent = new Intent(LoginActivity.this, Content.class);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        finish();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "The username or password is incorrect",
                            Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    _loginButton.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call<TokenId> call, Throwable t) {
                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                _loginButton.setEnabled(true);
            }
        });
    }

    private void setIdAccount(String idToken){
        mAPIService.getAccountInfo( "Bearer " + idToken)
                .enqueue(new Callback<Account>() {
                    @Override
                    public void onResponse(Call<Account> call, Response<Account> response) {
                        Log.e("myApp", "success: "+response);
                        if (response.isSuccessful()){
                            Account a = response.body();
                            SharedPrefs.getInstance().put(CURRENT_ID, a.getId());
                        }
                    }

                    @Override
                    public void onFailure(Call<Account> call, Throwable t) {
                        Log.e("myApp", "success: "+t.getMessage());
                    }
                });
    }
}
