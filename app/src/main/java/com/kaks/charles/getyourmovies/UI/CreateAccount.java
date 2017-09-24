package com.kaks.charles.getyourmovies.UI;

import android.content.Intent;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kaks.charles.getyourmovies.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CreateAccount extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.user_name) EditText mUserName;
    @Bind(R.id.create_email) EditText mCreateEmail;
    @Bind(R.id.create_password) EditText mCreatePassword;
    @Bind(R.id.password_confirm) EditText mPasswordConfirm;
    @Bind(R.id.login_text) TextView mLoginText;
    @Bind(R.id.create_account) Button mCreateAccount;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        createAuthStateListenerForAccount();
        mLoginText.setOnClickListener(this);
        mCreateAccount.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == mLoginText){
            Intent intent = new Intent(CreateAccount.this, LogIn.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (view==mCreateAccount){
            createAccount();
        }
    }

    private void createAccount(){
        final String name = mUserName.getText().toString().trim();
        final String email = mCreateEmail.getText().toString().trim();
        String password = mCreatePassword.getText().toString().trim();
        String confirmPassword = mPasswordConfirm.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.v("Authentication", "Authentication is a success");
                        }else{
                            Toast.makeText(CreateAccount.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void createAuthStateListenerForAccount(){
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(CreateAccount.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
