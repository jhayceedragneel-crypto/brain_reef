package com.example.brainreef;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatActivity;

public class LibraryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        startAnimations();
        setupSidebar();
    }

    private void startAnimations() {
        Animation floatUp = AnimationUtils.loadAnimation(this, R.anim.float_up);
        Animation swim = AnimationUtils.loadAnimation(this, R.anim.swim_across);

        findViewById(R.id.bubble1).startAnimation(floatUp);
        findViewById(R.id.bubble2).startAnimation(floatUp);
        findViewById(R.id.fish1).startAnimation(swim);
    }

    private void setupSidebar() {
        findViewById(R.id.nav_home).setOnClickListener(v -> startActivity(new Intent(this, DashboardActivity.class)));
        findViewById(R.id.nav_quests).setOnClickListener(v -> startActivity(new Intent(this, QuestsActivity.class)));
        findViewById(R.id.nav_library).setOnClickListener(v -> {}); // Already here
        findViewById(R.id.nav_settings).setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
    }
}