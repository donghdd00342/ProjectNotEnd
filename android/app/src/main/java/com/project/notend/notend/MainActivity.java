package com.project.notend.notend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.project.notend.notend.activity.Content;
import com.project.notend.notend.activity.EditProfile;
import com.project.notend.notend.activity.LoginActivity;
import com.project.notend.notend.activity.Payment;
import com.project.notend.notend.activity.Register;
import com.project.notend.notend.data.storage_share.SharedPrefs;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.project.notend.notend.data.config.config.CURRENT_TOKEN_ID;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btLogin)
    Button btLogin;
    @BindView(R.id.btRegister)
    Button btRegister;
    @BindView(R.id.btContent)
    Button btContent;
    @BindView(R.id.btPayment)
    Button btPayment;
    @BindView(R.id.btEdit)
    Button btEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        final String token = SharedPrefs.getInstance().get(CURRENT_TOKEN_ID,String.class);
//        Toast.makeText(getBaseContext(), token , Toast.LENGTH_SHORT).show();
        if (token.length() != 0){
            Intent intent = new Intent(MainActivity.this, Content.class);
            startActivity(intent);
        }

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                MainActivity.this.startActivity(intent);
            }
        });
        btContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (token.length() != 0) {
                    Intent intent = new Intent(MainActivity.this, Content.class);
                    MainActivity.this.startActivity(intent);
                }
            }
        });
        btPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Payment.class);
                MainActivity.this.startActivity(intent);
            }
        });

        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditProfile.class);
                MainActivity.this.startActivity(intent);
            }
        });

    }

}
