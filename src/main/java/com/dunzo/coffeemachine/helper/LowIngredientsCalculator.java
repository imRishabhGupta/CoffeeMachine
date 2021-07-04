package com.dunzo.coffeemachine.helper;

import com.dunzo.coffeemachine.repository.IngredientRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The type Low ingredients calculator. This class was created in order to fulfil this requirement -
 * "There would be an indicator that would show which all ingredients are running low."
 */
public class LowIngredientsCalculator {

    // Threshold can be adjusted
    private static final int THRESHOLD = 100;
    private final IngredientRepository ingredientRepository;

    /**
     * Instantiates a new Low ingredients calculator.
     *
     * @param ingredientRepository the ingredient repository
     */
    public LowIngredientsCalculator(final IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    /**
     * Gets low ingredients.
     *
     * @return the low ingredients
     */
    public List<String> getLowIngredients() {

        return ingredientRepository.getAllIngredients()
                .entrySet()
                .parallelStream()
                .filter(stringIntegerEntry -> stringIntegerEntry.getValue() <= THRESHOLD)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
