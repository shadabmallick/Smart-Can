package com.sketch.smartcan.Activity;



import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.util.Log;


import com.sketch.smartcan.MainActivity;
import com.sketch.smartcan.R;


/**
 * Created by developer on 21/1/19.
 */

public class SplashScreen extends Activity {
    public static final String MyPREFERENCES1 = "somthing";
    SharedPreferences sharedpreferences;
    private static int SPLASH_TIME_OUT = 3000;
    private static final int PERMISSION_REQUEST_CODE=1000;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}