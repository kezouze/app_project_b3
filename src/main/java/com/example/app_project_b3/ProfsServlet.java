package com.example.app_project_b3;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*; // Il est préférable d'importer les classes individuellement

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.*;

@WebServlet(name = "profsServlet", value = "/profs-servlet")
public class ProfsServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String query = "SELECT * from profs";
        Connection co = DatabaseConnection.myConnection();
        try {
            PreparedStatement ps = co.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            out.println("<html><body><h1>Liste des professeurs</h1><ul>");
            while(rs.next()){
                String firstName = rs.getString("prof_first_name");
                String lastName = rs.getString("prof_last_name");
                out.println("<li>" + firstName+ " "+ lastName + "</li>" );
            }
            out.println("</ul></html></body>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
