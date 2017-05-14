package com.example.renanribeirobrando.theguesser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by renanribeirobrando on 13/05/17.
 */

public class SplashScreen extends Activity {
    // Time that the splashscreen will be visible
    private final int SPLASH_DISPLAY_LENGTH = 3500;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.activity_splash);
        // Executes the animation
        loadScreen();
    }

    private void loadScreen() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.animation_splash);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.splash); if (iv != null) {
            iv.clearAnimation();
            iv.startAnimation(anim);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION); startActivity(intent);
                SplashScreen.this.finish(); }
        }, SPLASH_DISPLAY_LENGTH); }
}
