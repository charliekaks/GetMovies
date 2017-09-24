package com.kaks.charles.getyourmovies.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kaks.charles.getyourmovies.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LogIn extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.create_account_text)
    TextView mCreateText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this);
        mCreateText.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view==mCreateText){
            Intent intent = new Intent(LogIn.this, CreateAccount.class);
            startActivity(intent);
            finish();
        }

    }
}
