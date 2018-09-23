package com.project.notend.notend.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.notend.notend.R;
import com.project.notend.notend.adapter.ListUserAdapter;
import com.project.notend.notend.adapter.ListUserChatAdapter;
import com.project.notend.notend.data.remote.APIService;
import com.project.notend.notend.data.remote.ApiUtils;
import com.project.notend.notend.data.storage_share.SharedPrefs;
import com.project.notend.notend.entities.Account;
import com.project.notend.notend.entities.Friend;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.project.notend.notend.data.config.config.CURRENT_TOKEN_ID;

public class MessengerFragment extends Fragment {
    private static final String TAG = "GetList";
    View v;
    List<Friend> friendslist = new ArrayList<>();
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
        View rootView = inflater.inflate(R.layout.fragment_messenger, container, false);
        rv = (RecyclerView) rootView.findViewById(R.id.rvListUserChat);
        initData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        return rootView;
    }

    private void initData(){
        mAPIService.getAllFriendsChat("Bearer "+SharedPrefs.getInstance().
                get(CURRENT_TOKEN_ID, String.class).toString())
                .enqueue(new Callback<List<Friend>>() {
                    @Override
                    public void onResponse(Call<List<Friend>> call, Response<List<Friend>> response) {
                        if (response.body() == null) return;
                        friendslist  = (List<Friend>) response.body();
                        ListUserChatAdapter rvAdapter = new ListUserChatAdapter(getContext(),friendslist);
                        rv.setAdapter(rvAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<Friend>> call, Throwable t) {
                        Log.e(TAG, "Unable to submit post to API.");
                        Log.e(TAG, "onFailure: message"+t.getMessage() );
                    }
                });
    }
}
