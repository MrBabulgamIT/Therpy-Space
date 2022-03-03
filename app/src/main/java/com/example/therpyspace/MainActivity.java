package com.example.therpyspace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView clientCardiew,practitionerCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clientCardiew=findViewById(R.id.client_cardView_id);
        practitionerCardView=findViewById(R.id.practitioner_Card_id);

        clientCardiew.setOnClickListener(this);
        practitionerCardView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.client_cardView_id:
                startActivity(new Intent(getApplicationContext(),Client_Activity.class));
                break;

            case R.id.practitioner_Card_id:
                startActivity(new Intent(getApplicationContext(),Practitioner_Activity.class));

        }
    }
}