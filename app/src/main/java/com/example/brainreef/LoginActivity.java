package com.example.brainreef;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        startAnimations();

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
        
        findViewById(R.id.tv_go_to_signup).setOnClickListener(v -> {
            startActivity(new Intent(this, SignUpActivity.class));
            finish();
        });

        findViewById(R.id.btn_login_submit).setOnClickListener(v -> {
            startActivity(new Intent(this, DashboardActivity.class));
            finish();
        });
    }

    private void startAnimations() {
        Animation floatUp = AnimationUtils.loadAnimation(this, R.anim.float_up);
        Animation swim = AnimationUtils.loadAnimation(this, R.anim.swim_across);
        Animation pulse = AnimationUtils.loadAnimation(this, R.anim.pulse);

        findViewById(R.id.bubble1).startAnimation(floatUp);
        findViewById(R.id.bubble2).startAnimation(floatUp);
        findViewById(R.id.fish1).startAnimation(swim);
        findViewById(R.id.btn_login_submit).startAnimation(pulse);
    }
}