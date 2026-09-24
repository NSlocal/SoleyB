webView.setWebViewClient(new WebViewClient() {
    @Override
    public boolean shouldOverrideUrlLoading(WebView wv, String url) {
        if (url.startsWith("solely://")) {
            handleSolelyUrl(url);
            return true;
        }
        wv.loadUrl(url);
        etUrl.setText(url);
        return false;
    }

    // ✅ ADD THIS — Block ads
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView wv, WebResourceRequest req) {
        WebResourceResponse blocked = AdBlocker.blockIfNeeded(req);
        return blocked != null ? blocked : super.shouldInterceptRequest(wv, req);
    }
});
