package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Juliao LB
 */
public class Dao {
    
    private static Connection con;
    public static Connection  getConnection() throws SQLException, Exception {
        
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/hotel";
        String usuario = "postgres";
        String senha = "1234";
        try {
           con = DriverManager.getConnection(url, usuario, senha);
           System.out.println("CONEXAO ESTABELECIDA \n COM SUCESS CONEXAO BD\n["+con+"]");
        } catch(Exception e){
                System.out.println("\n[error \n["+e.getMessage()+"]]");
                System.out.println("ERROR CONEXAO BD\n["+e.getMessage()+"]");
		}
        return con;
    }
    
    
}
