package com.example.brainreef;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class TestFileAttachmentActivity extends AppCompatActivity {

    private ActivityResultLauncher<String> filePickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testfileattachment);

        // Initialize File Picker
        filePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) {
                        handleFileSelection(uri);
                    }
                }
        );

        startAnimations();
        setupSidebar();

        findViewById(R.id.btn_attach_file).setOnClickListener(v -> {
            // Launch file picker for any type of file
            filePickerLauncher.launch("*/*");
        });
    }

    private void handleFileSelection(Uri uri) {
        // Here you would typically process the file
        String fileName = uri.getLastPathSegment();
        Toast.makeText(this, "Discovery Attached: " + fileName, Toast.LENGTH_LONG).show();
    }

    private void startAnimations() {
        Animation floatUp = AnimationUtils.loadAnimation(this, R.anim.float_up);
        Animation swim = AnimationUtils.loadAnimation(this, R.anim.swim_across);
        Animation pulse = AnimationUtils.loadAnimation(this, R.anim.pulse);

        if (findViewById(R.id.bubble1) != null) findViewById(R.id.bubble1).startAnimation(floatUp);
        if (findViewById(R.id.bubble2) != null) findViewById(R.id.bubble2).startAnimation(floatUp);
        if (findViewById(R.id.fish1) != null) findViewById(R.id.fish1).startAnimation(swim);
        if (findViewById(R.id.btn_attach_file) != null) findViewById(R.id.btn_attach_file).startAnimation(pulse);
    }

    private void setupSidebar() {
        findViewById(R.id.nav_home).setOnClickListener(v -> startActivity(new Intent(this, DashboardActivity.class)));
        findViewById(R.id.nav_quests).setOnClickListener(v -> startActivity(new Intent(this, QuestsActivity.class)));
        findViewById(R.id.nav_library).setOnClickListener(v -> startActivity(new Intent(this, LibraryActivity.class)));
        findViewById(R.id.nav_settings).setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
    }
}