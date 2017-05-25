package com.fnm.ecodata.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.fnm.ecodata.MainActivity;
import com.fnm.ecodata.R;

public class SplashActivity extends Activity {

    private static final int SPLASH_TIME_OUT = 0;
    Handler mHideHandler = new Handler();
    Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            gotoLandingActivity();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mHideHandler.postDelayed(mHideRunnable, SPLASH_TIME_OUT);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    public void gotoLandingActivity() {
        Intent i = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(i);
        overridePendingTransition(android.R.anim.fade_in, 0);
        finish();
    }
}
