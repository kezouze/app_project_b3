package com.example.app_project_b3;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name="deleteServlet", value="/delete-servlet")
public class DeleteServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String table = request.getParameter("table");
        int id = Integer.parseInt(request.getParameter("id"));

        if(id > 0) { // Ajouter condition si existant en BDD

            String query = "DELETE from " + table + " WHERE " + table.substring(0, table.length() -1) + "_id = ?";
            try {
                Connection co = DatabaseConnection.myConnection();
                try (PreparedStatement ps = co.prepareStatement(query)) {
                    ps.setInt(1, id);
                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        response.sendRedirect(table+"-servlet");
    }
}