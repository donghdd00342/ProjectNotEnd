package com.project.notend.notend.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.notend.notend.R;
import com.project.notend.notend.activity.FriendDetailUser;
import com.project.notend.notend.entities.Friend;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.project.notend.notend.data.remote.ApiUtils.SERVER_URL_ACCOUNT;

public class FriendsListAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Friend> listFriend;
    private boolean paidUser;

    public FriendsListAdapter(Context context, List<Friend> listFriend, boolean paidUser) {
        this.mContext = context;
        this.listFriend = listFriend;
        this.paidUser = paidUser;
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
        final Friend friend = listFriend.get(position);
        newsHolder.tvName.setText(friend.getFriendFirstName() + " " + friend.getFriendLastName());
        newsHolder.tvDetail.setText(" ");
        String url = SERVER_URL_ACCOUNT + friend.getFriendImageUrl();
        Glide.with(mContext).load(url).into(newsHolder.imAvatar);
        Log.e("paid_FriendListAdapter",""+paidUser);
        if(paidUser){
            newsHolder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position, boolean isLongClick) {
                    Intent intent = new Intent(mContext, FriendDetailUser.class);
                    intent.putExtra("friendLogin",friend.getFriendLogin());
                    mContext.startActivity(intent);
                }
            });
        }else {
            newsHolder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position, boolean isLongClick) {
                    view.setClickable(false);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listFriend.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tvName) TextView tvName;
        @BindView(R.id.tvDes) TextView tvDetail;
        @BindView(R.id.imAvatar) CircleImageView imAvatar;
        private ItemClickListener itemClickListener;
        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener)
        {
            this.itemClickListener = itemClickListener;
        }
        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),false);
        }
    }

    public interface ItemClickListener {
        void onClick(View view, int position,boolean isLongClick);
    }
}
