package com.example.brainreef;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        try {
            startAnimations();
            setupSidebar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startAnimations() {
        Animation floatUp = AnimationUtils.loadAnimation(this, R.anim.float_up);
        Animation swim = AnimationUtils.loadAnimation(this, R.anim.swim_across);

        if (findViewById(R.id.bubble1) != null) {
            findViewById(R.id.bubble1).startAnimation(floatUp);
        }
        if (findViewById(R.id.bubble2) != null) {
            findViewById(R.id.bubble2).startAnimation(floatUp);
        }
        if (findViewById(R.id.fish1) != null) {
            findViewById(R.id.fish1).startAnimation(swim);
        }
    }

    private void setupSidebar() {
        if (findViewById(R.id.nav_home) != null) {
            findViewById(R.id.nav_home).setOnClickListener(v -> startActivity(new Intent(this, DashboardActivity.class)));
        }
        if (findViewById(R.id.nav_quests) != null) {
            findViewById(R.id.nav_quests).setOnClickListener(v -> startActivity(new Intent(this, QuestsActivity.class)));
        }
        if (findViewById(R.id.nav_library) != null) {
            findViewById(R.id.nav_library).setOnClickListener(v -> startActivity(new Intent(this, LibraryActivity.class)));
        }
        // Settings button is already handled by being the current activity
    }
}