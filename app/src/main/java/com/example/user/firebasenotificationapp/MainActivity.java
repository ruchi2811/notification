package com.example.user.firebasenotificationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
   TextView user,profile,notification;

   private ViewPager viewPager;
   private PagerViewAdapter pagerViewAdapter;

   private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser==null){
            sendToLogin();
        }

    }
    private void sendToLogin(){
        Intent loginIntent = new Intent(MainActivity.this , LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        user = (TextView)findViewById(R.id.Users);
        profile = (TextView)findViewById(R.id.profile);
        notification = (TextView)findViewById(R.id.Notifications);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
            }
        });


        viewPager = (ViewPager)findViewById(R.id.mainPager);
        viewPager.setOffscreenPageLimit(2);
        pagerViewAdapter = new PagerViewAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerViewAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
             ChangeTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

    private void ChangeTab(int position) {

        if(position ==0){
//            profile.setTextColor(getColor(R.color.text_color_dart));
            profile.setTextSize(22);

//            user.setTextColor(getColor(R.color.text_color_light));
            user.setTextSize(16);

//            notification.setTextColor(getColor(R.color.text_color_light));
            notification.setTextSize(16);


        }

        if(position ==1){
//            user.setTextColor(getColor(R.color.text_color_dart));
            user.setTextSize(22);

//            profile.setTextColor(getColor(R.color.text_color_light));
            profile.setTextSize(16);

//            notification.setTextColor(getColor(R.color.text_color_light));
            notification.setTextSize(16);

        }

        if(position ==2){
//            notification.setTextColor(getColor(R.color.text_color_dart));
            notification.setTextSize(22);

//            user.setTextColor(getColor(R.color.text_color_light));
            user.setTextSize(16);

//            profile.setTextColor(getColor(R.color.text_color_light));
            profile.setTextSize(16);

        }
    }
}
