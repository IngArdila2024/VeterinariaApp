package config;

import java.sql.*;

public class DatabaseConnection {
 
    private static final String URL = "jdbc:mysql://localhost:3306/veterinaria";
    private static final String USER ="root";
    private static final String PASSWORD ="admin";
    
    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }catch(SQLException e){
            System.out.println("Error al conectar a la bade de datos"+e.getMessage());
            return null;
        
    }
    }
}
