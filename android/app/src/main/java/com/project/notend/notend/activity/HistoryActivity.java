package com.project.notend.notend.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.project.notend.notend.R;
import com.project.notend.notend.adapter.TransactionAdapter;
import com.project.notend.notend.data.remote.APIService;
import com.project.notend.notend.data.remote.ApiUtils;
import com.project.notend.notend.data.storage_share.SharedPrefs;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.project.notend.notend.data.config.config.CURRENT_TOKEN_ID;

public class HistoryActivity extends AppCompatActivity {
    private static final String TAG = "HistoryActivity";
    private static final int REQUEST_SIGNUP = 0;
    private APIService mAPIService;

    List<String> transactions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
        mAPIService = ApiUtils.getApiService();
        init_data();
        Log.e("TAG", String.valueOf(transactions.size()) );

        TransactionAdapter adapter = new TransactionAdapter(this, transactions);

        GridLayoutManager layoutManager = new GridLayoutManager(this,1);

        RecyclerView rvTransaction = (RecyclerView) findViewById(R.id.rvHistory);
        rvTransaction.setLayoutManager(layoutManager);
        rvTransaction.setAdapter(adapter);
    }

    private void init_data(){
        String token = SharedPrefs.getInstance().get(CURRENT_TOKEN_ID,String.class);
        String header = "Bearer " + token;
        mAPIService.getHistory(header).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                String gson = new Gson().toJson(response.body());
                try {
                    JSONArray json = new JSONArray(gson);
                    for (int i = 0; i < json.length(); i++){
                        JSONObject jobj = new JSONObject(json.getString(i));
                        String transaction = "Amount: " + jobj.getString("amout") + "$\nPaydate: " +
                                jobj.getString("payDate");
                        transactions.add(transaction);
                    }
                } catch (Exception e){
                    Log.e("TAG", e.toString() );
                }
                Log.e("TAG", "response 33: "+new Gson().toJson(response.body()) );
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d(TAG, "Meo' hieu?");
            }
        });
    }
}
