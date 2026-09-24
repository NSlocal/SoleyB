package com.solely.browser;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private EditText urlBar;
    private FlagsProvider flags;
    private HttpsOnlyInterceptor httpsUpgrader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flags = FlagsProvider.get(this);
        httpsUpgrader = new HttpsOnlyInterceptor(flags);

        webView = findViewById(R.id.webview);
        urlBar = findViewById(R.id.url_bar);

        // Handle deep links: solely://flags / solely://settings
        if (getIntent().getData() != null) {
            String host = getIntent().getData().getHost();
            if ("flags".equals(host)) {
                startActivity(new Intent(this, FlagsActivity.class));
                finish(); return;
            }
            if ("settings".equals(host)) {
                startActivity(new Intent(this, SettingsActivity.class));
                finish(); return;
            }
        }

        // Configure WebView
        WebSettings ws = webView.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setDomStorageEnabled(true);
        ws.setCacheMode(WebSettings.LOAD_DEFAULT);
        ws.setAllowFileAccess(false);
        ws.setAllowContentAccess(false);

        // Apply TLS & security
        TlsHardening.apply(webView, flags);
        DarkMode.apply(this, webView, flags);

        webView.setWebViewClient(new BrowserWebViewClient(flags));
        webView.loadUrl(SearchEngine.getHomeUrl(this));

        urlBar.setOnEditorActionListener((v, actionId, event) -> {
            String input = urlBar.getText().toString().trim();
            if (!input.isEmpty()) {
                String url = httpsUpgrader.upgrade(SearchEngine.normalize(input));
                if (httpsUpgrader.shouldBlock(url)) {
                    urlBar.setError(getString(R.string.err_http_blocked));
                    return true;
                }
                webView.loadUrl(url);
                urlBar.clearFocus();
            }
            return true;
        });
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) webView.goBack();
        else super.onBackPressed();
    }
}
