package com.example.customlistview;

public class Movie {
    String name;
    String director;
    int movieResource;

    public Movie(String name, String director, int movieResource) {
        this.name = name;
        this.director = director;
        this.movieResource = movieResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getMovieResource() {
        return movieResource;
    }

    public void setMovieResource(int movieResource) {
        this.movieResource = movieResource;
    }

    @Override
    public String toString() {
        return name + ", directed by '" + director;
    }
}
