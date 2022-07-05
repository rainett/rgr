package main.db;

import java.sql.ResultSet;

public interface EntityMapper<T> {
    default T mapRow(ResultSet rs) {
        return null;
    }
}
