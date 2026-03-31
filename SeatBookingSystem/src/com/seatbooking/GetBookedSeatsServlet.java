package com.seatbooking;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * GetBookedSeatsServlet.java
 * ---------------------------
 * Handles GET requests to fetch all booked seat numbers.
 * URL Mapping: /GetBookedSeats
 *
 * Returns: JSON array e.g. ["A1","B3"]
 * Used by index.html on page load to mark booked seats RED.
 */
public class GetBookedSeatsServlet extends HttpServlet {

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

            // SELECT only the seat numbers
            String sql = "SELECT seat_no FROM seats_booking";
            stmt = conn.createStatement();
            rs   = stmt.executeQuery(sql);

            // Build a JSON array manually
            StringBuilder json = new StringBuilder("[");
            boolean first = true;

            while (rs.next()) {
                if (!first) json.append(",");
                json.append("\"").append(rs.getString("seat_no")).append("\"");
                first = false;
            }
            json.append("]");

            out.print(json.toString());

        } catch (SQLException e) {
            out.print("[]"); // Return empty array on error
            e.printStackTrace();

        } finally {
            try { if (rs   != null) rs.close();   } catch (SQLException ex) { ex.printStackTrace(); }
            try { if (stmt != null) stmt.close();  } catch (SQLException ex) { ex.printStackTrace(); }
            try { if (conn != null) conn.close();  } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }
}
