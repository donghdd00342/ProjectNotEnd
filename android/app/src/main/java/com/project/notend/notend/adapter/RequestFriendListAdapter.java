package com.project.notend.notend.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.project.notend.notend.R;
import com.project.notend.notend.activity.FriendDetailUser;
import com.project.notend.notend.data.remote.APIService;
import com.project.notend.notend.data.remote.ApiUtils;
import com.project.notend.notend.data.storage_share.SharedPrefs;
import com.project.notend.notend.entities.Friend;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.project.notend.notend.data.config.config.CURRENT_TOKEN_ID;
import static com.project.notend.notend.data.remote.ApiUtils.SERVER_URL_ACCOUNT;

public class RequestFriendListAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Friend> listRequestFriend;
    private APIService mAPIService;

    public RequestFriendListAdapter(Context mContext, List<Friend> listRequestFriend) {
        this.mContext = mContext;
        this.listRequestFriend = listRequestFriend;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_request_friend, parent, false);
        NewsHolder holder = new NewsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final NewsHolder newsHolder = (NewsHolder) holder;
        final Friend friend = listRequestFriend.get(position);
        newsHolder.reqName.setText(friend.getOwnerLastName() + " " + friend.getOwnerFirstName());
        String url = SERVER_URL_ACCOUNT + friend.getOwnerImageUrl();
        Glide.with(mContext).load(url).into(newsHolder.reqAvatar);
        newsHolder.btn_AcceptReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friend.setStatus(12);
                Log.e("conffriend",""+friend.getStatus());
                acceptRequest(friend);
                listRequestFriend.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, listRequestFriend.size());
            }
        });
        newsHolder.btn_DeleteReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("delfriend",""+friend.getId());

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("You want to delete this request?");
                builder.setCancelable(false);
                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        deleteRequest(friend.getId());
                        notifyItemRemoved(position);
                        listRequestFriend.remove(position);
                        notifyItemRangeChanged(position, listRequestFriend.size());
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listRequestFriend.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.reqName)
        TextView reqName;
        @BindView(R.id.reqAvatar)
        CircleImageView reqAvatar;
//        @BindView(R.id.reqHolder)
//        RelativeLayout reqHolder;
        @BindView(R.id.btn_AcceptReq)
        Button btn_AcceptReq;
        @BindView(R.id.btn_DeleteReq)
        Button btn_DeleteReq;
        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private void acceptRequest(Friend paramObject) {
        mAPIService = ApiUtils.getApiService();
        mAPIService.acceptRequestFriend(paramObject, "Bearer " + SharedPrefs.getInstance().get(CURRENT_TOKEN_ID, String.class).toString()).enqueue(new Callback<Friend>() {
            @Override
            public void onResponse(Call<Friend> call, Response<Friend> response) {
                Log.e("accept",""+response);
            }

            @Override
            public void onFailure(Call<Friend> call, Throwable t) {
            }
        });
    }

    private void deleteRequest(Integer friendId) {
        mAPIService = ApiUtils.getApiService();
        mAPIService.deleteRequestFriend(friendId, "Bearer " + SharedPrefs.getInstance().get(CURRENT_TOKEN_ID, String.class).toString()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.e("delete",""+response);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }

}
