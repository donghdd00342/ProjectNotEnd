package com.project.notend.notend.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.project.notend.notend.R;
import com.project.notend.notend.data.remote.APIService;
import com.project.notend.notend.data.remote.ApiUtils;
import com.project.notend.notend.data.storage_share.SharedPrefs;
import com.project.notend.notend.entities.Account;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.project.notend.notend.data.config.config.CURRENT_TOKEN_ID;

public class PaypalActivity extends AppCompatActivity {

    private static final String TAG = "PaypalActivity";
    private APIService mAPIService;
    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId("AceDZ2M63jz4B4g-YbnI27D8HQKH6YgeSpUTMCEzkcELbcDuUK3UPbMytyhW2tPWHqITzyZyRNIEuJx4");
    @BindView(R.id.btn_paypal)
    Button paypal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal);
        ButterKnife.bind(this);
        mAPIService = ApiUtils.getApiService();
        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);

        paypal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onPaypalPressed();
            }
        });
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

    public void onPaypalPressed(){
        PayPalPayment payment = new PayPalPayment(new BigDecimal(150), "USD", "Cortana",
                PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if(confirm != null){
                try{
                    Log.i("PaymentExample", confirm.toJSONObject().toString(4));
                    Toast.makeText(this, "Payment Successfull", Toast.LENGTH_SHORT).show();
                } catch(JSONException e){
                    Log.e("PaymentExample", "Failure occurred",e);
                }
            }
        }
        else if(resultCode == Activity.RESULT_CANCELED){
            Log.i("PaymentExample", "User canceled");
        }
        else if(resultCode == PaymentActivity.RESULT_EXTRAS_INVALID){
            Log.i("PaymentExample", "Invalid Payment or PayPalConfiguration was submitted.");
        }
    }
}
