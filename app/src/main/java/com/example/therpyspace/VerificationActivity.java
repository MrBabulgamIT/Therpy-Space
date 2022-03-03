package com.example.therpyspace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class VerificationActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView irrowverify;
    private TextView textDontHave, sentemail;
    private Button openButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        irrowverify = findViewById(R.id.arrowVerfi_id);
        textDontHave = findViewById(R.id.textDontEmailver);
        sentemail = findViewById(R.id.textSentAgainver);
        openButton = findViewById(R.id.verButton_id);

        irrowverify.setOnClickListener(this);
        sentemail.setOnClickListener(this);
        textDontHave.setOnClickListener(this);
        openButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.arrowVerfi_id:
                startActivity(new Intent(getApplicationContext(), SignupEmailActivity.class));
                break;
            case R.id.textDontEmailver:
                startActivity(new Intent(getApplicationContext(), SignupEmailActivity.class));
                break;

            case R.id.textSentAgainver:
                startActivity(new Intent(getApplicationContext(), SignupEmailActivity.class));
                break;

            case R.id.verButton_id:
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.google.android.gm");
                if (intent != null) {
                    startActivity(intent);
                } else {

                    gotoUrl("https://play.google.com/store/apps/details?id=com.google.android.gm&hl=en&gl=US");

                }
                break;

        }

    }

    private void gotoUrl(String s) {

        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}