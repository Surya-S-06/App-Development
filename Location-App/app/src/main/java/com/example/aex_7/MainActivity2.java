package com.example.aex_7;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

// ✅ Enhancement: Added "Copy to Clipboard" feature and accuracy display
public class MainActivity2 extends AppCompatActivity {

    TextView tvLatitude, tvLongitude, tvLink, tvAccuracy;
    Button btnCopyCoords, btnShare, btnOpenMap;

    double latitude, longitude;
    float accuracy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvLatitude   = findViewById(R.id.tvLatitude);
        tvLongitude  = findViewById(R.id.tvLongitude);
        tvLink       = findViewById(R.id.tvLink);
        tvAccuracy   = findViewById(R.id.tvAccuracy);
        btnCopyCoords = findViewById(R.id.btnCopyCoords);
        btnShare     = findViewById(R.id.btnShare);
        btnOpenMap   = findViewById(R.id.btnOpenMap);

        Intent intent = getIntent();
        latitude  = intent.getDoubleExtra("latitude", 0.0);
        longitude = intent.getDoubleExtra("longitude", 0.0);
        // ✅ Enhancement: Read accuracy from intent
        accuracy  = intent.getFloatExtra("accuracy", -1f);

        tvLatitude.setText("Latitude: " + latitude);
        tvLongitude.setText("Longitude: " + longitude);

        // ✅ Enhancement: Display accuracy in meters
        if (accuracy >= 0) {
            tvAccuracy.setText("Accuracy: ±" + Math.round(accuracy) + " meters");
        } else {
            tvAccuracy.setText("Accuracy: unknown");
        }

        String mapsLink = "https://maps.google.com/?q=" + latitude + "," + longitude;
        tvLink.setText(mapsLink);

        // ✅ Enhancement: Copy coordinates to clipboard
        btnCopyCoords.setOnClickListener(v -> {
            String coordText = latitude + ", " + longitude;
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Coordinates", coordText);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "✅ Coordinates copied to clipboard!", Toast.LENGTH_SHORT).show();
        });

        // Share via WhatsApp, SMS, Gmail, etc.
        btnShare.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My Current Location");
            shareIntent.putExtra(Intent.EXTRA_TEXT,
                    "📍 Here is my current location:\n" + mapsLink +
                    "\n\n(Accuracy: ±" + Math.round(accuracy) + "m)");
            startActivity(Intent.createChooser(shareIntent, "Share Location via"));
        });

        // Open in Google Maps or browser fallback
        btnOpenMap.setOnClickListener(v -> {
            Uri mapUri = Uri.parse(mapsLink);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            } else {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mapsLink)));
            }
        });
    }
}