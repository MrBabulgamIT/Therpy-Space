package com.example.therpyspace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Practitioner_Activity extends AppCompatActivity implements View.OnClickListener {
    private Button signupPra;
    private TextView loginPrac;
    private ImageView arrowPracLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practitioner);

        loginPrac=findViewById(R.id.login_prac_id);
        signupPra=findViewById(R.id.signup_prac_id);
        arrowPracLogin=findViewById(R.id.arrowPractitioner_id);

        loginPrac.setOnClickListener(this);
        signupPra.setOnClickListener(this);
        arrowPracLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.arrowPractitioner_id:
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            break;

            case R.id.login_prac_id:

                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                break;
            case R.id.signup_prac_id:
                startActivity(new Intent(getApplicationContext(), SignupEmailActivity.class));
                break;
        }
    }
}