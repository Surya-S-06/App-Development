package com.example.restaurantfeedback;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FeedbackDisplayActivity extends AppCompatActivity {

    // Step 1: Declare variables for TextViews and Back button
    TextView tvName, tvPhone, tvGender, tvFoodRating, tvFeedback;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_display);

        // Step 2: Connect Java variables to XML elements
        tvName = findViewById(R.id.tvName);
        tvPhone = findViewById(R.id.tvPhone);
        tvGender = findViewById(R.id.tvGender);
        tvFoodRating = findViewById(R.id.tvFoodRating);
        tvFeedback = findViewById(R.id.tvFeedback);
        btnBack = findViewById(R.id.btnBack);

        // Step 3: Receive data from Page 1 using getIntent()
        String name = getIntent().getStringExtra("NAME");
        String phone = getIntent().getStringExtra("PHONE");
        String gender = getIntent().getStringExtra("GENDER");
        float foodRating = getIntent().getFloatExtra("FOOD_RATING", 0);
        String feedback = getIntent().getStringExtra("FEEDBACK");

        // Step 4: Display the received data in TextViews
        tvName.setText("Name: " + name);
        tvPhone.setText("Phone: " + phone);
        tvGender.setText("Gender: " + gender);
        tvFoodRating.setText("Food Rating: " + foodRating + " / 5.0");
        tvFeedback.setText("Feedback: " + feedback);

        // Step 5: Back button - go back to Page 1
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
