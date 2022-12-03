package com.example.themoviedb.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.themoviedb.Classes.Media;
import com.example.themoviedb.GUI.MediaDescriptorActivity;
import com.example.themoviedb.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Media> listMedia;

    public MediaAdapter(ArrayList<Media> listMedia, Context context) {
        this.context = context;
        this.listMedia = listMedia;
    }

    public Media getItem(int position) {
        return listMedia.get(position);
    }

    public ArrayList<Media> getListMedia() {
        return listMedia;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.media_layout, parent, false);
        return new MediaAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MediaAdapter.MyViewHolder holder, int position) {
        holder.setMedia(listMedia.get(position));

        holder.mediaImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MediaDescriptorActivity.class);
                intent.putExtra("Film", listMedia.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMedia.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView mediaTitle;
        public ImageView mediaImg;

        public MyViewHolder(View itemView) {
            super(itemView);
            mediaTitle = itemView.findViewById(R.id.mediaTitle);
            mediaImg = itemView.findViewById(R.id.mediaImg);
        }

        void setMedia(Media media) {
            mediaTitle.setText(media.getTitle());

            if (media.getCover().equals("null")) {
                mediaImg.setImageResource(R.drawable.ic_launcher_background);
            }
            else {
                Picasso.with(context).load("https://image.tmdb.org/t/p/w500" + media.getCover()).into(mediaImg);
            }
        }
    }
}