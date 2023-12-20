package com.example.app_project_b3;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*; // Il est préférable d'importer les classes individuellement

import java.io.IOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "usersServlet", value = "/users-servlet")
public class UsersServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String category = request.getParameter("category");
        HttpSession session = request.getSession(false);
        Object isTokenValid = session.getAttribute("valid");
        if (isTokenValid != null) {
            Connection co = DatabaseConnection.myConnection();
            String query = "SELECT user_id, user_last_name, user_first_name from users WHERE user_status = '"+category+"' ORDER BY user_last_name";
            try {
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                List<Prof> profs = new ArrayList<>();
                while (rs.next()) {
                    int id = rs.getInt("user_id");
                    String firstName = rs.getString("user_first_name");
                    String lastName = rs.getString("user_last_name").toUpperCase();
                    Prof prof = new Prof(id, firstName.substring(0,1).toUpperCase()+ firstName.substring(1), lastName);
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
