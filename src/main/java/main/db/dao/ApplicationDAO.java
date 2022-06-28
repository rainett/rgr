package main.db.dao;

import main.db.DBManager;
import main.db.EntityMapper;
import main.db.Fields;
import main.db.entities.Application;
import main.db.entities.Dish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDAO {
    private static final String SQL__GET_ALL_APPLICATIONS =
            "SELECT * FROM applications";

    private static final String SQL__GET_APPLICATION_BY_ID =
            "SELECT * FROM applications WHERE application_id = ?";

    private static final String SQL__USER_APPLICATIONS =
            "SELECT * FROM applications WHERE user_id = ?";

    private static final String SQL_UPDATE_APPLICATION =
            "UPDATE applications SET user_id=?, role=?, application_state=? WHERE application_id=?";

    private static final String SQL_INSERT_APPLICATION =
            "INSERT INTO applications (user_id, role, application_state) VALUES ( ?, ?, ? )";

    private static final String SQL_DELETE_APPLICATION =
            "DELETE FROM applications WHERE application_id = ?";

    private static ApplicationDAO instance;

    public static synchronized ApplicationDAO getInstance() {
        if (instance == null) {
            instance = new ApplicationDAO();
        }
        return instance;
    }

    private ApplicationDAO() {

    }

    public List<Application> getAllApplications() {
        List<Application> applications = new ArrayList<>();
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            ApplicationMapper mapper = new ApplicationMapper();
            pstmt = con.prepareStatement(SQL__GET_ALL_APPLICATIONS);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                applications.add(mapper.mapRow(rs));
            }
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
        return applications;
    }

    public Application getUserApplication(int userId) {
        Application application = null;
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            ApplicationMapper mapper = new ApplicationMapper();
            pstmt = con.prepareStatement(SQL__USER_APPLICATIONS);
            pstmt.setLong(1, userId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                application = mapper.mapRow(rs);
            }
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
        return application;
    }

    public Application getApplication(int id) {
        Application application = null;
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            ApplicationMapper mapper = new ApplicationMapper();
            pstmt = con.prepareStatement(SQL__GET_APPLICATION_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next())
                application = mapper.mapRow(rs);
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
        return application;
    }

    public void deleteApplication(int id) {
        PreparedStatement pstmt;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_APPLICATION);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            assert con != null;
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
    }


    public void updateApplication(Application application) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            updateAppliaction(con, application);
        } catch (SQLException ex) {
            assert con != null;
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
    }

    private void updateAppliaction(Connection con, Application application) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_APPLICATION);
        int k = 1;
        pstmt.setLong(k++, application.getUserId());
        pstmt.setString(k++, application.getRole());
        pstmt.setBoolean(k++, application.getState());
        pstmt.setLong(k, application.getId());
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void newApplication(Application application) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            insertDish(con, application);
        } catch (SQLException ex) {
            assert con != null;
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
    }

    private void insertDish(Connection con, Application application) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_INSERT_APPLICATION);
        int k = 1;
        pstmt.setLong(k++, application.getUserId());
        pstmt.setString(k++, application.getRole());
        pstmt.setBoolean(k, false);
        pstmt.executeUpdate();
        pstmt.close();
    }


    private static class ApplicationMapper implements EntityMapper<Application> {
        @Override
        public Application mapRow(ResultSet rs) {
            Application application = new Application();
            try {
                application.setId(rs.getInt(Fields.FIELD__APPLICATION_ID));
                application.setUserId(rs.getInt(Fields.FIELD__APPLICATION_USER_ID));
                application.setRole(rs.getString(Fields.FIELD__APPLICATION_ROLE));
                application.setState(rs.getBoolean(Fields.FIELD__APPLICATION_STATE));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return application;
        }
    }

}
