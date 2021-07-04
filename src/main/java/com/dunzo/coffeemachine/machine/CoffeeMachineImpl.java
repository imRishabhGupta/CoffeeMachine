package com.dunzo.coffeemachine.machine;

import com.dunzo.coffeemachine.helper.BeverageProcessor;
import com.dunzo.coffeemachine.helper.BeverageProcessorTask;
import com.dunzo.coffeemachine.helper.CoffeeMachineValidator;
import com.dunzo.coffeemachine.helper.LowIngredientsCalculator;
import com.dunzo.coffeemachine.helper.RejectedBeverageProcessingHandler;
import com.dunzo.coffeemachine.model.CoffeeMachineModel;
import com.dunzo.coffeemachine.repository.BeverageRepository;
import com.dunzo.coffeemachine.repository.BeverageRepositoryImpl;
import com.dunzo.coffeemachine.repository.IngredientRepository;
import com.dunzo.coffeemachine.repository.IngredientRepositoryImpl;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * The type Coffee machine.
 * Please read java doc of interface.
 */
public class CoffeeMachineImpl implements CoffeeMachine {

    private final ThreadPoolExecutor executor;
    private final IngredientRepository ingredientRepository;
    private final BeverageRepository beverageRepository;
    private final LowIngredientsCalculator lowIngredientsCalculator;
    private final BeverageProcessor beverageProcessor;

    /**
     * Instantiates a new Coffee machine.
     *
     * @param coffeeMachineModel the coffee machine model
     */
    public CoffeeMachineImpl(final CoffeeMachineModel coffeeMachineModel) {

        CoffeeMachineValidator.validateCoffeeMachine(coffeeMachineModel);

        this.ingredientRepository = new IngredientRepositoryImpl(coffeeMachineModel.getMachine()
                .getTotal_items_quantity());

        this.beverageRepository = new BeverageRepositoryImpl(coffeeMachineModel.getMachine().getBeverages());

        int outlets = coffeeMachineModel.getMachine().getOutlets().getCount_n();

        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(outlets);
        this.executor.setRejectedExecutionHandler(new RejectedBeverageProcessingHandler());
        this.lowIngredientsCalculator = new LowIngredientsCalculator(ingredientRepository);

        this.beverageProcessor = new BeverageProcessor(beverageRepository, ingredientRepository);
    }

    @Override
    public void getLowIngredients() {
        System.out.println("Low Ingredients are: " + lowIngredientsCalculator.getLowIngredients());
    }

    @Override
    public void prepareBeverage(final String beverage) {
        executor.execute(new BeverageProcessorTask(beverageProcessor, beverage));
    }

    @Override
    public List<String> getAvailableBeverages() {
        return beverageRepository.getAvailableBeverages();
    }

    @Override
    public void addIngredient(final String ingredient, final int newQuantity) {
        ingredientRepository.refillIngredient(ingredient, newQuantity);
    }

    @Override
    public void getAllIngredients() {
        System.out.println("All Ingredients are: " + ingredientRepository.getAllIngredients());
    }

}
