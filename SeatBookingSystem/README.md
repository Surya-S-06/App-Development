# Seat Booking System

A web-based seat booking application built using Java Servlets, JDBC, and MySQL. Provides a complete frontend for users to book seats and view real-time seat availability.

## Tech Stack
*   **Frontend:** HTML, CSS, JavaScript
*   **Backend:** Java Servlets
*   **Database:** MySQL
*   **Server:** Apache Tomcat

## Setup Instructions

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/Surya-S-06/SeatBookingSystem.git
    ```
2.  **Database Setup:**
    *   Create a MySQL database named `seat_booking_db`.
    *   Execute the `db/schema.sql` script to create the necessary tables.
    *   Update `src/com/seatbooking/DBConnection.java` with your MySQL credentials (username and password).
3.  **Project Deployment:**
    *   Open the project in an IDE like Eclipse or VS Code.
    *   Ensure the `mysql-connector-j.jar` is placed in `WebContent/WEB-INF/lib/` (download it if you haven't).
    *   Compile the Java classes and place them in `WebContent/WEB-INF/classes/`.
    *   Deploy the `WebContent` folder (or build a WAR file) to an Apache Tomcat Server (e.g., in Tomcat's `webapps` directory as `SeatBookingSystem`).
4.  **Run the Application:**
    *   Start Tomcat Server.
    *   Access the application at `http://localhost:8080/SeatBookingSystem/`.

## Features
*   **Real-time Seat Interface:** View which seats are available or booked in a dynamic UI.
*   **Booking System:** Users can book available seats.
*   **Data Persistence:** Bookings are saved securely in a MySQL database.
