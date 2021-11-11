package com.example.canprojectapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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
import java.util.List;

public class MovieDetail extends AppCompatActivity {
    private TextView textTitle;
    private TextView textRating;
    private TextView textYear;
    String namee;


//w img
    private static String JSON_URL = "https://run.mocky.io/v3/ef3af9d3-ebda-43b7-8510-b9f204b9e10b";

    List<ActorCardHelper> aa;
    RecyclerView bb;
// no img
   // private static String JSON_URL = "https://run.mocky.io/v3/681247a4-36bf-49bf-9853-12947a005df2";

    //Retrofit retrofit


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
        MovieDetail.GetDataTP gettopdata1 = new MovieDetail.GetDataTP();
        gettopdata1.execute();

    }

    public class GetDataTP extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {
            String current ="";
            try{
                URL url;
                HttpURLConnection urlConnection = null;
                try{
                    url = new URL(JSON_URL);
                    urlConnection=(HttpURLConnection) url.openConnection();
                    InputStream is =urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    int data = isr.read();
                    while (data != -1){
                        current += (char) data;
                        data = isr.read();
                    }
                    return current;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(urlConnection != null){
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return current;
        }
        @Override
        protected void onPostExecute(String s){
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("movies");
                for (int i = 0; i< jsonArray.length();i++){
                    JSONObject jsonObjectw = jsonArray.getJSONObject(i);
                    if (jsonObjectw.getString("name").equals(namee)) {
                        JSONArray moreDetail = jsonObjectw.getJSONArray("actors");
                        for (int j = 0; j < moreDetail.length(); j++) {
                            JSONObject detail = moreDetail.getJSONObject(j);
                            ActorCardHelper aap = new ActorCardHelper();
                            aap.setPicture(detail.getString("photo"));
                            aap.setName(detail.getString("cast"));
                            aa.add(aap);

                }
                    }
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
            PutDataToRecyclerView(aa);
        }
    }

    private void PutDataToRecyclerView(List<ActorCardHelper> a){
        ActorAdapter p = new ActorAdapter(this,a);
        bb.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        bb.setAdapter(p);

    }

}

//    @Override
//    protected void onPostExecute(String s){
//        try {
//            JSONObject jsonObject = new JSONObject(s);
//            JSONArray jsonArray = jsonObject.getJSONArray("movies");
//            for (int i = 0; i< jsonArray.length();i++){
//                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//
//                ActorCardHelper aa = new ActorCardHelper();
//
//                aa.setName(jsonObject1.getString("name"));
//                aa.setPicture(jsonObject1.getString("poster"));
//
////
////                    if (jsonObject1.getString("name").equals("Alien")) {
////                        JSONArray moreDetail = jsonObject1.getJSONArray("actors");
////                        for (int j = 0; j < moreDetail.length(); j++) {
////                            JSONObject detail = moreDetail.getJSONObject(j);
////                            ActorCardHelper aa = new ActorCardHelper();
////                            aa.setPicture(detail.getString("name"));
////                            aa.setName(detail.getString("rating"));
////                            actorList.add(aa);
////                        }
////
////                    }
//
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        PutDataToRecyclerView(actorList);
//    }
//}



//
//    @Override
//    protected void onPostExecute(String s){
//        try {
//            JSONObject jsonObject = new JSONObject(s);
//            JSONArray jsonArray = jsonObject.getJSONArray("movies");
//            for (int i = 0; i< jsonArray.length();i++){
//                JSONObject jsonObjectw = jsonArray.getJSONObject(i);
//                ActorCardHelper Tcardhelper = new ActorCardHelper();
//                Tcardhelper.setName(jsonObjectw.getString("name"));
//                Tcardhelper.setPicture(jsonObjectw.getString("poster"));
//
//                aa.add(Tcardhelper);
//
//            }
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        PutDataToRecyclerView(aa);
//    }
//}