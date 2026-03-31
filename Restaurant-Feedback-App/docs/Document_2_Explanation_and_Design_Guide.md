# Document 2: Restaurant Feedback App – Explanation Guide

## Project: Restaurant Feedback App
**Language:** Java  
**IDE:** Android Studio  
**Level:** Basic / Entry Level

---

## 1. How to Create This Project in Android Studio

### Steps:

1. Open **Android Studio**
2. Click **"New Project"**
3. Select **"Empty Activity"** → Click Next
4. Fill in:
   - Name: `RestaurantFeedback`
   - Language: **Java**
   - Minimum SDK: API 21
5. Click **Finish**
6. Rename `MainActivity.java` → `FeedbackFormActivity.java`
7. Rename `activity_main.xml` → `activity_feedback_form.xml`
8. Create second activity: Right-click package → **New → Activity → Empty Activity** → Name it `FeedbackDisplayActivity`
9. Copy the code from Document 1 into each file
10. Click **Run** to test the app

---

## 2. What Does This App Do?

This is a simple 2-page app:

```
PAGE 1 (Form)               PAGE 2 (Display)
┌──────────────────┐        ┌──────────────────┐
│ Enter Name       │        │ Name: John       │
│ Enter Phone      │ SUBMIT │ Phone: 12345     │
│ Select Gender    │ ─────► │ Gender: Male     │
│ Rate Food ★★★★☆  │        │ Food Rating: 4.0 │
│ Enter Feedback   │        │ Feedback: Good!  │
│ [SUBMIT]         │        │ [BACK]           │
└──────────────────┘        └──────────────────┘
```

- **Page 1**: User fills a form (name, phone, gender, food rating, feedback)
- User clicks **SUBMIT** → goes to Page 2
- **Page 2**: Shows what the user entered + a **BACK** button to return

---

## 3. What is an Element?

An element is a UI component (like a button, text box, etc.) that you place on the screen.

In Android, every element has two parts:
- **XML** (how it looks) → defined in `.xml` layout files
- **Java** (what it does) → controlled in `.java` files

---

## 4. Page 1 Elements – Feedback Form

### Element 1: ScrollView

```xml
<ScrollView
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <!-- Everything goes inside here -->
</ScrollView>
```

- **What is it?** A container that allows the page to scroll up and down
- **Why we use it:** When there are many input fields, the screen may not fit everything. ScrollView lets the user scroll down to see more content
- **Important rule:** ScrollView can have ONLY ONE child element inside it (we put a LinearLayout as that one child)
- **Key attributes:**
  - `match_parent` = take the full width and height of the screen

---

### Element 2: LinearLayout

```xml
<LinearLayout
    android:orientation="vertical"
    android:padding="20dp">
```

- **What is it?** A container that arranges items in a line (vertical or horizontal)
- **Why we use it:** We set `orientation="vertical"` so all items appear one below the other (top to bottom)
- **Key attributes:**
  - `orientation="vertical"` → stack items top to bottom
  - `padding="20dp"` → adds space inside the container from all edges

---

### Element 3: TextView

```xml
<TextView
    android:text="Customer Name"
    android:textSize="16sp"
    android:textColor="#333333" />
```

- **What is it?** Displays text on screen. User CANNOT type in it
- **Why we use it:** To show labels like "Customer Name", "Phone Number"
- **Key attributes:**
  - `android:text` → the text to show
  - `android:textSize="16sp"` → size of text (sp = scalable pixels)
  - `android:textColor="#333333"` → dark grey color
  - `android:textStyle="bold"` → makes text bold
  - `android:gravity="center"` → centers the text

---

### Element 4: EditText

```xml
<EditText
    android:id="@+id/etName"
    android:hint="Enter your name"
    android:inputType="textPersonName" />
```

- **What is it?** A text box where user CAN type
- **Why we use it:** To collect name, phone number, and feedback from user
- **Key attributes:**
  - `android:id="@+id/etName"` → unique ID (used to find it in Java)
  - `android:hint` → grey placeholder text when box is empty
  - `android:inputType` → controls the keyboard:
    - `textPersonName` → normal keyboard
    - `phone` → number keyboard
    - `textMultiLine` → allows typing multiple lines
- **In Java:** Get typed text: `etName.getText().toString()`

---

### Element 5: RadioGroup

```xml
<RadioGroup
    android:id="@+id/rgGender"
    android:orientation="horizontal">
```

- **What is it?** A container that groups RadioButtons together
- **Why we use it:** Ensures user can select ONLY ONE option (Male or Female)
- **Key attribute:** `orientation="horizontal"` → shows options side by side
- **In Java:** Check which is selected: `rgGender.getCheckedRadioButtonId()`

---

### Element 6: RadioButton

```xml
<RadioButton
    android:id="@+id/rbMale"
    android:text="Male" />
```

- **What is it?** A circular selectable button
- **Why we use it:** User taps to choose Male or Female
- **Rule:** When inside a RadioGroup, only ONE RadioButton can be selected

---

### Element 7: RatingBar

```xml
<RatingBar
    android:id="@+id/ratingFood"
    android:numStars="5"
    android:stepSize="1"
    android:rating="0" />
```

- **What is it?** A row of stars that user taps to give a rating
- **Why we use it:** To rate food quality (1 to 5 stars)
- **Key attributes:**
  - `android:numStars="5"` → shows 5 stars
  - `android:stepSize="1"` → rating goes by whole numbers (1, 2, 3, 4, 5)
  - `android:rating="0"` → starts at 0 (no stars selected)
- **In Java:** Get rating value: `ratingFood.getRating()` (returns 0.0 to 5.0)

---

### Element 8: Button

```xml
<Button
    android:id="@+id/btnSubmit"
    android:text="SUBMIT"
    android:backgroundTint="#E65100"
    android:textColor="#FFFFFF" />
```

- **What is it?** A clickable button
- **Why we use it:** When tapped, it collects data and goes to Page 2
- **Key attributes:**
  - `android:text` → text on the button
  - `android:backgroundTint` → button background color
  - `android:textColor` → text color on button
- **In Java:** Handle clicks with:
  ```java
  btnSubmit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          // code runs when clicked
      }
  });
  ```

---

## 5. Page 2 Elements – Feedback Display

### Element 1: ScrollView
- Same as Page 1 – wraps everything so the page can scroll

### Element 2: LinearLayout
- Same as Page 1 – arranges items vertically
- Different background color: `#E8F5E9` (light green)

### Element 3: TextView (Display data)

```xml
<TextView
    android:id="@+id/tvName"
    android:text="Name: "
    android:textSize="18sp"
    android:padding="12dp"
    android:background="#FFFFFF" />
```

- **What it does here:** Shows the data received from Page 1
- **In Java:** We set the text: `tvName.setText("Name: " + name)`
- `android:background="#FFFFFF"` → white background (looks like a card)

### Element 4: Button (Back)

```xml
<Button
    android:id="@+id/btnBack"
    android:text="BACK"
    android:backgroundTint="#1B5E20"
    android:textColor="#FFFFFF" />
```

- **What it does:** Takes the user back to Page 1
- **In Java:** We call `finish()` which closes the current page and goes back:
  ```java
  btnBack.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          finish();  // close this page, go back
      }
  });
  ```

---

## 6. Java Concepts Explained

### Concept 1: Activity
- **Activity** = one screen in the app
- Our app has **2 Activities** = 2 pages
- Each Activity has a `.java` file (logic) and an `.xml` file (layout)

### Concept 2: findViewById()
- Connects Java variable to XML element using the ID
  ```java
  etName = findViewById(R.id.etName);
  ```
- After this line, `etName` in Java refers to the EditText in XML

### Concept 3: Intent (Going to Page 2)
- **Intent** = a message to go from one page to another
  ```java
  Intent intent = new Intent(FeedbackFormActivity.this, FeedbackDisplayActivity.class);
  intent.putExtra("NAME", name);    // attach data
  startActivity(intent);            // go to Page 2
  ```

### Concept 4: Receiving Data (on Page 2)
- Page 2 reads data sent from Page 1:
  ```java
  String name = getIntent().getStringExtra("NAME");
  float foodRating = getIntent().getFloatExtra("FOOD_RATING", 0);
  ```
- `getStringExtra()` → for text data
- `getFloatExtra()` → for decimal numbers (like rating 4.0)

### Concept 5: Toast
- Shows a small popup message at bottom of screen
  ```java
  Toast.makeText(FeedbackFormActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
  ```

### Concept 6: finish()
- Closes the current Activity and goes back to the previous one
  ```java
  finish();  // goes back to Page 1
  ```

---

## 7. Complete Element Summary Table

### Page 1 – Feedback Form (9 elements)

| # | Element | ID | What It Does |
|---|---------|-----|-------------|
| 1 | ScrollView | — | Makes the page scrollable |
| 2 | LinearLayout | — | Arranges items vertically |
| 3 | TextView | — | Shows labels (title, field names) |
| 4 | EditText | etName | User enters their name |
| 5 | EditText | etPhone | User enters phone number |
| 6 | RadioGroup | rgGender | Groups gender options |
| 7 | RadioButton | rbMale | Select "Male" option |
| 8 | RadioButton | rbFemale | Select "Female" option |
| 9 | RatingBar | ratingFood | User rates food (1-5 stars) |
| 10 | EditText | etFeedback | User types feedback (multi-line) |
| 11 | Button | btnSubmit | Sends data to Page 2 |

### Page 2 – Feedback Display (9 elements)

| # | Element | ID | What It Does |
|---|---------|-----|-------------|
| 1 | ScrollView | — | Makes the page scrollable |
| 2 | LinearLayout | — | Arranges items vertically |
| 3 | TextView | — | Shows title and thank you message |
| 4 | TextView | tvName | Displays customer name |
| 5 | TextView | tvPhone | Displays phone number |
| 6 | TextView | tvGender | Displays gender |
| 7 | TextView | tvFoodRating | Displays food rating (e.g., 4.0/5.0) |
| 8 | TextView | tvFeedback | Displays feedback text |
| 9 | Button | btnBack | Goes back to Page 1 |

---

## 8. How Data Flows Between Pages

```
PAGE 1                              PAGE 2
(FeedbackFormActivity)              (FeedbackDisplayActivity)

User types name       ─┐
User types phone      ─┤  putExtra()     getStringExtra()
User selects gender   ─┤  ──────────►    getFloatExtra()
User rates food       ─┤   Intent        ──────────►  Show in TextViews
User types feedback   ─┘
                     startActivity()

                                         User clicks BACK
                                         ◄──────────
                                         finish()
```

---

## 9. How User Details Are Displayed on Page 2 (Step-by-Step)

When the user fills the form on **Page 1** and clicks **SUBMIT**, here is exactly what happens to show their details on **Page 2**:

### Step 1: User Fills the Form (Page 1)

The user enters the following information:
- **Name** → typed in `EditText` (id: `etName`)
- **Phone Number** → typed in `EditText` (id: `etPhone`)
- **Gender** → selected from `RadioButton` (Male / Female)
- **Food Rating** → tapped on `RatingBar` stars (1 to 5)
- **Feedback** → typed in `EditText` (id: `etFeedback`)

### Step 2: Collect Data from Form Fields (Page 1 — Java)

When the user clicks SUBMIT, the Java code reads every field:

```java
// Read text from EditText fields
String name = etName.getText().toString();       // Gets "John"
String phone = etPhone.getText().toString();     // Gets "9876543210"
String feedback = etFeedback.getText().toString(); // Gets "Great food!"
float foodRating = ratingFood.getRating();       // Gets 4.0

// Read gender from RadioGroup
String gender = "";
int selectedId = rgGender.getCheckedRadioButtonId();
if (selectedId == R.id.rbMale) {
    gender = "Male";
} else if (selectedId == R.id.rbFemale) {
    gender = "Female";
}
```

### Step 3: Pack Data into Intent (Page 1 — Java)

An **Intent** is like a package/envelope that carries data from one page to another:

```java
// Create an Intent (envelope) to go to Page 2
Intent intent = new Intent(FeedbackFormActivity.this, FeedbackDisplayActivity.class);

// Put each piece of data into the Intent using putExtra()
// putExtra("KEY", value) — KEY is a label to identify the data
intent.putExtra("NAME", name);              // Pack name
intent.putExtra("PHONE", phone);            // Pack phone
intent.putExtra("GENDER", gender);          // Pack gender
intent.putExtra("FOOD_RATING", foodRating); // Pack rating
intent.putExtra("FEEDBACK", feedback);      // Pack feedback

// Send the Intent and open Page 2
startActivity(intent);
```

**Think of it like this:**
```
┌─────────────────────────────────────┐
│           INTENT (Envelope)         │
│                                     │
│   "NAME"        → "John"           │
│   "PHONE"       → "9876543210"     │
│   "GENDER"      → "Male"          │
│   "FOOD_RATING" → 4.0             │
│   "FEEDBACK"    → "Great food!"   │
│                                     │
│   DESTINATION: FeedbackDisplayActivity │
└─────────────────────────────────────┘
```

### Step 4: Receive Data on Page 2 (Page 2 — Java)

Page 2 opens and unpacks the data from the Intent using `getIntent()`:

```java
// Unpack each piece of data using the SAME KEY used in putExtra
String name = getIntent().getStringExtra("NAME");           // Gets "John"
String phone = getIntent().getStringExtra("PHONE");         // Gets "9876543210"
String gender = getIntent().getStringExtra("GENDER");       // Gets "Male"
float foodRating = getIntent().getFloatExtra("FOOD_RATING", 0); // Gets 4.0
String feedback = getIntent().getStringExtra("FEEDBACK");   // Gets "Great food!"
```

**Important:** The KEY must match exactly!
- Page 1 sends: `putExtra("NAME", name)` → KEY is `"NAME"`
- Page 2 receives: `getStringExtra("NAME")` → same KEY `"NAME"`

### Step 5: Connect TextViews Using findViewById (Page 2 — Java)

Before we can display data, we connect Java variables to the XML TextViews:

```java
// Connect each Java variable to its XML element
tvName = findViewById(R.id.tvName);           // → <TextView android:id="@+id/tvName" />
tvPhone = findViewById(R.id.tvPhone);         // → <TextView android:id="@+id/tvPhone" />
tvGender = findViewById(R.id.tvGender);       // → <TextView android:id="@+id/tvGender" />
tvFoodRating = findViewById(R.id.tvFoodRating); // → <TextView android:id="@+id/tvFoodRating" />
tvFeedback = findViewById(R.id.tvFeedback);   // → <TextView android:id="@+id/tvFeedback" />
```

### Step 6: Display Data in TextViews (Page 2 — Java)

Finally, we use `setText()` to show the data on screen:

```java
tvName.setText("Name: " + name);                  // Shows → "Name: John"
tvPhone.setText("Phone: " + phone);               // Shows → "Phone: 9876543210"
tvGender.setText("Gender: " + gender);            // Shows → "Gender: Male"
tvFoodRating.setText("Food Rating: " + foodRating + " / 5.0"); // Shows → "Food Rating: 4.0 / 5.0"
tvFeedback.setText("Feedback: " + feedback);      // Shows → "Feedback: Great food!"
```

### Complete Visual Flow

```
 PAGE 1 (Form)                                    PAGE 2 (Display)
 ┌──────────────────┐                              ┌──────────────────────────┐
 │ Name: [John    ] │                              │    Feedback Summary      │
 │ Phone:[98765.. ] │    ┌──────────────────┐      │  Thank you for your      │
 │ Gender: (•)Male  │───►│  INTENT carries: │───►  │      feedback!           │
 │ Rating: ★★★★☆   │    │  NAME = John     │      │                          │
 │ Feedback:        │    │  PHONE = 98765.. │      │ ┌──────────────────────┐ │
 │ [Great food!   ] │    │  GENDER = Male   │      │ │ Name: John           │ │
 │                  │    │  RATING = 4.0    │      │ │ Phone: 9876543210    │ │
 │ [   SUBMIT    ] │    │  FEEDBACK = ...  │      │ │ Gender: Male         │ │
 └──────────────────┘    └──────────────────┘      │ │ Food Rating: 4.0/5.0│ │
                                                    │ │ Feedback: Great food!│ │
      Java Code:              Java Code:           │ └──────────────────────┘ │
   getText()              getStringExtra()         │                          │
   putExtra()             setText()                │ [       BACK           ] │
   startActivity()                                 └──────────────────────────┘
```

### Summary of Methods Used

| Method | Used In | Purpose |
|--------|---------|---------|
| `getText().toString()` | Page 1 | Read what user typed in EditText |
| `getRating()` | Page 1 | Read star rating from RatingBar |
| `getCheckedRadioButtonId()` | Page 1 | Check which RadioButton is selected |
| `putExtra("KEY", value)` | Page 1 | Attach data to Intent |
| `startActivity(intent)` | Page 1 | Open Page 2 with the data |
| `getStringExtra("KEY")` | Page 2 | Retrieve text data from Intent |
| `getFloatExtra("KEY", 0)` | Page 2 | Retrieve decimal number from Intent |
| `findViewById(R.id.xxx)` | Page 2 | Connect Java variable to XML element |
| `setText("text")` | Page 2 | Display text in a TextView |
| `finish()` | Page 2 | Close Page 2 and go back to Page 1 |

---

## 10. AndroidManifest.xml Explained

- **Every Activity must be listed** in AndroidManifest.xml
- `MAIN` + `LAUNCHER` intent-filter on Page 1 = this page opens first
- `android:icon="@mipmap/ic_launcher"` = the app icon shown on the phone
- `android:roundIcon="@mipmap/ic_launcher_round"` = round version of the icon
- `android:label="Restaurant Feedback"` = app name shown below the icon

---

**End of Document 2**
