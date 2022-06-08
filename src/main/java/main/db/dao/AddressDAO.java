package main.db.dao;

import main.db.DBManager;
import main.db.EntityMapper;
import main.db.Fields;
import main.db.entities.Address;
import main.db.entities.Dish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO {
    private static final String SQL__GET_USER_ADDRESSES =
            "SELECT * FROM addresses JOIN clients c ON addresses.client_id = c.client_id WHERE c.login = BINARY ?";

    public List<Address> getUserAddresses(String login) {
        List<Address> addresses = new ArrayList<>();
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            AddressMapper addressMapper = new AddressMapper();
            pstmt = con.prepareStatement(SQL__GET_USER_ADDRESSES);
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
