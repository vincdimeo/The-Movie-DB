package com.example.themoviedb.GUI;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.themoviedb.Adapters.SpinnerAdapter;
import com.example.themoviedb.MainActivity;
import com.example.themoviedb.R;


public class AccountFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private Spinner impostazioniSchermo;
    private SpinnerAdapter spinnerAdapter;
    private Button logoutBtn;
    private ImageView accountFoto;
    private TextView username, impostazioniLbl;

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

        username = view.findViewById(R.id.username);
        username.setText("Hey,\n" +MainActivity.utente.getUsername());

        impostazioniSchermo = view.findViewById(R.id.spinner);
        impostazioniSchermo.setOnItemSelectedListener(this);

        spinnerAdapter = new SpinnerAdapter(getContext());
        impostazioniSchermo.setAdapter(spinnerAdapter);

        if (MainActivity.tema != "") {
            impostazioniSchermo.setSelection(spinnerAdapter.getIndexOf(MainActivity.tema));
        }

        accountFoto = view.findViewById(R.id.accountPh);

        impostazioniLbl = view.findViewById(R.id.impostazioniTitolo);

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
            case "Nessuno":
                MainActivity.tema = "Nessuno";
                setDefaultTextSize();
                spinnerAdapter.setDefaultTextSize();
                break;

            case "Scala di grigi":
                MainActivity.tema = "Scala di grigi";
                setGreyScale();
                spinnerAdapter.setDefaultTextSize();
                break;

            case "Filtro rosso/verde":
                break;

            case "Filtro verde/rosso":
                break;

            case "Filtro blu/giallo":
                break;

            case "Testo grande":
                MainActivity.tema = "Testo grande";
                increaseText();
                spinnerAdapter.increaseText();
                break;
        }

        saveTheme(spinnerAdapter.getItem(i));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void saveTheme(String tema) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Utente", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Tema", tema);
        editor.commit();
    }

    private void setGreyScale() {
        username.setTextColor(getResources().getColor(R.color.black_GreyScale));
        impostazioniLbl.setTextColor(getResources().getColor(R.color.black_GreyScale));
        logoutBtn.setBackgroundColor(getResources().getColor(R.color.black_GreyScale));
        accountFoto.setColorFilter(getResources().getColor(R.color.grey));
    }

    private void increaseText() {
        impostazioniLbl.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_large));
        logoutBtn.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_large));
    }

    private void setDefaultTextSize() {
        username.setTextColor(getResources().getColor(R.color.black));
        impostazioniLbl.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_dimen));
        logoutBtn.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_dimen));
        logoutBtn.setBackgroundColor(getResources().getColor(R.color.black));
        accountFoto.setColorFilter(getResources().getColor(R.color.purple));
    }
}