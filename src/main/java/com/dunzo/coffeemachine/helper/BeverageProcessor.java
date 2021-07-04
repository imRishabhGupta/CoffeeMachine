package com.dunzo.coffeemachine.helper;

import com.dunzo.coffeemachine.exception.BeverageNotSupportedException;
import com.dunzo.coffeemachine.repository.BeverageRepository;
import com.dunzo.coffeemachine.repository.IngredientRepository;

import java.util.Map;

/**
 * This class is responsible for processing the given beverage.
 */
public class BeverageProcessor {

    private final BeverageRepository beverageRepository;
    private final IngredientRepository ingredientRepository;

    /**
     * Instantiates a new Beverage processor.
     *
     * @param beverageRepository   the beverage repository
     * @param ingredientRepository the ingredient repository
     */
    public BeverageProcessor(final BeverageRepository beverageRepository,
                             final IngredientRepository ingredientRepository) {
        this.beverageRepository = beverageRepository;
        this.ingredientRepository = ingredientRepository;
    }

    /**
     * Function processes the given beverage. It does following:
     *  1. Checks if the ingredients present in beverage composition are available in IngredientRepository.
     *  2. Checks if the ingredients present in beverage composition are sufficiently present in IngredientRepository.
     *  3. Consumes the ingredients present in beverage composition from IngredientRepository.
     *
     * Please note - First 2 checks have been intentionally kept in separate loops so that we can make sure output
     * contains "not available" instead of "not sufficient".
     * @param beverage the beverage
     */
    public synchronized void process(final String beverage) {

        // get beverage composition
        final Map<String, Integer> beverageComposition = beverageRepository.getBeverageComposition(beverage);

        if(beverageComposition == null)
            throw new BeverageNotSupportedException(String.format("%s is not supported by CoffeeMachine", beverage));

        // validate if beverage composition is available in ingredient repository
        for (Map.Entry<String, Integer> ingredientNeeded: beverageComposition.entrySet()) {

            if (!ingredientRepository.isIngredientPresent(ingredientNeeded.getKey())) {
                System.out.println(beverage + " cannot be prepared because " + ingredientNeeded.getKey() +
                        " is not available");
                return;
            }
        }

        for (Map.Entry<String, Integer> ingredientNeeded: beverageComposition.entrySet()) {

            if (ingredientRepository.getIngredientQuantity(ingredientNeeded.getKey()) < ingredientNeeded.getValue()) {
                System.out.println(beverage + " cannot be prepared because " + ingredientNeeded.getKey() +
                        " is not sufficient");
                return;
            }
        }

        // consume ingredients from ingredient repository in parallel for faster processing
        beverageComposition.entrySet()
                .parallelStream()
                .forEach(ingredientNeeded ->
                        ingredientRepository.consumeIngredient(ingredientNeeded.getKey(), ingredientNeeded.getValue()));

        System.out.println(beverage + " is prepared");
    }

}
