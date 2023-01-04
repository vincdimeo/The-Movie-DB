package com.example.themoviedb.GUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.themoviedb.Adapters.SpinnerAdapter;
import com.example.themoviedb.MainActivity;
import com.example.themoviedb.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView impostazioniTitolo;
    private EditText username, password;
    private Button registraBtn;
    private FloatingActionButton backBtn;
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

        spinnerAdapter = new SpinnerAdapter(this);
        impostazioniSchermo.setAdapter(spinnerAdapter);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        handler = new Handler();

        // Connecting to server
        clientThread = new ClientThread();
        thread = new Thread(clientThread);
        thread.start();

        registraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Alcuni campi sono vuoti", Toast.LENGTH_SHORT).show();
                }
                else {
                    String usernameValue = username.getText().toString();
                    String passwordValue = password.getText().toString();

                    // Aggiungere inserimento utente sul db
                    SharedPreferences sharedPreferences = getSharedPreferences("Utente", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Username", usernameValue);
                    editor.putString("Password", passwordValue);
                    editor.putBoolean("Logged", true);
                    editor.putString("Tema", tema);
                    editor.commit();

                    clientThread.sendMessage("Registrazione;" + usernameValue + ";" + passwordValue + ";" + tema);
                    Toast.makeText(RegisterActivity.this, "Registrazione avvenuta con successo", Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    // startActivity(intent);
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

    class ClientThread implements Runnable {

        private final String SERVER_IP = "20.197.17.179";
        private final int SERVER_PORT = 8080;
        private Socket socket;
        private BufferedReader input;
        private PrintWriter out;

        @Override
        public void run() {
            try {
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                socket = new Socket(serverAddr, SERVER_PORT);
            }
            catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        void sendMessage(final String message) {
            new Thread(() -> {
                try {
                    if (null != socket) {
                        out = new PrintWriter(new BufferedWriter(
                                new OutputStreamWriter(socket.getOutputStream())),
                                true);
                        out.println(message);
                        socket.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}