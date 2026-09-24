package com.solely.browser;

import android.net.Uri;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.HashSet;
import java.util.Set;

public class AdBlocker {
    private static final Set<String> AD_HOSTS = new HashSet<>();
    
    static {
        // Full ad/tracking domains
        String[] hosts = {
            "doubleclick.net", "googlesyndication.com", "googleadservices.com",
            "ad.doubleclick.net", "adservice.google.com", "amazon-adsystem.com",
            "facebook.com/tr", "analytics.google.com", "googletagmanager.com",
            "googletagservices.com", "scorecardresearch.com", "quantserve.com",
            "chartbeat.com", "hotjar.com", "newrelic.com", "segment.io",
            "adroll.com", "criteo.com", "media.net", "openx.net",
            "pubmatic.com", "rubiconproject.com", "ssp.api", "adnxs.com",
            "advertising.com", "attn.tv", "bouncex.net", "brsrvr.com",
            "cloudflareinsights.com", "consent.cookiebot.com", "cookiebot.com",
            "cruxd.com", "dwin1.com", "exelator.com", "gemius.pl",
            "heapanalytics.com", "hs-analytics.net", "hubspot.com",
            "liadm.com", "mxpnl.com", "nr-data.net", "omtrdc.net",
            "outbrain.com", "pagefair.com", "parsely.com", "piano.io",
            "privacy-mgmt.com", "quantummetric.com", "sentry-cdn.com",
            "simpli.fi", "stripe.network", "tapad.com", "teads.tv",
            "thetradedesk.com", "tracker.com", "trk.pinterest.com",
            "userzoom.com", "verizonmedia.com", "wcf.io", "xiti.com",
            "ad-srv.media", "adserver", "ads.", "ad.", "banner.", "pixel.",
            "track.", "beacon.", "stats.", "log.", "cdn.ads", "media.ads"
        };
        for (String h : hosts) AD_HOSTS.add(h);
    }

    public static boolean shouldBlock(String url) {
        if (url == null) return false;
        Uri uri = Uri.parse(url);
        String host = uri.getHost();
        if (host == null) return false;
        
        FlagsProvider flags = FlagsProvider.get(null);
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
