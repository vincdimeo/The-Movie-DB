package com.example.themoviedb.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.provider.MediaStore;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.themoviedb.Classes.Media;
import com.example.themoviedb.GUI.MediaDescriptorActivity;
import com.example.themoviedb.MainActivity;
import com.example.themoviedb.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Media> listMedia;

    private static final float[] PROTANOPIA = {
            0.567f,0.433f,0,0,0,
            0.558f,0.442f,0,0,0,
            0,0.242f,0.758f,0,0,
            0,0,0,1f,0,
            0,0,0,0
    };

    private static final float[] TRITANOPIA = {
            0.95f,0.05f,0,0,0,
            0,0.433f,0.567f,0,0,
            0,0.475f,0.525f,0,0,
            0,0,0,1,0,
            0,0,0,0,1
    };

    private static final float[] ACROMATOPSIA = {
            0.299f,0.587f,0.114f,0,0,
            0.299f,0.587f,0.114f,0,0,
            0.299f,0.587f,0.114f,0,0,
            0,0,0,1,0,
            0,0,0,0,1
    };

    private static final float[] DEUTERANOPIA = {
            0.625f,0.375f,0,0,0,
            0.7f,0.3f,0,0,0,
            0,0.3f,0.7f,0,0,
            0,0,0,1,0,
            0,0,0,0,1
    };

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

        switch (MainActivity.tema) {
            case "Nessuno":
                setTemaDefault(holder);
                break;

            case "Scala di grigi":
                setTemaDefault(holder);
                setTemaGreyScale(holder);
                break;

            case "Filtro rosso/verde":
                setTemaDefault(holder);
                setTemaProtanopia(holder);
                break;

            case "Filtro verde/rosso":
                setTemaDefault(holder);
                setTemaDaltonismo(holder);
                break;

            case "Filtro blu/giallo":
                setTemaDefault(holder);
                setTemaTritanopia(holder);
                break;

            case "Testo grande":
                increaseText(holder);
                break;
        }

        holder.mediaImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MediaDescriptorActivity.class);
                intent.putExtra("Media", listMedia.get(position));
                context.startActivity(intent);
            }
        });
    }

    private void setTemaTritanopia(MyViewHolder holder) {
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(TRITANOPIA);
        holder.mediaImg.setColorFilter(filter);
        holder.mediaTitle.setTextColor(context.getResources().getColor(R.color.accentColor1_Tritanopia));
    }

    private void setTemaDaltonismo(MyViewHolder holder) {
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(DEUTERANOPIA);
        holder.mediaImg.setColorFilter(filter);
        holder.mediaTitle.setTextColor(context.getResources().getColor(R.color.accentColor1_Daltonismo));
    }

    private void setTemaProtanopia(MyViewHolder holder) {
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(PROTANOPIA);
        holder.mediaImg.setColorFilter(filter);
        holder.mediaTitle.setTextColor(context.getResources().getColor(R.color.accentColor1_Protanopia));
    }

    private void setTemaGreyScale(MyViewHolder holder) {
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(ACROMATOPSIA);
        holder.mediaImg.setColorFilter(filter);
        holder.mediaImg.setColorFilter(filter);
        holder.mediaTitle.setTextColor(context.getResources().getColor(R.color.accentColor1_GreyScale));
    }

    private void increaseText(MediaAdapter.MyViewHolder holder) {
        holder.mediaTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.label_large));
        holder.mediaImg.getLayoutParams().width = 400;
        holder.mediaImg.getLayoutParams().height = 650;
    }

    private void setTemaDefault(MediaAdapter.MyViewHolder holder) {
        holder.mediaTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.label_dimen));
        holder.mediaImg.getLayoutParams().width = 300;
        holder.mediaImg.getLayoutParams().height = 450;
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

            if (media.getCover() == null) {
                mediaImg.setImageResource(R.drawable.ic_launcher_background);
            }
            else {
                Picasso.with(context).load("https://image.tmdb.org/t/p/w500" + media.getCover()).into(mediaImg);
            }
        }
    }
}