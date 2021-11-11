package com.example.canprojectapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class TopMoviesAdapter extends RecyclerView.Adapter<TopMoviesAdapter.ViewHolder> {

    private Context tContext;
    private List<Movie> tData;
    private TopmovieListener topMovieListener;

    public TopMoviesAdapter (Context mContext, List<Movie> tData, TopmovieListener topMovieListener1){
        this.tData = tData;
        this.tContext = mContext;
        this.topMovieListener = topMovieListener1 ;
    }


    @NonNull
    @Override
    public TopMoviesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(tContext);
        v = inflater.inflate(R.layout.topmoviemodel,parent,false);
        return new TopMoviesAdapter.ViewHolder(v, topMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TopMoviesAdapter.ViewHolder holder, int position) {
        holder.titleT.setText(tData.get(position).getTitle());
        holder.yearT.setText(tData.get(position).getYear());
        holder.ratingT.setText(tData.get(position).getImDbRating());
        Glide.with(tContext).load(tData.get(position).getImage()).into(holder.posterImgT);

    }

    @Override
    public int getItemCount() {
        return tData.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView posterImgT;
        TextView ratingT, titleT, yearT;
        TopmovieListener topmovieListener;

        public ViewHolder(@NonNull View itemView,TopmovieListener topMovieListener) {
            super(itemView);
            posterImgT = itemView.findViewById(R.id.posterTop);
            ratingT = itemView.findViewById(R.id.ratingTop);
            titleT = itemView.findViewById(R.id.titleTop);
            yearT = itemView.findViewById(R.id.yearTop);
            this.topmovieListener =topMovieListener;
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            topmovieListener.onTopMovieClick(getAdapterPosition());
        }

    }
}
