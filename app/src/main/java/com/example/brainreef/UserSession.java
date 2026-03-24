package com.example.brainreef;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserSession {
    private static final String PREF_NAME = "UserAccountPrefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_HISTORY = "activity_history";
    
    private SharedPreferences prefs;

    public UserSession(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setUsername(String username) {
        prefs.edit().putString(KEY_USERNAME, username).apply();
    }

    public String getUsername() {
        return prefs.getString(KEY_USERNAME, "Explorer");
    }

    public void logActivity(String activity) {
        Set<String> history = new HashSet<>(prefs.getStringSet(KEY_HISTORY, new HashSet<>()));
        history.add(System.currentTimeMillis() + ": " + activity);
        prefs.edit().putStringSet(KEY_HISTORY, history).apply();
    }

    public List<String> getActivityHistory() {
        return new ArrayList<>(prefs.getStringSet(KEY_HISTORY, new HashSet<>()));
    }
}