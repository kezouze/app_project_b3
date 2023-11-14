package com.example.app_project_b3;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="coursServlet", value="/cours-servlet")
public class CoursServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response ) throws IOException {
        Connection co = DatabaseConnection.myConnection();
        String query = "SELECT C.*, P.prof_first_name, P.prof_last_name " +
                        "FROM cours C " +
                        "JOIN profs P ON C.prof_id = P.prof_id " +
                        "ORDER BY C.cours_name";
        try {
            PreparedStatement ps = co.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            List<Cours> cours = new ArrayList<>();
            while(rs.next()) {
                int id = rs.getInt("cours_id");
                String name = rs.getString("cours_name").toUpperCase();
                int prof_id = rs.getInt("prof_id");
                String lastName = rs.getString("prof_last_name").toUpperCase();
                String firstName = rs.getString("prof_first_name");
                //String profName = rs.getString("prof_last_name") +" "+rs.getString("prof_first_name");
                Cours cours_2 = new Cours(id, name, prof_id);
                cours_2.setLastName(lastName);
                cours_2.setFirstName(firstName);
                cours.add(cours_2);
            }
            request.setAttribute("cours", cours);
            RequestDispatcher rd = request.getRequestDispatcher("cours.jsp");
            rd.forward(request, response);
        } catch(SQLException | ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
