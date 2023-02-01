package com.example.themoviedb.GUI;

import static android.content.Context.MODE_PRIVATE;
import static androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG;
import static androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.Executor;

import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.themoviedb.MainActivity;
import com.example.themoviedb.R;


public class LoginFragment extends Fragment {

    private EditText username, password;
    private TextView registratiLbl, oppureLbl;
    private CheckBox ricordaUsername;
    private ImageView biometricBtn;
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
        oppureLbl = view.findViewById(R.id.oppureLbl);
        ricordaUsername = view.findViewById(R.id.ricordaUsername);
        biometricBtn = view.findViewById(R.id.fingerprint);
        loginBtn = view.findViewById(R.id.mostraBtn);

        checkBioMetricSupported();

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
                else {
                    LoginUser loginUser = new LoginUser(username.getText().toString(), password.getText().toString(), getContext());
                    loginUser.execute();
                }
            }
        });

        Executor executor = ContextCompat.getMainExecutor(getContext());
        BiometricPrompt biometricPrompt = new BiometricPrompt((FragmentActivity) getContext(), executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getContext(), "Errore durante autenticazione: " + errString, Toast.LENGTH_SHORT).show();
            }

            // this method will automatically call when it is succeed verify fingerprint
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getContext(), "Autenticazione effettuata con successo" , Toast.LENGTH_SHORT).show();
            }

            // this method will automatically call when it is failed verify fingerprint
            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                //attempt not regconized fingerprint
                Toast.makeText(getContext(), "Autenticazione fallita", Toast.LENGTH_SHORT).show();
            }
        });

        biometricBtn.setOnClickListener(v -> {
            BiometricPrompt.PromptInfo.Builder promptInfo = dialogMetric();
            promptInfo.setDeviceCredentialAllowed(true);
            biometricPrompt.authenticate(promptInfo.build());
        });

        return  view;
    }

    BiometricPrompt.PromptInfo.Builder dialogMetric() {
        //Show prompt dialog
        return new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Inserisci PIN")
                .setSubtitle("Effettua l'accesso usando le credenziali del tuo dispositivo");
    }

    void checkBioMetricSupported() {
        BiometricManager manager = BiometricManager.from(getContext());

        switch (manager.canAuthenticate(BIOMETRIC_WEAK | BIOMETRIC_STRONG)) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                // App can authenticate using biometrics
                enableButton(true);
                break;

            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                // No biometric features available on this device
                enableButton(false);
                break;

            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                // Biometric features are currently unavailable
                enableButton(false);
                break;

            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                // Need register at least one finger print
                enableButton(false);
                break;

            default:
                // Unknown cause
                enableButton(false);
        }
    }

    void enableButton(boolean enable) {
        //just enable or disable button
        if (enable) {
            oppureLbl.setVisibility(View.VISIBLE);
            biometricBtn.setVisibility(View.VISIBLE);
        }
        else {
            oppureLbl.setVisibility(View.INVISIBLE);
            biometricBtn.setVisibility(View.INVISIBLE);
        }
    }

    class LoginUser extends AsyncTask<Void, Void, String> {

        private static final String SERVER_IP = "20.197.17.179";
        private static final int SERVER_PORT = 8080;
        private InputStream in;
        private BufferedWriter out;
        private Socket socket;
        private String username, password;
        private Context context;
        private String response = "";
        private ProgressDialog pdLoading;

        LoginUser(String username, String password, Context context) {
            this.username = username;
            this.password = password;
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdLoading = new ProgressDialog(context);
            pdLoading.setMessage("\tLogin in corso...");
            pdLoading.setCancelable(false);
            pdLoading.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                socket = new Socket(serverAddr, SERVER_PORT);

                in = socket.getInputStream();
                out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                sendDataToServer("Login;" + username + ";" + password + ";");

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
                byte[] buffer = new byte[1024];
                int bytesRead;

                if ((bytesRead = in.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                    response = byteArrayOutputStream.toString("UTF-8");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    socket.close();
                    in.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            pdLoading.dismiss();

            System.out.println("Server: " + result);

            if (!result.equals("Errore")) {
                if (ricordaUsername.isChecked()) {
                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("Utente", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Username", username);
                    editor.putString("Password", password);
                    editor.putBoolean("Logged", true);
                    editor.putString("Tema", result);
                    editor.commit();
                }

                Toast.makeText(context, "Login avvenuto con successo", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(context, "Si è verificato un errore durante la login", Toast.LENGTH_SHORT).show();
            }
        }

        public void sendDataToServer(final String data) {
            try {
                out.write(data);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
}