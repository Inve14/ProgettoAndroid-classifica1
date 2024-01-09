package com.example.progettoandroid;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Classifica extends Fragment {

    private ClassificaViewModel viewModel;
    private List<String> help;
    private int f = 1;
    private ProgressBar progressBar;

    public Classifica() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.classifica, container, false);

        ListView listView = view.findViewById(R.id.contactsListView);
        progressBar = view.findViewById(R.id.progressBar);

        help = new ArrayList<>();

        ApiInterface apiInterface = ClassificaRetrofitClient.getRetrofitIstance().create(ApiInterface.class);
        Call<List<User>> call = apiInterface.getUserInformation("l5p8XVRmz6ApeTVeeUwK");

        progressBar.setVisibility(View.VISIBLE);

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                progressBar.setVisibility(View.GONE);
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

                                                // Utilizza un ArrayAdapter per popolare la ListView
                                                ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, help);
                                                listView.setAdapter(adapter);

                                                // Aggiungi l'OnClickListener per gestire il clic sugli elementi
                                                listView.setOnItemClickListener((parent, view, position, id) -> {
                                                    User selectedUser = risposta.get(position);
                                                    Log.d("MainActivity", "onItemClick: " + selectedUser);
                                                    avviaDettagliActivity(selectedUser);
                                                });
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
                } else {
                    progressBar.setVisibility(View.GONE);
                    Log.d("MainActivity", "onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.d("MainActivity", "onFailure: " + t.getMessage());
            }
        });

        return view;
    }

    private void avviaDettagliActivity(User user) {
        // Avvia DettagliUtenteFragment
        DettagliUtenteFragment dettagliFragment = DettagliUtenteFragment.newInstance(user);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.info, dettagliFragment)
                .addToBackStack(null)
                .commit();
    }
}

