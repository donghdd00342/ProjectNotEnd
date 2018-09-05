package com.project.notend.notend.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.notend.notend.R;
import com.project.notend.notend.entities.User;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class UserAdapter extends RecyclerView.Adapter {

    private Activity activity;
    private List<User> listNews;

    public UserAdapter(Activity activity, List<User> listNews) {
        this.activity = activity;
        this.listNews = listNews;
    }

    public void setData(List<User> listNews) {
        this.listNews = listNews;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.new_items,parent,false);
        NewsHolder holder = new NewsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NewsHolder newsHolder = (NewsHolder) holder;
        User user = listNews.get(position);
        newsHolder.tvName.setText(user.getName());
        newsHolder.tvDetail.setText(user.getAge()+","+user.getGender());
        newsHolder.imAvatar.setImageResource(user.getAvatar());
    }

    @Override
    public int getItemCount() {
        return listNews.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvDetail;
        CircleImageView imAvatar;
        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDetail = itemView.findViewById(R.id.tvDes);
            imAvatar = (CircleImageView) itemView.findViewById(R.id.imAvatar);
        }
    }
}
