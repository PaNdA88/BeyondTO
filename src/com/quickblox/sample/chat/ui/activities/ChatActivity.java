package com.quickblox.sample.chat.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.beyondto.App;
import com.example.beyondto.Connector;
import com.example.beyondto.Infoton;
//import com.quickblox.sample.chat.R;
import com.example.beyondto.R;
import com.quickblox.sample.chat.core.Chat;
import com.quickblox.sample.chat.core.RoomChat;
import com.quickblox.sample.chat.model.ChatMessage;
import com.quickblox.sample.chat.ui.adapters.ChatAdapter;

import org.jivesoftware.smack.XMPPException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChatActivity extends Activity {

    public static final String EXTRA_MODE = "mode";
    private static final String TAG = ChatActivity.class.getSimpleName();
    private EditText messageEditText;
    private Mode mode = Mode.GROUP;
    private Chat chat;
    private ChatAdapter adapter;
    private ListView messagesContainer;
    Connector con = new Connector();
    String[] infoUser = con.getUserInfo(Infoton.getInstance().getUserId());
    
    public static void start(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initViews();
    }

    @Override
    public void onBackPressed() {
        try {
            chat.release();
        } catch (XMPPException e) {
            Log.e(TAG, "failed to release chat", e);
        }
        super.onBackPressed();
    }

    private void initViews() {
        messagesContainer = (ListView) findViewById(R.id.messagesContainer);
        messageEditText = (EditText) findViewById(R.id.messageEdit);
        Button sendButton = (Button) findViewById(R.id.chatSendButton);
        TextView meLabel = (TextView) findViewById(R.id.meLabel);
        TextView companionLabel = (TextView) findViewById(R.id.companionLabel);
        RelativeLayout container = (RelativeLayout) findViewById(R.id.container);

        adapter = new ChatAdapter(this, new ArrayList<ChatMessage>());
        messagesContainer.setAdapter(adapter);

        
  
                chat = new RoomChat(this);
                container.removeView(meLabel);
                container.removeView(companionLabel);
                
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	String nome = infoUser[1];
                String lastMsg = nome + ": " + messageEditText.getText().toString();
                if (TextUtils.isEmpty(lastMsg)) {
                    return;
                }

                messageEditText.setText("");
                try {
                    chat.sendMessage(lastMsg);
                } catch (XMPPException e) {
                    Log.e(TAG, "failed to send a message", e);
                }

                if (mode == Mode.SINGLE) {
                    showMessage(new ChatMessage(lastMsg, Calendar.getInstance().getTime(), false));
                }
            }
        });
    }

    public void showMessage(ChatMessage message) {
        adapter.add(message);
        adapter.notifyDataSetChanged();
        scrollDown();
    }

    public void showMessage(List<ChatMessage> messages) {
        adapter.add(messages);
        adapter.notifyDataSetChanged();
        scrollDown();
    }


    private void restoreMessagesFromHistory(int userId) {
        List<ChatMessage> messages = ((App)getApplication()).getMessages(userId);
        if (messages != null) {
            showMessage(messages);
        }
    }

    private void scrollDown() {
        messagesContainer.setSelection(messagesContainer.getCount() - 1);
    }

    public static enum Mode {SINGLE, GROUP}
}
