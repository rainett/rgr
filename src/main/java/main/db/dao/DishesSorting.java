package main.db.dao;

import java.util.Map;
import java.util.TreeMap;

public class DishesSorting {
    public static final String SORT__FROM_EXPENSIVE = "fromExp";
    public static final String SORT__FROM_CHEAP = "fromCheap";
    public static final String SORT__BY_ALPHABET = "byAlphabet";

    private static final String SORT__QUERY_FROM_EXPENSIVE = "SELECT * FROM dishes ORDER BY dish_price DESC";
    private static final String SORT__QUERY_FROM_CHEAP = "SELECT * FROM dishes ORDER BY dish_price ASC";
    private static final String SORT__QUERY_BY_ALPHABET = "SELECT * FROM dishes ORDER BY dish_name ASC";

    public static String getSortQuery(String sorting) {
        Map<String, String> sorts = new TreeMap<>();
        String query = "";
        sorts.put(SORT__FROM_EXPENSIVE, SORT__QUERY_FROM_EXPENSIVE);
        sorts.put(SORT__FROM_CHEAP, SORT__QUERY_FROM_CHEAP);
        sorts.put(SORT__BY_ALPHABET, SORT__QUERY_BY_ALPHABET);

        for (Map.Entry<String, String> sortingType : sorts.entrySet()) {
            if (sorting.equals(sortingType.getKey())) {
                query = sortingType.getValue();
                break;
            }
        }

        return query;
    }
}
