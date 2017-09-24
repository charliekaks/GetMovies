package com.kaks.charles.getyourmovies;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kaks.charles.getyourmovies.models.MovieModel;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PopularFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.image_details)
    ImageView mImageDetails;
    @Bind(R.id.overview_details) TextView mOverViewDetails;
    @Bind(R.id.release_date_details) TextView mReleaseDetails;
    @Bind(R.id.title_details) TextView mTitle;
    @Bind(R.id.save_movie)
    Button mSavedButton;
    private MovieModel mMovies;

    public static PopularFragment newInstance(MovieModel movie) {
        PopularFragment movieDetailFragment = new PopularFragment();
        Bundle args = new Bundle();
        args.putParcelable("movie", Parcels.wrap(movie));
        movieDetailFragment.setArguments(args);
        return movieDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovies = Parcels.unwrap(getArguments().getParcelable("movie"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mMovies.getPoster_path()).into(mImageDetails);
        mTitle.setText(mMovies.getTitle());
        mOverViewDetails.setText(mMovies.getOverview());
        mReleaseDetails.setText("The release Date \n"+mMovies.getRelease_date());
        mSavedButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        if (v == mSavedButton) {
            DatabaseReference restaurantRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_POPULAR_MOVIES);
            restaurantRef.push().setValue(mMovies);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }



}
