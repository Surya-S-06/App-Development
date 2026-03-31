package com.example.shapeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroupShape;
    private RadioGroup radioGroupColor;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroupShape = findViewById(R.id.radioGroupShape);
        radioGroupColor = findViewById(R.id.radioGroupColor);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedShapeId = radioGroupShape.getCheckedRadioButtonId();
                int selectedColorId = radioGroupColor.getCheckedRadioButtonId();

                if (selectedShapeId == -1 || selectedColorId == -1) {
                    Toast.makeText(MainActivity.this, R.string.error_selection, Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton shapeButton = findViewById(selectedShapeId);
                RadioButton colorButton = findViewById(selectedColorId);

                String shape = shapeButton.getText().toString();
                String color = colorButton.getText().toString();

                Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                intent.putExtra("SHAPE", shape);
                intent.putExtra("COLOR", color);
                startActivity(intent);
            }
        });
    }
}
