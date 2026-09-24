package com.solely.browser;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BrowserWebViewClient extends WebViewClient {
    private final FlagsProvider flags;
    public BrowserWebViewClient(FlagsProvider f) { this.flags = f; }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest req) {
        view.loadUrl(req.getUrl().toString());
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (flags.isEnabled("adblock_easy", true)) AdBlocker.inject(view);
    }
}
