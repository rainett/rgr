package main.db.dao;

import main.db.DBManager;
import main.db.EntityMapper;
import main.db.Fields;
import main.db.entities.Address;
import main.db.entities.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {
    private static final String SQL_GET_USER_PAYMENTS =
            "SELECT * FROM cards JOIN clients c ON cards.client_id = c.client_id WHERE c.login = BINARY ?";

    private static final String SQL_FIND_PAYMENT_BY_ID =
            "SELECT * FROM cards WHERE card_id = ?";

    private static final String SQL_UPDATE_PAYMENT =
            "UPDATE cards SET card_number=?, card_till=?, card_cvv=? WHERE card_id=?";

    private static final String SQL_NEW_PAYMENT =
            "INSERT INTO cards(client_id, card_number, card_till, card_cvv) " +
            "VALUES (?, ?, ?, ?)";

    private static final String SQL_DELETE_PAYMENT =
            "DELETE FROM cards WHERE card_id=?";

    public List<Payment> getUserPayments(String login) {
        List<Payment> payments = new ArrayList<>();
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            PaymentMapper paymentMapper = new PaymentMapper();
            pstmt = con.prepareStatement(SQL_GET_USER_PAYMENTS);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            while(rs.next())
                payments.add(paymentMapper.mapRow(rs));
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
        return payments;
    }

    public Payment findPayment(long cardId) {
        Payment payment = null;
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            PaymentMapper mapper = new PaymentMapper();
            pstmt = con.prepareStatement(SQL_FIND_PAYMENT_BY_ID);
            pstmt.setLong(1, cardId);
            rs = pstmt.executeQuery();
            if (rs.next())
                payment = mapper.mapRow(rs);
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
        return payment;
    }

    public void updatePayment(Payment payment) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            updatePayment(con, payment);
        } catch (SQLException ex) {
            assert con != null;
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
    }

    private void updatePayment(Connection con, Payment payment) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_PAYMENT);
        int k = 1;
        pstmt.setString(k++, payment.getNumber());
        pstmt.setString(k++, payment.getTill());
        pstmt.setString(k++, payment.getCvv());
        pstmt.setLong(k, payment.getCardId());
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void newPayment(String login, Payment payment) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            insertAddress(con, login, payment);
        } catch (SQLException e) {
            assert con != null;
            DBManager.getInstance().rollbackAndClose(con);
            e.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
    }

    private void insertAddress(Connection con, String login, Payment payment) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_NEW_PAYMENT);
        int k = 1;
        pstmt.setLong(k++, new UserDAO().findUser(login).getClientId());
        pstmt.setString(k++, payment.getNumber());
        pstmt.setString(k++, payment.getTill());
        pstmt.setString(k, payment.getCvv());
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void deletePayment(long cardId) {
        PreparedStatement pstmt;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_PAYMENT);
            pstmt.setLong(1, cardId);
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

    private static class PaymentMapper implements EntityMapper<Payment> {
        @Override
        public Payment mapRow(ResultSet rs) {
            Payment payment = new Payment();
            try {
                payment.setCardId(rs.getInt(Fields.FIELD__PAYMENT_ID));
                payment.setClientId(rs.getInt(Fields.FIELD__PAYMENT_CLIENT_ID));
                payment.setNumber(rs.getString(Fields.FIELD__PAYMENT_NUMBER));
                payment.setTill(rs.getString(Fields.FIELD__PAYMENT_TILL));
                payment.setCvv(rs.getString(Fields.FIELD__PAYMENT_CVV));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return payment;
        }
    }
}
