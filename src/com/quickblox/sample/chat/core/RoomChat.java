package com.quickblox.sample.chat.core;

import java.util.Calendar;
import java.util.Date;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

import android.util.Log;
import android.widget.Toast;

import com.example.beyondto.App;
import com.example.beyondto.Connector;
import com.example.beyondto.Infoton;
import com.quickblox.module.chat.QBChatRoom;
import com.quickblox.module.chat.QBChatService;
import com.quickblox.module.chat.listeners.ChatMessageListener;
import com.quickblox.module.chat.listeners.RoomListener;
import com.quickblox.module.chat.utils.QBChatUtils;
import com.quickblox.module.users.model.QBUser;
import com.quickblox.sample.chat.model.ChatMessage;
import com.quickblox.sample.chat.ui.activities.ChatActivity;

public class RoomChat implements Chat, RoomListener, ChatMessageListener {

    public static final String EXTRA_ROOM_NAME = "name";
    public static final String EXTRA_ROOM_ACTION = "action";
    private static final String TAG = RoomChat.class.getSimpleName();
    private ChatActivity chatActivity;
    private QBChatRoom chatRoom;
    private String login, nome;
    private String[] infoUser;

    public RoomChat(ChatActivity chatActivity) {
        this.chatActivity = chatActivity;

        String chatRoomName = chatActivity.getIntent().getStringExtra(EXTRA_ROOM_NAME);
        RoomAction action = (RoomAction) chatActivity.getIntent().getSerializableExtra(EXTRA_ROOM_ACTION);
        
        Connector con= new Connector();
        infoUser = con.getUserInfo(Infoton.getInstance().getUserId());
        login = "";
        nome = infoUser[1];//fazione si trova a posizione 2
        String[] parts = nome.split(" ");
        for(int i=0; i<parts.length; i++){
        	login=login + parts[i];
        }
        
        switch (action) {
            case CREATE:
                create(chatRoomName);
                break;
            case JOIN:            	
            	join(chatRoomName);
            	break;
            	
        }
    }

    @Override
    public void sendMessage(String message) throws XMPPException {
        if (chatRoom != null) {
            chatRoom.sendMessage(message);
        } else {
            Toast.makeText(chatActivity, "Join unsuccessful", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void release() throws XMPPException {
        if (chatRoom != null) {
            QBChatService.getInstance().leaveRoom(chatRoom);
            chatRoom.removeMessageListener(this);
        }
    }

    @Override
    public void onCreatedRoom(QBChatRoom room) {
        Log.d(TAG, "room was created");
        chatRoom = room;
        chatRoom.addMessageListener(this);
    }

    @Override
    public void onJoinedRoom(QBChatRoom room) {
        Log.d(TAG, "joined to room");
        chatRoom = room;
        chatRoom.addMessageListener(this);
    }

    @Override
    public void onError(String msg) {
        Log.d(TAG, "error joining to room");
    }

    @Override
    public void processMessage(Message message) {
        Date time = QBChatUtils.parseTime(message);
        if (time == null) {
            time = Calendar.getInstance().getTime();
        }
        // Show message
                
        String sender = QBChatUtils.parseRoomOccupant(message.getFrom());
        
       /* QBUsers.getUser(Integer.parseInt(sender), new QBCallbackImpl() {
            @Override
            public void onComplete(Result result) {
                if (result.isSuccess()) {
                    QBUserResult qbUserResult = (QBUserResult) result;
                    //qbUserResult.getUser().getId();
                    nomeUtente= qbUserResult.getUser().getLogin();
                }
            } 
        });*/
        
        QBUser qbUser = ((App) (chatActivity.getApplication())).getQbUser();
        if (sender.equals(qbUser.getFullName()) || sender.equals(qbUser.getId().toString())) {
            chatActivity.showMessage(new ChatMessage(message.getBody(), time, false));
        } else {
        	
            chatActivity.showMessage(new ChatMessage(message.getBody(), time, true));
        }
    }
    
    

    @Override
    public boolean accept(Message.Type messageType) {
        switch (messageType) {
            case groupchat:
                return true;
            default:
                return false;
        }
    }

    public void create(String roomName) {
        // Creates open & persistent room
        QBChatService.getInstance().createRoom(roomName, false, true, this);
    }

    public void join(QBChatRoom room) {
        QBChatService.getInstance().joinRoom(room, this);
    }
    
    public void join(String roomName) {
        QBChatService.getInstance().joinRoom(roomName, this);
    }

    public static enum RoomAction {CREATE, JOIN}

}
