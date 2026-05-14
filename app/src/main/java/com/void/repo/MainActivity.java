package com.void.repo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;

public class MainActivity extends AppCompatActivity {

    private boolean isLocked = false;
    private boolean isPanicModeEnabled = false;
    private FileLocker fileLocker;
    private String userPasskey = "1234";

    private TextView tvStatus;
    private Switch switchLock;
    private Switch switchPanic;
    private Switch switchStealth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileLocker = new FileLocker(this, userPasskey);

        tvStatus = findViewById(R.id.tv_status);
        switchLock = findViewById(R.id.switch_lock);
        switchPanic = findViewById(R.id.switch_panic);
        switchStealth = findViewById(R.id.switch_stealth);
        TextView tvCredential = findViewById(R.id.tv_credential);
        Button btnSelectFiles = findViewById(R.id.btn_select_files);

        tvCredential.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://alexsifatrayhan.github.io/about-me/"));
            startActivity(browserIntent);
        });

        switchLock.setOnCheckedChangeListener((buttonView, isChecked) -> {
            try {
                if (isChecked) {
                    tvStatus.setText(R.string.state_locked);
                    isLocked = true;
                } else {
                    fileLocker.unlockAll();
                    tvStatus.setText(R.string.state_unlocked);
                    isLocked = false;
                }
            } catch (Exception e) {
                Toast.makeText(this, "Operation failed", Toast.LENGTH_SHORT).show();
                switchLock.setChecked(!isChecked);
            }
        });

        switchPanic.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isPanicModeEnabled = isChecked;
        });

        switchStealth.setOnCheckedChangeListener((buttonView, isChecked) -> {
            toggleStealthMode(isChecked);
        });

        btnSelectFiles.setOnClickListener(v -> {
            Toast.makeText(this, "File picker would open here", Toast.LENGTH_SHORT).show();
        });
    }

    private void toggleStealthMode(boolean enabled) {
        PackageManager pm = getPackageManager();
        int flagAlias = enabled ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED : PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
        int flagMain = enabled ? PackageManager.COMPONENT_ENABLED_STATE_DISABLED : PackageManager.COMPONENT_ENABLED_STATE_ENABLED;

        pm.setComponentEnabledSetting(new ComponentName(this, "com.void.repo.MainActivity"), flagMain, PackageManager.DONT_KILL_APP);
        pm.setComponentEnabledSetting(new ComponentName(this, "com.void.repo.CalculatorAlias"), flagAlias, PackageManager.DONT_KILL_APP);
        
        Toast.makeText(this, "Icon will change after restart", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isPanicModeEnabled && !isLocked) {
            try {
                switchLock.setChecked(true);
                Toast.makeText(this, "Panic Mode: Files Locked", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
