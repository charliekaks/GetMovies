package com.kaks.charles.getyourmovies.UI;

import android.app.ProgressDialog;
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

public class LogIn extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.create_account_text) TextView mCreateText;
    @Bind(R.id.user_email) EditText mUserEmail;
    @Bind(R.id.email_password) EditText mEmailPassword;
    @Bind(R.id.button_login) Button mButtonLogin;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog mProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this);
        mCreateText.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        mButtonLogin.setOnClickListener(this);
        createProgressDialog();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(LogIn.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    @Override
    public void onClick(View view) {
        if (view==mCreateText){
            Intent intent = new Intent(LogIn.this, CreateAccount.class);
            startActivity(intent);
            finish();
        }
        if (view == mButtonLogin){
            loginToAccount();
        }

    }

    private void createProgressDialog(){
        mProgress = new ProgressDialog(this);
        mProgress.setTitle("Loading...");
        mProgress.setMessage("Signin in To your Account...");
        mProgress.setCancelable(false);
    }

    private void loginToAccount(){
        String email = mUserEmail.getText().toString().trim();
        String password = mEmailPassword.getText().toString().trim();
        if (email.equals("")){
            mUserEmail.setError("Please Enter Email");
            return;
        }
        if (password.equals("")){
            mEmailPassword.setError("Please Enter Password");
            return;
        }
        mProgress.show();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mProgress.dismiss();
                        Log.d("Email SignIn", "signInWithEmail:onComplete:" + task.isSuccessful());
                        if (!task.isSuccessful()) {
                            Log.w("Email SignIn", "signInWithEmail", task.getException());
                            Toast.makeText(LogIn.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

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
