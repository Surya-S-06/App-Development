package com.example.restaurantfeedback;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class FeedbackFormActivity extends AppCompatActivity {

    // Step 1: Declare variables for each UI element
    EditText etName, etPhone, etFeedback;
    RadioGroup rgGender;
    RadioButton rbMale, rbFemale;
    RatingBar ratingFood;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_form);

        // Step 2: Connect Java variables to XML elements using findViewById
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etFeedback = findViewById(R.id.etFeedback);
        rgGender = findViewById(R.id.rgGender);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        ratingFood = findViewById(R.id.ratingFood);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Step 3: Set click listener for Submit button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Step 4: Get text from EditText fields
                String name = etName.getText().toString();
                String phone = etPhone.getText().toString();
                String feedback = etFeedback.getText().toString();
                float foodRating = ratingFood.getRating();

                // Step 5: Get selected gender from RadioGroup
                String gender = "";
                int selectedId = rgGender.getCheckedRadioButtonId();
                if (selectedId == R.id.rbMale) {
                    gender = "Male";
                } else if (selectedId == R.id.rbFemale) {
                    gender = "Female";
                }

                // Step 6: Validation - check if required fields are empty
                if (name.isEmpty()) {
                    Toast.makeText(FeedbackFormActivity.this,
                            "Please enter your name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (phone.isEmpty()) {
                    Toast.makeText(FeedbackFormActivity.this,
                            "Please enter your phone number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (gender.isEmpty()) {
                    Toast.makeText(FeedbackFormActivity.this,
                            "Please select gender", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Step 7: Create Intent to go to Page 2
                Intent intent = new Intent(FeedbackFormActivity.this,
                        FeedbackDisplayActivity.class);

                // Step 8: Send data to Page 2 using putExtra
                intent.putExtra("NAME", name);
                intent.putExtra("PHONE", phone);
                intent.putExtra("GENDER", gender);
                intent.putExtra("FOOD_RATING", foodRating);
                intent.putExtra("FEEDBACK", feedback);

                // Step 9: Start Page 2
                startActivity(intent);
            }
        });
    }
}
