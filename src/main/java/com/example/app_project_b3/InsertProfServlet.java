package com.example.app_project_b3;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "insertProfServlet", value="/insert-prof-servlet")
public class InsertProfServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String lastName = request.getParameter("last_name");
        String firstName = request.getParameter("first_name");
        Connection co = DatabaseConnection.myConnection();
        String query = "INSERT into profs (prof_last_name, prof_first_name) VALUE(?,?)";
        try {
            PreparedStatement ps = co.prepareStatement(query);
            ps.setString(1, lastName);
            ps.setString(2, firstName);
            ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        response.sendRedirect("profs-servlet");
    }
}
