package com.dunzo.coffeemachine.repository;

import java.util.List;
import java.util.Map;

/**
 * BeverageRepository holds the beverage compositions and provided CRUD operations on them.
 *
 * So, if we want to add a capability to the CoffeeMachine to update beverage composition of a running CoffeeMachine,
 * we can expose a new method here.
 */
public interface BeverageRepository extends Repository {

    /**
     * Gets beverage composition.
     *
     * @param beverage the beverage
     * @return the beverage composition
     */
    Map<String, Integer> getBeverageComposition(final String beverage);

    /**
     * Gets available beverages.
     *
     * @return the available beverages
     */
    List<String> getAvailableBeverages();

    /* To be used for future use cases -

    public void addNewBeverage();

    public void updateBeverageComposition();

    public void removeBeverage();
    */
}
