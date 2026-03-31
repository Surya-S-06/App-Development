# 🍽️ Restaurant Feedback App

A simple 2-page Android application built with **Java** in **Android Studio**.

## 📱 About This App

This is a basic/entry-level Android app with two pages:

- **Page 1 (Feedback Form):** Collects user details — name, phone number, gender, food rating (stars), and written feedback
- **Page 2 (Feedback Display):** Shows all the submitted information in a clean summary view

## 🛠️ Technologies Used

- **Language:** Java
- **IDE:** Android Studio
- **Min SDK:** API 21 (Android 5.0)
- **Target SDK:** API 34 (Android 14)
- **Dependencies:** AndroidX AppCompat, Material Design

## 📂 Project Structure

```
RestaurantFeedback/
├── build.gradle                        (Project-level Gradle config)
├── settings.gradle                     (Project settings)
├── gradle.properties                   (Gradle properties)
├── app/
│   ├── build.gradle                    (App-level Gradle config)
│   ├── proguard-rules.pro              (ProGuard rules)
│   └── src/main/
│       ├── AndroidManifest.xml         (App configuration)
│       ├── java/com/example/restaurantfeedback/
│       │   ├── FeedbackFormActivity.java      (Page 1 - Form)
│       │   └── FeedbackDisplayActivity.java   (Page 2 - Display)
│       └── res/
│           ├── layout/
│           │   ├── activity_feedback_form.xml     (Page 1 Layout)
│           │   └── activity_feedback_display.xml  (Page 2 Layout)
│           ├── drawable/               (App icon vectors)
│           ├── mipmap-*/               (App launcher icons)
│           └── values/
│               ├── colors.xml          (Color definitions)
│               ├── strings.xml         (App strings)
│               └── themes.xml          (App theme)
└── docs/
    ├── Document_1_Java_and_XML_Code.md        (Complete source code)
    └── Document_2_Explanation_and_Design_Guide.md (Detailed explanations)
```

## 🎨 UI Elements Used

| Element | Purpose |
|---------|---------|
| EditText | Text input for name, phone, feedback |
| RadioGroup & RadioButton | Gender selection (Male/Female) |
| RatingBar | Food rating with 5 stars |
| Button | Submit and Back navigation |
| TextView | Labels and data display |
| ScrollView | Scrollable page layout |

## 🚀 How to Open in Android Studio

1. Clone this repository:
   ```
   git clone https://github.com/Surya-S-06/Restaurant-Feedback-App.git
   ```
2. Open **Android Studio**
3. Click **File → Open**
4. Select the cloned `Restaurant-Feedback-App` folder
5. Wait for Gradle sync to complete
6. Click **Run ▶** to build and test on emulator or device

## 📄 Documentation

- **[Document 1](docs/Document_1_Java_and_XML_Code.md):** Complete Java and XML source code with comments
- **[Document 2](docs/Document_2_Explanation_and_Design_Guide.md):** Detailed explanation of every element, concept, and data flow
