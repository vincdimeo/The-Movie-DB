package com.example.themoviedb.GUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.themoviedb.Adapters.SpinnerAdapter;
import com.example.themoviedb.Classes.ClientThread;
import com.example.themoviedb.MainActivity;
import com.example.themoviedb.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView impostazioniTitolo;
    private EditText username, password;
    private Button registraBtn;
    private FloatingActionButton backBtn;
    private ProgressBar progressBar;
    private Spinner impostazioniSchermo;
    private SpinnerAdapter spinnerAdapter;
    private String tema = "";

    private ClientThread clientThread;
    private Thread thread;
    private Handler handler;

    private static final String SERVER_IP = "20.197.17.179";
    private static final int SERVER_PORT = 8080;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        impostazioniTitolo = findViewById(R.id.impostazioniTitolo);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        registraBtn = findViewById(R.id.registraBtn);
        backBtn = findViewById(R.id.backBtn);
        impostazioniSchermo = findViewById(R.id.spinner);
        impostazioniSchermo.setOnItemSelectedListener(this);
        progressBar = findViewById(R.id.progressBar);

        spinnerAdapter = new SpinnerAdapter(this);
        impostazioniSchermo.setAdapter(spinnerAdapter);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        registraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Alcuni campi sono vuoti", Toast.LENGTH_SHORT).show();
                }
                else {
                    String usernameValue = username.getText().toString();
                    String passwordValue = password.getText().toString();

                    // Connecting to server
                    clientThread = new ClientThread("Registrazione;" + usernameValue + ";" + passwordValue + ";" + tema);
                    thread = new Thread(clientThread);
                    thread.start();

                    registraBtn.setEnabled(false);
                    while (thread.isAlive() && clientThread.getResponse().equals("")) {
                        System.out.println("Loading...");
                    }
                    progressBar.setVisibility(View.GONE);

                    if (clientThread.getResponse().equals("OK")) {
                        SharedPreferences sharedPreferences = getSharedPreferences("Utente", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Username", usernameValue);
                        editor.putString("Password", passwordValue);
                        editor.putBoolean("Logged", true);
                        editor.putString("Tema", tema);
                        editor.commit();

                        Toast.makeText(RegisterActivity.this, "Registrazione avvenuta con successo", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "Si è verificato un errore durante la registrazione", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        tema = spinnerAdapter.getItem(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}