package com.void.repo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PasskeyActivity extends Activity {

    private String correctPasskey = "1234"; // Should be stored securely in real app

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passkey);

        EditText etPasskey = findViewById(R.id.et_passkey);
        Button btnUnlock = findViewById(R.id.btn_unlock);

        btnUnlock.setOnClickListener(v -> {
            String entered = etPasskey.getText().toString();
            if (entered.equals(correctPasskey)) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Incorrect Passkey", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
