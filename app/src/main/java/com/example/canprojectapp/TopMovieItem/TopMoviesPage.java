package com.example.canprojectapp.TopMovieItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.canprojectapp.HomeItem1.JSONResponse;
import com.example.canprojectapp.HomeItem1.Movie;
import com.example.canprojectapp.APIs.MovieApi;
import com.example.canprojectapp.MovieDetailItem.MovieDetail;
import com.example.canprojectapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TopMoviesPage extends AppCompatActivity implements TopmovieListener {

    List<Movie> Topmovielist;
    RecyclerView rcyViewTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_movies_page);

        Topmovielist = new ArrayList<>();
        rcyViewTop = findViewById(R.id.topmovieslist);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://imdb-api.com/en/API/")
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
        Intent intent = new Intent(this, MovieDetail.class);
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