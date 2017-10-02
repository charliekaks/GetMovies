package com.kaks.charles.getyourmovies;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kaks.charles.getyourmovies.models.MovieModel;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchFragment extends Fragment implements View.OnClickListener{
    @Bind(R.id.image_details) ImageView mImageDetails;
    @Bind(R.id.overview_details)
    TextView mOverViewDetails;
    @Bind(R.id.title_details) TextView mTitle;
    @Bind(R.id.save_movie)
    Button mSavedButton;
    private MovieModel mSearchMovies;



    public static SearchFragment newInstance(MovieModel movie) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putParcelable("movie", Parcels.wrap(movie));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSearchMovies = Parcels.unwrap(getArguments().getParcelable("movie"));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mSearchMovies.getPoster_path()).into(mImageDetails);
        mTitle.setText(mSearchMovies.getTitle());
        mOverViewDetails.setText(mSearchMovies.getOverview());
        mSavedButton.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        if (view == mSavedButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            DatabaseReference movieRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_POPULAR_MOVIES)
                    .child(uid);
            DatabaseReference pushRef= movieRef.push();
            String pushId = pushRef.getKey();
            mSearchMovies.setPushId(pushId);
            pushRef.setValue(mSearchMovies);
            //movieRef.push().setValue(mSearchMovies);
            Snackbar.make(getView(), "Saved", Snackbar.LENGTH_SHORT).show();
            //Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }

    }
}
