package com.example.letsgojohor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {


    private static final String TAG = "LoginActivity";
    EditText email, password;
    TextView textReg;

    Button login, register;

    //FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //pdateUI(currentUser);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        // ref = FirebaseDatabase.getInstance().getReference().child("User");

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        //login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String em = email.getText().toString();
                final String pw = password.getText().toString();

                //private static final String TAG = "LoginActivity";


                if(em.isEmpty() || pw.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Both email and password field are required to fill in!", Toast.LENGTH_SHORT).show();

                }


                else {

                    mAuth.signInWithEmailAndPassword(em, pw)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "signInWithEmail:success");
                                        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Intent main = new Intent(LoginActivity.this, MainActivity.class);
                                        //main.putExtra("uName",uname);
                                        startActivity(main);
                                        // user.getEmail();
                                        //updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        Toast.makeText(LoginActivity.this, "Incorrect email or password. Login failed.", Toast.LENGTH_SHORT).show();
                                        // updateUI(null);
                                    }

                                    // ...
                                }
                            });


                }



            }
        });

        //register
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                final String em = email.getText().toString();
                final String pw = password.getText().toString();


                if(em.isEmpty() || pw.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Both email and password field are required to fill in!", Toast.LENGTH_SHORT).show();

                }


                else {

                    mAuth.createUserWithEmailAndPassword(em, pw)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "createUserWithEmail:success");
                                        Toast.makeText(LoginActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        //updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(LoginActivity.this, "Registration failed. Please enter a valid email address and password must be 6 or more characters.", Toast.LENGTH_SHORT).show();
                                        //updateUI(null);
                                    }

                                    // ...
                                }
                            });
                }


            }
        });

        //end register

    }

}

