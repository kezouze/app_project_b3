package com.example.app_project_b3;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name="deconnectionServlet", value="/deco-servlet")
public class DeconnectionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response ) throws IOException {
        // Récupérer la session sans en créer une nouvelle si aucun résultat
        HttpSession session = request.getSession(false);

        if(session != null) {
            session.invalidate();
        }

        response.sendRedirect("index.jsp");
    }
}
