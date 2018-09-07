package com.project.notend.notend.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.notend.notend.R;
import com.project.notend.notend.entities.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


public class ListUserAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<User> listData;

    public ListUserAdapter(Context context, List<User> listData) {
        this.mContext = context;
        this.listData = listData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.new_items,parent,false);
        NewsHolder holder = new NewsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NewsHolder newsHolder = (NewsHolder) holder;
        User user = listData.get(position);
        newsHolder.tvName.setText(user.getName());
        newsHolder.tvDetail.setText(user.getAge()+","+user.getGender());
        newsHolder.imAvatar.setImageResource(user.getAvatar());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvName) TextView tvName;
        @BindView(R.id.tvDes)TextView tvDetail;
        @BindView(R.id.imAvatar)CircleImageView imAvatar;
        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
