package com.solely.browser;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    private static final String P = "solely_prefs";
    private static Prefs inst;
    private final SharedPreferences p;

    private Prefs(Context c) {
        p = c.getApplicationContext().getSharedPreferences(P, Context.MODE_PRIVATE);
    }
    public static synchronized Prefs get(Context c) {
        if (inst == null) inst = new Prefs(c);
        return inst;
    }
    public void put(String k, String v) { p.edit().putString(k, v).apply(); }
    public String get(String k, String d) { return p.getString(k, d); }
}
