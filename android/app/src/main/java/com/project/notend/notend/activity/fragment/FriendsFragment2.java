package com.project.notend.notend.activity.fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.project.notend.notend.R;
import com.project.notend.notend.adapter.RequestFriendListAdapter;
import com.project.notend.notend.data.remote.APIService;
import com.project.notend.notend.data.remote.ApiUtils;
import com.project.notend.notend.data.storage_share.SharedPrefs;
import com.project.notend.notend.entities.Friend;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static com.project.notend.notend.data.config.config.CURRENT_TOKEN_ID;
public class FriendsFragment2 extends Fragment {
    private RecyclerView rv;
    private static String token = SharedPrefs.getInstance().get(CURRENT_TOKEN_ID,String.class);
    List<Friend> requestFriendList = new ArrayList<>();
    private APIService mAPIService;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAPIService = ApiUtils.getApiServiceAccount();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friends_2, container, false);
        rv = (RecyclerView) view.findViewById(R.id.rvListRequestFriend);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
        setHasOptionsMenu(true);
        getRequestFriend();
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    private void getRequestFriend() {
        mAPIService.getRequestFriend("Bearer "+token).enqueue(new Callback<List<Friend>>() {
            @Override
            public void onResponse(Call<List<Friend>> call, Response<List<Friend>> response) {
                if (response.isSuccessful()){
                    requestFriendList = response.body();
                    RequestFriendListAdapter rvAdapter = new RequestFriendListAdapter(getContext(),requestFriendList);
                    rv.setAdapter(rvAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<Friend>> call, Throwable t) {
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            getRequestFriend();
        }
    }
}