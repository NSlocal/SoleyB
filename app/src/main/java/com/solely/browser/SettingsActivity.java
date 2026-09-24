package com.solely.browser;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        findViewById(R.id.btn_flags).setOnClickListener(v -> {
            startActivity(new Intent(this, FlagsActivity.class));
        });

        TextView verText = findViewById(R.id.app_version);
        if (verText != null) {
            verText.setText("Solely Browser v1.0.36");
        }
    }
}
