package com.example.brainreef;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        startAnimations();
        setupSidebar();

        findViewById(R.id.btn_start_quest).setOnClickListener(v -> {
            startActivity(new Intent(this, QuestsActivity.class));
        });
    }

    private void startAnimations() {
        Animation floatUp = AnimationUtils.loadAnimation(this, R.anim.float_up);
        Animation swim = AnimationUtils.loadAnimation(this, R.anim.swim_across);

        findViewById(R.id.bubble1).startAnimation(floatUp);
        findViewById(R.id.bubble2).startAnimation(floatUp);
        findViewById(R.id.fish1).startAnimation(swim);
    }

    private void setupSidebar() {
        findViewById(R.id.nav_home).setOnClickListener(v -> {}); // Already home
        findViewById(R.id.nav_quests).setOnClickListener(v -> startActivity(new Intent(this, QuestsActivity.class)));
        findViewById(R.id.nav_library).setOnClickListener(v -> startActivity(new Intent(this, LibraryActivity.class)));
        findViewById(R.id.nav_settings).setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
    }
}