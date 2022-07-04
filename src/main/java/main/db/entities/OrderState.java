package main.db.entities;

import java.util.Locale;

public enum OrderState {
    RESERVED, PRE_COOKING, COOKING, COOKED, PRE_DELIVERING, DELIVERING, FINISHED;

    private final String stateName = this.name().substring(0, 1).toUpperCase(Locale.ROOT)
            + this.name().substring(1).toLowerCase(Locale.ROOT);

    public String getStateName() {
        return stateName;
    }

    public static OrderState getState(int stateId) {
        return OrderState.values()[stateId];
    }
}
