package com.solely.browser;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;

public class ExtensionBridge {
    private final WebView webView;

    public ExtensionBridge(WebView wv) { this.webView = wv; }

    @JavascriptInterface
    public String getPlatform() { return "solely-browser-android"; }

    @JavascriptInterface
    public boolean isMV3Supported() { return true; }
}
