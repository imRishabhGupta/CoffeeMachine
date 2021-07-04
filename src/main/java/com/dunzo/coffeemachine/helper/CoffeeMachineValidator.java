package com.dunzo.coffeemachine.helper;

import com.dunzo.coffeemachine.model.CoffeeMachineModel;
import com.google.common.base.Preconditions;

/**
 * The type Coffee machine validator.
 */
public class CoffeeMachineValidator {

    /**
     * Validate input provided during the instantiation of CoffeeMachine
     *
     * @param coffeeMachineModel the coffee machine model
     */
    public static void validateCoffeeMachine(final CoffeeMachineModel coffeeMachineModel) {

        Preconditions.checkNotNull(coffeeMachineModel,
                "CoffeeMachine input can't be null!");
        Preconditions.checkNotNull(coffeeMachineModel.getMachine(),
                "CoffeeMachine input can't be null!");
        Preconditions.checkNotNull(coffeeMachineModel.getMachine().getBeverages(),
                "CoffeeMachine beverages can't be null!");
        Preconditions.checkArgument(!coffeeMachineModel.getMachine().getBeverages().isEmpty(),
                "CoffeeMachine beverages can't be empty!");
        Preconditions.checkNotNull(coffeeMachineModel.getMachine().getTotal_items_quantity(),
                "CoffeeMachine ingredients can't be null!");
        Preconditions.checkArgument(!coffeeMachineModel.getMachine().getTotal_items_quantity().isEmpty(),
                "CoffeeMachine ingredients can't be empty!");
        Preconditions.checkNotNull(coffeeMachineModel.getMachine().getOutlets(),
                "Coffee Machine needs to have at least 1 outlet to serve beverage!");
        Preconditions.checkArgument(coffeeMachineModel.getMachine().getOutlets().getCount_n() > 0,
                "Coffee Machine needs to have at least 1 outlet to serve beverage!");

    }

}
