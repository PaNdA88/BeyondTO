package com.quickblox.sample.chat.ui.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.quickblox.core.QBCallback;
import com.quickblox.core.result.Result;
import com.quickblox.module.chat.QBChatService;
import com.quickblox.module.chat.listeners.SessionCallback;
import com.quickblox.module.chat.smack.SmackAndroid;
import com.quickblox.module.users.QBUsers;
import com.quickblox.module.users.model.QBUser;
import com.example.beyondto.App;
import com.example.beyondto.Connector;
import com.example.beyondto.Infoton;
//import com.quickblox.sample.chat.R;
import com.example.beyondto.R;

public class ChatLoginActivity extends Activity implements QBCallback {

    private static final String TAG = ChatLoginActivity.class.getSimpleName();
    private ProgressDialog progressDialog;
    private String login, nome;
    private String password= "beyondto"; 
    private String[] infoUser;
    private QBUser user;
    private SmackAndroid smackAndroid;
    public boolean loggato = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        login = "";
        Connector con= new Connector();
        infoUser = con.getUserInfo(Infoton.getInstance().getUserId());
        nome = infoUser[1];//fazione si trova a posizione 2
        String[] parts = nome.split(" ");
        for(int i=0; i<parts.length; i++){
        	login=login + parts[i];
        }
        
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");

        smackAndroid = SmackAndroid.init(this);
             
        user = new QBUser(login, password);

        progressDialog.show();
        QBUsers.signIn(user, this);
    }

    @Override
    protected void onDestroy() {
        smackAndroid.onDestroy();
        super.onDestroy();
    }

   

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    @Override
    public void onComplete(Result result) {
        if (result.isSuccess()) {
            ((App)getApplication()).setQbUser(user);
            QBChatService.getInstance().loginWithUser(user, new SessionCallback() {
                @Override
                public void onLoginSuccess() {
                    if (progressDialog != null) {
                        progressDialog.dismiss();
                    }
                    Log.i(TAG, "success when login");

                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    
                    //Intent passaASecondActivity= new Intent(LoginActivity.this, SecondActivity.class);
    				
                    //startActivity(passaASecondActivity);
                   
                    finish();
                    
                }

                @Override
                public void onLoginError(String error) {
                    Log.i(TAG, "error when login");
                }

            });
        } else {
        	
        	//se lo faccio ripassare dal main si ferma. Lanciare direttamente Registrazione da qui?
        	Intent passaARegistrazione= new Intent(ChatLoginActivity.this, RegistrationActivity.class);			
            startActivity(passaARegistrazione);
            
            //Intent intent = new Intent();
           // setResult(RESULT_CANCELED, intent);
            
            //AlertDialog.Builder dialog = new AlertDialog.Builder(this);
           // dialog.setMessage("Error(s) occurred. Look into DDMS log for details, " +
               //     "please. Errors: " + result.getErrors()).create().show();
        }
    }

    @Override
    public void onComplete(Result result, Object context) {
    }
}
