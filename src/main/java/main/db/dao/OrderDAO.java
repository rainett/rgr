package main.db.dao;

import main.db.DBManager;
import main.db.EntityMapper;
import main.db.Fields;
import main.db.entities.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private static final String SQL_NEW_ORDER =
            "INSERT INTO orders(user_id, payment_id, address_id, price) " +
                    "VALUES (?, ?, ?, ?);";

    private static final String SQL_USER_ORDERS =
            "SELECT * FROM orders " +
                    "JOIN users u on orders.user_id = u.user_id " +
                    "WHERE u.user_id = ?";

    private static final String SQL_FIND_ORDER =
            "SELECT * FROM orders WHERE order_id = ?";

    private static final String SQL_DELETE_ORDER =
            "DELETE FROM orders WHERE order_id = ?";


    private static OrderDAO instance;

    public static synchronized OrderDAO getInstance() {
        if (instance == null) {
            instance = new OrderDAO();
        }
        return instance;
    }

    private OrderDAO() {

    }


    public int newOrder(Order order) {
        Connection con = null;
        int id = 0;
        try {
            con = DBManager.getInstance().getConnection();
            id = insertOrder(con, order);
        } catch (SQLException e) {
            assert con != null;
            DBManager.getInstance().rollbackAndClose(con);
            e.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return id;
    }

    private int insertOrder(Connection con, Order order) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_NEW_ORDER, Statement.RETURN_GENERATED_KEYS);
        int k = 1;
        pstmt.setLong(k++, order.getUserId());
        pstmt.setLong(k++, order.getPaymentId());
        pstmt.setLong(k++, order.getAddressId());
        pstmt.setLong(k, order.getPrice());
        pstmt.executeUpdate();
        int id = 0;
        ResultSet rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }
        pstmt.close();
        return id;
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

    public List<Order> getUserOrders(int id) {
        List<Order> orders = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_USER_ORDERS);
            pstmt.setLong(1, id);
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
                order.setId(rs.getInt(Fields.FIELD__ORDER_ID));
                order.setUserId(rs.getInt(Fields.FIELD__ORDER_USER_ID));
                order.setPaymentId(rs.getInt(Fields.FIELD__ORDER_PAYMENT_ID));
                order.setAddressId(rs.getInt(Fields.FIELD__ORDER_ADDRESS_ID));
                order.setPrice(rs.getInt(Fields.FIELD__ORDER_PRICE));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return order;
        }
    }
}
