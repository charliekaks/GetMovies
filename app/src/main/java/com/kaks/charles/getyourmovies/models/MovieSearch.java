package com.kaks.charles.getyourmovies.models;

import org.parceler.Parcel;

/**
 * Created by charles on 9/22/17.
 */
@Parcel
public class MovieSearch {



        private String overview;
        private String original_title;
        private String title;
        private String poster_path;
        private String release_date;
        private int vote_average;
        private int popularity;
        private String original_name;
        private String first_release;
        public MovieSearch(){
        }
        public MovieSearch(String overview, String original_title, String title, String poster_path, String release_date, Integer vote_average, Integer popularity, String o_name, String release){
            this.overview=overview;
            this.original_title=original_title;
            this.title=title;
            this.poster_path=poster_path;
            this.release_date=release_date;
            this.vote_average=vote_average;
            this.popularity=popularity;
            this.original_name = o_name;
            this.first_release = release;
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

        public String getOriginal_name(){return original_name;}

        public String getFirst_release(){return first_release;}


}
