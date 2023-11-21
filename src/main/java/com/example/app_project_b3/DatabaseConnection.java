package com.example.app_project_b3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection myConnection() {
        Connection connection = null;
        String url = "jdbc:mysql://localhost:8889/app_project_b3";
        String utilisateur = "root";
        String mdp = "root";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, utilisateur, mdp);
        } catch (ClassNotFoundException | SQLException e ) {
            e.printStackTrace();
        }
        return connection;
    }
}
