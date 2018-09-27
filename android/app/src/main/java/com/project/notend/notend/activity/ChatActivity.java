package com.project.notend.notend.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.project.notend.notend.R;
import com.project.notend.notend.adapter.ChatAdapter;
import com.project.notend.notend.data.remote.APIService;
import com.project.notend.notend.data.remote.ApiUtils;
import com.project.notend.notend.data.storage_share.SharedPrefs;
import com.project.notend.notend.entities.ChatMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.project.notend.notend.data.config.config.CURRENT_ID;
import static com.project.notend.notend.data.config.config.CURRENT_TOKEN_ID;

public class ChatActivity extends AppCompatActivity {
    private RecyclerView mMessageRecycler;
    private ChatAdapter mMessageAdapter;
    private List<ChatMessage> messageList = new ArrayList<>();
    private EditText edittext_chatbox;
    private Button button_chatbox_send;
    private APIService mAPIService;
    private Socket mSocket;

//    {
//        try {
//            mSocket = IO.socket("http://chat.socket.io");
//        } catch (URISyntaxException e) {
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        //create start
        mAPIService = ApiUtils.getApiService();
        mMessageRecycler = (RecyclerView) findViewById(R.id.reyclerview_message_list);
        button_chatbox_send = (Button) findViewById(R.id.button_chatbox_send);
        edittext_chatbox = findViewById(R.id.edittext_chatbox);
        //get intent
        Intent intent = getIntent();
        final int idFriend = intent.getExtras().getInt("id_friend");
        String fistNameFriend = intent.getExtras().getString("fistNameFriend");
        String lastNameFriend = intent.getExtras().getString("lastNameFriend");
        String imageUrlFriend = intent.getExtras().getString("imageUrlFriend");
        messageList.add(new ChatMessage(1, 1, "Xin chào!", "1-1-2012", 2,imageUrlFriend,fistNameFriend,lastNameFriend));
        messageList.add(new ChatMessage(1, 1, "mình là " + fistNameFriend, "1-1-2012", 2,imageUrlFriend,fistNameFriend,lastNameFriend));
        messageList.add(new ChatMessage(2, 1, "rất vui khi được làm quen với bạn", "1-1-2012", 1,imageUrlFriend,fistNameFriend,lastNameFriend));
        messageList.add(new ChatMessage(1, 1, " : )) - end", "1-1-2012", 2,imageUrlFriend,fistNameFriend,lastNameFriend));
        //set ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(lastNameFriend + " " + fistNameFriend);
        //set RecylerView
        initdata(idFriend,lastNameFriend,fistNameFriend,imageUrlFriend);
        mMessageAdapter = new ChatAdapter(this, messageList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        mMessageRecycler.setLayoutManager(linearLayoutManager);
        mMessageRecycler.setAdapter(mMessageAdapter);
        //Start socket
//        mSocket.on("new message", onNewMessage);
//        mSocket.connect();
        //set Click button
        button_chatbox_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = edittext_chatbox.getText().toString().trim();
                if (TextUtils.isEmpty(message)) {
                    return;
                }
                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setToUserId(idFriend);
                chatMessage.setFromUserId(SharedPrefs.getInstance()
                        .get(CURRENT_ID, Integer.class));
                chatMessage.setMsg(message);
                createMessager(chatMessage);
                chatMessage.setSendingDateTime(java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()));
                displayMessage(chatMessage);
                edittext_chatbox.setText("");
            }

        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mSocket.off("new message");
//        mSocket.off("login");
//        mSocket.disconnect();
    }
    //set button back
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    //show messager in recycler view
    public void displayMessage(ChatMessage message) {
        mMessageAdapter.addItem(message);
        mMessageRecycler.scrollToPosition(mMessageAdapter.getItemCount() - 1);
    }

    //get data messagers
    private void initdata(final int id_friend, final String lastNameFriend,
                          final String fistNameFriend, final String imageUrlFriend) {
        mAPIService.getAllMessagers("Bearer " + SharedPrefs.getInstance().get(CURRENT_TOKEN_ID, String.class))
                .enqueue(new Callback<List<ChatMessage>>() {
                    @Override
                    public void onResponse(Call<List<ChatMessage>> call, Response<List<ChatMessage>> response) {
                        List<ChatMessage> list = new ArrayList<>();
                        if (response.isSuccessful()) {
                            list = response.body();
                            for (ChatMessage message : list) {
                                if (message.getFromUserId() == id_friend) {
                                    message.setFromUserImageUrl(imageUrlFriend);
                                    message.setFromUserFirstName(fistNameFriend);
                                    message.setFromUserLastName(lastNameFriend);
                                    messageList.add(message);
                                }
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<List<ChatMessage>> call, Throwable t) {
                        Log.e("error", t.getMessage().toString());
                    }
                });
    }

    //send 1 Messager to server
    private void createMessager(ChatMessage message) {
        mAPIService.createMessager(message,
                "Bearer " + SharedPrefs.getInstance().get(CURRENT_TOKEN_ID, String.class)).enqueue(
                new Callback<ChatMessage>() {
                    @Override
                    public void onResponse(Call<ChatMessage> call, Response<ChatMessage> response) {
                        Log.e("submit", "onResponse: " + response.isSuccessful());
                        Log.e("submit", "onResponse:, responebody--- " + response.body());
                    }

                    @Override
                    public void onFailure(Call<ChatMessage> call, Throwable t) {
                        Log.e("error", "Unable to submit post to API.");
                        Log.e("error", "onFailure: message" + t.getMessage());
                    }
                }
        );
    }

    //send 1 messenger to server socket
    private void attemptSend() {
       // mSocket.emit("new message", message);

    }

    //With this we listen on the new message event to receive messages from other users.
//    private Emitter.Listener onNewMessage = new Emitter.Listener() {
//        @Override
//        public void call(Object... args) {
//            try {
//                JSONObject jsonObject = new JSONObject(args[0].toString());
//                String username = jsonObject.getString("username");
//                String message = jsonObject.getString("message");
//                ChatMessage chatMessage = new ChatMessage();
//                chatMessage.setFromUserId(idFriend);
//                chatMessage.setMsg(message);
//                chatMessage.setSendingDateTime(DateFormat.getDateTimeInstance().format(new Date()).toString());
//                chatMessage.setToUserId(SharedPrefs.getInstance()
//                        .get(CURRENT_ID, Integer.class));
//                displayMessage(chatMessage);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    };

}
