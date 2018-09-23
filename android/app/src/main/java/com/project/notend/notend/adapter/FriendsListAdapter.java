package com.project.notend.notend.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.project.notend.notend.entities.Account;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.project.notend.notend.data.remote.ApiUtils.SERVER_URL_ACCOUNT;

public class FriendsListAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Account> listAcc;


    public FriendsListAdapter(Context context, List<Account> listAcc) {
        this.mContext = context;
        this.listAcc = listAcc;
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
        final Account account = listAcc.get(position);
        newsHolder.tvName.setText(account.getFirstName() + " " + account.getLastName());
        newsHolder.tvDetail.setText(account.getEmail());
        String url = SERVER_URL_ACCOUNT + account.getImageUrl();
        Glide.with(mContext).load(url).into(newsHolder.imAvatar);
        newsHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(mContext, FriendDetailUser.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("data", account);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listAcc.size();
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
