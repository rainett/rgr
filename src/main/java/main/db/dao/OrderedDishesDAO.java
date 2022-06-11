package main.db.dao;

import main.db.DBManager;
import main.db.EntityMapper;
import main.db.Fields;
import main.db.entities.OrderedDish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderedDishesDAO {
    private static final String SQL_GET_LAST_ORDER =
            "SELECT ordered_id FROM ordered_dishes ORDER BY ordered_id DESC";

    private static final String SQL_ORDER_PRICE =
            "SELECT SUM(dish_price*dish_amount) AS price FROM ordered_dishes " +
                    "INNER JOIN dishes d ON d.dish_id = ordered_dishes.dish_id " +
                    "WHERE ordered_id = ?;";

    private static final String SQL_NEW_ORDERED_DISH =
            "INSERT INTO ordered_dishes(ordered_id, dish_id, dish_amount) " +
                    "VALUES (?, ?, ?)";

    private static final String SQL_DELETE_ORDERED_DISHES =
            "DELETE FROM ordered_dishes WHERE ordered_id=?";


    public int[] newOrderedDishes(List<OrderedDish> orderedDishes) {
        Connection con = null;
        int orderedId = 0;
        int orderedPrice = 0;
        try {
            con = DBManager.getInstance().getConnection();
            orderedId = getLastOrderedId(con) + 1;
            for (OrderedDish o : orderedDishes) {
                o.setOrderedId(orderedId);
                insertOrderedDishes(con, o);
            }
            orderedPrice = getOrderPrice(con, orderedId);
        } catch (SQLException e) {
            assert con != null;
            DBManager.getInstance().rollbackAndClose(con);
            e.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return new int[] {orderedId, orderedPrice};
    }

    private int getLastOrderedId(Connection con) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_GET_LAST_ORDER);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            int lastId = rs.getInt(Fields.FIELD__ORDERED_DISHES_ID);
            rs.close();
            pstmt.close();
            return lastId;
        }
        rs.close();
        pstmt.close();
        return 0;
    }

    private int getOrderPrice(Connection con, int orderedId) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_ORDER_PRICE);
        pstmt.setLong(1, orderedId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            int lastId = rs.getInt("price");
            rs.close();
            pstmt.close();
            return lastId;
        }
        rs.close();
        pstmt.close();
        return 0;
    }

    private void insertOrderedDishes(Connection con, OrderedDish orderedDish) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_NEW_ORDERED_DISH);
        int k = 1;
        pstmt.setLong(k++, orderedDish.getOrderedId());
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

    private static class OrderedDishMapper implements EntityMapper<OrderedDish> {
        @Override
        public OrderedDish mapRow(ResultSet rs) {
            OrderedDish orderedDish = new OrderedDish();
            try {
                orderedDish.setOrderedId(rs.getInt(Fields.FIELD__ORDERED_DISHES_ID));
                orderedDish.setDishId(rs.getInt(Fields.FIELD__ORDERED_DISHES_DISH_ID));
                orderedDish.setDishAmount(rs.getInt(Fields.FIELD__ORDERED_DISHES_DISH_AMOUNT));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return orderedDish;
        }
    }
}
