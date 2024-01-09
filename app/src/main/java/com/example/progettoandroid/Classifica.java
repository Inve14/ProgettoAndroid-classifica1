package com.example.progettoandroid;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Classifica extends Fragment {

    private ProgressBar progressBar;

    public Classifica() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.classifica, container, false);

        ListView listView = view.findViewById(R.id.contactsListView);
        progressBar = view.findViewById(R.id.progressBar);

        ApiInterface apiInterface = ClassificaRetrofitClient.getRetrofitIstance().create(ApiInterface.class);
        Call<List<User>> call = apiInterface.getUserInformation("l5p8XVRmz6ApeTVeeUwK");

        progressBar.setVisibility(View.VISIBLE);

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    List<User> risposta = response.body();

                    List<String> userNames = new ArrayList<>();

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
                                            userNames.add(userData.getName() + " - " + userData.getExperience());

                                            if (userNames.size() == risposta.size()) {
                                                // Ordina la lista in base all'esperienza
                                                Collections.sort(userNames, (s1, s2) -> {
                                                    int exp1 = Integer.parseInt(s1.split(" - ")[1]);
                                                    int exp2 = Integer.parseInt(s2.split(" - ")[1]);
                                                    return Integer.compare(exp2, exp1);
                                                });

                                                // Popola la ListView direttamente senza utilizzare un adapter
                                                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_list_item_1, userNames);
                                                listView.setAdapter(arrayAdapter);

                                                listView.setOnItemClickListener((parent, view, position, id) -> {
                                                    User selectedUser = risposta.get(position);
                                                    showUserName(selectedUser.getUid());
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

    private void showUserName(int uid) {

        ApiInterface apiInterface2 = ClassificaRetrofitClient.getRetrofitIstance().create(ApiInterface.class);
        Call<UserData> callUser = apiInterface2.getUserData(uid, "l5p8XVRmz6ApeTVeeUwK");
        callUser.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                try {
                    if (response.isSuccessful()) {
                        UserData userDataName = response.body();
                        // Mostra il nome nel TextView all'interno del FrameLayout
                        TextView nameTextView = requireView().findViewById(R.id.nameTextView);
                        nameTextView.setText(userDataName.getName());
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
/*
        Button backButton = requireView().findViewById(R.id.btnIndietro);
        backButton.setOnClickListener(v -> {
            // Nascondi il FrameLayout e mostra la ListView
            requireView().findViewById(R.id.info).setVisibility(View.GONE);
            requireView().findViewById(R.id.contactsListView).setVisibility(View.VISIBLE);
        });

 */

        // Mostra il FrameLayout e nascondi la ListView
        requireView().findViewById(R.id.info).setVisibility(View.VISIBLE);
        requireView().findViewById(R.id.contactsListView).setVisibility(View.GONE);
    }
}
