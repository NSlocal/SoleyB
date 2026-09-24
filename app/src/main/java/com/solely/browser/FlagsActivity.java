package com.solely.browser;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class FlagsActivity extends Activity {

    private FlagsProvider flags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flags);

        flags = FlagsProvider.get(this);

        setupToggle(R.id.switch_https_only, "https_only_mode", true);
        setupToggle(R.id.switch_cleartext, "cleartext_block", false);
        setupToggle(R.id.switch_quic, "quic_enabled", true);
        setupToggle(R.id.switch_dark, "dark_mode", false);
        setupToggle(R.id.switch_adblock, "adblock", true);
        setupToggle(R.id.switch_tls13, "tls13_only", false);
    }

    private void setupToggle(int resId, String key, boolean defaultValue) {
        Switch toggle = findViewById(resId);
        if (toggle == null) return;

        toggle.setChecked(flags.isEnabled(key, defaultValue));
        toggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            flags.set(key, isChecked);
        });
    }
}
