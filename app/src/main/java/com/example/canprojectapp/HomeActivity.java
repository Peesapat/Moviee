package com.example.canprojectapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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

/// json w casts https://run.mocky.io/v3/681247a4-36bf-49bf-9853-12947a005df2
public class HomeActivity extends AppCompatActivity implements View.OnClickListener, OnMovieListener {

    /*
    ViewPager2 vpHorizontal;
    int[] images = {R.drawable.avatarposter,R.drawable.avengersposter,R.drawable.cargoposter,R.drawable.deadpoolposter,R.drawable.oblivionposter};

    HomeAdapter adapter;

     */


   // private static String JSON_URL = "https://run.mocky.io/v3/655ef7fd-58d1-420a-ba6e-32ae8acaaf3c";
    private static String JSON_URL = "https://imdb-api.com/en/API/Top250Movies/k_3o41i3ti";

    List<Movie> movielist;
    RecyclerView recyclerView;

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
                movielist = new ArrayList<>(Arrays.asList(jsonResponse.getMovies()));
                putDataToRCY(movielist);

            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {

            }
        });


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




        /*
        vpHorizontal = findViewById(R.id.vp_horizontal);

        adapter = new HomeAdapter(images);

        vpHorizontal.setClipToPadding(false);
        vpHorizontal.setClipChildren(false);
        vpHorizontal.setOffscreenPageLimit(3);
        vpHorizontal.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
        vpHorizontal.setAdapter(adapter);

        CompositePageTransformer transformer = new CompositePageTransformer();
        transformer.addTransformer(new MarginPageTransformer(8));
        transformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {

                float v = 1 - Math.abs(position);
                page.setScaleY(0.8f + v * 0.2f);
            }
        });

        vpHorizontal.setPageTransformer(transformer);

         */
    }
    @Override
    public void onMovieClick(int position) {
        Intent intent = new Intent(this,MovieDetail.class);
        intent.putExtra("rating", movielist.get(position).getImDbRating());
        intent.putExtra("name", movielist.get(position).getTitle());
        intent.putExtra("year", movielist.get(position).getYear());
        startActivity(intent);

    }
    public void putDataToRCY(List<Movie> movieList){
        HomecardAdapter adpt = new HomecardAdapter(this, movieList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adpt);
    }


    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){

            case R.id.seemore: i = new Intent(this,TopMoviesPage.class);startActivity(i);break;

        }}


}
