package com.kaks.charles.getyourmovies;

/**
 * Created by charles on 9/14/17.
 */
//api.themoviedb.org/3/search/multi?api_key=a29a067555d7139438f64f51b33a2c69&language=en-US&query=shrek
//api.themoviedb.org/3/multi?api_key=a29a067555d7139438f64f51b33a2c69&query=shrek

public class Constants {
    public static final String MOVIE_API_KEY = BuildConfig.MOVIE_API_KEY;
    public static final String BASE_URL = "https://api.themoviedb.org/3/movie/";
    public static final String BASE_URL_POPULAR = "https://api.themoviedb.org/3/search/multi";
    public static final String QUERY = "query";
    public static final String API_KEY = "api_key";
    public static final String POPULAR_MOVIES_FIREBASE = "popularMovies";
    public static final String FIREBASE_CHILD_SEARCHED_MOVIE = "searchedMovie";
    public static final String FIREBASE_CHILD_POPULAR_MOVIES = "popular";

}
