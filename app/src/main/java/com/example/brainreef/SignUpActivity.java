package com.example.brainreef;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    private EditText useruse, userpass, userconfirm;
    private Button btn_signup_submit;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();
        
        useruse = findViewById(R.id.useruse);
        userpass = findViewById(R.id.userpass);
        userconfirm = findViewById(R.id.userconfirm);
        btn_signup_submit = findViewById(R.id.btn_signup_submit);

        startAnimations();

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());

        findViewById(R.id.tv_go_to_login).setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        btn_signup_submit.setOnClickListener(v -> {
            String email = useruse.getText().toString().trim();
            String password = userpass.getText().toString().trim();
            String confirm = userconfirm.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirm)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(this + "Account Created!" + DashboardActivity.class));
                            finish();
                        } else {
                            Toast.makeText(this, "Authentication failed: " + task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
        });
    }

    private void startAnimations() {
        Animation floatUp = AnimationUtils.loadAnimation(this, R.anim.float_up);
        Animation swim = AnimationUtils.loadAnimation(this, R.anim.swim_across);
        Animation pulse = AnimationUtils.loadAnimation(this, R.anim.pulse);

        findViewById(R.id.bubble1).startAnimation(floatUp);
        findViewById(R.id.bubble2).startAnimation(floatUp);
        findViewById(R.id.fish1).startAnimation(swim);
        btn_signup_submit.startAnimation(pulse);
    }
}