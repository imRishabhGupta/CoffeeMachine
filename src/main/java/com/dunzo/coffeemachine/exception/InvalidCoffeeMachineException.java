package com.dunzo.coffeemachine.exception;

/**
 * This exception is thrown when invalid input is provided during the start of CoffeeMachine
 */
public class InvalidCoffeeMachineException extends RuntimeException {

    /**
     * Instantiates a new Invalid coffee machine exception.
     *
     * @param message the message
     */
    public InvalidCoffeeMachineException(final String message) {
        super(message);
    }
}
