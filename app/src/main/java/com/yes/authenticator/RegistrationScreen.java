package com.yes.authenticator;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationScreen extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);
        Button btn = (Button) findViewById(R.id.saveButton);
        FirebaseApp.initializeApp(RegistrationScreen.this);

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {   EditText passwordText = (EditText)findViewById(R.id.InputPasswordText);
                    passwordText.getText();

                    EditText ReinputPasswordText = (EditText)findViewById(R.id.ReinputPasswordText);
                    passwordText.getText();

                    EditText AgeText = (EditText)findViewById(R.id.AgeText);
                    passwordText.getText();

                    try
                    {
                        if(passwordText.getText().toString().length() < 6)
                        {
                            passwordText.setText("");
                            Toast.makeText(getApplicationContext(), "This does not meet the password requirement", Toast.LENGTH_LONG).show();
                        }
                        if(passwordText.getText().equals(ReinputPasswordText.getText())) {
                            Toast.makeText(getApplicationContext(), "Your Passwords are not identical.", Toast.LENGTH_LONG).show();
                        }
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Unexpected Error in password.", Toast.LENGTH_LONG).show();
                    }

                    if (AgeText.getText().toString().length() != 4)
                    {
                        Toast.makeText(getApplicationContext(), "Incorrect Year.", Toast.LENGTH_LONG).show();
                    }
                    else
                        {
                            EditText userNameText = (EditText)findViewById(R.id.EmailText);
                            EditText passText = (EditText)findViewById(R.id.InputPasswordText);
                            createAccount(userNameText.getText().toString(), passText.getText().toString());
                        }
                    // else grab all fields and send to firebase.
                }
                catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(),"Unexpected Error.", Toast.LENGTH_LONG).show();
                }

            }}
        );
    }


        @Override
        public void onStart()
    {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
        public void updateUI(FirebaseUser user)
        {

        }

        public void createAccount(String email, String password)
        {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("Tag", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("Tag", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(RegistrationScreen.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }

                            // ...
                        }
                    });
        }

    }
