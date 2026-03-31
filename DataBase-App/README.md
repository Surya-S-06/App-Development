# Student Database Management System

A lightweight Android application demonstrating SQLite database operations. This project showcases CRUD (Create, Read, Update, Delete) functionality with a multi-screen interface for managing student records.

## Features

- ✅ User registration with username and password
- ✅ SQLite database integration for persistent data storage
- ✅ Navigate through stored records (First, Last, Next, Previous)
- ✅ Clean, intuitive UI with input fields and navigation controls
- ✅ Multi-activity navigation pattern
- ✅ Toast notifications for user feedback

## Technologies Used

- **Language:** Java
- **Platform:** Android
- **Database:** SQLite
- **UI Framework:** AndroidX, Material Design
- **Layout:** ConstraintLayout with LinearLayout
- **IDE:** Android Studio

## Project Structure

```
app/src/main/
├── java/com/example/ex1_76/
│   ├── MainActivity.java       # User registration screen
│   └── MainActivity2.java      # Student records browser
├── res/
│   ├── layout/                 # XML layout files
│   │   ├── activity_main.xml   # Registration form
│   │   └── activity_main2.xml  # Records navigation
│   ├── values/                 # Resources (strings, colors, IDs)
│   └── drawable/               # Icons and images
└── AndroidManifest.xml         # App manifest
```

## Screens

### 1. **Registration Screen** (MainActivity)
- Input fields for username and password
- Insert button to save student records to database
- Navigation button to proceed to records browser

### 2. **Records Browser** (MainActivity2)
- Display student records from database
- Navigation controls:
  - **First:** Jump to first record
  - **Last:** Jump to last record
  - **Next:** Move to next record
  - **Previous:** Move to previous record
- Back button to return to registration screen

## Getting Started

### Prerequisites
- Android Studio (Arctic Fox or later)
- Android SDK 21 or higher
- Java 8+

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/Surya-S-06/DataBase-App.git
   cd DataBase-App
   ```

2. Open in Android Studio:
   - File → Open → Select project folder
   - Wait for Gradle build to complete

3. Run the app:
   - Connect an Android device or start an emulator
   - Click "Run" or press Shift+F10

## Usage

1. **Register a Student:**
   - Enter username and password
   - Click "Insert" button
   - Confirm with toast notification

2. **Browse Records:**
   - Click "Next Activity" button
   - Use navigation buttons to browse through stored records
   - View student details in the text fields

## Database Schema

```sql
CREATE TABLE Student (
    Username TEXT,
    Password TEXT
)
```

## Author

- **Surya S** - [GitHub Profile](https://github.com/Surya-S-06)

## License

This project is open source and available under the MIT License.

## Contributing

Contributions are welcome! Feel free to fork this repository and submit pull requests.

## Notes

- This is an educational project demonstrating Android fundamentals
- For production use, consider implementing:
  - Proper authentication mechanisms
  - Password encryption
  - Room Database for better data persistence
  - Input validation and error handling
  - Modern MVVM architecture

---

**Last Updated:** March 2026
