package org.lentils.oboronataganroga1855.register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.lentils.oboronataganroga1855.R;

public class ForgotPassword extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
    }
}