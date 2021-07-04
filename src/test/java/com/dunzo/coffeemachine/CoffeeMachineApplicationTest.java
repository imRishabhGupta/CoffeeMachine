package com.dunzo.coffeemachine;

import com.dunzo.coffeemachine.exception.InvalidCoffeeMachineException;
import com.dunzo.coffeemachine.machine.CoffeeMachine;
import com.dunzo.coffeemachine.machine.CoffeeMachineImpl;
import com.dunzo.coffeemachine.model.CoffeeMachineModel;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

public class CoffeeMachineApplicationTest {

    @Before
    public void setup() {

    }

    // testing with valid input
    @Test
    public void test_WITH_givenSampleInput() {
        CoffeeMachineApplication.main("input1.json");
    }

    // testing with 0 outlets
    @Test(expected = IllegalArgumentException.class)
    public void test_WHEN_outlets_IS_0_IN_inputJson() {
        CoffeeMachineApplication.main("input2.json");
    }

    // all not available
    @Test
    public void test_WHEN_no_beverage_has_all_ingredients_available() {
        CoffeeMachineApplication.main("input3.json");
    }

    // 1 will not be made. Since all beverages are being processed in parallel, so any one of them will not be made and
    // it depends on java runtime which will not be made.
    @Test
    public void test_WHEN_one_of_the_beverage_will_NOT_be_made() {
        CoffeeMachineApplication.main("input4.json");
    }

    // only 1 be made - depends on teh java runtime which one will be made
    @Test
    public void test_WHEN_only_1_will_be_made() {
        CoffeeMachineApplication.main("input5.json");
    }

    // happy case - all will be made
    @Test
    public void test_WHEN_all_beverages_will_be_made() {
        CoffeeMachineApplication.main("input6.json");
    }

    // invalid json
    @Test(expected = InvalidCoffeeMachineException.class)
    public void test_WHEN_invalid_json_IS_given_in_input() {
        CoffeeMachineApplication.main("input7.json");
    }

    // viewing low ingredient and refilling them
    @Test
    public void test_low_ingredients_display_AND_refill_ingredient() throws InterruptedException {
        File file = null;
        try {
            file = new File(this.getClass()
                    .getClassLoader()
                    .getResource("input6.json")
                    .toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String inputJson = sb.toString();

            final CoffeeMachine coffeeMachine = new CoffeeMachineImpl(CoffeeMachineModel.fromJson(inputJson));

            // Preparing all available beverages in parallel
            coffeeMachine.getAvailableBeverages()
                    .parallelStream()
                    .forEach(coffeeMachine::prepareBeverage);

            // Sleep for 1s to make sure that all beverages are processed, otherwise it will show intermediate results
            Thread.sleep(1000);

            coffeeMachine.getLowIngredients();// green mixture will be here. Please validate from console output

            coffeeMachine.addIngredient("green_mixture", 400);

            coffeeMachine.getLowIngredients();// green mixture won't be here. Please validate from console output

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
