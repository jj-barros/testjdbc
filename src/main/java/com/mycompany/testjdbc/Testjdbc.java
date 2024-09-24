package com.mycompany.testjdbc;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Testjdbc {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Testjdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String usuario = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/java";
        Connection conexion; // Donde se guarda la conexion
        Statement statement; // Donde podemos guardar el objeto para hacer consulta
        ResultSet rs; // Donde guardamos los datos que se traen de la bd con un SELECT
        try {
            conexion = DriverManager.getConnection(url, usuario, password);
            statement = conexion.createStatement();
            rs = statement.executeQuery("SELECT * FROM usuarios");
            System.out.println("Antes de While");
            while(rs.next()){ // Ciclo que valida si hay otra fila, y si hay fila, la obtiene
                System.out.println("id:"+rs.getInt("id")+"usuario:"+rs.getString("username"));
            }
            
            statement.execute("INSERT INTO usuarios (username) VALUES (\"Karen\")");
            
             rs = statement.executeQuery("SELECT * FROM usuarios");
            System.out.println("Despues de insertar");
            while(rs.next()){ // Ciclo que valida si hay otra fila, y si hay fila, la obtiene
                System.out.println("id:"+rs.getInt("id")+"usuario:"+rs.getString("username"));
            }
            
            statement.executeUpdate("UPDATE usuarios SET username=\"Julieta\" WHERE username=\"Karen\"");
            
             rs = statement.executeQuery("SELECT * FROM usuarios");
            System.out.println("Despues de actualizar");
            while(rs.next()){ // Ciclo que valida si hay otra fila, y si hay fila, la obtiene
                System.out.println("id:"+rs.getInt("id")+"usuario:"+rs.getString("username"));
            }
            
            statement.executeUpdate("DELETE FROM usuarios WHERE username=\"Juan Barros\"");
            
             rs = statement.executeQuery("SELECT * FROM usuarios");
            System.out.println("Despues de eliminar");
            while(rs.next()){ // Ciclo que valida si hay otra fila, y si hay fila, la obtiene
                System.out.println("id:"+rs.getInt("id")+"usuario:"+rs.getString("username"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Testjdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }            
}
