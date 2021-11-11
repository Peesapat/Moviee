package com.example.canprojectapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TopMoviesPage extends AppCompatActivity implements TopmovieListener {
    //private static String JSON_URL = "https://run.mocky.io/v3/655ef7fd-58d1-420a-ba6e-32ae8acaaf3c";

    List<Movie> Topmovielist;
    RecyclerView rcyViewTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_movies_page);

        Topmovielist = new ArrayList<>();
        rcyViewTop = findViewById(R.id.topmovieslist);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://imdb-api.com/en/API/Top250Movies/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieApi movieApi = retrofit.create(MovieApi.class);

        Call<JSONResponse> call = movieApi.getMovies();

        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonResponse = response.body();
                Topmovielist = new ArrayList<>(Arrays.asList(jsonResponse.getMovies()));
                PutDataToRecyclerView(Topmovielist);

            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {

            }
        });




    }

    @Override
    public void onTopMovieClick(int position) {
        Intent intent = new Intent(this,MovieDetail.class);
        intent.putExtra("rating",Topmovielist.get(position).getImDbRating());
        intent.putExtra("name", Topmovielist.get(position).getTitle());
        intent.putExtra("year", Topmovielist.get(position).getYear());
        startActivity(intent);

    }



    private void PutDataToRecyclerView(List<Movie> Topmovielist){
        TopMoviesAdapter adpt = new TopMoviesAdapter(this,Topmovielist,this);
        rcyViewTop.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        rcyViewTop.setAdapter(adpt);

    }
}