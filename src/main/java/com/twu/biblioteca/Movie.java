package com.twu.biblioteca;

import java.util.ArrayList;

public class Movie {
    private String title;
    private String year;
    private String director;
    private int rating;


    public Movie(String title, String year, String director, int rating){
        this.title = title;
        this.year = year;
        this.director = director;
        this.rating = rating;

    }

    public String getTitle() {
        return this.title;
    }

    public String getYear() {
        return this.year;
    }

    public String getDirector() {
        return this.director;
    }

    public int getRating() {
        return this.rating;
    }
}
