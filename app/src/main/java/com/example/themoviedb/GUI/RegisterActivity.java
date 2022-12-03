package com.example.themoviedb.GUI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.themoviedb.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RegisterActivity extends AppCompatActivity {

    private EditText username, password;
    private String tipologia;
    private Button registraBtn;
    private FloatingActionButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        registraBtn = findViewById(R.id.registraBtn);
        backBtn = findViewById(R.id.backBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.protan:
                if (checked)
                    tipologia = "Protan";
                    break;

            case R.id.deutan:
                if (checked)
                    tipologia = "Deutan";
                    break;

            case R.id.acrimatico:
                if (checked)
                    tipologia = "Acrimatico";
                break;

            case R.id.ipermetropia:
                if (checked)
                    tipologia = "Ipermetropia";
                break;
        }
    }
}