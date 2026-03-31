package com.seatbooking;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * BookSeatServlet.java
 * ----------------------
 * Handles POST requests to book a seat.
 * URL Mapping: /BookSeat
 *
 * Flow:
 *  1. Read seat_no and user_name from request parameters
 *  2. Check if seat already exists in DB (SELECT)
 *  3. If exists → return error JSON
 *  4. If not → INSERT into seats_booking and return success JSON
 */
public class BookSeatServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set response type to JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Read parameters from frontend fetch request
        String seatNo   = request.getParameter("seat_no");
        String userName = request.getParameter("user_name");

        PrintWriter out = response.getWriter();

        // ── Input Validation ───────────────────────────────────────────────
        if (seatNo == null || seatNo.trim().isEmpty() ||
            userName == null || userName.trim().isEmpty()) {
            out.print("{\"status\":\"error\",\"message\":\"Seat number and name are required.\"}");
            return;
        }

        // ── Database Operations ────────────────────────────────────────────
        Connection        conn       = null;
        PreparedStatement checkStmt  = null;
        PreparedStatement insertStmt = null;
        ResultSet         rs         = null;

        try {
            conn = DBConnection.getConnection();

            // STEP 1: Check if seat is already booked
            String checkSQL = "SELECT id FROM seats_booking WHERE seat_no = ?";
            checkStmt = conn.prepareStatement(checkSQL);
            checkStmt.setString(1, seatNo.trim());
            rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Seat already booked — return error
                out.print("{\"status\":\"error\",\"message\":\"Seat " + seatNo + " is already booked!\"}");

            } else {
                // STEP 2: Insert new booking into DB
                String insertSQL = "INSERT INTO seats_booking (seat_no, user_name) VALUES (?, ?)";
                insertStmt = conn.prepareStatement(insertSQL);
                insertStmt.setString(1, seatNo.trim());
                insertStmt.setString(2, userName.trim());
                insertStmt.executeUpdate();

                out.print("{\"status\":\"success\",\"message\":\"Seat " + seatNo + " booked successfully for " + userName.trim() + "!\"}");
            }

        } catch (SQLException e) {
            // Database error
            out.print("{\"status\":\"error\",\"message\":\"Database error: " + e.getMessage() + "\"}");
            e.printStackTrace();

        } finally {
            // Always close resources to avoid connection leaks
            try { if (rs         != null) rs.close();         } catch (SQLException ex) { ex.printStackTrace(); }
            try { if (checkStmt  != null) checkStmt.close();  } catch (SQLException ex) { ex.printStackTrace(); }
            try { if (insertStmt != null) insertStmt.close(); } catch (SQLException ex) { ex.printStackTrace(); }
            try { if (conn       != null) conn.close();       } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
