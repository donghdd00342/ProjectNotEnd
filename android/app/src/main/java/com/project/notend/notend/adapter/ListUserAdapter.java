package com.project.notend.notend.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.project.notend.notend.R;
import com.project.notend.notend.activity.DetailUser;
import com.project.notend.notend.activity.FriendDetailUser;
import com.project.notend.notend.entities.Account;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.project.notend.notend.data.remote.ApiUtils.SERVER_URL_ACCOUNT;


public class ListUserAdapter extends RecyclerView.Adapter  {

    private Context mContext;
    private List<Account> listData;
    private boolean paidUser;

    public ListUserAdapter(Context context, List<Account> listData, boolean paidUser) {
        this.mContext = context;
        this.listData = listData;
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
        final Account account = listData.get(position);
        newsHolder.tvName.setText(account.getLastName() + " " + account.getFirstName());
        newsHolder.tvDetail.setText(account.getEmail().toString());
        String url = SERVER_URL_ACCOUNT + account.getImageUrl();
        Glide.with(mContext).load(url).into(newsHolder.imAvatar);
        if(paidUser){
            newsHolder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position, boolean isLongClick) {
                    Intent intent = new Intent(mContext, DetailUser.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("data", account);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            });
        }else {
            newsHolder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position, boolean isLongClick) {
                    showAlert().show();
                    //view.setClickable(false);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    private Dialog showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("You need to become Paid User");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return builder.create();
    }

    public class NewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tvName) TextView tvName;
        @BindView(R.id.tvDes)TextView tvDetail;
        @BindView(R.id.imAvatar)CircleImageView imAvatar;
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

    public void addAccount(List<Account> list){

        for (Account account : list){
            listData.add(account);
        }
        notifyDataSetChanged();

    }

}