package com.example.canprojectapp.MovieDetailItem;

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

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ViewHolder> {

    private Context aContext;
    private List<ActorCardHelper> aData;

    public ActorAdapter (Context aContext,List<ActorCardHelper> aData){
        this.aData = aData;
        this.aContext = aContext;
    }


    @NonNull
    @Override
    public ActorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(aContext);
        v = inflater.inflate(R.layout.actorcard,parent,false);
        return new ActorAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorAdapter.ViewHolder holder, int position) {
        holder.cast.setText(aData.get(position).getName());
        Glide.with(aContext).load(aData.get(position).getPicture()).into(holder.pic);
    }


    @Override
    public int getItemCount() {
        return aData.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder {
        ImageView pic;
        TextView cast;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.actorpic);
            cast = itemView.findViewById(R.id.actorname);

        }
    }
}