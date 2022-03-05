package com.example.therpyspace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView arrowlogin;
    private EditText emaillogin,passwordlogin;
    private Button login;
    private TextView forgetTextView;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    String email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emaillogin=findViewById(R.id.emailLogin_id);
        passwordlogin=findViewById(R.id.passwordlogin_id);
        arrowlogin=findViewById(R.id.arrowLogin_id);
        login=findViewById(R.id.pracloginB_id);
        forgetTextView=findViewById(R.id.forget_password_id);

        arrowlogin.setOnClickListener(this);
        login.setOnClickListener(this);
        forgetTextView.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Login");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.arrowLogin_id:

                startActivity(new Intent(getApplicationContext(),Practitioner_Activity.class));
                break;

            case R.id.pracloginB_id:
                loginUserAccount();
                break;

            case R.id.forget_password_id:

                startActivity(new Intent(getApplicationContext(),ForgetActivtiy.class));
                break;


        }

    }

    private void loginUserAccount()
    {
        email = emaillogin.getText().toString();
        password = passwordlogin.getText().toString();

        // validations for input email and password
        if (email.isEmpty()) {
            emaillogin.setError("Please enter Email !!");
            emaillogin.requestFocus();
            return;
        }
        else
        if (email.length() < 10) {
            emaillogin.setError("Enter a valid email Or Number ");
            emaillogin.requestFocus();
            return;

        }else
        if (password.isEmpty()) {
            passwordlogin.setError("Please enter password !!");
            passwordlogin.requestFocus();
            return;
        }
        else
        if (password.length() < 9) {
            passwordlogin.setError("Enter a valid password ");
            passwordlogin.requestFocus();
            return;

        }

        String key= databaseReference.push().getKey();
        UserRegistration users=new UserRegistration(email,password);
        databaseReference.child(key).setValue(users);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(
                                    @NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(getApplicationContext(),MainScreenOnActivity.class));
                                    Toast.makeText(getApplicationContext(),
                                            "Login successful!!",
                                            Toast.LENGTH_LONG)
                                            .show();
                                }
                                else {
                                    emaillogin.setText("");
                                    passwordlogin.setText("");
                                    emaillogin.requestFocus();
                                    // sign-in failed
                                    Toast.makeText(getApplicationContext(),
                                            "Login failed!!",
                                            Toast.LENGTH_LONG)
                                            .show();
                                }
                            }
                        });

    }
}