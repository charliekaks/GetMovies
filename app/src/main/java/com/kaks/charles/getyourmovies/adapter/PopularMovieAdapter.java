package com.kaks.charles.getyourmovies.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaks.charles.getyourmovies.MovieDetails;
import com.kaks.charles.getyourmovies.R;
import com.kaks.charles.getyourmovies.models.MovieModel;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by charles on 9/15/17.
 */

public class PopularMovieAdapter extends RecyclerView.Adapter<PopularMovieAdapter.ViewHolder> {
    private final Context mContext;
    private final LayoutInflater layoutInflater;
    private final List<MovieModel> mMovies;
    public PopularMovieAdapter(Context mContext, List<MovieModel> movies) {
        this.mContext = mContext;
        layoutInflater = LayoutInflater.from(mContext);
        this.mMovies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.movie_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bindMovies(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public final TextView title;
       // public final TextView overview;
        public final ImageView image;
        public final TextView voterAverage;
       // public final TextView release;
        //public final TextView popularity;
      ;


        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
           // overview = (TextView) itemView.findViewById(R.id.overview);
            image = (ImageView) itemView.findViewById(R.id.image_url);
            //release  = (TextView) itemView.findViewById(R.id.release_date);
            voterAverage = (TextView) itemView.findViewById(R.id.v_average);
           // popularity = (TextView) itemView.findViewById(R.id.popularity);


            itemView.setOnClickListener(this);


        }
        public void bindMovies(MovieModel movies) {

            title.setText(movies.getTitle());
           // overview.setText(movies.getOverview());
            voterAverage.setText("Vote" + " "+ String.valueOf(movies.getVote_average())+"/10");
           // release.setText("Movie Release Date \n"+movies.getRelease_date());
           // popularity.setText("Movie Popularity"+" "+String.valueOf(movies.getPopularity()));
            Picasso.with(mContext).load(movies.getPoster_path()).into(image);

        }

        @Override
        public void onClick(View view) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, MovieDetails.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("movies", Parcels.wrap(mMovies));
            intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        }
    }
}
