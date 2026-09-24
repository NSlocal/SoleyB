package com.solely.browser;

import android.content.Context;
import android.net.Uri;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;

import java.util.HashSet;
import java.util.Set;

public class AdBlocker {
    private static final Set<String> AD_HOSTS = new HashSet<>();
    private static Context appContext;

    static {
        String[] hosts = {
            "doubleclick.net", "googlesyndication.com", "googleadservices.com",
            "ad.doubleclick.net", "adservice.google.com", "amazon-adsystem.com",
            "analytics.google.com", "googletagmanager.com", "googletagservices.com",
            "scorecardresearch.com", "quantserve.com", "chartbeat.com", "hotjar.com",
            "adroll.com", "criteo.com", "media.net", "openx.net", "pubmatic.com",
            "rubiconproject.com", "adnxs.com", "advertising.com", "teads.tv",
            "outbrain.com", "cookiebot.com", "cloudflareinsights.com",
            "ad.", "ads.", "banner.", "pixel.", "track.", "beacon.", "stats."
        };
        for (String h : hosts) AD_HOSTS.add(h);
    }

    public static void init(Context context) {
        appContext = context.getApplicationContext();
    }

    public static boolean shouldBlock(String url) {
        if (url == null || appContext == null) return false;
        
        Uri uri = Uri.parse(url);
        String host = uri.getHost();
        if (host == null) return false;

        FlagsProvider flags = FlagsProvider.get(appContext);
        if (!flags.isEnabled("adblock", true)) return false;

        for (String adHost : AD_HOSTS) {
            if (host.contains(adHost)) return true;
        }
        return false;
    }

    public static WebResourceResponse blockIfNeeded(WebResourceRequest req) {
        if (shouldBlock(req.getUrl().toString())) {
            return new WebResourceResponse("text/plain", "utf-8", null);
        }
        return null;
    }
}
