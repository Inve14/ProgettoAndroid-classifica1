package com.example.progettoandroid;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Classifica extends Fragment implements ClassificaRecyclerViewClickListener {

    private ClassificaViewModel viewModel;
    private List<String> help;
    private int f = 1;

    public Classifica() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.classifica, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.contactsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        help = new ArrayList<>();

        ApiInterface apiInterface = ClassificaRetrofitClient.getRetrofitIstance().create(ApiInterface.class);
        Call<List<User>> call = apiInterface.getUserInformation("l5p8XVRmz6ApeTVeeUwK");

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    List<User> risposta = response.body();

                    for (User user : risposta) {

                        ApiInterface apiInterface2 = ClassificaRetrofitClient.getRetrofitIstance().create(ApiInterface.class);
                        Call<UserData> call2 = apiInterface2.getUserData(user.getUid(), "l5p8XVRmz6ApeTVeeUwK");

                        call2.enqueue(new Callback<UserData>() {
                            @Override
                            public void onResponse(Call<UserData> call, Response<UserData> response) {
                                try {
                                    if (response.isSuccessful()) {
                                        UserData userData = response.body();
                                        if (userData != null) {
                                            help.add(f + "° " + userData.getName() + " " + userData.getExperience());
                                            f++;

                                            if (f > risposta.size()) {
                                                viewModel = new ViewModelProvider(requireActivity()).get(ClassificaViewModel.class);
                                                viewModel.setHelp(help);

                                                ClassificaAdapter adapter = new ClassificaAdapter(requireContext(), viewModel, Classifica.this);
                                                recyclerView.setAdapter(adapter);
                                            }
                                        } else {
                                            Log.d("MainActivity", "onResponse: " + response.errorBody());
                                        }
                                    } else {
                                        Log.d("MainActivity", "onResponse: " + response.errorBody());
                                    }
                                } catch (Exception e) {
                                    Log.e("MainActivity", "Errore durante la gestione della risposta", e);
                                }
                            }

                            @Override
                            public void onFailure(Call<UserData> call, Throwable t) {
                                Log.d("MainActivity", "onFailure: " + t.getMessage());
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("MainActivity", "onFailure: " + t.getMessage());
            }
        });

        return view;
    }
    /*
    @Override
    public void onItemClicked(int position) {
        String selectedUser = help.get(position);
        DettagliUtenteFragment dettagliFragment = DettagliUtenteFragment.newInstance(selectedUser);

        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.info, dettagliFragment)
                .addToBackStack(null)
                .commit();
    }

     */

    @Override
    public void onItemClicked(int position) {
        String selectedUser = help.get(position);

        // Passa le informazioni dell'utente al DettagliUtenteFragment
        DettagliUtenteFragment dettagliFragment = DettagliUtenteFragment.newInstance(selectedUser);

        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.info, dettagliFragment)
                .addToBackStack(null)
                .commit();
    }
}
