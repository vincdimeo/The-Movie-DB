package com.example.themoviedb.GUI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.themoviedb.MainActivity;
import com.example.themoviedb.R;


public class SearchFragment extends Fragment {

    private TextView cercaTitolo;
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

        cercaTitolo = view.findViewById(R.id.cercaTitolo);
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
                setTemaDefault();
                break;

            case "Scala di grigi":
                setTemaDefault();
                setTemaGreyScale();
                break;

            case "Filtro rosso/verde":
                setTemaDefault();
                setTemaProtonopia();
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

        return  view;
    }

    private void setTemaTritanopia() {
        cercaTitolo.setTextColor(getContext().getResources().getColor(R.color.accentColor1_Tritanopia));
        cercaEditText.setTextColor(getContext().getResources().getColor(R.color.accentColor1_Tritanopia));
        cercaBtn.setBackgroundColor(getContext().getResources().getColor(R.color.secondaryColor_Tritanopia));
    }

    private void setTemaDaltonismo() {
        cercaTitolo.setTextColor(getContext().getResources().getColor(R.color.accentColor1_Daltonismo));
        cercaEditText.setTextColor(getContext().getResources().getColor(R.color.accentColor1_Daltonismo));
        cercaBtn.setBackgroundColor(getContext().getResources().getColor(R.color.secondaryColor_Daltonismo));
    }

    private void setTemaProtonopia() {
        cercaTitolo.setTextColor(getContext().getResources().getColor(R.color.accentColor1_Protanopia));
        cercaEditText.setTextColor(getContext().getResources().getColor(R.color.accentColor1_Protanopia));
        cercaBtn.setBackgroundColor(getContext().getResources().getColor(R.color.secondaryColor_Protanopia));
    }

    private void increaseText() {
        cercaEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_large));
        cercaBtn.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_large));
    }

    private void setTemaDefault() {
        cercaTitolo.setTextColor(getContext().getResources().getColor(R.color.black));
        cercaEditText.setTextColor(getContext().getResources().getColor(R.color.black));
        cercaBtn.setBackgroundColor(getContext().getResources().getColor(R.color.purple));
        cercaEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_dimen));
        cercaBtn.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_dimen));
    }

    private void setTemaGreyScale() {
        cercaTitolo.setTextColor(getContext().getResources().getColor(R.color.accentColor1_GreyScale));
        cercaEditText.setTextColor(getContext().getResources().getColor(R.color.accentColor1_GreyScale));
        //cercaEditText.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.secondaryColor_GreyScale));
        cercaBtn.setBackgroundColor(getContext().getResources().getColor(R.color.secondaryColor_GreyScale));
    }
}