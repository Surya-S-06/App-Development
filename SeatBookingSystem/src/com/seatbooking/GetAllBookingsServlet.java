package com.seatbooking;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * GetAllBookingsServlet.java
 * ---------------------------
 * Handles GET requests to fetch all booking records.
 * URL Mapping: /GetAllBookings
 *
 * Returns: JSON array of objects with id, seat_no, user_name, booking_time
 * Used by view.html and admin.html to populate booking tables.
 */
public class GetAllBookingsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out  = response.getWriter();
        Connection  conn = null;
        Statement   stmt = null;
        ResultSet   rs   = null;

        try {
            conn = DBConnection.getConnection();

            // SELECT all columns, latest booking first
            String sql = "SELECT id, seat_no, user_name, booking_time " +
                         "FROM seats_booking ORDER BY booking_time DESC";
            stmt = conn.createStatement();
            rs   = stmt.executeQuery(sql);

            // Build JSON array of booking objects
            StringBuilder json = new StringBuilder("[");
            boolean first = true;

            while (rs.next()) {
                if (!first) json.append(",");
                json.append("{")
                    .append("\"id\":"          ).append(rs.getInt("id")                   ).append(",")
                    .append("\"seat_no\":\""   ).append(rs.getString("seat_no")           ).append("\",")
                    .append("\"user_name\":\""  ).append(escapeJson(rs.getString("user_name"))).append("\",")
                    .append("\"booking_time\":\"").append(rs.getTimestamp("booking_time")  ).append("\"")
                    .append("}");
                first = false;
            }
            json.append("]");

            out.print(json.toString());

        } catch (SQLException e) {
            out.print("[]");
            e.printStackTrace();

        } finally {
            try { if (rs   != null) rs.close();   } catch (SQLException ex) { ex.printStackTrace(); }
            try { if (stmt != null) stmt.close();  } catch (SQLException ex) { ex.printStackTrace(); }
            try { if (conn != null) conn.close();  } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }

    /**
     * Escapes special characters for safe JSON output
     */
    private String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }
}
