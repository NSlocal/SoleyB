package com.solely.browser;

import android.content.Context;
import android.content.SharedPreferences;

public class FlagsProvider {
    private static final String PREF = "solely_flags";
    private static FlagsProvider instance;
    private final SharedPreferences prefs;

    // ✅ Konstruktor PRIVATE — hanya dipanggil dari sini
    private FlagsProvider(Context context) {
        prefs = context.getApplicationContext().getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }

    // ✅ Metode SATU-SATUNYA untuk dapat objek
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

    // Pintasan
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
