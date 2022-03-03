package com.example.therpyspace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupEmailActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText pracEmailET,pracPasswordET;
    private Button pracemailbuttonET;
    private ImageView arrowemailsignup;
    Dialog dialog;
    String emails,passwords,key;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupemail);

        pracEmailET=findViewById(R.id.emailsignup_id);
        pracPasswordET=findViewById(R.id.passwordsignup_id);
        pracemailbuttonET=findViewById(R.id.pracEmailnextB_id);
        arrowemailsignup=findViewById(R.id.arrowsignupEmail_id);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        mAuth = FirebaseAuth.getInstance();

        dialog=new Dialog(this);
        pracemailbuttonET.setOnClickListener(this);
        pracEmailET.setOnClickListener(this);
        arrowemailsignup.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.arrowsignupEmail_id:
                startActivity(new Intent(getApplicationContext(),Practitioner_Activity.class));
                break;

            case R.id.pracEmailnextB_id:

                registerNewUser();

                break;
        }
    }
    private void registerNewUser()
    {

        emails = pracEmailET.getText().toString().trim();
        passwords = pracPasswordET.getText().toString().trim();

        // Validations for input details
        if (emails.isEmpty()) {
            pracEmailET.setError("Please enter email!!");
            pracEmailET.requestFocus();
            return;
        }
        else
        if (emails.length() < 11) {

            pracEmailET.setError("Please enter valid mail");
            pracEmailET.requestFocus();
            return;

        }
        else
        if (passwords.isEmpty()) {
            pracPasswordET.setError("Please enter password!!");
            pracPasswordET.requestFocus();
            return;
        }
        else
        if (passwords.length() < 9) {
            pracPasswordET.setError("Minimum length of a password should be 9");
            pracPasswordET.requestFocus();
            return;

        }else

            // create new user or register new user
            mAuth.createUserWithEmailAndPassword(emails, passwords)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (task.isSuccessful()) {

                                FirebaseUser user = mAuth.getCurrentUser();
                                user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(SignupEmailActivity.this, "Verification Email has been sent", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(SignupEmailActivity.this, "Error! Email not sent", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                key= databaseReference.push().getKey();
                                UserRegistration users=new UserRegistration(emails,passwords);
                                databaseReference.child(key).setValue(users);

                                Toast.makeText(getApplicationContext(),
                                        "Registration successful!",
                                        Toast.LENGTH_LONG)
                                        .show();
                                openDialog();
                                // if the user created intent to login activity

                            }
                            else {

                                // Registration failed
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Registration failed!!"
                                                + " Please try again later",
                                        Toast.LENGTH_LONG)
                                        .show();

                            }
                        }
                    });
    }
    private void openDialog() {
        dialog.setContentView(R.layout.alert_mail);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView emailalert,yesalert,editalert;
        emailalert=dialog.findViewById(R.id.alertEmail_id);
        yesalert=dialog.findViewById(R.id.yes_alert_id);
        editalert=dialog.findViewById(R.id.alertEdit_id);

        emailalert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailalert.setText(emails);
            }
        });

        yesalert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),VerificationActivity.class));
            }
        });

        editalert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              dialog.dismiss();
            }
        });
        dialog.show();

    }


}