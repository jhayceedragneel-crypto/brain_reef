package com.example.brainreef;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private UserSession session;
    private TextView tvUsername, tvHistory;
    private EditText etNewUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        session = new UserSession(this);
        session.logActivity("Visited Profile");

        tvUsername = findViewById(R.id.tv_current_username);
        tvHistory = findViewById(R.id.tv_activity_history);
        etNewUsername = findViewById(R.id.et_new_username);
        Button btnUpdate = findViewById(R.id.btn_update_username);

        updateUI();
        setupSidebar();
        startAnimations();

        btnUpdate.setOnClickListener(v -> {
            String newName = etNewUsername.getText().toString().trim();
            if (!newName.isEmpty()) {
                session.setUsername(newName);
                session.logActivity("Changed username to: " + newName);
                updateUI();
                etNewUsername.setText("");
                Toast.makeText(this, "Username updated!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI() {
        tvUsername.setText("Current Name: " + session.getUsername());
        List<String> history = session.getActivityHistory();
        StringBuilder sb = new StringBuilder();
        for (int i = history.size() - 1; i >= 0; i--) {
            sb.append("• ").append(history.get(i)).append("\n\n");
        }
        tvHistory.setText(sb.toString());
    }

    private void setupSidebar() {
        findViewById(R.id.nav_home).setOnClickListener(v -> startActivity(new Intent(this, DashboardActivity.class)));
        findViewById(R.id.nav_quests).setOnClickListener(v -> startActivity(new Intent(this, QuestsActivity.class)));
        findViewById(R.id.nav_library).setOnClickListener(v -> startActivity(new Intent(this, LibraryActivity.class)));
        findViewById(R.id.nav_settings).setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
    }

    private void startAnimations() {
        Animation floatUp = AnimationUtils.loadAnimation(this, R.anim.float_up);
        Animation swim = AnimationUtils.loadAnimation(this, R.anim.swim_across);
        if (findViewById(R.id.bubble1) != null) findViewById(R.id.bubble1).startAnimation(floatUp);
        if (findViewById(R.id.fish1) != null) findViewById(R.id.fish1).startAnimation(swim);
    }
}