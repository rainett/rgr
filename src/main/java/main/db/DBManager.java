package main.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class DBManager {

    private static DBManager instance = null;

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://oliadkuxrl9xdugh.chr7pe7iynqr.eu-west-1.rds.amazonaws.com:3306/hasvf5g259kwfoeu?user=yv5ivcp2saszvwwr&password=vipg57hn54jj2n1e");
    }

    public void commitAndClose(Connection con) {
        try {
            con.commit();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void rollbackAndClose(Connection con) {
        try {
            con.rollback();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
