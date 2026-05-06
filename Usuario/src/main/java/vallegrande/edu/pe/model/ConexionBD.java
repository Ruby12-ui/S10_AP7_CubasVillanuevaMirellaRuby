package vallegrande.edu.pe.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {
    public static Connection getConexion() {
        try {
            // Se usa la base de datos usuarios1 y el puerto 3307 según tus capturas
            String url = "jdbc:mysql://localhost:3307/usuarios1?serverTimezone=UTC";
            String user = "root";
            String pass = "Ruby120";

            Connection con = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexión exitosa");
            return con;
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }
}