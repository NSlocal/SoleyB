package com.solely.browser;

import android.webkit.WebView;

public class FindInPage {
    public static void find(WebView wv, String t) { wv.findAllAsync(t); }
    public static void clear(WebView wv) { wv.clearMatches(); }
}
