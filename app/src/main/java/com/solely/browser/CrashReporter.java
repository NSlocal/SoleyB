package com.solely.browser;

import android.util.Log;

public class CrashReporter {
    private static final String TAG = "SolelyBrowser";
    public static void logError(String msg, Throwable e) {
        Log.e(TAG, msg, e);
    }
}
