package main.db.dao;

import main.db.Fields;

public class DishesSorting {
    public static final String SORT__PRICE_DESC = "fromExp";
    public static final String SORT__PRICE_ASC = "fromCheap";
    public static final String SORT__BY_ALPHABET = "byAlphabet";
    public static final String SORT__BY_CATEGORY = "byCategory";

    private static final String PRICE_FILTER = " WHERE "+ Fields.FIELD__DISH_PRICE +" BETWEEN ? AND ?";
    private static final String FIRST_CATEGORY_FILTER = " AND "+ Fields.FIELD__DISH_CATEGORY_ID +" = ?";
    private static final String CATEGORY_FILTER = " OR "+ Fields.FIELD__DISH_CATEGORY_ID +" = ?";
    private static final String SORTING = " ORDER BY ? ?";

    private final String priceQuery;
    private final String categoryQuery;
    private final String sortingQuery;

    public DishesSorting(String priceParam, String[] categoryParam, String sortingParam) {
        priceQuery = getPriceQuery(priceParam);
        categoryQuery = getCategoryQuery(categoryParam);
        sortingQuery = getSortingQuery(sortingParam);
    }

    private String getSortingQuery(String sortingParam) {
        String sortQueryTemp;
        String column;
        String direction;
        switch (sortingParam) {
            case SORT__PRICE_DESC: {
                column = Fields.FIELD__DISH_PRICE;
                direction = "DESC";
                break;
            }
            case SORT__PRICE_ASC: {
                column = Fields.FIELD__DISH_PRICE;
                direction = "ASC";
                break;
            }
            case SORT__BY_ALPHABET: {
                column = Fields.FIELD__DISH_NAME;
                direction = "ASC";
                break;
            }
            case SORT__BY_CATEGORY: {
                column = Fields.FIELD__DISH_CATEGORY_ID;
                direction = "ASC";
                break;
            }
            default: {
                column = "dish_id";
                direction = "ASC";
            }
        }
        sortQueryTemp = SORTING
                .replaceFirst("\\?", column)
                .replaceFirst("\\?", direction);
        return sortQueryTemp;
    }

    private String getPriceQuery(String priceParam) {
        String priceQueryTemp;
        String[] split = priceParam.split(" ");
        String lowerBorder = split[1];
        String upperBorder = split[4];
        priceQueryTemp = PRICE_FILTER
                .replaceFirst("\\?", lowerBorder)
                .replaceFirst("\\?", upperBorder);
        return priceQueryTemp;
    }

    private String getCategoryQuery(String[] categoryParam) {
        StringBuilder categoryBuilder = new StringBuilder();
        if (categoryParam == null) {
            return categoryBuilder.toString();
        }
        for (int i = 0; i < categoryParam.length; i++) {
            if (i == 0) {
                categoryBuilder.append(FIRST_CATEGORY_FILTER.replaceFirst("\\?", categoryParam[i]));
                continue;
            }
            categoryBuilder.append(CATEGORY_FILTER.replaceFirst("\\?", categoryParam[i]));
        }
        return categoryBuilder.toString();
    }

    public String getQuery() {
        String query = "SELECT * FROM dishes" +
                priceQuery +
                categoryQuery +
                sortingQuery;
        System.out.println(query);
        return query;
    }
}
