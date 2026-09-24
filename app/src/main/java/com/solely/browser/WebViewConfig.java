package com.solely.browser;

import android.content.Context;
import android.os.Build;
import android.webkit.WebView;

public class WebViewConfig {
    public static void init(Context ctx) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            WebView.enableSlowWholeDocumentDraw();
        }
    }
}
