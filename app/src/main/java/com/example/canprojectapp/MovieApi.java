package com.example.canprojectapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
//https://run.mocky.io/v3/ 655ef7fd-58d1-420a-ba6e-32ae8acaaf3c
public interface MovieApi {
    @GET("k_3o41i3ti")
    Call<JSONResponse>getMovies();
}
