package com.example.themoviedb.GUI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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

    private RecyclerView popolariRV, serieRV, netflixRV;
    private MediaAdapter popularAdapter, upcomingAdapter, latestAdapter;
    private TextView popolariLbl, serieLbl, netflixLbl;

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

        popolariLbl = view.findViewById(R.id.popolari);
        popolariRV = view.findViewById(R.id.popolariRV);
        popolariRV.setHasFixedSize(true);
        serieLbl = view.findViewById(R.id.serieTV);
        serieRV = view.findViewById(R.id.serieRV);
        serieRV.setHasFixedSize(true);
        netflixLbl = view.findViewById(R.id.netflix);
        netflixRV = view.findViewById(R.id.netflixRV);
        netflixRV.setHasFixedSize(true);

        LinearLayoutManager ll1 = new LinearLayoutManager(this.getContext());
        ll1.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager ll2 = new LinearLayoutManager(this.getContext());
        ll2.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager ll3 = new LinearLayoutManager(this.getContext());
        ll3.setOrientation(LinearLayoutManager.HORIZONTAL);

        popolariRV.setLayoutManager(ll1);
        serieRV.setLayoutManager(ll2);
        netflixRV.setLayoutManager(ll3);

        switch (MainActivity.tema) {
            case "Nessuno":
                setDefaultTextSize();
                break;

            case "Scala di grigi":
                setDefaultTextSize();
                setGreyScale();
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

    private void setGreyScale() {
        popolariLbl.setTextColor(getResources().getColor(R.color.black_GreyScale));
        serieLbl.setTextColor(getResources().getColor(R.color.black_GreyScale));
        netflixLbl.setTextColor(getResources().getColor(R.color.black_GreyScale));
    }

    private void increaseText() {
        popolariLbl.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_large));
        serieLbl.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_large));
        netflixLbl.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_large));
    }

    private void setDefaultTextSize() {
        popolariLbl.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_dimen));
        serieLbl.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_dimen));
        netflixLbl.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.label_dimen));
    }
}