package com.solely.browser;

import android.webkit.WebSettings;
import android.webkit.WebView;

public class TlsHardening {
    public static void apply(WebView wv, FlagsProvider f) {
        WebSettings ws = wv.getSettings();
        ws.setMixedContentMode(f.httpsOnly()
            ? WebSettings.MIXED_CONTENT_NEVER_ALLOW
            : WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        ws.setCacheMode(WebSettings.LOAD_DEFAULT);
    }
}
