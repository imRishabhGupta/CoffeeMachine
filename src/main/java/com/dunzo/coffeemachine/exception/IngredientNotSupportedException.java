package com.dunzo.coffeemachine.exception;

/**
 * This exception is thrown when unknown ingredient is being consumed from the Ingredient repository.
 */
public class IngredientNotSupportedException extends RuntimeException {

    /**
     * Instantiates a new Ingredient not supported exception.
     *
     * @param message the message
     */
    public IngredientNotSupportedException(final String message) {
        super(message);
    }
}
