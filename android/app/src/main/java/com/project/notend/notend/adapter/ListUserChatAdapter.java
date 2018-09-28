package com.project.notend.notend.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.notend.notend.R;
import com.project.notend.notend.activity.ChatActivity;
import com.project.notend.notend.entities.Friend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.project.notend.notend.data.remote.ApiUtils.SERVER_URL_ACCOUNT;

public class ListUserChatAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Friend> listData;


    public ListUserChatAdapter(Context mContext, List<Friend> listDataIn) {
        this.mContext = mContext;
        this.listData = listDataIn;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_user_chat, parent, false);
        NewsHolder holder = new NewsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NewsHolder newsHolder =  (NewsHolder) holder;
        final Friend friend = listData.get(position);
        newsHolder.tvName.setText(friend.getFriendFirstName() + " " + friend.getFriendLastName());
        newsHolder.tvMessage.setText(friend.getFriendLogin());
        String url = SERVER_URL_ACCOUNT + friend.getFriendImageUrl();
        Glide.with(mContext).load(url).into(newsHolder.civAvatar);
        newsHolder.setItemClickListener(new ListUserAdapter.ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(mContext, ChatActivity.class);
                intent.putExtra("id_friend",friend.getFriendId());
                intent.putExtra("fistNameFriend",friend.getFriendFirstName());
                intent.putExtra("lastNameFriend",friend.getFriendLastName());
                intent.putExtra("imageUrlFriend",friend.getFriendImageUrl());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvMessage)
        TextView tvMessage;
        @BindView(R.id.civAvatar)
        CircleImageView civAvatar;
        private ListUserAdapter.ItemClickListener itemClickListener;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ListUserAdapter.ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), false);
        }
    }

    public interface ItemClickListener {
        void onClick(View view, int position, boolean isLongClick);
    }


}
