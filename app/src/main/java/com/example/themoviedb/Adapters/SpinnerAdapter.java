package com.example.themoviedb.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.themoviedb.R;

public class SpinnerAdapter extends BaseAdapter {

    private String[] filtri = {
            "Nessuno",
            "Scala di grigi",
            "Filtro rosso/verde",
            "Filtro verde/rosso",
            "Filtro blu/giallo",
            "Testo grande"
    };

    private String[] tipologie = {
            "-",
            "Acromatopsia",
            "Protanopia",
            "Daltonismo",
            "Tritanopia",
            "Ipovisione"
    };

    private Context context;

    public SpinnerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return filtri.length;
    }

    @Override
    public String getItem(int i) {
        return filtri[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.spinner_layout, null);
        TextView filtro = (TextView) view.findViewById(R.id.filtro);
        TextView tipologia = (TextView) view.findViewById(R.id.tipologia);
        filtro.setText(filtri[i]);
        tipologia.setText(tipologie[i]);
        return view;
    }
}
