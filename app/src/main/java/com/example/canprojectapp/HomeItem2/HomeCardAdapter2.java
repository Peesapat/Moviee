package com.example.canprojectapp.HomeItem2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.canprojectapp.R;

import java.util.List;

public class HomeCardAdapter2 extends RecyclerView.Adapter<HomeCardAdapter2.ViewHolder> {


    private Context rContext;
    private List<Movie2> rData;
    private OnMovieListener2 onMovieListener;


    public HomeCardAdapter2 (Context mContext, List<Movie2> mData, OnMovieListener2 onMovieListener1){
        this.rData = mData;
        this.rContext = mContext;
        this.onMovieListener = onMovieListener1 ;
    }

    @NonNull
    @Override
    public HomeCardAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(rContext);
        v = inflater.inflate(R.layout.homercy2,parent,false);
        return new HomeCardAdapter2.ViewHolder(v, onMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCardAdapter2.ViewHolder holder, int position) {
        holder.title.setText(rData.get(position).getTitle());
        Glide.with(rContext).load(rData.get(position).getImage()).into(holder.posterImg);

    }

    @Override
    public int getItemCount() {

        return 5;
    }




    public static class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView posterImg;
        TextView title;
        OnMovieListener2 onMovieListener2;


        public ViewHolder(@NonNull View itemView, OnMovieListener2 onMovieListener2) {
            super(itemView);
            this.onMovieListener2 = onMovieListener2;
            posterImg = itemView.findViewById(R.id.hmimg);
            title = itemView.findViewById(R.id.hmtitle);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            onMovieListener2.onMovieClick2(getAdapterPosition());

        }
    }


}