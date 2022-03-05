package com.example.therpyspace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainScreenOnActivity extends AppCompatActivity implements View.OnClickListener {
    private Button openApp;
    private TextView takeTour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen_on);

        openApp=findViewById(R.id.openappMainS_id);
        takeTour=findViewById(R.id.taketour_id);

        openApp.setOnClickListener(this);
        takeTour.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.openappMainS_id)
        {
            startActivity(new Intent(getApplicationContext(),DesbordActivity.class));
        }else
            if (view.getId()==R.id.taketour_id)
            {
                startActivity(new Intent(getApplicationContext(),OnBoardingScreen.class));
            }

    }
}