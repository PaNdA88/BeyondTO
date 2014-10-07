package com.example.beyondto;

import com.example.beyondto.R;
import com.quickblox.core.QBCallback;
import com.quickblox.core.QBSettings;
import com.quickblox.core.result.Result;
import com.quickblox.module.auth.QBAuth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity implements QBCallback {

    private static int SPLASH_TIME_OUT = 3000;
    private static final String APP_ID = "99";
    private static final String AUTH_KEY = "63ebrp5VZt7qTOv";
    private static final String AUTH_SECRET = "YavMAxm5T59-BRw";
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        QBSettings.getInstance().fastConfigInit(APP_ID, AUTH_KEY, AUTH_SECRET);
        QBAuth.createSession(this);
 
        new Handler().postDelayed(new Runnable() {
 
            /*
             * Showing splash screen with a timer. 
             */
 
            @Override
            public void run() {
                // This method will be executed once the timer is over
                Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
    
    @Override
    public void onComplete(Result result) {
        
    }

    @Override
    public void onComplete(Result result, Object context) {
    }
 
}