package com.example.themoviedb.GUI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.themoviedb.Classes.Utente;
import com.example.themoviedb.MainActivity;
import com.example.themoviedb.R;


public class AccountFragment extends Fragment {

    private Button logoutBtn;
    private ImageView accountFoto;
    private TextView accountTitolo, username, impostazioniLbl, impostazioniSchermo;

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

        accountTitolo = view.findViewById(R.id.accountTitolo);

        username = view.findViewById(R.id.username);
        username.setText("@" + MainActivity.utente.getUsername());

        impostazioniSchermo = view.findViewById(R.id.impostazioni);
        impostazioniSchermo.setText(MainActivity.tema);

        accountFoto = view.findViewById(R.id.accountPh);

        impostazioniLbl = view.findViewById(R.id.impostazioniTitolo);

        logoutBtn = view.findViewById(R.id.mostraBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.utente = new Utente(null, null, null);
                MainActivity.tema = "Nessuno";

                SharedPreferences sharedPreferences = getContext().getSharedPreferences("Utente", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("Logged", false);
                editor.commit();

                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
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

            case "Scala di grigi + Testo grande":
                setTemaDefault();
                setTemaGreyScale();
                increaseText();
                break;

            case "Filtro rosso/verde + Testo grande":
                setTemaDefault();
                setTemaProtanopia();
                increaseText();
                break;

            case "Filtro verde/rosso + Testo grande":
                setTemaDefault();
                setTemaDaltonismo();
                increaseText();
                break;

            case "Filtro blu/giallo + Testo grande":
                setTemaDefault();
                setTemaTritanopia();
                increaseText();
                break;
        }

        return  view;
    }

    private void setTemaTritanopia() {
        accountTitolo.setTextColor(getResources().getColor(R.color.accentColor1_Tritanopia));
        username.setTextColor(getResources().getColor(R.color.accentColor1_Tritanopia));
        impostazioniLbl.setTextColor(getResources().getColor(R.color.accentColor1_Tritanopia));
        logoutBtn.setBackgroundColor(getResources().getColor(R.color.secondaryColor_Tritanopia));
        accountFoto.setColorFilter(getResources().getColor(R.color.accentColor3_Tritanopia));
    }

    private void setTemaDaltonismo() {
        accountTitolo.setTextColor(getResources().getColor(R.color.accentColor1_Daltonismo));
        username.setTextColor(getResources().getColor(R.color.accentColor1_Daltonismo));
        impostazioniLbl.setTextColor(getResources().getColor(R.color.accentColor1_Daltonismo));
        logoutBtn.setBackgroundColor(getResources().getColor(R.color.secondaryColor_Daltonismo));
        accountFoto.setColorFilter(getResources().getColor(R.color.accentColor3_Daltonismo));
    }

    private void setTemaProtanopia() {
        accountTitolo.setTextColor(getResources().getColor(R.color.accentColor1_Protanopia));
        username.setTextColor(getResources().getColor(R.color.accentColor1_Protanopia));
        impostazioniLbl.setTextColor(getResources().getColor(R.color.accentColor1_Protanopia));
        logoutBtn.setBackgroundColor(getResources().getColor(R.color.secondaryColor_Protanopia));
        accountFoto.setColorFilter(getResources().getColor(R.color.accentColor3_Protanopia));
    }

    private void setTemaGreyScale() {
        accountTitolo.setTextColor(getResources().getColor(R.color.accentColor1_GreyScale));
        username.setTextColor(getResources().getColor(R.color.accentColor1_GreyScale));
        impostazioniLbl.setTextColor(getResources().getColor(R.color.accentColor1_GreyScale));
        logoutBtn.setBackgroundColor(getResources().getColor(R.color.secondaryColor_GreyScale));
        accountFoto.setColorFilter(getResources().getColor(R.color.accentColor3_GreyScale));
    }

    private void increaseText() {
        username.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_large));
        impostazioniLbl.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_large));
        impostazioniSchermo.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_large));
        logoutBtn.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_large));
    }

    private void setTemaDefault() {
        accountTitolo.setTextColor(getResources().getColor(R.color.black));
        username.setTextColor(getResources().getColor(R.color.black));
        impostazioniLbl.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_dimen));
        logoutBtn.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_dimen));
        logoutBtn.setBackgroundColor(getResources().getColor(R.color.black));
        accountFoto.setColorFilter(getResources().getColor(R.color.purple));
    }
}