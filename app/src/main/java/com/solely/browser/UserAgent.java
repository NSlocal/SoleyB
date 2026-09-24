package com.solely.browser;

import android.webkit.WebView;

public class UserAgent {
    public static String getDefault(WebView wv) {
        return wv.getSettings().getUserAgentString().replace("; wv)", ")");
    }
}
