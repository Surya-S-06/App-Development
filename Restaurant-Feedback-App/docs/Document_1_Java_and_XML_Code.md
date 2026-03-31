# Document 1: Restaurant Feedback App – Java and XML Code

## Project: Restaurant Feedback App (2 Pages)
**Language:** Java  
**IDE:** Android Studio  
**Package:** com.example.restaurantfeedback

---

## File Structure

```
app/src/main/
├── AndroidManifest.xml
├── java/com/example/restaurantfeedback/
│   ├── FeedbackFormActivity.java      (Page 1)
│   └── FeedbackDisplayActivity.java   (Page 2)
└── res/layout/
    ├── activity_feedback_form.xml     (Page 1 Layout)
    └── activity_feedback_display.xml  (Page 2 Layout)
```

---

## 1. AndroidManifest.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.restaurantfeedback">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:label="Restaurant Feedback"
        android:theme="@style/Theme.AppCompat.Light.NoActionBar">

        <!-- Page 1: Feedback Form (This is the first page that opens) -->
        <activity android:name=".FeedbackFormActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

        <!-- Page 2: Feedback Display -->
        <activity android:name=".FeedbackDisplayActivity" />

    </application>

</manifest>
```

---

## 2. FeedbackFormActivity.java (Page 1 – Java Code)

```java
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
```

---

## 3. activity_feedback_form.xml (Page 1 – XML Layout)

```xml
<?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#FFF8E1">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="20dp">

        <!-- Title -->
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Restaurant Feedback"
            android:textSize="22sp"
            android:textStyle="bold"
            android:textColor="#E65100"
            android:gravity="center"
            android:layout_marginBottom="20dp" />

        <!-- Name Label -->
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Customer Name"
            android:textSize="16sp"
            android:textColor="#333333" />

        <!-- Name Input -->
        <EditText
            android:id="@+id/etName"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Enter your name"
            android:inputType="textPersonName"
            android:layout_marginBottom="12dp" />

        <!-- Phone Label -->
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Phone Number"
            android:textSize="16sp"
            android:textColor="#333333" />

        <!-- Phone Input -->
        <EditText
            android:id="@+id/etPhone"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Enter your phone number"
            android:inputType="phone"
            android:layout_marginBottom="12dp" />

        <!-- Gender Label -->
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Gender"
            android:textSize="16sp"
            android:textColor="#333333" />

        <!-- Gender RadioGroup -->
        <RadioGroup
            android:id="@+id/rgGender"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal"
            android:layout_marginBottom="12dp">

            <RadioButton
                android:id="@+id/rbMale"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Male" />

            <RadioButton
                android:id="@+id/rbFemale"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Female" />

        </RadioGroup>

        <!-- Food Rating Label -->
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Food Rating"
            android:textSize="16sp"
            android:textColor="#333333" />

        <!-- Food Rating Stars -->
        <RatingBar
            android:id="@+id/ratingFood"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:numStars="5"
            android:stepSize="1"
            android:rating="0"
            android:layout_marginBottom="12dp" />

        <!-- Feedback Label -->
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Your Feedback"
            android:textSize="16sp"
            android:textColor="#333333" />

        <!-- Feedback Input (Multi-line) -->
        <EditText
            android:id="@+id/etFeedback"
            android:layout_width="match_parent"
            android:layout_height="100dp"
            android:hint="Enter your feedback here..."
            android:inputType="textMultiLine"
            android:gravity="top"
            android:layout_marginBottom="20dp" />

        <!-- Submit Button -->
        <Button
            android:id="@+id/btnSubmit"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="SUBMIT"
            android:backgroundTint="#E65100"
            android:textColor="#FFFFFF" />

    </LinearLayout>

</ScrollView>
```

---

## 4. FeedbackDisplayActivity.java (Page 2 – Java Code)

```java
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
```

---

## 5. activity_feedback_display.xml (Page 2 – XML Layout)

```xml
<?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#E8F5E9">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="20dp">

        <!-- Title -->
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Feedback Summary"
            android:textSize="22sp"
            android:textStyle="bold"
            android:textColor="#1B5E20"
            android:gravity="center"
            android:layout_marginBottom="10dp" />

        <!-- Thank You Message -->
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Thank you for your feedback!"
            android:textSize="16sp"
            android:textColor="#388E3C"
            android:gravity="center"
            android:layout_marginBottom="25dp" />

        <!-- Display Name -->
        <TextView
            android:id="@+id/tvName"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Name: "
            android:textSize="18sp"
            android:textColor="#333333"
            android:padding="12dp"
            android:background="#FFFFFF"
            android:layout_marginBottom="10dp" />

        <!-- Display Phone -->
        <TextView
            android:id="@+id/tvPhone"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Phone: "
            android:textSize="18sp"
            android:textColor="#333333"
            android:padding="12dp"
            android:background="#FFFFFF"
            android:layout_marginBottom="10dp" />

        <!-- Display Gender -->
        <TextView
            android:id="@+id/tvGender"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Gender: "
            android:textSize="18sp"
            android:textColor="#333333"
            android:padding="12dp"
            android:background="#FFFFFF"
            android:layout_marginBottom="10dp" />

        <!-- Display Food Rating -->
        <TextView
            android:id="@+id/tvFoodRating"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Food Rating: "
            android:textSize="18sp"
            android:textColor="#333333"
            android:padding="12dp"
            android:background="#FFFFFF"
            android:layout_marginBottom="10dp" />

        <!-- Display Feedback -->
        <TextView
            android:id="@+id/tvFeedback"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Feedback: "
            android:textSize="18sp"
            android:textColor="#333333"
            android:padding="12dp"
            android:background="#FFFFFF"
            android:layout_marginBottom="20dp" />

        <!-- Back Button -->
        <Button
            android:id="@+id/btnBack"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="BACK"
            android:backgroundTint="#1B5E20"
            android:textColor="#FFFFFF" />

    </LinearLayout>

</ScrollView>
```

---

**End of Document 1**
