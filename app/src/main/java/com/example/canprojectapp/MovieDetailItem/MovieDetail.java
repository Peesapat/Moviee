package com.example.canprojectapp.MovieDetailItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.canprojectapp.APIs.MovieApi;
import com.example.canprojectapp.HomeItem1.JSONResponse;
import com.example.canprojectapp.R;

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

public class MovieDetail extends AppCompatActivity {
    private TextView textTitle;
    private TextView textRating;
    private TextView textYear;
    private Button buttonAdd;
    private MovieApi movieApi;



    String namee;
    private static String JSON_URL = "https://run.mocky.io/v3/ef3af9d3-ebda-43b7-8510-b9f204b9e10b";

    List<ActorCardHelper> aa;
    RecyclerView bb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        textTitle = findViewById(R.id.mdTxt1);
        textRating = findViewById(R.id.mdTxt3);
        textYear = findViewById(R.id.mdTxt4);


        textTitle.setText(getIntent().getStringExtra("name"));
        textRating.setText(getIntent().getStringExtra("rating"));
        textYear.setText(getIntent().getStringExtra("year"));

        namee= textTitle.getText().toString();

        aa = new ArrayList<>();
        bb = findViewById(R.id.actorview);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://imdb-api.com/en/API/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieApi = retrofit.create(MovieApi.class);

        Call<JSONResponse> call = movieApi.getMovies();


        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonResponse = response.body();

            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {

            }
        });



    }

    private void PutDataToRecyclerView(List<ActorCardHelper> a){
        ActorAdapter p = new ActorAdapter(this,a);
        bb.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        bb.setAdapter(p);

    }

}
