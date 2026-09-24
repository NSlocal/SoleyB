package com.solely.browser;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.webkit.CookieManager;

public class DownloadHelper {
    public static void download(Context c, String url, String userAgent) {
        DownloadManager.Request r = new DownloadManager.Request(Uri.parse(url));
        r.setMimeType("application/octet-stream");
        r.addRequestHeader("Cookie", CookieManager.getInstance().getCookie(url));
        r.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        ((DownloadManager)c.getSystemService(Context.DOWNLOAD_SERVICE)).enqueue(r);
    }
}
