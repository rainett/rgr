package main.db.entities;

import java.util.Locale;

public enum Category {
    SNACK, SALAD, SOUP, PASTA, GARNISH, GRILL, DESSERT, MAIN;

    private final String categoryName = this.name().substring(0, 1).toUpperCase(Locale.ROOT)
            + this.name().substring(1).toLowerCase(Locale.ROOT);

    public String getCategoryName() {
        return categoryName;
    }

    public static Category getCategory(String categoryStr) {
        categoryStr = categoryStr.toUpperCase(Locale.ROOT);
        for (Category c : Category.values()) {
            if (c.name().equals(categoryStr)) {
                return c;
            }
        }
        return null;
    }
}
