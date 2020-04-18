package com.studiomasteguh.citygiude.common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.studiomasteguh.citygiude.R;
import com.studiomasteguh.citygiude.helperclasses.SilderAdapter;
import com.studiomasteguh.citygiude.user.UserDashboard;

public class OnBoarding extends AppCompatActivity {

    // variables
    ViewPager viewPager;
    LinearLayout linearLayout;
    SilderAdapter silderAdapter;
    TextView[] dots;
    Button btnGetStarted;
    Animation animation;
    int currentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // init id from xml layout
        viewPager = findViewById(R.id.slider1);
        linearLayout = findViewById(R.id.linear_layout);
        btnGetStarted = findViewById(R.id.btn_get_started);

        // call a adapter
        silderAdapter = new SilderAdapter(this);
        viewPager.setAdapter(silderAdapter);

        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    }

    // add a dots
    private void addDots(int position){

        dots = new TextView[4];
        linearLayout.removeAllViews();

        for (int i= 0; i<dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);

            linearLayout.addView(dots[i]);
        }

        if (dots.length > 0){
            dots[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    // melompati activity dan langgsung menuju activity user dashboard
    public void skip(View view){
        startActivity(new Intent(this, UserDashboard.class));
        finishAffinity();
    }

    // berpindah satu activity
    public void next(View view){
        viewPager.setCurrentItem(currentPos + 1);

    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPos = position;

            if (position == 0){
                btnGetStarted.setVisibility(View.INVISIBLE);
            } else if (position == 1){
                btnGetStarted.setVisibility(View.INVISIBLE);
            } else if (position == 2){
                btnGetStarted.setVisibility(View.INVISIBLE);
            } else {
                animation = AnimationUtils.loadAnimation(OnBoarding.this, R.anim.side_up);
                btnGetStarted.setAnimation(animation);
                btnGetStarted.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
