package com.example.themoviedb.GUI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.themoviedb.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MediaDescriptorActivity extends AppCompatActivity {

    private FloatingActionButton backBtn;
    private ImageView mediaCover;
    private TextView mediaTitolo, mediaTrama, mediaDataRilascio, mediaValutazione;

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
        mediaTrama = findViewById(R.id.tramaMedia);
        mediaDataRilascio = findViewById(R.id.dataRilascio);
        mediaValutazione = findViewById(R.id.valutazioneMedia);
    }
}