package com.solely.browser;

import android.webkit.WebView;

public class AdBlocker {
    private static final String JS =
        "(function(){var s='ad,ads,ad-banner,ad-container,adbox,ad-sponsor,ad-zone,advert,advertisement,adframe'.split(',');"+
        "for(var i=0;i<s.length;i++){var e=document.querySelectorAll('[class*='+s[i]+'],[id*='+s[i]+']');"+
        "for(var j=0;j<e.length;j++)e[j].style.display='none';}})();";

    public static void inject(WebView wv) { wv.evaluateJavascript(JS, null); }
}
