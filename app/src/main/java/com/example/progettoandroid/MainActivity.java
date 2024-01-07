package com.example.progettoandroid;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.location.CurrentLocationRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.tasks.Task;

import android.Manifest;




import java.util.Map;




public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            ImageButton btnRank = findViewById(R.id.info);
            ImageButton btnMap = findViewById(R.id.map);
            ImageButton btnProfile = findViewById(R.id.profile);

            btnRank.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm = getSupportFragmentManager();
                    fm.beginTransaction()
                            .replace(R.id.fragmentContainerView, new Classifica())
                            .setReorderingAllowed(true)
                            .addToBackStack(null)
                            .commit();
                }
            });

            btnMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm = getSupportFragmentManager();
                    fm.beginTransaction()
                            .replace(R.id.fragmentContainerView, new Mappa())
                            .setReorderingAllowed(true)
                            .addToBackStack(null)
                            .commit();
                }
            });

            btnProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm = getSupportFragmentManager();
                    fm.beginTransaction()
                            .replace(R.id.fragmentContainerView, new Profilo())
                            .setReorderingAllowed(true)
                            .addToBackStack(null)
                            .commit();
                }
            });


            //CODICE PER LA MAPPA

            if(ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION

            )== PackageManager.PERMISSION_GRANTED) {
                // permessi garantiti
                requestPosition();
            }else{
                ActivityResultLauncher<String[]> locationPermissionRequest =
                        registerForActivityResult(new ActivityResultContracts
                                        .RequestMultiplePermissions(),
                                (result) -> onPermissionRequestResult(result)
                        );
                locationPermissionRequest.launch(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                });

            }
    }

    private void onPermissionRequestResult(Map<String, Boolean> result) {
        Log.d("fil", "onPermissionRequestResult");
        Boolean fineLocationGranted = result.getOrDefault(
                Manifest.permission.ACCESS_FINE_LOCATION,
                false
        );
        Boolean coarseLocationGranted = result.getOrDefault(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                false
        );
        if (fineLocationGranted != null && fineLocationGranted) {
            // Precise location access granted
            Log.d("fil", "onPermissionRequestResult: fineLocationGranted");
            requestPosition();
        } else if (coarseLocationGranted != null && coarseLocationGranted) {
            // Only approximate location access granted.
            Log.d("fil", "onPermissionRequestResult: coarseLocationGranted");
            requestPosition();
        } else {
            // No location access granted.
            Log.d("fil", "Permessi necessari per accedere alla posizione");
        }
    }

    @SuppressLint("MissingPermission")
    private void requestPosition() {

        CurrentLocationRequest clr = new CurrentLocationRequest.Builder().setPriority(Priority.PRIORITY_HIGH_ACCURACY).build();
        FusedLocationProviderClient fusedLocationClient;
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        Task<Location> task = fusedLocationClient.getCurrentLocation(clr, null);
        task.addOnSuccessListener(
                location -> {
                    Log.d("filippo", "posizione ricevuta: ");
                    if (location != null) {
                        Log.d("filippo", "Location: " + location.toString());
                    }
                }
        );

    }
}