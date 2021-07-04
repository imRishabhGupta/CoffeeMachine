package com.dunzo.coffeemachine.machine;

import java.util.List;

/**
 * The interface Coffee machine.
 */
public interface CoffeeMachine {

    /**
     * Gets low ingredients.
     * We can add an argument "threshold" to it to get the dynamic results instead of fixed threshold.
     */
    void getLowIngredients();

    /**
     * Prepare beverage.
     *
     * @param beverage the beverage
     */
    void prepareBeverage(String beverage);

    /**
     * Gets available beverages.
     *
     * @return the available beverages
     */
    List<String> getAvailableBeverages();

    /**
     * Adds/Refills ingredient to IngredientRepository.
     *
     * @param ingredient  the ingredient
     * @param newQuantity the new quantity
     */
    void addIngredient(String ingredient, int newQuantity);

    /**
     * Gets all ingredients.
     */
    void getAllIngredients();
}
