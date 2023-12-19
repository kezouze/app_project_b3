package com.example.app_project_b3;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "elevesServlet", value = "/eleves-servlet")
public class ElevesServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        Object isTokenValid = session.getAttribute("valid");
        if (isTokenValid != null) {
            Connection co = DatabaseConnection.myConnection();
            String query = "SELECT * from eleves ORDER BY eleve_last_name";
            try {
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                List<Eleve> eleves = new ArrayList<>();
                while (rs.next()) {
                    int id = rs.getInt("eleve_id");
                    String firstName = rs.getString("eleve_first_name");
                    String lastName = rs.getString("eleve_last_name").toUpperCase();
                    Eleve eleve = new Eleve(id, firstName, lastName);
                    eleves.add(eleve);
                }
                request.setAttribute("eleves", eleves);
                RequestDispatcher rd = request.getRequestDispatcher("eleves.jsp");
                rd.forward(request, response);
            } catch (SQLException | ServletException e) { // deux blocs catch ?
                throw new RuntimeException(e);
            }
        } else {
            response.sendRedirect("index.jsp");
        }
    }
}
