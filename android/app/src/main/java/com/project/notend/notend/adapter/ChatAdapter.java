package com.project.notend.notend.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.notend.notend.R;
import com.project.notend.notend.data.storage_share.SharedPrefs;
import com.project.notend.notend.entities.ChatMessage;

import java.util.List;

import butterknife.internal.Utils;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.project.notend.notend.data.config.config.CURRENT_ID;
import static com.project.notend.notend.data.remote.ApiUtils.SERVER_URL_ACCOUNT;

public class ChatAdapter extends RecyclerView.Adapter {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    private final List<ChatMessage> mMessageList;
    private Context context;

    public ChatAdapter(Context context, List<ChatMessage> mMessageList) {
        this.context = context;
        this.mMessageList = mMessageList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.item_message_sent, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.item_message_received, parent, false);
            return new ReceivedMessageHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatMessage message = (ChatMessage) mMessageList.get(position);

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(message, context);
        }
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }


    @Override
    public int getItemViewType(int position) {
        ChatMessage message = (ChatMessage) mMessageList.get(position);

        if (message.getFromUserId().equals(SharedPrefs.getInstance()
                .get(CURRENT_ID, Integer.class))) {
            // If the current user is the sender of the message
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            // If some other user sent the message
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    private class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText;

        SentMessageHolder(View itemView) {
            super(itemView);

            messageText = (TextView) itemView.findViewById(R.id.text_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
        }

        void bind(ChatMessage message) {
            messageText.setText(message.getMsg());

            // Format the stored timestamp into a readable String using method.
            timeText.setText(message.getSendingDateTime());
        }
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText, nameText;
        CircleImageView profileImage;

        ReceivedMessageHolder(View itemView) {
            super(itemView);
            messageText = (TextView) itemView.findViewById(R.id.text_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
            nameText = (TextView) itemView.findViewById(R.id.text_message_name);
            profileImage = (CircleImageView) itemView.findViewById(R.id.image_message_profile);
        }

        void bind(ChatMessage message, Context mContext) {
            messageText.setText(message.getMsg());

            // Format the stored timestamp into a readable String using method.
            timeText.setText(message.getSendingDateTime().substring(0,20));

            nameText.setText(message.getFromUserLastName() + " " + message.getFromUserFirstName());
            // Insert the profile image from the URL into the ImageView.
            String url = SERVER_URL_ACCOUNT + message.getFromUserImageUrl();
            Glide.with(mContext).load(url).into(profileImage);

        }
    }

    public void addItem(ChatMessage message) {
        mMessageList.add(message);
        notifyItemInserted(mMessageList.size());
    }

}
