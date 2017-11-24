package com.example.taseef_pc.splash_screen_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import static android.R.style.Animation;

public class splash_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activity);

        final ImageView v= (ImageView) findViewById(R.id.imageView);
        Animation a= AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate);
        final Animation a2= AnimationUtils.loadAnimation(getBaseContext(),R.anim.fade_out);
        v.startAnimation(a);
         a.setAnimationListener(new Animation.AnimationListener() {
             @Override
             public void onAnimationStart(Animation animation) {

             }

             @Override
             public void onAnimationEnd(Animation animation) {
                 v.startAnimation(a2);
                 finish();
                 Intent it=new Intent(getApplicationContext(),MainActivity.class);
                 startActivity(it);
             }

             @Override
             public void onAnimationRepeat(Animation animation) {

             }
         });
    }
}
