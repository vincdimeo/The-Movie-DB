package com.example.themoviedb.Adapters;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.themoviedb.R;

public class SpinnerAdapter extends BaseAdapter {

    private TextView filtro, tipologia;

    private String[] filtri = {
            "Nessuno",
            "Scala di grigi",
            "Filtro rosso/verde",
            "Filtro verde/rosso",
            "Filtro blu/giallo",
            "Testo grande",
            "Scala di grigi + Testo grande",
            "Filtro rosso/verde + Testo grande",
            "Filtro verde/rosso + Testo grande",
            "Filtro blu/giallo + Testo grande"
    };

    private String[] tipologie = {
            "-",
            "Acromatopsia",
            "Protanopia",
            "Daltonismo",
            "Tritanopia",
            "Ipovisione",
            "Acromatopsia + Ipovisione",
            "Protanopia + Ipovisione",
            "Daltonismo + Ipovisione",
            "Tritanopia + Ipovisione"
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

    public int getIndexOf(String option) {
        int i = 0;

        while (i < filtri.length) {
            if (filtri[i] == option) {
                return i;
            }
            else {
                i = i + 1;
            }
        }

        return -1;
    }

    public void increaseText() {
        filtro.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.label_large));
        tipologia.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.label_large));
    }

    public void setDefaultTextSize() {
        filtro.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.label_dimen));
        tipologia.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.label_dimen));
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.spinner_layout, null);
        filtro = (TextView) view.findViewById(R.id.filtro);
        tipologia = (TextView) view.findViewById(R.id.tipologia);
        filtro.setText(filtri[i]);
        tipologia.setText(tipologie[i]);
        return view;
    }
}
