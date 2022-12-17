package com.example.themoviedb.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Media> results;

    public ResultAdapter(ArrayList<Media> results, Context context) {
        this.context = context;
        this.results = results;
    }

    public Media getItem(int position) {
        return results.get(position);
    }

    public ArrayList<Media> getResults() {
        return results;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.result_layout, parent, false);
        return new ResultAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultAdapter.MyViewHolder holder, int position) {
        holder.setMedia(results.get(position));

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

        holder.mostraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MediaDescriptorActivity.class);
                intent.putExtra("Media", results.get(position));
                context.startActivity(intent);
            }
        });
    }

    private void setTemaTritanopia(MyViewHolder holder) {
        holder.mediaTitle.setTextColor(context.getResources().getColor(R.color.secondaryColor_Tritanopia));
        holder.mediaTrama.setTextColor(context.getResources().getColor(R.color.accentColor3_Tritanopia));
        holder.mostraBtn.setTextColor(context.getResources().getColor(R.color.secondaryColor_Tritanopia));
    }

    private void setTemaDaltonismo(MyViewHolder holder) {
        holder.mediaTitle.setTextColor(context.getResources().getColor(R.color.accentColor1_Daltonismo));
        holder.mediaTrama.setTextColor(context.getResources().getColor(R.color.accentColor3_Daltonismo));
        holder.mostraBtn.setTextColor(context.getResources().getColor(R.color.secondaryColor_Daltonismo));
    }

    private void setTemaProtanopia(MyViewHolder holder) {
        holder.mediaTitle.setTextColor(context.getResources().getColor(R.color.accentColor1_Protanopia));
        holder.mediaTrama.setTextColor(context.getResources().getColor(R.color.accentColor3_Protanopia));
        holder.mostraBtn.setTextColor(context.getResources().getColor(R.color.secondaryColor_Protanopia));
    }

    private void setTemaGreyScale(MyViewHolder holder) {
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        holder.mediaImg.setColorFilter(filter);
        holder.mediaTitle.setTextColor(context.getResources().getColor(R.color.accentColor2_GreyScale));
        holder.mediaTrama.setTextColor(context.getResources().getColor(R.color.accentColor1_GreyScale));
        holder.mostraBtn.setTextColor(context.getResources().getColor(R.color.secondaryColor_GreyScale));
    }

    private void increaseText(ResultAdapter.MyViewHolder holder) {
        holder.mediaTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.label_large));
        holder.mediaTrama.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.label_large));
        holder.mostraBtn.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.label_large));
        holder.mediaImg.getLayoutParams().width = 400;
        holder.mediaImg.getLayoutParams().height = 650;
    }

    private void setTemaDefault(ResultAdapter.MyViewHolder holder) {
        holder.mediaTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.label_dimen));
        holder.mediaTrama.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.label_dimen));
        holder.mostraBtn.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.label_dimen));
        holder.mediaImg.getLayoutParams().width = 300;
        holder.mediaImg.getLayoutParams().height = 450;
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView mediaImg;
        public TextView mediaTitle, mediaTrama;
        public Button mostraBtn;

        public MyViewHolder(View itemView) {
            super(itemView);
            mediaTitle = itemView.findViewById(R.id.mediaTitle);
            mediaTrama = itemView.findViewById(R.id.mediaTrama);
            mediaImg = itemView.findViewById(R.id.mediaImg);
            mostraBtn = itemView.findViewById(R.id.mostraBtn);
        }

        void setMedia(Media media) {
            mediaTitle.setText(media.getTitle());
            mediaTrama.setText(media.getDescription());

            if (media.getCover() == null) {
                mediaImg.setImageResource(R.drawable.ic_launcher_background);
            }
            else {
                Picasso.with(context).load("https://image.tmdb.org/t/p/w500" + media.getCover()).into(mediaImg);
            }
        }
    }
}