package com.example.canprojectapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.canprojectapp.HomeItem1.HomecardAdapter;
import com.example.canprojectapp.HomeItem1.JSONResponse;
import com.example.canprojectapp.HomeItem1.Movie;
import com.example.canprojectapp.HomeItem1.OnMovieListener;
import com.example.canprojectapp.HomeItem2.HomeCardAdapter2;
import com.example.canprojectapp.HomeItem2.JSONResponse2;
import com.example.canprojectapp.HomeItem2.Movie2;
import com.example.canprojectapp.HomeItem2.OnMovieListener2;
import com.example.canprojectapp.MovieDetailItem.MovieDetail;
import com.example.canprojectapp.TopMovieItem.TopMoviesPage;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.canprojectapp.APIs.MovieApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class HomeActivity extends AppCompatActivity implements View.OnClickListener, OnMovieListener, OnMovieListener2 {
    private MovieApi movieApi;

    List<Movie> movielist;
    RecyclerView recyclerView;

    List<Movie2> movielist2;
    RecyclerView recyclerView2;


    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //notification bar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(HomeActivity.this,R.color.black));
        }

        TextView toTopMovies = findViewById(R.id.seemore);
        toTopMovies.setOnClickListener(this);


        recyclerView = findViewById(R.id.homeview);
        movielist = new ArrayList<>();

        recyclerView2 = findViewById(R.id.homeview2);
        movielist2 = new ArrayList<>();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://imdb-api.com/en/API/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieApi = retrofit.create(MovieApi.class);

       getOne();
       getTwo();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(),SearchActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        return true;

                    case R.id.list:
                        startActivity(new Intent(getApplicationContext(),ListActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });


    }
    @Override
    public void onMovieClick(int position) {
        Intent intent = new Intent(this, MovieDetail.class);
        intent.putExtra("rating", movielist.get(position).getImDbRating());
        intent.putExtra("name", movielist.get(position).getTitle());
        intent.putExtra("year", movielist.get(position).getYear());
        startActivity(intent);

    }

    public  void getOne(){
        Call<JSONResponse> call = movieApi.getMovies();


        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonResponse = response.body();
                movielist = new ArrayList<>(Arrays.asList(jsonResponse.getMovies()));
                putDataToRCY(movielist);

            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {

            }
        });

    }
    public void getTwo(){
        Call<JSONResponse2> call = movieApi.getMovies2();
        call.enqueue(new Callback<JSONResponse2>() {
            @Override
            public void onResponse(Call<JSONResponse2> call, Response<JSONResponse2> response) {
                JSONResponse2 jsonResponse = response.body();
                movielist2 = new ArrayList<>(Arrays.asList(jsonResponse.getMovies2()));
                putDataToRCY2(movielist2);

            }
            @Override
            public void onFailure(Call<JSONResponse2> call, Throwable t) {

            }
        });

    }
    public void putDataToRCY(List<Movie> movieList){
        HomecardAdapter adpt = new HomecardAdapter(this, movieList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adpt);
    }
    public void putDataToRCY2(List<Movie2> movieList2){
        HomeCardAdapter2 adpt2 = new HomeCardAdapter2(this, movieList2, this);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView2.setAdapter(adpt2);
    }



    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){

            case R.id.seemore: i = new Intent(this, TopMoviesPage.class);startActivity(i);break;

        }}


    @Override
    public void onMovieClick2(int position) {
        Intent intent = new Intent(this, MovieDetail.class);
        intent.putExtra("rating", movielist2.get(position).getImDbRating());
        intent.putExtra("name", movielist2.get(position).getTitle());
        intent.putExtra("year", movielist2.get(position).getYear());
        startActivity(intent);

    }
}
