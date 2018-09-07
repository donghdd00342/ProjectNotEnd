package com.project.notend.notend.activity.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.notend.notend.R;
import com.project.notend.notend.adapter.ListUserAdapter;
import com.project.notend.notend.entities.User;

import java.util.ArrayList;
import java.util.List;

public class ListUserFragment extends Fragment {
    View v;
    List<User> userlist = new ArrayList<>();
    RecyclerView rv;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.fragment_list, container, false);
         rv = (RecyclerView) v.findViewById(R.id.rvUser);
        ListUserAdapter rvAdapter = new ListUserAdapter(getContext(),userlist);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(rvAdapter);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);;

    }
    private void initData(){
        userlist.add(new User(1,"ha",18,"female",R.drawable.im1));
        userlist.add(new User(2,"ha",18,"female",R.drawable.img2));
        userlist.add(new User(3,"ha",18,"female",R.drawable.img3));
        userlist.add(new User(4,"ha",18,"female",R.drawable.img4));
        userlist.add(new User(5,"ha",18,"female",R.drawable.img5));
        userlist.add(new User(6,"ha",18,"female",R.drawable.im1));
        userlist.add(new User(7,"ha",18,"female",R.drawable.img2));
        userlist.add(new User(8,"ha",18,"female",R.drawable.img3));
        userlist.add(new User(9,"ha",18,"female",R.drawable.img4));
        userlist.add(new User(10,"ha",18,"female",R.drawable.img5));
    }
}
