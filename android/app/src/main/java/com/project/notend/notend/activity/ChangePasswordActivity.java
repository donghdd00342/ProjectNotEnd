package com.project.notend.notend.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.notend.notend.R;
import com.project.notend.notend.data.remote.APIService;
import com.project.notend.notend.data.remote.ApiUtils;
import com.project.notend.notend.data.storage_share.SharedPrefs;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.project.notend.notend.data.config.config.CURRENT_TOKEN_ID;
import static com.project.notend.notend.data.config.config.PREFS_NAME;

public class ChangePasswordActivity extends AppCompatActivity {

    private static final String TAG = "ChangePasswordActivity";
    private static final int REQUEST_SIGNUP = 0;
    private APIService mAPIService;

    @BindView(R.id.edCurrentPassword)
    EditText _currPasswordText;
    @BindView(R.id.edNewPassword)
    EditText _newPasswordText;
    @BindView(R.id.edConfirmPassword)
    EditText _confirmPassword;
    @BindView(R.id.btn_confirm)
    Button _confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
        ButterKnife.bind(this);
        mAPIService = ApiUtils.getApiService();
        _confirmButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                changePassword();
            }
        });
    }

    public void changePassword() {
        Log.d(TAG, "ChangePassword");
        _confirmButton.setEnabled(false);

        if (!validate()) {
            return;
        }


        final ProgressDialog progressDialog = new ProgressDialog(ChangePasswordActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        final String currentPassword = _currPasswordText.getText().toString();
        final String newPassword = _newPasswordText.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        try {
                            JSONObject paramObject = new JSONObject();
                            paramObject.put("currentPassword", currentPassword);
                            paramObject.put("newPassword", newPassword);

//                            SharedPreferences sharedPref1 = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
//                            String token = sharedPref1.getString(CURRENT_TOKEN_ID, "");
                            String token = SharedPrefs.getInstance().get(CURRENT_TOKEN_ID,String.class);
                            String header = "Bearer " + token;

                            mAPIService.changePassword(paramObject.toString(), header).enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
//                                    Log.d(TAG, "onResponse: " + response.isSuccessful());
//                                    Log.d(TAG, "onResponse:, responebody--- " + response.code());
                                    if (response.isSuccessful()) {
                                        Toast.makeText(getBaseContext(), "Done !!!", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                    Intent intent = new Intent(ChangePasswordActivity.this, Content.class);
                                    startActivity(intent);
                                }
                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                //this.finish();
            }
        }
    }

    public boolean validate() {
        boolean valid = true;
        String currentPassword = _currPasswordText.getText().toString();
        String newPassword = _newPasswordText.getText().toString();
        String confirmPassword = _confirmPassword.getText().toString();

        if (currentPassword.isEmpty() || currentPassword.length() < 4 || currentPassword.length() > 10) {
            _currPasswordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _currPasswordText.setError(null);
        }

        if (newPassword.isEmpty() || newPassword.length() < 4 || newPassword.length() > 10) {
            _newPasswordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _newPasswordText.setError(null);
        }

        if (confirmPassword.isEmpty() || confirmPassword.length() < 4 || confirmPassword.length() > 10) {
            _confirmPassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _confirmPassword.setError(null);
        }

        if (!newPassword.equals(confirmPassword)) {
            _confirmPassword.setError("Password not match");
            valid = false;
        } else {
            _confirmPassword.setError(null);
        }

        if (!valid ){
            _confirmButton.setEnabled(true);
        }
        return valid;
    }
}
