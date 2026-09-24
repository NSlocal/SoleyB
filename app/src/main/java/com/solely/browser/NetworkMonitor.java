package com.solely.browser;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;

public class NetworkMonitor {
    public static boolean isOnline(Context c) {
        ConnectivityManager m = (ConnectivityManager)
            c.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network n = m.getActiveNetwork();
        return n != null;
    }
}
