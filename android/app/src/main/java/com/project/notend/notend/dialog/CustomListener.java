package com.project.notend.notend.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.project.notend.notend.MainActivity;

public class CustomListener implements View.OnClickListener {
    private final Dialog dialog;
    private Activity activity;;

    public CustomListener(Dialog dialog, Activity activity) {
        this.dialog = dialog;
        this.activity = activity;
    }
    @Override
    public void onClick(View view) {
        dialog.cancel();
//        Intent intent = new Intent(activity, MainActivity.class);
//        activity.startActivity(intent);
        activity.setResult(-1, null);
        ((Activity) activity).finish();
    }
}
