package com.project.notend.notend.activity;

import android.app.Activity;
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
import com.project.notend.notend.dialog.CustomListener;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.project.notend.notend.data.config.config.CURRENT_TOKEN_ID;

public class GetkeyPasswordActivity extends AppCompatActivity {

    private static final String TAG = "ResetPasswordActivity";
    private static final int REQUEST_SIGNUP = 0;
    private APIService mAPIService;

    @BindView(R.id.edEmail)
    EditText _email;
    @BindView(R.id.btn_getkey)
    Button getkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getkeyreset);
        ButterKnife.bind(this);
        mAPIService = ApiUtils.getApiService();
        getkey.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                get_key();
            }
        });
    }

    public void get_key() {
        Log.d(TAG, "ResetPassword");
        getkey.setEnabled(false);

        if (!validate()) {
            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(GetkeyPasswordActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        final String email = _email.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        mAPIService.getKey(email).enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
//                                    Log.d(TAG, "onResponse: " + response.isSuccessful());
//                                    Log.d(TAG, "onResponse:, responebody--- " + response.code());
                                if (response.isSuccessful()) {
                                    reset();
                                } else {
                                    Toast.makeText(getBaseContext(), "Incorrect email address", Toast.LENGTH_SHORT).show();
                                    getkey.setEnabled(true);
                                }
                            }
                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    public void reset() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AppTheme_Dark_Dialog));
        builder.setMessage("A key has been sent to your email address!!!");
        builder.setCancelable(false);
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                SharedPrefs.getInstance().clear();
                Intent intent = new Intent(GetkeyPasswordActivity.this,
                        ResetPasswordActivity.class);
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
        return valid;
    }
}
