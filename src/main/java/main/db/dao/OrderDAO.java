package main.db.dao;

import main.db.DBManager;
import main.db.EntityMapper;
import main.db.Fields;
import main.db.entities.Order;
import main.db.entities.OrderedDish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private static final String SQL_NEW_ORDER =
            "INSERT INTO orders(client_id, ordered_id, card_id, address_id, price) " +
                    "VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_USER_ORDERS =
            "SELECT * FROM orders " +
                    "JOIN clients c on orders.client_id = c.client_id " +
                    "WHERE c.login = BINARY ?";

    private static final String SQL_FIND_ORDER =
            "SELECT * FROM orders WHERE order_id = ?";

    private static final String SQL_DELETE_ORDER =
            "DELETE FROM orders WHERE order_id=?";


    public void newOrder(Order order) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            insertOrder(con, order);
        } catch (SQLException e) {
            assert con != null;
            DBManager.getInstance().rollbackAndClose(con);
            e.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
    }

    private void insertOrder(Connection con, Order order) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_NEW_ORDER);
        int k = 1;
        pstmt.setLong(k++, order.getClientId());
        pstmt.setLong(k++, order.getOrderedId());
        pstmt.setLong(k++, order.getCardId());
        pstmt.setLong(k++, order.getAddressId());
        pstmt.setLong(k, order.getPrice());
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void deleteOrder(long orderId) {
        PreparedStatement pstmt;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_ORDER);
            pstmt.setLong(1, orderId);
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

    public List<Order> getUserOrders(String login) {
        List<Order> orders = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_USER_ORDERS);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            OrderMapper orderMapper = new OrderMapper();
            while(rs.next()) {
                orders.add(orderMapper.mapRow(rs));
            }
            pstmt.close();
            rs.close();
        } catch (SQLException e) {
            assert con != null;
            DBManager.getInstance().rollbackAndClose(con);
            e.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return orders;
    }

    public Order getOrder(int orderId) {
        Order order = null;
        Connection con = null;
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_FIND_ORDER);
            pstmt.setLong(1, orderId);
            rs = pstmt.executeQuery();
            OrderMapper orderMapper = new OrderMapper();
            if (rs.next()) {
                order = orderMapper.mapRow(rs);
            }
        } catch (SQLException e) {
            assert con != null;
            DBManager.getInstance().rollbackAndClose(con);
            e.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return order;
    }

    private static class OrderMapper implements EntityMapper<Order> {
        @Override
        public Order mapRow(ResultSet rs) {
            Order order = new Order();
            try {
                order.setOrderId(rs.getInt(Fields.FIELD__ORDER_ID));
                order.setClientId(rs.getInt(Fields.FIELD__ORDER_CLIENT_ID));
                order.setOrderedId(rs.getInt(Fields.FIELD__ORDER_ORDERED_ID));
                order.setCardId(rs.getInt(Fields.FIELD__ORDER_CARD_ID));
                order.setPrice(rs.getInt(Fields.FIELD__ORDER_PRICE));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return order;
        }
    }
}
