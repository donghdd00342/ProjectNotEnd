package com.project.notend.notend.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.notend.notend.R;
import com.project.notend.notend.entities.Account;
import com.project.notend.notend.entities.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.project.notend.notend.data.remote.ApiUtils.SERVER_URL_ACCOUNT;


public class ListUserAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<Account> listData;

    public ListUserAdapter(Context context, List<Account> listData) {
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
        Account account = listData.get(position);
        newsHolder.tvName.setText(account.getLastName() + " " + account.getFirstName());
        newsHolder.tvDetail.setText(account.getEmail().toString());
        String url = SERVER_URL_ACCOUNT + account.getImageUrl();
        Glide.with(mContext).load(url).into(newsHolder.imAvatar);
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
