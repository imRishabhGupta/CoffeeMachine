package com.dunzo.coffeemachine.repository;

import com.dunzo.coffeemachine.exception.IngredientNotSupportedException;
import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The type Ingredient repository.
 * Please read java doc of interface.
 */
public class IngredientRepositoryImpl implements IngredientRepository {

    /**
     * I chose to use ConcurrentHashMap here to achieve synchronisation during consuming and refilling ingredient.
     *
     */
    private final ConcurrentHashMap<String, Integer> ingredientQuantityMap;

    /**
     * Instantiates a new Ingredient repository.
     *
     * @param ingredientQuantityMap the ingredient quantity map
     */
    public IngredientRepositoryImpl(Map<String, Integer> ingredientQuantityMap) {
        this.ingredientQuantityMap = new ConcurrentHashMap<>(ingredientQuantityMap);
    }

    @Override
    public Map<String, Integer> getAllIngredients() {
        return ImmutableMap.copyOf(ingredientQuantityMap);
    }

    @Override
    public boolean isIngredientPresent(final String ingredient) {
        return ingredientQuantityMap.containsKey(ingredient);
    }

    @Override
    public int getIngredientQuantity(final String ingredient) {
        return ingredientQuantityMap.getOrDefault(ingredient, 0);
    }

    @Override
    public void consumeIngredient(final String ingredient, final int quantityToBeConsumed) {
        if (!ingredientQuantityMap.containsKey(ingredient)) {
            throw new IngredientNotSupportedException(String.format("%s is not available in the CoffeeMachine.",
                    ingredient));
        }

        ingredientQuantityMap.put(ingredient, ingredientQuantityMap.get(ingredient) - quantityToBeConsumed);
    }

    @Override
    public void refillIngredient(final String ingredient, final int quantity) {
        ingredientQuantityMap.put(ingredient, quantity);
    }
}

