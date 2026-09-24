package com.solely.browser;

import android.webkit.PermissionRequest;

public class PermissionHandler {
    public static void handle(PermissionRequest r) {
        // Minimal: deny by default → extend as needed
        r.deny();
    }
}
