package main.db.dao;

import main.db.DBManager;
import main.db.EntityMapper;
import main.db.Fields;
import main.db.entities.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO {
    private static final String SQL_GET_USER_ADDRESSES =
            "SELECT * FROM addresses JOIN clients c ON addresses.client_id = c.client_id WHERE c.login = BINARY ?";

    private static final String SQL_FIND_ADDRESS_BY_ID =
            "SELECT * FROM addresses WHERE address_id = ?";

    private static final String SQL_UPDATE_ADDRESS =
            "UPDATE addresses SET city=?, street=?, house_number=?, apartment_number=? WHERE address_id=?";

    private static final String SQL_DELETE_ADDRESS =
            "DELETE FROM addresses WHERE address_id=?";

    public List<Address> getUserAddresses(String login) {
        List<Address> addresses = new ArrayList<>();
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            AddressMapper addressMapper = new AddressMapper();
            pstmt = con.prepareStatement(SQL_GET_USER_ADDRESSES);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            while(rs.next())
                addresses.add(addressMapper.mapRow(rs));
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            assert con != null;
            DBManager.getInstance().rollbackAndClose(con);
            e.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return addresses;
    }

    public Address findAddress(long addressId) {
        Address address = null;
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            AddressMapper mapper = new AddressMapper();
            pstmt = con.prepareStatement(SQL_FIND_ADDRESS_BY_ID);
            pstmt.setLong(1, addressId);
            rs = pstmt.executeQuery();
            if (rs.next())
                address = mapper.mapRow(rs);
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
        return address;
    }

    public void updateAddress(Address address) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            updateAddress(con, address);
        } catch (SQLException ex) {
            assert con != null;
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
    }

    private void updateAddress(Connection con, Address address) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_ADDRESS);
        int k = 1;
        pstmt.setString(k++, address.getCity());
        pstmt.setString(k++, address.getStreet());
        pstmt.setString(k++, address.getHouseNumber());
        pstmt.setString(k++, address.getApartmentNumber());
        pstmt.setLong(k, address.getAddressId());
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void deleteAddress(long addressId) {
        PreparedStatement pstmt;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_ADDRESS);
            pstmt.setLong(1, addressId);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            assert con != null;
            DBManager.getInstance().rollbackAndClose(con);
            e.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
    }

    private static class AddressMapper implements EntityMapper<Address> {
        @Override
        public Address mapRow(ResultSet rs) {
            Address address = new Address();
            try {
                address.setAddressId(rs.getInt(Fields.FIELD__ADDRESS_ID));
                address.setClientId(rs.getInt(Fields.FIELD__ADDRESS_CLIENT_ID));
                address.setCity(rs.getString(Fields.FIELD__ADDRESS_CITY));
                address.setStreet(rs.getString(Fields.FIELD__ADDRESS_STREET));
                address.setHouseNumber(rs.getString(Fields.FIELD__ADDRESS_HOUSE_NUMBER));
                address.setApartmentNumber(rs.getString(Fields.FIELD__ADDRESS_APARTMENT_NUMBER));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return address;
        }
    }
}
