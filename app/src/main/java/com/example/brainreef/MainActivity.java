package com.example.brainreef;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startAnimations();

        findViewById(R.id.nav_home).setOnClickListener(v -> {
            startActivity(new Intent(this, DashboardActivity.class));
        });

        findViewById(R.id.nav_login).setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
        });

        findViewById(R.id.btn_start_learning).setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
        });
    }

    private void startAnimations() {
        Animation floatUp = AnimationUtils.loadAnimation(this, R.anim.float_up);
        Animation swim = AnimationUtils.loadAnimation(this, R.anim.swim_across);
        Animation pulse = AnimationUtils.loadAnimation(this, R.anim.pulse);

        findViewById(R.id.bubble1).startAnimation(floatUp);
        findViewById(R.id.bubble2).startAnimation(floatUp);
        findViewById(R.id.bubble3).startAnimation(floatUp);

        findViewById(R.id.fish1).startAnimation(swim);
        findViewById(R.id.fish2).startAnimation(swim);
        
        findViewById(R.id.btn_start_learning).startAnimation(pulse);
    }
}