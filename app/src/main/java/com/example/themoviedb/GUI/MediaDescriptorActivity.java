package com.example.themoviedb.GUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
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
    private ImageView mediaCover, dataRilascioIcon, valutazioneIcon;
    private TextView mediaTitolo, tramaLbl, mediaTrama, infoLbl, mediaDataRilascio, mediaValutazione;

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
        dataRilascioIcon = findViewById(R.id.calendarioIcona);
        mediaDataRilascio = findViewById(R.id.dataRilascio);
        valutazioneIcon = findViewById(R.id.valutazioneIcona);
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
                setTemaDefault();
                break;

            case "Scala di grigi":
                setTemaDefault();
                setTemaGreyScale();
                break;

            case "Filtro rosso/verde":
                setTemaDefault();
                setTemaProtanopia();
                break;

            case "Filtro verde/rosso":
                setTemaDefault();
                setTemaDaltonismo();
                break;

            case "Filtro blu/giallo":
                setTemaDefault();
                setTemaTritanopia();
                break;

            case "Testo grande":
                increaseText();
                break;
        }
    }

    private void setTemaTritanopia() {
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(TRITANOPIA);
        mediaCover.setColorFilter(filter);
        backBtn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.secondaryColor_Tritanopia)));
        backBtn.setColorFilter(getResources().getColor(R.color.white));
        tramaLbl.setTextColor(getResources().getColor(R.color.accentColor1_Tritanopia));
        mediaTrama.setTextColor(getResources().getColor(R.color.accentColor1_Tritanopia));
        infoLbl.setTextColor(getResources().getColor(R.color.accentColor1_Tritanopia));
        mediaDataRilascio.setTextColor(getResources().getColor(R.color.accentColor1_Tritanopia));
        mediaValutazione.setTextColor(getResources().getColor(R.color.accentColor1_Tritanopia));
    }

    private void setTemaDaltonismo() {
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(DEUTERANOPIA);
        mediaCover.setColorFilter(filter);
        backBtn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.secondaryColor_Daltonismo)));
        tramaLbl.setTextColor(getResources().getColor(R.color.accentColor1_Daltonismo));
        mediaTrama.setTextColor(getResources().getColor(R.color.accentColor1_Daltonismo));
        infoLbl.setTextColor(getResources().getColor(R.color.accentColor1_Daltonismo));
        mediaDataRilascio.setTextColor(getResources().getColor(R.color.accentColor1_Daltonismo));
        mediaValutazione.setTextColor(getResources().getColor(R.color.accentColor1_Daltonismo));
    }

    private void setTemaProtanopia() {
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(PROTANOPIA);
        mediaCover.setColorFilter(filter);
        backBtn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.secondaryColor_Protanopia)));
        tramaLbl.setTextColor(getResources().getColor(R.color.accentColor1_Protanopia));
        mediaTrama.setTextColor(getResources().getColor(R.color.accentColor1_Protanopia));
        infoLbl.setTextColor(getResources().getColor(R.color.accentColor1_Protanopia));
        mediaDataRilascio.setTextColor(getResources().getColor(R.color.accentColor1_Protanopia));
        mediaValutazione.setTextColor(getResources().getColor(R.color.accentColor1_Protanopia));
    }

    private void setTemaGreyScale() {
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(ACROMATOPSIA);
        mediaCover.setColorFilter(filter);
        backBtn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
        backBtn.setColorFilter(getResources().getColor(R.color.black));
        tramaLbl.setTextColor(getResources().getColor(R.color.accentColor1_GreyScale));
        mediaTrama.setTextColor(getResources().getColor(R.color.accentColor1_GreyScale));
        infoLbl.setTextColor(getResources().getColor(R.color.accentColor1_GreyScale));
        dataRilascioIcon.setColorFilter(getResources().getColor(R.color.secondaryColor_GreyScale));
        mediaDataRilascio.setTextColor(getResources().getColor(R.color.accentColor1_GreyScale));
        valutazioneIcon.setColorFilter(getResources().getColor(R.color.secondaryColor_GreyScale));
        mediaValutazione.setTextColor(getResources().getColor(R.color.accentColor1_GreyScale));
    }

    private void increaseText() {
        mediaCover.clearColorFilter();
        tramaLbl.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_large));
        mediaTrama.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_large));
        infoLbl.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_large));
        mediaDataRilascio.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_large));
        mediaValutazione.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_large));
    }

    private void setTemaDefault() {
        tramaLbl.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_dimen));
        mediaTrama.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_dimen));
        infoLbl.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_dimen));
        mediaDataRilascio.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_dimen));
        mediaValutazione.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_dimen));
    }
}