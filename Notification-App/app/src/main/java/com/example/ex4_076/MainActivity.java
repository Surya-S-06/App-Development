package com.example.ex4_076;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// ✅ Enhancement: Added record count, better validation, UI feedback
public class MainActivity extends AppCompatActivity {

    Button b, b2;
    EditText e1, e2;
    TextView tvRecordCount;

    SQLiteDatabase db;

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

        b = findViewById(R.id.button);
        b2 = findViewById(R.id.button3);
        e1 = findViewById(R.id.editTextText);
        e2 = findViewById(R.id.editTextText2);
        tvRecordCount = findViewById(R.id.tvRecordCount);

        db = openOrCreateDatabase("Student", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Student(Username TEXT, Password TEXT)");

        updateRecordCount();

        b.setOnClickListener(view -> {
            String user = e1.getText().toString().trim();
            String pass = e2.getText().toString().trim();

            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(MainActivity.this, "⚠ Enter all fields to save", Toast.LENGTH_SHORT).show();
                return;
            }

            // Added Alert Dialog as requested
            new android.app.AlertDialog.Builder(MainActivity.this)
                    .setTitle("Confirm Registration")
                    .setMessage("Are you sure you want to save this record into the database?")
                    .setCancelable(false)
                    .setPositiveButton("Yes, Save", (dialog, which) -> {
                        db.execSQL("INSERT INTO Student VALUES('" + user + "','" + pass + "')");
                        Toast.makeText(MainActivity.this, "✅ Record Inserted Successfully", Toast.LENGTH_SHORT).show();

                        e1.setText("");
                        e2.setText("");
                        
                        updateRecordCount();
                    })
                    .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                    .show();
        });

        b2.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(i);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Update count when returning from MainActivity2
        if (db != null) {
            updateRecordCount();
        }
    }

    private void updateRecordCount() {
        try {
            Cursor c = db.rawQuery("SELECT COUNT(*) FROM Student", null);
            if (c.moveToFirst()) {
                int count = c.getInt(0);
                tvRecordCount.setText("Total records in database: " + count);
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}