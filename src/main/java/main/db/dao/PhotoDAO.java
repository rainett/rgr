package main.db.dao;

import main.db.DBManager;
import main.db.EntityMapper;
import main.db.Fields;
import main.db.entities.Photo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PhotoDAO {

    private static final String SQL__FIND_PHOTO_BY_ID =
            "SELECT * FROM photos WHERE photo_id = binary ?";

    private static final String SQL_UPDATE_PHOTO =
            "UPDATE photos SET photo_blob=? WHERE photo_id=?";

    private static final String SQL_INSERT_PHOTO =
            "INSERT INTO photos (photo_blob) VALUES ( ? )";

    private static PhotoDAO instance;

    public static synchronized PhotoDAO getInstance() {
        if (instance == null) {
            instance = new PhotoDAO();
        }
        return instance;
    }

    private PhotoDAO() {

    }

    public Photo getPhoto(int id) {
        Photo photo = null;
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            PhotoMapper mapper = new PhotoMapper();
            pstmt = con.prepareStatement(SQL__FIND_PHOTO_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next())
                photo = mapper.mapRow(rs);
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
        return photo;
    }

    public void updatePhoto(Photo photo) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            updatePhoto(con, photo);
        } catch (SQLException ex) {
            assert con != null;
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
    }

    private void updatePhoto(Connection con, Photo photo) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_PHOTO);
        int k = 1;
        pstmt.setBlob(k, photo.getPic());
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void newPhoto(Photo photo) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            insertPhoto(con, photo);
        } catch (SQLException ex) {
            assert con != null;
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
    }

    private void insertPhoto(Connection con, Photo photo) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_INSERT_PHOTO);
        int k = 1;
        pstmt.setBlob(k, photo.getPic());
        pstmt.executeUpdate();
        pstmt.close();
    }


    private static class PhotoMapper implements EntityMapper<Photo> {
        @Override
        public Photo mapRow(ResultSet rs) {
            Photo photo = new Photo();
            try {
                photo.setId(rs.getInt(Fields.FIELD__PHOTO_ID));
                photo.setPic(rs.getBinaryStream(Fields.FIELD__PHOTO_BLOB));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return photo;
        }
    }

}
