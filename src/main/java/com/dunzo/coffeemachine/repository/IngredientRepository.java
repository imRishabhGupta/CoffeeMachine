package com.dunzo.coffeemachine.repository;

import java.util.Map;

/**
 * IngredientRepository holds the ingredient levels available in CoffeeMachine and provided CRUD operations on it.
 * This class can be extended in future to use cases like -
 * updating ingredient level, adding new ingredient, releasing ingredient.
 */
public interface IngredientRepository extends Repository {

    /**
     * Gets all ingredient level.
     *
     * @return the all ingredient level
     */
    Map<String, Integer> getAllIngredients();

    /**
     * Checks if ingredient is present in the repository.
     *
     * @param ingredient the ingredient
     * @return the boolean
     */
    boolean isIngredientPresent(final String ingredient);

    /**
     * Returns the quantity of ingredient.
     *
     * @param ingredient the ingredient
     * @return the ingredient level
     */
    int getIngredientQuantity(final String ingredient);

    /**
     * Consumes/decreases ingredient quantity from repository.
     *
     * @param ingredient           the ingredient
     * @param quantityToBeConsumed the quantity to be consumed
     */
    void consumeIngredient(final String ingredient, final int quantityToBeConsumed);

    /**
     * Refills/adds more quantity to the ingredient repository.
     *
     * @param ingredient the ingredient
     * @param newQuantity   the newQuantity
     */
    void refillIngredient(final String ingredient, final int newQuantity);

    // To be used in future use cases -

    // public void acquireNewIngredient()

    // public void releaseExistingIngredient()
}
