package com.solely.browser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class FlagsActivity extends AppCompatActivity {
    private ListView listView;
    private final List<FlagItem> items = new ArrayList<>();
    private FlagsProvider flags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flags);
        flags = FlagsProvider.get(this);
        listView = findViewById(R.id.flags_list);
        loadFlags();
        listView.setAdapter(new FlagAdapter(items));
    }

    private void loadFlags() {
        items.add(new FlagItem("https_only_mode",
            getString(R.string.flag_https_only),
            getString(R.string.flag_https_only_desc), true));
        items.add(new FlagItem("tls_min_version",
            getString(R.string.flag_tls_min),
            getString(R.string.flag_tls_min_desc), true));
        items.add(new FlagItem("tls_13_enforced",
            getString(R.string.flag_tls13),
            getString(R.string.flag_tls13_desc), true));
        items.add(new FlagItem("cleartext_block",
            getString(R.string.flag_cleartext),
            getString(R.string.flag_cleartext_desc), false));
        items.add(new FlagItem("quic_enabled",
            getString(R.string.flag_quic),
            getString(R.string.flag_quic_desc), true));
        items.add(new FlagItem("adblock_easy",
            getString(R.string.flag_adblock),
            getString(R.string.flag_adblock_desc), true));
        items.add(new FlagItem("force_dark_mode",
            getString(R.string.flag_dark),
            getString(R.string.flag_dark_desc), false));
        items.add(new FlagItem("third_party_cookies",
            getString(R.string.flag_3pc),
            getString(R.string.flag_3pc_desc), false));
    }

    private class FlagItem {
        String key, title, desc; boolean def;
        FlagItem(String k, String t, String d, boolean df) {
            key=k; title=t; desc=d; def=df;
        }
    }

    private class FlagAdapter extends ArrayAdapter<FlagItem> {
        FlagAdapter(List<FlagItem> data) {
            super(FlagsActivity.this, R.layout.item_flag, data);
        }
        @Override
        public View getView(int pos, View cv, ViewGroup p) {
            View v = cv != null ? cv : LayoutInflater.from(getContext())
                .inflate(R.layout.item_flag, p, false);
            FlagItem it = getItem(pos);
            TextView t = v.findViewById(R.id.flag_title);
            TextView d = v.findViewById(R.id.flag_desc);
            Switch s = v.findViewById(R.id.flag_switch);
            t.setText(it.title); d.setText(it.desc);
            s.setChecked(flags.isEnabled(it.key, it.def));
            s.setOnCheckedChangeListener(null);
            s.setOnCheckedChangeListener((btn, isOn) -> flags.set(it.key, isOn));
            return v;
        }
    }
}
