package db;

import java.sql.*;

public class DBManager {

    private static Connection conn = null;
    private static DBManager instance = null;

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private DBManager(){
        try {
            String url = "jdbc:mysql://localhost:3306/rgrdb?user=root&password=11111111";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Unable to get connection with the DB");
        }
    }

    public boolean check(String username, String password) {
        String query = String.format("SELECT * FROM clients WHERE login='%s' and password='%s'", username, password);
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
        }
        return false;
    }

    public boolean addUser(String username, String password, String email) {
        String role = "client";
        String query = String.format("SELECT * FROM clients WHERE login='%s' or mail='%s'", username, email);
        String insertQuery = String.format("INSERT INTO clients (login, password, mail, role) VALUES ( '%s', '%s', '%s', '%s' )", username, password, email, role);
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                return false;
            }
            statement.executeUpdate(insertQuery);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
        }
        return false;
    }

    private void closeStatement(Statement statement) {
        if (statement == null)
            return;
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
