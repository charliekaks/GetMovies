package com.kaks.charles.getyourmovies.UI;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kaks.charles.getyourmovies.Constants;
import com.kaks.charles.getyourmovies.R;
import com.kaks.charles.getyourmovies.SavedMovies;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.edittext_categories) EditText mEditCategory;
    @Bind(R.id.button_genre) Button mButtonGenre;
    @Bind(R.id.button_movies) Button mButtonMovies;
    @Bind(R.id.savedMovieButton) Button mSavedButton;
    private DatabaseReference mPopularMovies;
    private ValueEventListener mSearchedMovieReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

            mPopularMovies = FirebaseDatabase
                    .getInstance()
                    .getReference()
                    .child(Constants.FIREBASE_CHILD_SEARCHED_MOVIE);
        mSearchedMovieReference = mPopularMovies.addValueEventListener(new ValueEventListener() { //attach listener to listen for changes in the DB.

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //something changes intialsize this method
                for (DataSnapshot categorySnapshot : dataSnapshot.getChildren()) {
                    String category = categorySnapshot.getValue().toString();
                    Log.d("Locations updated", "location: " + category); //log
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred.

            }
        });


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/amita_regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        mButtonGenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String category = mEditCategory.getText().toString();
                saveCategoryToFirebase(category );
                Intent intent = new Intent(MainActivity.this, Movies.class);
                intent.putExtra("categories", category);
                startActivity(intent);
            }
        });
        mButtonMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Popular.class);
                startActivity(intent);

            }
        });
        mSavedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SavedMovies.class);
                startActivity(intent);

            }
        });


    }
    public void saveCategoryToFirebase(String category) {
        mPopularMovies.setValue(category);
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPopularMovies.removeEventListener(mSearchedMovieReference);
    }
}
