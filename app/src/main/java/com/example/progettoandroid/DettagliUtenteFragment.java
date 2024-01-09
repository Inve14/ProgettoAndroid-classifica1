package com.example.progettoandroid;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class DettagliUtenteFragment extends Fragment {

    private TextView userDetailsTextView;

    public static DettagliUtenteFragment newInstance(User user) {
        DettagliUtenteFragment fragment = new DettagliUtenteFragment();
        Bundle args = new Bundle();
        args.putParcelable("selectedUser", (Parcelable) user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dettagli_utente, container, false);
        userDetailsTextView = view.findViewById(R.id.userDetailsTextView);

        // Recupera i dati dall'argomento passato
        User selectedUser = getArguments().getParcelable("selectedUser");
        showUserDetails(selectedUser);

        // Aggiungi la gestione del clic per il bottone Indietro
        Button btnIndietro = view.findViewById(R.id.btnIndietro);
        btnIndietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Torna indietro alla MainActivity
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }

    private void showUserDetails(User selectedUser) {
        // Mostra le informazioni dell'utente nel TextView
        userDetailsTextView.setText(selectedUser.getName() + " " + selectedUser.getExperience());
    }
}
