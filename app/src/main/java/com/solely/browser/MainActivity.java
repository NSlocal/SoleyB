package com.solely.browser;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    private WebView webView;
    private EditText etUrl;
    private FlagsProvider flags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flags = new FlagsProvider(this);
        webView = findViewById(R.id.webview);
        etUrl = findViewById(R.id.et_url);

        setupWebView();
        setupButtons();
        handleIntent(getIntent());
    }

    private void setupWebView() {
        WebSettings ws = webView.getSettings();
        ws.setJavaScriptEnabled(false);
        ws.setDomStorageEnabled(false);
        ws.setAllowFileAccess(false);
        ws.setAllowContentAccess(false);
        ws.setCacheMode(WebSettings.LOAD_DEFAULT);
        ws.setMixedContentMode(flags.httpsOnly()
            ? WebSettings.MIXED_CONTENT_NEVER_ALLOW
            : WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);

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
        });
    }

    private void setupButtons() {
        findViewById(R.id.btn_go).setOnClickListener(v -> goUrl());
        findViewById(R.id.btn_home).setOnClickListener(v -> goHome());
        findViewById(R.id.btn_back).setOnClickListener(v -> { if(webView.canGoBack()) webView.goBack(); });
        findViewById(R.id.btn_forward).setOnClickListener(v -> { if(webView.canGoForward()) webView.goForward(); });
        findViewById(R.id.btn_flags).setOnClickListener(v ->
            startActivity(new Intent(this, FlagsActivity.class)));
        findViewById(R.id.btn_settings).setOnClickListener(v ->
            startActivity(new Intent(this, SettingsActivity.class)));

        // Pintasan cepat
        findViewById(R.id.sc_google).setOnClickListener(v -> loadUrl("https://google.com"));
        findViewById(R.id.sc_youtube).setOnClickListener(v -> loadUrl("https://youtube.com"));
        findViewById(R.id.sc_new).setOnClickListener(v -> {
            etUrl.setText("");
            webView.loadUrl("about:blank");
        });
    }

    private void goUrl() {
        String url = etUrl.getText().toString().trim();
        if (url.isEmpty()) return;
        if (!url.contains("://")) url = "https://" + url;
        loadUrl(url);
    }

    private void loadUrl(String url) {
        etUrl.setText(url);
        webView.loadUrl(url);
    }

    private void goHome() {
        etUrl.setText("");
        webView.loadUrl("about:blank");
    }

    private void handleSolelyUrl(String url) {
        if (url.contains("flags"))
            startActivity(new Intent(this, FlagsActivity.class));
        else if (url.contains("settings"))
            startActivity(new Intent(this, SettingsActivity.class));
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_VIEW.equals(intent.getAction()) && intent.getData() != null) {
            loadUrl(intent.getData().toString());
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) webView.goBack();
        else super.onBackPressed();
    }
}
