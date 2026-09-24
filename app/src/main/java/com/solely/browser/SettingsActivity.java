package com.solely.browser;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        findViewById(R.id.btn_flags).setOnClickListener(v ->
            startActivity(getPackageManager().getLaunchIntentForPackage(getPackageName())
                .setData(android.net.Uri.parse("solely://flags"))));
    }
}
