package com.example.shapeapp;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        String shape = getIntent().getStringExtra("SHAPE");
        String colorName = getIntent().getStringExtra("COLOR");

        int colorCode = Color.BLACK; // Default
        if (colorName != null) {
            switch (colorName) {
                case "Red":
                    colorCode = Color.RED;
                    break;
                case "Blue":
                    colorCode = Color.BLUE;
                    break;
                case "Green":
                    colorCode = Color.GREEN;
                    break;
                case "Yellow":
                    colorCode = Color.YELLOW;
                    break;
            }
        }

        FrameLayout container = findViewById(R.id.shapeContainer);
        ShapeView shapeView = new ShapeView(this, shape, colorCode);
        container.addView(shapeView);
    }
}
