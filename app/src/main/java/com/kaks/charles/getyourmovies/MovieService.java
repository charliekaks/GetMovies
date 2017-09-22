package com.kaks.charles.getyourmovies;

import android.util.Log;

import com.kaks.charles.getyourmovies.models.MovieModel;
import com.kaks.charles.getyourmovies.models.MovieSearch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by charles on 9/14/17.
 */

public class MovieService {
    public static void findMovie(String category, Callback callback){
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_URL_POPULAR).newBuilder();
                                    urlBuilder.addQueryParameter( Constants.API_KEY, Constants.MOVIE_API_KEY)
                                            .addQueryParameter(Constants.QUERY,category);

        String url = urlBuilder.build().toString();
        Log.v("THE URL", url);

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public static void findPopularMovies(Callback callback){
        OkHttpClient clientMovie = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_URL).newBuilder();
                                    urlBuilder.addPathSegments("popular")
                                    .addQueryParameter(Constants.API_KEY,Constants.MOVIE_API_KEY);

        String url = urlBuilder.build().toString();
        Log.v("THE Popular URL", url);

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = clientMovie.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<MovieModel> processMovies(Response response){
        ArrayList<MovieModel> movies = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()){
                JSONObject movieJSON = new JSONObject(jsonData);
                JSONArray resultJSON = movieJSON.getJSONArray("results");
                for (int i = 0; i <resultJSON.length() ; i++) {
                    JSONObject movieResults = resultJSON.getJSONObject(i);
                    String overview = movieResults.getString("overview");
                    String original_title = movieResults.getString("original_title");
                    String title = movieResults.getString("title");
                    String imageUrl = "https://image.tmdb.org/t/p/w500"+movieResults.getString("poster_path");
                    String release = movieResults.getString("release_date");
                    Integer voter_average = movieResults.getInt("vote_average");
                    Integer popularity = movieResults.getInt("popularity");
                    MovieModel mMovies = new MovieModel(overview,original_title,title,imageUrl,release,voter_average,popularity);
                    movies.add(mMovies);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public ArrayList<MovieSearch> processMoviesSearched(Response response){
        ArrayList<MovieSearch> moviesSearched = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()){
                JSONObject movieJSON = new JSONObject(jsonData);
                JSONArray resultJSON = movieJSON.getJSONArray("results");
                for (int i = 0; i <resultJSON.length() ; i++) {
                    JSONObject movieResults = resultJSON.getJSONObject(i);
                    if (movieResults.getString("media_type").equals("tv")){
                    String overview = movieResults.getString("overview");
                    String original_title = "";
                    String title ="";
                    String originalName = movieResults.getString("original_name");
                    String imageUrl = "https://image.tmdb.org/t/p/w500"+movieResults.getString("poster_path");
                    String release = "";
                    String release_date = movieResults.getString("first_air_date");
                    Integer voter_average = movieResults.getInt("vote_average");
                    Integer popularity = movieResults.getInt("popularity");
                       MovieSearch mMoviesSearch = new MovieSearch(overview,original_title,title,imageUrl,release,voter_average,popularity,originalName,release_date);
                        moviesSearched.add(mMoviesSearch);
                    }else{
                        String overview = movieResults.getString("overview");
                        String original_title = movieResults.getString("original_title");
                        String title = movieResults.getString("title");
                        String originalName = "";
                        String imageUrl = "https://image.tmdb.org/t/p/w500"+movieResults.getString("poster_path");
                        String release = movieResults.getString("release_date");
                        String release_date = "";
                        Integer voter_average = movieResults.getInt("vote_average");
                        Integer popularity = movieResults.getInt("popularity");
                        MovieSearch mMoviesSearch = new MovieSearch(overview,original_title,title,imageUrl,release,voter_average,popularity,originalName,release_date);
                        moviesSearched.add(mMoviesSearch);
                    }



                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return moviesSearched;
    }
}
