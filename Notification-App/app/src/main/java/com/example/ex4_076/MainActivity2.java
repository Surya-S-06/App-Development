package com.example.ex4_076;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// ✅ Enhancement: Fixed navigation bugs, clean feedback toasts, disabled direct edits
public class MainActivity2 extends AppCompatActivity {

    EditText e1, e2;
    Button b, b2, b3, b4, b5;
    SQLiteDatabase db;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        b = findViewById(R.id.button2);   // First
        b2 = findViewById(R.id.button4);  // Next
        b3 = findViewById(R.id.button5);  // Last
        b4 = findViewById(R.id.button6);  // Previous
        b5 = findViewById(R.id.button7);  // Back to home

        e1 = findViewById(R.id.editTextText3);
        e2 = findViewById(R.id.editTextText4);

        db = openOrCreateDatabase("Student", MODE_PRIVATE, null);
        c = db.rawQuery("SELECT * FROM Student", null);

        if (c.getCount() == 0) {
            Toast.makeText(this, "⚠ No records found in database.", Toast.LENGTH_SHORT).show();
            e1.setText("No Data");
            e2.setText("No Data");
        } else {
            c.moveToFirst();
            updateFields();
        }

        // FIRST 
        b.setOnClickListener(v -> {
            if (c.getCount() > 0 && c.moveToFirst()) {
                updateFields();
                Toast.makeText(this, "Moved to First", Toast.LENGTH_SHORT).show();
            }
        });

        // LAST
        b3.setOnClickListener(v -> {
            if (c.getCount() > 0 && c.moveToLast()) {
                updateFields();
                Toast.makeText(this, "Moved to Last", Toast.LENGTH_SHORT).show();
            }
        });

        // NEXT
        b2.setOnClickListener(v -> {
            if (c.getCount() > 0) {
                if (c.isLast()) {
                    Toast.makeText(this, "⚠ Already at the last record", Toast.LENGTH_SHORT).show();
                } else if (c.moveToNext()) {
                    updateFields();
                }
            }
        });

        // PREVIOUS
        b4.setOnClickListener(v -> {
            if (c.getCount() > 0) {
                if (c.isFirst()) {
                    Toast.makeText(this, "⚠ Already at the first record", Toast.LENGTH_SHORT).show();
                } else if (c.moveToPrevious()) {
                    updateFields();
                }
            }
        });

        // BACK TO HOME
        b5.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
            builder.setMessage("Do you want to return to Home?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", (dialog, which) -> {
                        finish(); // Returns to MainActivity without stacking
                    })
                    .setNegativeButton("No", (dialog, which) -> dialog.dismiss());
            builder.create().show();
        });
    }

    private void updateFields() {
        if (!c.isAfterLast() && !c.isBeforeFirst()) {
            e1.setText(c.getString(0));
            e2.setText(c.getString(1));
        }
    }

    @Override
    public void onBackPressed() {
        // Re-added Alert Dialog as requested instead of standard finish()
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
        builder.setMessage("Close the records view and go back?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, which) -> finish())
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (c != null) {
            c.close();
        }
    }
}