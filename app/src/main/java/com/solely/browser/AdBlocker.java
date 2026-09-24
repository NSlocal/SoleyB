package com.solely.browser;

import android.content.Context;
import android.net.Uri;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;

import java.util.HashSet;
import java.util.Set;

public class AdBlocker {
    private static final Set<String> AD_HOSTS = new HashSet<>();
    private static Context ctx;

    static {
        String[] hosts = {
            "doubleclick.net", "googlesyndication.com", "googleadservices.com",
            "ad.doubleclick.net", "adservice.google.com", "amazon-adsystem.com",
            "analytics.google.com", "googletagmanager.com", "googletagservices.com",
            "scorecardresearch.com", "quantserve.com", "chartbeat.com", "hotjar.com",
            "adroll.com", "criteo.com", "media.net", "openx.net", "pubmatic.com",
            "rubiconproject.com", "adnxs.com", "teads.tv", "outbrain.com",
            "cookiebot.com", "cloudflareinsights.com", "ad.", "ads.", "banner."
        };
        for (String h : hosts) AD_HOSTS.add(h);
    }

    public static void init(Context context) {
        ctx = context.getApplicationContext();
    }

    public static WebResourceResponse blockIfNeeded(WebResourceRequest req) {
        if (ctx == null) return null;
        
        String url = req.getUrl().toString();
        Uri uri = Uri.parse(url);
        String host = uri.getHost();
        if (host == null) return null;

        FlagsProvider flags = FlagsProvider.get(ctx);
        if (!flags.isEnabled("adblock", true)) return null;

        for (String adHost : AD_HOSTS) {
            if (host.contains(adHost)) {
                return new WebResourceResponse("text/plain", "utf-8", null);
            }
        }
        return null;
    }
}
