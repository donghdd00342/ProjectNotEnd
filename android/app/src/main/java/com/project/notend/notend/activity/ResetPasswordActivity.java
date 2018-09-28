package com.project.notend.notend.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
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

public class ResetPasswordActivity extends AppCompatActivity {

    private static final String TAG = "ResetPasswordActivity";
    private static final int REQUEST_SIGNUP = 0;
    private APIService mAPIService;

    @BindView(R.id.edKey)
    EditText _key;
    @BindView(R.id.edNewPassword)
    EditText _newPasswordText;
    @BindView(R.id.edConfirmPassword)
    EditText _confirmPassword;
    @BindView(R.id.btn_reset)
    Button _reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);
        ButterKnife.bind(this);
        mAPIService = ApiUtils.getApiService();
        _reset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }

    public void resetPassword() {
        Log.d(TAG, "ResetPassword");
        _reset.setEnabled(false);

        if (!validate()) {
            return;
        }


        final ProgressDialog progressDialog = new ProgressDialog(ResetPasswordActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        final String key = _key.getText().toString();
        final String newPassword = _newPasswordText.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        try {
                            JSONObject paramObject = new JSONObject();
                            paramObject.put("key", key);
                            paramObject.put("newPassword", newPassword);

                            mAPIService.resetPassword(paramObject.toString()).enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    if (response.isSuccessful()) {
//                                        Toast.makeText(getBaseContext(), "Done !!!", Toast.LENGTH_SHORT).show();
//                                        finish();
//                                        Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
//                                        startActivity(intent);
                                        reset();
                                    } else {
                                        Toast.makeText(getBaseContext(), "Incorrect key !!!", Toast.LENGTH_SHORT).show();
                                        _reset.setEnabled(true);
                                    }

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

    public void reset() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AppTheme_Dark_Dialog));
        builder.setMessage("Success !!!");
        builder.setCancelable(false);
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                SharedPrefs.getInstance().clear();
                Intent intent = new Intent(ResetPasswordActivity.this,
                        LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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
        String key = _key.getText().toString();
        String newPassword = _newPasswordText.getText().toString();
        String confirmPassword = _confirmPassword.getText().toString();

        if (key.isEmpty()) {
            _key.setError("Please enter key !!!");
            valid = false;
        } else {
            _key.setError(null);
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
            _reset.setEnabled(true);
        }
        return valid;
    }
}
