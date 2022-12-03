package com.example.themoviedb.GUI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.themoviedb.R;


public class ReleasesFragment extends Fragment {

    private RecyclerView popolariRV, serieRV, netflixRV;

    public ReleasesFragment() {
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
        View view = inflater.inflate(R.layout.fragment_releases, container, false);

        popolariRV = view.findViewById(R.id.popolariRV);
        serieRV = view.findViewById(R.id.serieRV);
        netflixRV = view.findViewById(R.id.netflixRV);

        return  view;
    }
}