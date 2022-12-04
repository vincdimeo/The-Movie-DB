package com.example.themoviedb.GUI;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.themoviedb.Adapters.SpinnerAdapter;
import com.example.themoviedb.R;


public class AccountFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private Spinner impostazioniSchermo;
    private SpinnerAdapter spinnerAdapter;
    private Button logoutBtn;

    public AccountFragment() {
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
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        impostazioniSchermo = view.findViewById(R.id.spinner);
        impostazioniSchermo.setOnItemSelectedListener(this);

        spinnerAdapter = new SpinnerAdapter(getContext());
        impostazioniSchermo.setAdapter(spinnerAdapter);

        logoutBtn = view.findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return  view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (spinnerAdapter.getItem(i)) {
            case "Scala di grigi":
                break;

            case "Filtro rosso/verde":
                break;

            case "Filtro verde/rosso":
                break;

            case "Filtro blu/giallo":
                break;

            case "Testo grande":
                break;
        }
        Toast.makeText(getContext(), spinnerAdapter.getItem(i), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}