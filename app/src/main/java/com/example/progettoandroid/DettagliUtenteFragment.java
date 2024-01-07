package com.example.progettoandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class DettagliUtenteFragment extends Fragment {

    private TextView userDetailsTextView;

    public static DettagliUtenteFragment newInstance(String selectedUser) {
        DettagliUtenteFragment fragment = new DettagliUtenteFragment();
        Bundle args = new Bundle();
        args.putString("selectedUser", selectedUser);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dettagli_utente, container, false);
        userDetailsTextView = view.findViewById(R.id.userDetailsTextView);

        // Recupera i dati dall'argomento passato
        String selectedUser = getArguments().getString("selectedUser");
        showUserDetails(selectedUser);

        return view;
    }

    private void showUserDetails(String selectedUser) {
        // Mostra le informazioni dell'utente nel TextView
        userDetailsTextView.setText(selectedUser);
    }
}
