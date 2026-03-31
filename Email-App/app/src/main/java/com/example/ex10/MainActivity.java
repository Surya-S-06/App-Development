package com.example.ex10;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// ✅ Enhancement: Added CC field, email format validation, clear button
public class MainActivity extends AppCompatActivity {

    EditText e1, e2, e3, eCC;
    Button b, btnClear;
    TextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize all views
        e1       = findViewById(R.id.editTextText4);    // To
        eCC      = findViewById(R.id.editTextCC);       // CC (new field)
        e2       = findViewById(R.id.editTextText6);    // Subject
        e3       = findViewById(R.id.editTextText7);    // Message
        b        = findViewById(R.id.button2);          // Send
        btnClear = findViewById(R.id.btnClear);         // Clear (new)
        tvError  = findViewById(R.id.tvError);          // Validation label (new)

        // ✅ Enhancement: Clear button resets all fields
        btnClear.setOnClickListener(v -> {
            e1.setText("");
            eCC.setText("");
            e2.setText("");
            e3.setText("");
            tvError.setText("");
            Toast.makeText(this, "Form cleared", Toast.LENGTH_SHORT).show();
        });

        b.setOnClickListener(v -> {
            String to      = e1.getText().toString().trim();
            String cc      = eCC.getText().toString().trim();
            String subject = e2.getText().toString().trim();
            String message = e3.getText().toString().trim();

            // ✅ Enhancement: Validate required fields and email format
            if (to.isEmpty()) {
                tvError.setText("⚠ Recipient email is required.");
                e1.requestFocus();
                return;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(to).matches()) {
                tvError.setText("⚠ Enter a valid recipient email address.");
                e1.requestFocus();
                return;
            }
            if (!cc.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(cc).matches()) {
                tvError.setText("⚠ Enter a valid CC email address (or leave blank).");
                eCC.requestFocus();
                return;
            }
            if (subject.isEmpty()) {
                tvError.setText("⚠ Subject is required.");
                e2.requestFocus();
                return;
            }
            if (message.isEmpty()) {
                tvError.setText("⚠ Message cannot be empty.");
                e3.requestFocus();
                return;
            }

            // All valid — clear error
            tvError.setText("");

            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
            // ✅ Enhancement: Include CC if provided
            if (!cc.isEmpty()) {
                emailIntent.putExtra(Intent.EXTRA_CC, new String[]{cc});
            }
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
            emailIntent.putExtra(Intent.EXTRA_TEXT, message);
            emailIntent.setType("message/rfc822");

            try {
                startActivity(Intent.createChooser(emailIntent, "Send mail via…"));
                Log.i("EmailApp", "Email compose intent launched.");
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(MainActivity.this,
                        "No email app installed.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}