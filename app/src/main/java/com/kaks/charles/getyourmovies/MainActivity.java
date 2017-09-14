package com.kaks.charles.getyourmovies;

import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.edittext_categories) EditText mEditCategory;
    @Bind(R.id.button_genre) Button mButtonGenre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mButtonGenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String category = mEditCategory.getText().toString();
                Intent intent = new Intent(MainActivity.this, movies.class);
                intent.putExtra("categories", category);
                startActivity(intent);
            }
        });


    }
}
