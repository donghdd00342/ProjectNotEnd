package com.project.notend.notend.activity.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

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
    private View v;
    private List<Account> accountslist = new ArrayList<>();
    private RecyclerView rv;
    private ProgressBar progressBar;
    private GridLayoutManager layoutManager;
    private ListUserAdapter rvAdapter;
    private APIService mAPIService;
    private int page_number = 1;
    private int item_count = 4;
    //pagination
    private boolean isLoading = true;
    private int pastVisibleItems, visibleItemCount, totallItemCount, previous_total = 0;
    private int view_threshold = 4;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAPIService = ApiUtils.getApiService();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_list, container, false);
        progressBar = v.findViewById(R.id.progressBar);
        rv = (RecyclerView) v.findViewById(R.id.rvUser);
        rv.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(), 2);
        rv.setLayoutManager(layoutManager);
        progressBar.setVisibility(View.VISIBLE);
        mAPIService.getAllDetailAccount("Bearer " + SharedPrefs.getInstance().get(CURRENT_TOKEN_ID, String.class).toString(),
                page_number, item_count).enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                if (response.body() == null) return;
                accountslist = response.body();
                rvAdapter = new ListUserAdapter(getContext(), accountslist);
                rv.setAdapter(rvAdapter);
                //Toast.makeText(getContext(), "page " + page_number + "is load", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
                Log.e(TAG, "onFailure: message" + t.getMessage());
            }
        });

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                   @Override
                                   public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                                       super.onScrolled(recyclerView, dx, dy);
                                       visibleItemCount = layoutManager.getChildCount();
                                       totallItemCount = layoutManager.getItemCount();
                                       pastVisibleItems = layoutManager.findFirstCompletelyVisibleItemPosition();
                                       if (dy > 0) {
                                           if (isLoading) {
                                               if (totallItemCount > previous_total) {
                                                   isLoading = false;
                                                   previous_total = totallItemCount;
                                               }
                                           }
                                           if (!isLoading && (totallItemCount - visibleItemCount) <= (pastVisibleItems + view_threshold)) {
                                               page_number++;
                                               initData();
                                               isLoading = true;
                                           }
                                       }
                                   }
                               }
        );

        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View v = view;
    }

    private void initData() {
//        progressBar.setVisibility(View.VISIBLE);
        mAPIService.getAllDetailAccount("Bearer " + SharedPrefs.getInstance().get(CURRENT_TOKEN_ID, String.class).toString(),
                page_number, item_count).enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                if (response.body() == null || response.body().size() == 0) {
                    page_number--;
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "no more user availabe...", Toast.LENGTH_SHORT).show();
                    return;
                } else if (response.body().size() > 0) {
                    List<Account> list = response.body();
                    rvAdapter.addAccount(list);
                }
//                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
                Log.e(TAG, "onFailure: message" + t.getMessage());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }

}