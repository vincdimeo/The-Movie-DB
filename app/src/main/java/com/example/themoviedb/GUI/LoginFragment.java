package com.example.themoviedb.GUI;

import static androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG;
import static androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
                    // Controllo credenziali sul db
                    /*
                    if (ricordaUsername.isChecked()) {
                        // Memorizza username in SharedPreferences
                    }
                    */
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
}