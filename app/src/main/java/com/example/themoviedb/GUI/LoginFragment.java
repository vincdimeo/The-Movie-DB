package com.example.themoviedb.GUI;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.themoviedb.R;


public class LoginFragment extends Fragment {

    private EditText username, password;
    private TextView registratiLbl;
    private Button loginBtn;

    public LoginFragment() {
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        registratiLbl = view.findViewById(R.id.registratiLbl);
        loginBtn = view.findViewById(R.id.loginBtn);

        registratiLbl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Alcuni campi sono vuoti", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return  view;
    }
}