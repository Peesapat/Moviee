package com.example.canprojectapp.HomeItem1;

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

public class HomecardAdapter extends RecyclerView.Adapter<HomecardAdapter.ViewHolder> {


    private Context mContext;
    private List<Movie> mData;
    private OnMovieListener onMovieListener;


    public HomecardAdapter (Context mContext, List<Movie> mData, OnMovieListener onMovieListener1){
        this.mData = mData;
        this.mContext = mContext;
        this.onMovieListener = onMovieListener1 ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v = inflater.inflate(R.layout.hometopmovies,parent,false);
        return new ViewHolder(v, onMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(mData.get(position).getTitle());
        holder.year.setText(mData.get(position).getYear());
        holder.rating.setText(mData.get(position).getImDbRating());
        Glide.with(mContext).load(mData.get(position).getImage()).into(holder.posterImg);

    }

    @Override
    public int getItemCount() {

        return 5;
    }




public static class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView posterImg;
    TextView rating, title, year;
    OnMovieListener onMovieListener;


    public ViewHolder(@NonNull View itemView, OnMovieListener onMovieListener) {
        super(itemView);
        this.onMovieListener = onMovieListener;
        posterImg = itemView.findViewById(R.id.homecardposter);
        rating = itemView.findViewById(R.id.homecardrating);
        title = itemView.findViewById(R.id.homecardtitle);
        year = itemView.findViewById(R.id.homecardyear);
        itemView.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        onMovieListener.onMovieClick(getAdapterPosition());

    }
}


}
