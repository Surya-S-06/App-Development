package com.seatbooking;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * DeleteBookingServlet.java
 * --------------------------
 * Handles DELETE requests to remove a booking record by ID.
 * URL Mapping: /DeleteBooking?id=<booking_id>
 *
 * Used by admin.html delete buttons.
 * Returns JSON with status and message.
 */
public class DeleteBookingServlet extends HttpServlet {

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("id");
        PrintWriter out = response.getWriter();

        // Validate the id parameter
        if (idParam == null || idParam.trim().isEmpty()) {
            out.print("{\"status\":\"error\",\"message\":\"Booking ID is required.\"}");
            return;
        }

        Connection        conn = null;
        PreparedStatement stmt = null;

        try {
            int id = Integer.parseInt(idParam.trim());

            conn = DBConnection.getConnection();

            // DELETE the booking with the given id
            String sql = "DELETE FROM seats_booking WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                out.print("{\"status\":\"success\",\"message\":\"Booking deleted successfully.\"}");
            } else {
                out.print("{\"status\":\"error\",\"message\":\"Booking not found.\"}");
            }

        } catch (NumberFormatException e) {
            out.print("{\"status\":\"error\",\"message\":\"Invalid booking ID format.\"}");

        } catch (SQLException e) {
            out.print("{\"status\":\"error\",\"message\":\"Database error: " + e.getMessage() + "\"}");
            e.printStackTrace();

        } finally {
            try { if (stmt != null) stmt.close(); } catch (SQLException ex) { ex.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }

    // Handle pre-flight OPTIONS requests from browser
    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
