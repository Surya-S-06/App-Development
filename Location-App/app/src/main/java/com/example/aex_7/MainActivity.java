package com.example.aex_7;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;

// ✅ Enhancement: Status label informs user of current state
public class MainActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST = 100;

    Button btnGetLocation;
    TextView tvStatus;

    FusedLocationProviderClient fusedClient;
    LocationCallback locationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fusedClient = LocationServices.getFusedLocationProviderClient(this);
        btnGetLocation = findViewById(R.id.btnGetLocation);
        tvStatus = findViewById(R.id.tvStatus);

        btnGetLocation.setOnClickListener(v -> {
            tvStatus.setText("Checking permissions…");
            checkPermissionAndGetLocation();
        });
    }

    private void checkPermissionAndGetLocation() {
        boolean fineGranted = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;

        boolean coarseGranted = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;

        if (!fineGranted && !coarseGranted) {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    },
                    LOCATION_PERMISSION_REQUEST);
        } else {
            fetchLiveLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LOCATION_PERMISSION_REQUEST) {
            boolean anyGranted = false;
            for (int result : grantResults) {
                if (result == PackageManager.PERMISSION_GRANTED) {
                    anyGranted = true;
                    break;
                }
            }
            if (anyGranted) {
                fetchLiveLocation();
            } else {
                tvStatus.setText("❌ Permission denied. Enable in Settings.");
                Toast.makeText(this,
                        "Go to App Settings → Permissions → Location → Allow",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    private void fetchLiveLocation() {
        // ✅ Enhancement: Status updates shown live in the UI
        tvStatus.setText("⏳ Fetching your live location…");
        btnGetLocation.setEnabled(false);
        btnGetLocation.setText("Fetching…");

        LocationRequest locationRequest = new LocationRequest.Builder(
                Priority.PRIORITY_HIGH_ACCURACY,
                1000)
                .setMinUpdateIntervalMillis(500)
                .setMaxUpdates(1)
                .build();

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                fusedClient.removeLocationUpdates(locationCallback);

                // Re-enable button
                btnGetLocation.setEnabled(true);
                btnGetLocation.setText("📡  Get My Location");

                if (locationResult.getLastLocation() != null) {
                    double latitude  = locationResult.getLastLocation().getLatitude();
                    double longitude = locationResult.getLastLocation().getLongitude();
                    // ✅ Enhancement: Pass accuracy alongside coordinates
                    float accuracy   = locationResult.getLastLocation().getAccuracy();

                    tvStatus.setText("✅ Location fetched successfully!");

                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("latitude", latitude);
                    intent.putExtra("longitude", longitude);
                    intent.putExtra("accuracy", accuracy);
                    startActivity(intent);

                } else {
                    tvStatus.setText("⚠️ Could not get location. Try again.");
                }
            }
        };

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        fusedClient.requestLocationUpdates(locationRequest, locationCallback, getMainLooper());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (fusedClient != null && locationCallback != null) {
            fusedClient.removeLocationUpdates(locationCallback);
        }
    }
}
