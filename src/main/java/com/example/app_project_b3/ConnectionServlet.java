package com.example.app_project_b3;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name="connectionServlet", value="/connection-servlet")
public class ConnectionServlet extends HttpServlet {

    public int getCount(String username, String password ) {
        int count = 0;
        if(!username.isEmpty() && !password.isEmpty()) {
            Connection co = DatabaseConnection.myConnection();
            String query = "SELECT COUNT(*) FROM profs WHERE admin_username = ? AND admin_password = ?";
            try {
                PreparedStatement ps = co.prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if(rs.next()) {
                    count = rs.getInt(1);
                }
            } catch(SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return count;
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response ) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int resultCount = getCount(username, password);
        /*
        Pour envoyer resultcount vers la page index.jsp
        request.setAttribute("resultCount", resultCount);
        request.getRequestDispatcher("index.jsp").forward(request, response);
        */
        if(resultCount == 1) {
            response.sendRedirect("connection-servlet");
            // et là on initialise le JWT
        }
    }
}