package main.db.dao;

import main.db.DBManager;
import main.db.EntityMapper;
import main.db.Fields;
import main.db.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private static final String SQL__FIND_USER_BY_USERNAME =
            "SELECT * FROM users WHERE username = binary ?";

    private static final String SQL__FIND_USER_BY_EMAIL =
            "SELECT * FROM users WHERE email=binary ?";

    private static final String SQL__FIND_USER_BY_ID =
            "SELECT * FROM users WHERE user_id =  ?";

    private static final String SQL_UPDATE_USER =
            "UPDATE users SET username=?, password=?, email=?, role=? WHERE user_id=?";

    private static final String SQL_INSERT_USER =
            "INSERT INTO users (username, password, email, role) VALUES ( ?, ?, ?, ? )";

    private static UserDAO instance;

    public static synchronized UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    private UserDAO() {

    }

    public User getUser(int id) {
        User user = null;
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();
            pstmt = con.prepareStatement(SQL__FIND_USER_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next())
                user = mapper.mapRow(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            assert con != null;
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return user;
    }

    public User getUser(String login) {
        User user = null;
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();
            pstmt = con.prepareStatement(SQL__FIND_USER_BY_USERNAME);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            if (rs.next())
                user = mapper.mapRow(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            assert con != null;
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return user;
    }

    public User findUserByEmail(String email) {
        User user = null;
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();
            pstmt = con.prepareStatement(SQL__FIND_USER_BY_EMAIL);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            if (rs.next())
                user = mapper.mapRow(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            assert con != null;
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return user;
    }

    public void updateUser(User user) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            updateUser(con, user);
        } catch (SQLException ex) {
            assert con != null;
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
    }

    private void updateUser(Connection con, User user) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_USER);
        int k = 1;
        pstmt.setString(k++, user.getUsername());
        pstmt.setString(k++, user.getPassword());
        pstmt.setString(k++, user.getEmail());
        pstmt.setString(k++, user.getRole());
        pstmt.setLong(k, user.getId());
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void addUser(User user) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            insertUser(con, user);
        } catch (SQLException ex) {
            assert con != null;
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
    }

    private void insertUser(Connection con, User user) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_INSERT_USER);
        int k = 1;
        pstmt.setString(k++, user.getUsername());
        pstmt.setString(k++, user.getPassword());
        pstmt.setString(k++, user.getEmail());
        pstmt.setString(k, user.getRole());
        pstmt.executeUpdate();
        pstmt.close();
    }


    private static class UserMapper implements EntityMapper<User> {
        @Override
        public User mapRow(ResultSet rs) {
            User user = new User();
            try {
                user.setId(rs.getInt(Fields.FIELD__USER_ID));
                user.setUsername(rs.getString(Fields.FIELD__USERNAME));
                user.setPassword(rs.getString(Fields.FIELD__PASSWORD));
                user.setEmail(rs.getString(Fields.FIELD__EMAIL));
                user.setRole(rs.getString(Fields.FIELD__ROLE));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return user;
        }
    }

}
