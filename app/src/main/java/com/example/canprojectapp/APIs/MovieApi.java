package com.example.canprojectapp.APIs;

import com.example.canprojectapp.HomeItem1.JSONResponse;
import com.example.canprojectapp.HomeItem2.JSONResponse2;

import retrofit2.Call;
import retrofit2.http.GET;

// https://imdb-api.com/en/API/Title/k_nxjt3mcv/tt1375666
public interface MovieApi {
    @GET("Top250Movies/k_3o41i3ti")
    Call<JSONResponse>getMovies();

    @GET("ComingSoon/k_nxjt3mcv")
    Call<JSONResponse2>getMovies2();

    @GET("Title/k_nxjt3mcv/tt1375666")
    Call<JSONResponse> getDetailMovie();






}
