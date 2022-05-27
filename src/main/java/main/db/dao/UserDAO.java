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
    private static final String SQL__FIND_USER_BY_LOGIN =
            "SELECT * FROM clients WHERE login=binary ?";

    private static final String SQL__FIND_USER_BY_LOGIN_OR_MAIL =
            "SELECT * FROM clients WHERE login=binary ? or mail=binary ?";

    private static final String SQL__FIND_USER_BY_ID =
            "SELECT * FROM clients WHERE client_id=binary ?";

    private static final String SQL_UPDATE_USER =
            "UPDATE clients SET login=?, password=?, mail=?, role=?	WHERE client_id=?";

    private static final String SQL_INSERT_USER =
            "INSERT INTO clients (login, password, mail, role) VALUES ( ?, ?, ?, ? )";

    public User findUser(Long id) {
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

    public User findUser(String login) {
        User user = null;
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();
            pstmt = con.prepareStatement(SQL__FIND_USER_BY_LOGIN);
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

    public User findUser(String login, String mail) {
        User user = null;
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();
            pstmt = con.prepareStatement(SQL__FIND_USER_BY_LOGIN_OR_MAIL);
            pstmt.setString(1, login);
            pstmt.setString(2, mail);
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
        pstmt.setString(k++, user.getLogin());
        pstmt.setString(k++, user.getPassword());
        pstmt.setString(k++, user.getMail());
        pstmt.setString(k++, user.getRole());
        pstmt.setLong(k, user.getClientId());
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void addUser(String username, String password, String mail) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            insertUser(username, password, mail, con);
        } catch (SQLException ex) {
            assert con != null;
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
    }

    private void insertUser(String username, String password, String mail, Connection con) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_INSERT_USER);
        int k = 1;
        pstmt.setString(k++, username);
        pstmt.setString(k++, password);
        pstmt.setString(k++, mail);
        pstmt.setString(k, "client");
        pstmt.executeUpdate();
        pstmt.close();
    }


    private static class UserMapper implements EntityMapper<User> {
        @Override
        public User mapRow(ResultSet rs) {
            User user = new User();
            try {
                user.setClientId(rs.getInt(Fields.FIELD__CLIENT_ID));
                user.setLogin(rs.getString(Fields.FIELD__LOGIN));
                user.setPassword(rs.getString(Fields.FIELD__PASSWORD));
                user.setMail(rs.getString(Fields.FIELD__MAIL));
                user.setRole(rs.getString(Fields.FIELD__ROLE));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return user;
        }
    }

}
