package com.example.brainreef;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class QuestsActivity extends AppCompatActivity {

    private LinearLayout questListContainer;
    private ActivityResultLauncher<String> filePickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quests);

        questListContainer = findViewById(R.id.quest_list_container);

        // Initialize File Picker
        filePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) {
                        addNewQuest(uri);
                    }
                }
        );

        startAnimations();
        setupSidebar();

        findViewById(R.id.btn_start_quest_main).setOnClickListener(v -> {
            filePickerLauncher.launch("*/*");
        });
    }

    private void addNewQuest(Uri uri) {
        String fileName = uri.getLastPathSegment();
        if (fileName != null && fileName.contains(":")) {
            fileName = fileName.substring(fileName.lastIndexOf(":") + 1);
        }

        // Inflate a new quest card
        View questCard = LayoutInflater.from(this).inflate(R.layout.item_quest_card, questListContainer, false);
        
        TextView titleTv = questCard.findViewById(R.id.quest_title);
        ImageView iconIv = questCard.findViewById(R.id.quest_icon);
        
        titleTv.setText(fileName);
        // Use a generic discovery icon for new uploads
        iconIv.setImageResource(android.R.drawable.ic_menu_save);
        iconIv.setBackgroundResource(R.drawable.nav_bg);
        iconIv.setBackgroundTintList(getColorStateList(R.color.bubble_blue));

        // Add to the horizontal list
        questListContainer.addView(questCard);
        
        Toast.makeText(this, "New Discovery Added: " + fileName, Toast.LENGTH_SHORT).show();
    }

    private void startAnimations() {
        Animation floatUp = AnimationUtils.loadAnimation(this, R.anim.float_up);
        Animation swim = AnimationUtils.loadAnimation(this, R.anim.swim_across);
        Animation pulse = AnimationUtils.loadAnimation(this, R.anim.pulse);

        if (findViewById(R.id.bubble1) != null) findViewById(R.id.bubble1).startAnimation(floatUp);
        if (findViewById(R.id.bubble2) != null) findViewById(R.id.bubble2).startAnimation(floatUp);
        if (findViewById(R.id.fish1) != null) findViewById(R.id.fish1).startAnimation(swim);
        if (findViewById(R.id.btn_start_quest_main) != null) findViewById(R.id.btn_start_quest_main).startAnimation(pulse);
    }

    private void setupSidebar() {
        findViewById(R.id.nav_home).setOnClickListener(v -> startActivity(new Intent(this, DashboardActivity.class)));
        findViewById(R.id.nav_quests).setOnClickListener(v -> {}); // Already here
        findViewById(R.id.nav_library).setOnClickListener(v -> startActivity(new Intent(this, LibraryActivity.class)));
        findViewById(R.id.nav_settings).setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
    }
}