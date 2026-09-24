package com.solely.browser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button btnFlags = findViewById(R.id.btn_open_flags);
        if (btnFlags != null) {
            btnFlags.setOnClickListener(v -> {
                Intent intent = new Intent(SettingsActivity.this, FlagsActivity.class);
                startActivity(intent);
            });
        }

        TextView versionText = findViewById(R.id.tv_app_version);
        if (versionText != null) {
            versionText.setText("Solely Browser v1.0.36");
        }
    }
}
