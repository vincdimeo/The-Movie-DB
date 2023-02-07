package com.example.themoviedb.GUI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.themoviedb.Adapters.MediaAdapter;
import com.example.themoviedb.Classes.APICall;
import com.example.themoviedb.Classes.Media;
import com.example.themoviedb.Classes.MoviesApiResponse;
import com.example.themoviedb.Classes.OnFetchDataListener;
import com.example.themoviedb.MainActivity;
import com.example.themoviedb.R;

import java.util.ArrayList;
import java.util.List;


public class ReleasesFragment extends Fragment {

    private RecyclerView popolariRV, serieRV;
    private MediaAdapter popularAdapter, upcomingAdapter;
    private TextView releasesTitolo, popolariLbl, serieLbl;

    public ReleasesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        APICall api = new APICall(getContext());
        api.getPopularMovies(listener, "it-IT");
        api.getUpcomingMovies(listener,"it-IT");
    }

    final OnFetchDataListener<MoviesApiResponse> listener = new OnFetchDataListener<MoviesApiResponse>() {
        @Override
        public void onFetchData(List<Media> list, String message) {
            if (list.isEmpty()) {
                Toast.makeText(getContext(), "No data found!", Toast.LENGTH_SHORT).show();
            }
            else {
                if(popularAdapter == null){
                    popularAdapter = new MediaAdapter((ArrayList<Media>) list, getContext());
                    popolariRV.setAdapter(popularAdapter);
                }
                else{
                    upcomingAdapter = new MediaAdapter((ArrayList<Media>) list, getContext());
                    serieRV.setAdapter(upcomingAdapter);
                }
            }
        }

        @Override
        public void onError(String message) {
            Toast.makeText(getContext(), "An Error Occurred!", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_releases, container, false);

        releasesTitolo = view.findViewById(R.id.novitaTitolo);
        popolariLbl = view.findViewById(R.id.popolari);
        popolariRV = view.findViewById(R.id.resultsRV);
        popolariRV.setHasFixedSize(true);
        serieLbl = view.findViewById(R.id.serieTV);
        serieRV = view.findViewById(R.id.serieRV);
        serieRV.setHasFixedSize(true);

        LinearLayoutManager ll1 = new LinearLayoutManager(this.getContext());
        ll1.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager ll2 = new LinearLayoutManager(this.getContext());
        ll2.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager ll3 = new LinearLayoutManager(this.getContext());
        ll3.setOrientation(LinearLayoutManager.HORIZONTAL);

        popolariRV.setLayoutManager(ll1);
        serieRV.setLayoutManager(ll2);

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
        releasesTitolo.setTextColor(getResources().getColor(R.color.accentColor1_Tritanopia));
        popolariLbl.setTextColor(getResources().getColor(R.color.accentColor1_Tritanopia));
        serieLbl.setTextColor(getResources().getColor(R.color.accentColor1_Tritanopia));
    }

    private void setTemaDaltonismo() {
        releasesTitolo.setTextColor(getResources().getColor(R.color.accentColor1_Daltonismo));
        popolariLbl.setTextColor(getResources().getColor(R.color.accentColor1_Daltonismo));
        serieLbl.setTextColor(getResources().getColor(R.color.accentColor1_Daltonismo));
    }

    private void setTemaProtanopia() {
        releasesTitolo.setTextColor(getResources().getColor(R.color.accentColor1_Protanopia));
        popolariLbl.setTextColor(getResources().getColor(R.color.accentColor1_Protanopia));
        serieLbl.setTextColor(getResources().getColor(R.color.accentColor1_Protanopia));
    }

    private void setTemaGreyScale() {
        releasesTitolo.setTextColor(getResources().getColor(R.color.accentColor1_GreyScale));
        popolariLbl.setTextColor(getResources().getColor(R.color.accentColor1_GreyScale));
        serieLbl.setTextColor(getResources().getColor(R.color.accentColor1_GreyScale));
    }

    private void increaseText() {
        popolariLbl.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_large));
        serieLbl.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_large));
    }

    private void setTemaDefault() {
        popolariLbl.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_dimen));
        serieLbl.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_dimen));
    }
}