package com.example.app_project_b3;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*; // Il est préférable d'importer les classes individuellement

import java.io.IOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "profsServlet", value = "/profs-servlet")
public class ProfsServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        Object isTokenValid = session.getAttribute("valid");
        if (isTokenValid != null) {
            Connection co = DatabaseConnection.myConnection();
            String query = "SELECT * from profs ORDER BY prof_last_name";
            try {
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                List<Prof> profs = new ArrayList<>();
                while (rs.next()) {
                    int id = rs.getInt("prof_id");
                    String firstName = rs.getString("prof_first_name");
                    String lastName = rs.getString("prof_last_name").toUpperCase();
                    Prof prof = new Prof(id, firstName, lastName);
                    profs.add(prof);
                }
                request.setAttribute("profs", profs);
                RequestDispatcher rd = request.getRequestDispatcher("profs.jsp");
                rd.forward(request, response);
            } catch (SQLException | ServletException e) { // deux blocs catch ?
                throw new RuntimeException(e);
            }
        } else {
            response.sendRedirect("index.jsp");
        }
    }
}
