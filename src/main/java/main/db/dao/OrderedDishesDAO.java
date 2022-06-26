package main.db.dao;

import main.db.DBManager;
import main.db.EntityMapper;
import main.db.Fields;
import main.db.entities.OrderedDish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderedDishesDAO {
    private static final String SQL_GET_USER_ORDERED_DISHES =
            "SELECT * FROM ordered_dishes WHERE order_id = ?";

    private static final String SQL_NEW_ORDERED_DISH =
            "INSERT INTO ordered_dishes(order_id, dish_id, dish_amount) " +
                    "VALUES (?, ?, ?)";

    private static final String SQL_DELETE_ORDERED_DISHES =
            "DELETE FROM ordered_dishes WHERE ordered_dishes_id = ?";

    private static OrderedDishesDAO instance;

    public static synchronized OrderedDishesDAO getInstance() {
        if (instance == null) {
            instance = new OrderedDishesDAO();
        }
        return instance;
    }

    private OrderedDishesDAO() {

    }


    public void newOrderedDishes(List<OrderedDish> orderedDishes) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            for (OrderedDish o : orderedDishes) {
                insertOrderedDishes(con, o);
            }
        } catch (SQLException e) {
            assert con != null;
            DBManager.getInstance().rollbackAndClose(con);
            e.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
    }

    private void insertOrderedDishes(Connection con, OrderedDish orderedDish) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_NEW_ORDERED_DISH);
        int k = 1;
        pstmt.setLong(k++, orderedDish.getOrderId());
        pstmt.setLong(k++, orderedDish.getDishId());
        pstmt.setLong(k, orderedDish.getDishAmount());
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void deleteOrderedDishes(long orderedId) {
        PreparedStatement pstmt;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_ORDERED_DISHES);
            pstmt.setLong(1, orderedId);
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

    public List<OrderedDish> getOrderedDishes(int orderId) {
        List<OrderedDish> orderedDishes = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_GET_USER_ORDERED_DISHES);
            pstmt.setLong(1, orderId);
            rs = pstmt.executeQuery();
            OrderedDishMapper orderedDishMapper = new OrderedDishMapper();
            while (rs.next()) {
                orderedDishes.add(orderedDishMapper.mapRow(rs));
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
        return orderedDishes;
    }

    private static class OrderedDishMapper implements EntityMapper<OrderedDish> {
        @Override
        public OrderedDish mapRow(ResultSet rs) {
            OrderedDish orderedDish = new OrderedDish();
            try {
                orderedDish.setId(rs.getInt(Fields.FIELD__ORDERED_DISHES_ID));
                orderedDish.setOrderId(rs.getInt(Fields.FIELD__ORDERED_ORDER_ID));
                orderedDish.setDishId(rs.getInt(Fields.FIELD__ORDERED_DISH_ID));
                orderedDish.setDishAmount(rs.getInt(Fields.FIELD__ORDERED_DISH_AMOUNT));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return orderedDish;
        }
    }
}
