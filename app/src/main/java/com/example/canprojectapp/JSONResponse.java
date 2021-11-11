package com.example.canprojectapp;

public class JSONResponse {
    private Movie[] items;

    public Movie[] getMovies() {
        return items;
    }

    public void setMovies(Movie[] movies) {
        this.items = movies;
    }
}
