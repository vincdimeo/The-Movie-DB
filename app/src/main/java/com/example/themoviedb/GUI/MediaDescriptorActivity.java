package com.example.themoviedb.GUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.themoviedb.Classes.Media;
import com.example.themoviedb.MainActivity;
import com.example.themoviedb.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class MediaDescriptorActivity extends AppCompatActivity {

    private FloatingActionButton backBtn;
    private ImageView mediaCover;
    private TextView mediaTitolo, tramaLbl, mediaTrama, infoLbl, mediaDataRilascio, mediaValutazione;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_descriptor);

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mediaCover = findViewById(R.id.movieCover);
        mediaTitolo = findViewById(R.id.mediaTitolo);
        tramaLbl = findViewById(R.id.tramaLbl);
        mediaTrama = findViewById(R.id.tramaMedia);
        infoLbl = findViewById(R.id.informazioniTitolo);
        mediaDataRilascio = findViewById(R.id.dataRilascio);
        mediaValutazione = findViewById(R.id.valutazioneMedia);

        // Getting media by previous view
        Intent intent = getIntent();
        Media media = (Media) intent.getSerializableExtra("Media");

        Picasso.with(MediaDescriptorActivity.this).load("https://image.tmdb.org/t/p/w500"+ media.getBackdrop()).into(mediaCover);
        mediaTitolo.setText(media.getTitle());
        mediaTrama.setText(media.getDescription());
        mediaDataRilascio.setText(media.getReleaseDate());
        mediaValutazione.setText(media.getValutation());

        switch (MainActivity.tema) {
            case "Nessuno":
                setDefaultTextSize();
                break;

            case "Scala di grigi":
                setDefaultTextSize();
                setGrayScale();
                break;

            case "Filtro rosso/verde":
                break;

            case "Filtro verde/rosso":
                break;

            case "Filtro blu/giallo":
                break;

            case "Testo grande":
                increaseText();
                break;
        }
    }

    private void setGrayScale() {
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        mediaCover.setColorFilter(filter);
        backBtn.setColorFilter(filter);
        tramaLbl.setTextColor(getResources().getColor(R.color.black_GreyScale));
        mediaTrama.setTextColor(getResources().getColor(R.color.black_GreyScale));
        infoLbl.setTextColor(getResources().getColor(R.color.black_GreyScale));
        mediaDataRilascio.setTextColor(getResources().getColor(R.color.black_GreyScale));
        mediaValutazione.setTextColor(getResources().getColor(R.color.black_GreyScale));
    }

    private void increaseText() {
        mediaCover.clearColorFilter();
        tramaLbl.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_large));
        mediaTrama.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_large));
        infoLbl.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_large));
        mediaDataRilascio.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_large));
        mediaValutazione.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_large));
    }

    private void setDefaultTextSize() {
        tramaLbl.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_dimen));
        mediaTrama.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_dimen));
        infoLbl.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_dimen));
        mediaDataRilascio.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_dimen));
        mediaValutazione.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_dimen));
    }
}