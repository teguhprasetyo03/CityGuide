package com.studiomasteguh.citygiude.common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.studiomasteguh.citygiude.R;
import com.studiomasteguh.citygiude.user.UserDashboard;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIMER = 5000;

    // Variabel
    ImageView bgImg;
    TextView poweredBy;

    // Animation
    Animation sideAnim, buttomAnim;

    SharedPreferences onBoardingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // init on id
        bgImg = findViewById(R.id.img_bg_image);
        poweredBy = findViewById(R.id.tv_powered);

        // Animations
        sideAnim = AnimationUtils.loadAnimation(this, R.anim.side_anim);
        buttomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);

        bgImg.setAnimation(sideAnim);
        poweredBy.setAnimation(buttomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                onBoardingScreen = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);
                boolean isFirstTime = onBoardingScreen.getBoolean("firstTime", true);

                if (isFirstTime) {

                    SharedPreferences.Editor editor = onBoardingScreen.edit();
                    editor.putBoolean("firstTime", false);
                    editor.commit();

                    Intent i = new Intent(getApplicationContext(), OnBoarding.class);
                    startActivity(i);
                    finish();
                } else {
                    Intent i = new Intent(getApplicationContext(), UserDashboard.class);
                    startActivity(i);
                    finish();
                }
            }
        }, SPLASH_TIMER);
    }
}
