package com.example.daredevil07.ItsShowTime;

import java.io.Serializable;

import java.util.ArrayList;


public class Film implements Serializable{

    //New checken film heeft meerdere voorsytelling
    private String title;
    //img
    private String posterPath;
    private String backDropPath;
    private String overview;
    private int runtime;

    private String certification;
    private String genres;
    private String director;
    private String actor;

    public Film() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }


    public void setPosterPath(String posterPath) {

        this.posterPath = "https://image.tmdb.org/t/p/w300" +
        posterPath;
    }

    public String getBackDropPath() {
        return backDropPath;
    }

    public void setBackDropPath(String backDropPath) {
        this.backDropPath = "https://image.tmdb.org/t/p/w300" + backDropPath;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {

        String genreList = "";

        for (int i = 0; i < genres.size(); i++) {


            genreList += genres.get(i);
            if ((genres.size() - i) > 1){
                genreList += " | ";
            }
            this.genres = "Genre: " + genreList;
        }
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {

        if(certification.equals("")){
            certification = "--";
        }
        this.certification = certification;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {

        this.director = "Director: " + director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(ArrayList<String> actor) {
        String actorList = "";

        for (String s: actor) {

            actorList += s + " | ";
            this.actor = actorList;

        }

    }




    @Override
    public String toString() {
        return title + " " + genres + " " + director + " " + actor;
    }
}
