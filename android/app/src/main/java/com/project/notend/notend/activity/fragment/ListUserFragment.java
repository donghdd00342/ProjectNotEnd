package com.project.notend.notend.activity.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.notend.notend.R;
import com.project.notend.notend.adapter.ListUserAdapter;
import com.project.notend.notend.data.remote.APIService;
import com.project.notend.notend.data.remote.ApiUtils;
import com.project.notend.notend.data.storage_share.SharedPrefs;
import com.project.notend.notend.entities.Account;
import com.project.notend.notend.entities.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.project.notend.notend.data.config.config.CURRENT_TOKEN_ID;

public class ListUserFragment extends Fragment {
    private static final String TAG = "GetList";
    View v;
    List<Account> accountslist = new ArrayList<>();
    RecyclerView rv;
    private APIService mAPIService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAPIService = ApiUtils.getApiService();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_list, container, false);
        rv = (RecyclerView) v.findViewById(R.id.rvUser);
        initData();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        rv.setLayoutManager(layoutManager);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void initData() {
        mAPIService.getAllDetailAccount("Bearer "+SharedPrefs.getInstance().get(CURRENT_TOKEN_ID, String.class).toString()).enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                if (response.body() == null) return;
                accountslist = response.body();
                ListUserAdapter rvAdapter = new ListUserAdapter(getContext(), accountslist);
                rv.setAdapter(rvAdapter);
            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
                Log.e(TAG, "onFailure: message"+t.getMessage() );
            }
        });
    }
}
