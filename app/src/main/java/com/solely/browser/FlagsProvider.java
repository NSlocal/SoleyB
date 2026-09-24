package com.solely.browser;

import android.content.Context;
import android.content.SharedPreferences;

public class FlagsProvider {
    private static final String PREF = "solely_flags";
    private static FlagsProvider instance;
    private final SharedPreferences prefs;

    private FlagsProvider(Context context) {
        prefs = context.getApplicationContext().getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }

    public static synchronized FlagsProvider get(Context context) {
        if (instance == null) {
            instance = new FlagsProvider(context.getApplicationContext());
        }
        return instance;
    }

    public void set(String key, boolean value) {
        prefs.edit().putBoolean(key, value).apply();
    }

    public boolean isEnabled(String key, boolean defaultValue) {
        return prefs.getBoolean(key, defaultValue);
    }

    public boolean httpsOnly() {
        return isEnabled("https_only_mode", true);
    }

    public boolean blockCleartext() {
        return isEnabled("cleartext_block", false);
    }

    public boolean quicEnabled() {
        return isEnabled("quic_enabled", true);
    }
}
