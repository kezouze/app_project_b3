package com.example.app_project_b3;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "insertServlet", value="/insert-servlet")
public class InsertServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String table = request.getParameter("table");
        String column = table.substring(0, table.length() - 1);
        String lastName = request.getParameter("last_name").trim();
        String firstName = request.getParameter("first_name").trim();
        if(!firstName.isEmpty() && !lastName.isEmpty()) {
            Connection co = DatabaseConnection.myConnection();
            String query = "INSERT into "+ table +" ("+ column+"_last_name,"+column+"_first_name) VALUE(?,?)";
            try {
                PreparedStatement ps = co.prepareStatement(query);
                ps.setString(1, lastName);
                ps.setString(2, firstName);
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        response.sendRedirect(table+"-servlet");
    }
}
