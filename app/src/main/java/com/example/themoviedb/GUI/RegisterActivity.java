package com.example.themoviedb.GUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.themoviedb.Adapters.SpinnerAdapter;
import com.example.themoviedb.MainActivity;
import com.example.themoviedb.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView impostazioniTitolo;
    private EditText username, password;
    private Button registraBtn;
    private FloatingActionButton backBtn;
    private Spinner impostazioniSchermo;
    private SpinnerAdapter spinnerAdapter;
    private String tema = "";

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
                    // Aggiungere inserimento utente sul db
                    SharedPreferences sharedPreferences = getSharedPreferences("Utente", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Username", username.getText().toString());
                    editor.putString("Password", password.getText().toString());
                    editor.putBoolean("Logged", true);
                    editor.putString("Tema", tema);
                    editor.commit();

                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
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