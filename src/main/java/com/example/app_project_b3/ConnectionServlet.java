package com.example.app_project_b3;

import com.auth0.jwt.JWTCreator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Objects;

@WebServlet(name = "connectionServlet", value = "/connection-servlet")
public class ConnectionServlet extends HttpServlet {

    public int getCount(String username, String password) {
        int count = 0;
        if (!username.isEmpty() && !password.isEmpty()) {
            Connection co = DatabaseConnection.myConnection();
            String query = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?";
            try {
                PreparedStatement ps = co.prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return count;
    }

    public String getStatus(String username) {
        String status = "";
        if (!username.isEmpty()) {
            Connection co = DatabaseConnection.myConnection();
            String query = "SELECT user_status FROM users WHERE username = ?";
            try {
                PreparedStatement ps = co.prepareStatement(query);
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    status = rs.getString("user_status");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return status;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int resultCount = getCount(username, password);
        String status = getStatus(username);

        if (resultCount == 1) {
            // On créer la session et on enregistre l'utilisateur
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            // session.setAttribute("status", status);

            // On créer le token et on le met dans la session
            String token = JWTManager.generateToken(username, "admin", 300000);
            session.setAttribute("token", token);

            // On vérifie la validité et on le met dans la session
            boolean isTokenValid = JWTManager.verifyToken(token, username, "admin");
            session.setAttribute("valid", isTokenValid);

            // On redirige vers la bonne page
            response.sendRedirect("logged-"+status+".jsp");
        } else {
            request.setAttribute("login-error", "");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}