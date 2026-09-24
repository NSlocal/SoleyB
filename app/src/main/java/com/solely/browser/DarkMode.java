package com.solely.browser;

import android.content.Context;
import android.webkit.WebView;

public class DarkMode {
    public static void apply(Context ctx, WebView wv, FlagsProvider f) {
        if (f.isEnabled("force_dark_mode", false)) {
            wv.evaluateJavascript(
                "document.documentElement.style.filter='invert(0.9) hue-rotate(180deg)';", null);
        }
    }
}
