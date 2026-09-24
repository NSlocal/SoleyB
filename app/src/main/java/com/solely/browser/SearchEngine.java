package com.solely.browser;

import android.content.Context;

public class SearchEngine {
    private static final String HOME = "https://duckduckgo.com";
    private static final String Q = "https://duckduckgo.com/?q=";

    public static String getHomeUrl(Context c) { return HOME; }
    public static String normalize(String input) {
        if (input.contains(".") && !input.contains(" ")) {
            return input.startsWith("http") ? input : "https://" + input;
        }
        return Q + input.replaceAll(" ", "+");
    }
}
