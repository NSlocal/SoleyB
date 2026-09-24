package com.solely.browser;

public class HttpsOnlyInterceptor {
    private final FlagsProvider f;
    public HttpsOnlyInterceptor(FlagsProvider fp) { f = fp; }

    public String upgrade(String url) {
        if (!f.httpsOnly()) return url;
        if (url.startsWith("http://") && !isLocal(url)) {
            return url.replaceFirst("http://", "https://");
        }
        return url;
    }
    public boolean shouldBlock(String url) {
        if (!f.blockCleartext()) return false;
        return url.startsWith("http://") && !isLocal(url);
    }
    private boolean isLocal(String u) {
        return u.contains("127.0.0.1") || u.contains("localhost");
    }
}
