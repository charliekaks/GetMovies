package com.kaks.charles.getyourmovies.models;
import java.util.List;

public class MovieModel {
    private String overview;
    private String original_title;
    private String title;
    private String poster_path;
    private String release_date;
    private Integer vote_average;
    private Integer popularity;
    public MovieModel(){
    }
    public MovieModel(String overview, String original_title, String title, String poster_path, String release_date, Integer vote_average, Integer popularity){
        this.overview=overview;
        this.original_title=original_title;
        this.title=title;
        this.poster_path=poster_path;
        this.release_date=release_date;
        this.vote_average=vote_average;
        this.popularity=popularity;
    }

    public String getOverview(){
        return overview;
    }


    public String getOriginal_title(){
        return original_title;
    }


    public String getTitle(){
        return title;
    }


    public String getPoster_path(){
        return poster_path;
    }

    public String getRelease_date(){
        return release_date;
    }

    public Integer getVote_average(){
        return vote_average;
    }

    public Integer getPopularity(){
        return popularity;
    }

}