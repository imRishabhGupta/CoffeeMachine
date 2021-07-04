package com.dunzo.coffeemachine.model;

import java.util.Map;

/**
 * The type Machine.
 */
public class Machine {

    private Outlets outlets;

    private Map<String, Integer> total_items_quantity;

    private Map<String, Map<String, Integer>> beverages;

    /**
     * Gets outlets.
     *
     * @return the outlets
     */
    public Outlets getOutlets() {
        return outlets;
    }

    /**
     * Gets total items quantity.
     *
     * @return the total items quantity
     */
    public Map<String, Integer> getTotal_items_quantity() {
        return total_items_quantity;
    }

    /**
     * Gets beverages.
     *
     * @return the beverages
     */
    public Map<String, Map<String, Integer>> getBeverages() {
        return beverages;
    }
}
