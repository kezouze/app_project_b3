package com.example.app_project_b3;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String query = "SELECT * from eleves";
        Connection co = DatabaseConnection.myConnection();
        try {
            PreparedStatement ps = co.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            out.println("<html><body><h1>Liste des élèves</h1><ul>");
            List<Eleve> eleves = new ArrayList<>();
            while(rs.next()) {
                int id = rs.getInt("eleve_id");
                String firstName = rs.getString("eleve_first_name");
                String lastName = rs.getString("eleve_last_name");
                Eleve eleve = new Eleve(id, firstName, lastName);
                eleves.add(eleve);
                out.println("<li>" + firstName + " " + lastName + "</li>");
            }
            out.println("</ul></html></body>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
