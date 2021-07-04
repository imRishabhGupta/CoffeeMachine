package com.dunzo.coffeemachine.exception;

/**
 * This exception is thrown when beverage asked to prepare is not supported by CoffeeMachine/
 */
public class BeverageNotSupportedException extends RuntimeException {

    /**
     * Instantiates a new Beverage not supported exception.
     *
     * @param message the message
     */
    public BeverageNotSupportedException(final String message) {
        super(message);
    }
}
