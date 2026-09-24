    private void setupWebView(WebView webView) {
        WebSettings ws = webView.getSettings();
        
        // Pengaturan dasar
        ws.setJavaScriptEnabled(true);
        ws.setDomStorageEnabled(true);
        ws.setDatabaseEnabled(true);
        ws.setCacheMode(WebSettings.LOAD_DEFAULT);
        ws.setAllowFileAccess(true);
        ws.setAllowContentAccess(true);
        ws.setLoadsImagesAutomatically(true);
        ws.setMediaPlaybackRequiresUserGesture(false);
        
        // Agen pengguna & tampilan
        ws.setUserAgentString("Mozilla/5.0 (Linux; Android 14) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Mobile Safari/537.36");
        ws.setUseWideViewPort(true);
        ws.setLoadWithOverviewMode(true);
        
        // 🔹 Dukungan Chrome Extension MV3
        webView.addJavascriptInterface(new ExtensionBridge(webView), "SolelyBridge");

        // 🔹 Skrip kompatibilitas MV3
        String mv3Compat = 
            "if (!window.chrome) { window.chrome = {}; }" +
            "if (!window.chrome.runtime) {" +
            "  window.chrome.runtime = {" +
            "    sendMessage: function(){}, " +
            "    onMessage: { addListener: function(){} }, " +
            "    getURL: function(p){return location.origin+p;}" +
            "  };" +
            "}" +
            "if (!window.chrome.storage) {" +
            "  window.chrome.storage = {" +
            "    local: {" +
            "      get: function(k){return Promise.resolve({});}, " +
            "      set: function(o){return Promise.resolve();}" +
            "    }," +
            "    sync: { get:function(){return Promise.resolve({});}, set:function(){return Promise.resolve();} }" +
            "  };" +
            "}" +
            "if (!window.chrome.action) { window.chrome.action = { setIcon:function(){}, setBadgeText:function(){} }; }" +
            "if (!window.chrome.tabs) { window.chrome.tabs = { query:function(o,c){c([]);} }; }";
        
        webView.evaluateJavascript(mv3Compat, null);

        // 🔹 WebViewClient + AdBlock
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView wv, WebResourceRequest req) {
                String url = req.getUrl().toString();
                if (url.startsWith("solely://")) {
                    handleSolelyUrl(url);
                    return true;
                }
                wv.loadUrl(url);
                if (etUrl != null) etUrl.setText(url);
                return false;
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView wv, WebResourceRequest req) {
                WebResourceResponse blocked = AdBlocker.blockIfNeeded(req);
                if (blocked != null) return blocked;
                return super.shouldInterceptRequest(wv, req);
            }
        });
    }
