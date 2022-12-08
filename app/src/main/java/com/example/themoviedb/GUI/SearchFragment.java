package com.example.themoviedb.GUI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.themoviedb.MainActivity;
import com.example.themoviedb.R;


public class SearchFragment extends Fragment {

    private EditText cercaEditText;
    private Button cercaBtn;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        cercaEditText = view.findViewById(R.id.cercaEditText);
        cercaBtn = view.findViewById(R.id.cercaBtn);

        cercaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cercaEditText.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Il campo di ricerca è vuoto", Toast.LENGTH_SHORT).show();
                }
            }
        });

        switch (MainActivity.tema) {
            case "Nessuno":
                setDefaultTextSize();
                break;

            case "Scala di grigi":
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

        return  view;
    }

    private void increaseText() {
        cercaEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_large));
        cercaBtn.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_large));
    }

    private void setDefaultTextSize() {
        cercaEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_dimen));
        cercaBtn.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_dimen));
    }
}