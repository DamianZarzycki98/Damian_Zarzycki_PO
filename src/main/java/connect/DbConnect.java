package connect;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnect {
    protected Connection connection;
    public Connection getConnection(){
        final String connectionString="jdbc:mysql://localhost:3306/biblioteka";
        try {
            connection= DriverManager.getConnection(connectionString,"root","");
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE,null,ex);
        }
        return connection;
    }
}
