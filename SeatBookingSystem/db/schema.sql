-- ============================================
--  Seat Booking System - Database Schema
--  Run this script in MySQL Workbench or CLI
-- ============================================

-- Step 1: Create the database
CREATE DATABASE IF NOT EXISTS seat_booking_db;

-- Step 2: Use the database
USE seat_booking_db;

-- Step 3: Create the bookings table
CREATE TABLE IF NOT EXISTS seats_booking (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    seat_no      VARCHAR(10)  NOT NULL,
    user_name    VARCHAR(100) NOT NULL,
    booking_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ============================================
--  Useful queries for viva demonstration
-- ============================================

-- View all bookings
-- SELECT * FROM seats_booking;

-- View only booked seat numbers
-- SELECT seat_no FROM seats_booking;

-- Count total bookings
-- SELECT COUNT(*) AS total_bookings FROM seats_booking;

-- Delete a specific booking by id
-- DELETE FROM seats_booking WHERE id = 1;
