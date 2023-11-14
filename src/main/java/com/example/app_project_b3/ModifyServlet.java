package com.example.app_project_b3;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name="modifyServlet", value="/modify-servlet")

public class ModifyServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response ) throws IOException {

        String table = request.getParameter("table");
        int id = Integer.parseInt(request.getParameter("id"));
        String lastName = request.getParameter("last_name");
        String firstName = request.getParameter("first_name");

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.println("<html><body>");
        pw.println("<h1>Modification de "+lastName+" "+firstName+" de la catégorie <i>"+table+"</i></h1>");
        pw.println("<h2>Renseignez les nouvelles valeurs");
        pw.println("<form method='post' action='modify-servlet'>");
        pw.println("<input type='hidden' name='id' value='"+id+"'>");
        pw.println("<input type='hidden' name='table' value='"+table+"'>");
        pw.println("<input placeholder='"+lastName+"' type='text' name='last_name'>");
        pw.println("<input placeholder='"+firstName+"' type='text' name='first_name'>");
        pw.println("<input type='submit' value='Valider'></form>");
        pw.println("<a href='"+table+"-servlet'>Annuler</a>");
        pw.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String table = request.getParameter("table");
        String column = table.substring(0, table.length() - 1);
        int id = Integer.parseInt(request.getParameter("id"));
        String lastName = request.getParameter("last_name");
        String firstName = request.getParameter("first_name");

        if(!firstName.isEmpty() && !lastName.isEmpty()) {
            Connection co = DatabaseConnection.myConnection();
            String query = "UPDATE " +table+ " SET "+column+ "_last_name = ?, "+column+ "_first_name = ? WHERE "+column+"_id = ?";

            try (PreparedStatement ps = co.prepareStatement(query)) {
                ps.setString(1, lastName);
                ps.setString(2, firstName);
                ps.setInt(3, id);
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            response.sendRedirect(table+"-servlet");
        }
    }
}
