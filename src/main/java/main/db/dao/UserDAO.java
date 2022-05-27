package main.db.dao;

public class UserDAO {
    private static final String SQL__FIND_USER_BY_LOGIN =
            "SELECT * FROM users WHERE login=?";

    private static final String SQL__FIND_USER_BY_ID =
            "SELECT * FROM users WHERE id=?";

    private static final String SQL_UPDATE_USER =
            "UPDATE users SET password=?, first_name=?, last_name=?, locale_name=?"+
                    "	WHERE id=?";



}
