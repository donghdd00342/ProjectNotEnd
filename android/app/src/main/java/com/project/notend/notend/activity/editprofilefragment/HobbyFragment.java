package com.project.notend.notend.activity.editprofilefragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.project.notend.notend.R;
import com.project.notend.notend.activity.ChangePasswordActivity;

public class HobbyFragment extends Fragment{
    Context context;

//    @BindView(R.id.btn_changepassview)
//    Button _changePassButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_yourself, container, false);
        context = rootView.getContext();

        return rootView;
    }
}
