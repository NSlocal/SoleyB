package com.solely.browser;

import android.content.Context;
import android.content.SharedPreferences;

public class FlagsProvider {
    private static final String PREF = "solely_flags";
    private static FlagsProvider inst;
    private final SharedPreferences p;

    private FlagsProvider(Context c) {
        p = c.getApplicationContext().getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }
    public static synchronized FlagsProvider get(Context c) {
        if (inst == null) inst = new FlagsProvider(c);
        return inst;
    }
    public void set(String k, boolean v) { p.edit().putBoolean(k, v).apply(); }
    public boolean isEnabled(String k, boolean def) { return p.getBoolean(k, def); }
    public boolean httpsOnly() { return isEnabled("https_only_mode", true); }
    public boolean blockCleartext() { return isEnabled("cleartext_block", false); }
}
