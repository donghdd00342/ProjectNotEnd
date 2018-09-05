package com.project.notend.notend.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.project.notend.notend.R;
import com.project.notend.notend.adapter.UserAdapter;
import com.project.notend.notend.entities.User;

import java.util.ArrayList;
import java.util.List;


public class list extends AppCompatActivity {
    List<User> userlist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initData();
        UserAdapter adapter = new UserAdapter(this,userlist);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        RecyclerView rv = findViewById(R.id.rvUser);
        rv.setLayoutManager(layoutManager);
       rv.setAdapter(adapter);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == 1) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
