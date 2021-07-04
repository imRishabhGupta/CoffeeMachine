package com.dunzo.coffeemachine.model;

import com.dunzo.coffeemachine.exception.InvalidCoffeeMachineException;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * The type Coffee machine model.
 */
public class CoffeeMachineModel {

    private Machine machine;

    /**
     * Gets machine.
     *
     * @return the machine
     */
    public Machine getMachine() {
        return machine;
    }

    /**
     * From json coffee machine model.
     *
     * @param inputJson the input json
     * @return the coffee machine model
     */
    public static CoffeeMachineModel fromJson(final String inputJson) {
        Gson gson = new Gson();

        try {
            return gson.fromJson(inputJson, CoffeeMachineModel.class);
        } catch (JsonSyntaxException e) {
            throw new InvalidCoffeeMachineException("Invalid input json provided to start coffee machine");
        }
    }
}
