package com.dunzo.coffeemachine;

import com.dunzo.coffeemachine.machine.CoffeeMachine;
import com.dunzo.coffeemachine.machine.CoffeeMachineImpl;
import com.dunzo.coffeemachine.model.CoffeeMachineModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * The type Coffee machine application.
 */
public class CoffeeMachineApplication {

    /**
     * The entry point of application.
     * This function accepts the input json file name in arguments.
     * Input file is to be present in resources folder of project.
     *
     * @param args the input arguments
     */
    public static void main(String... args) {

        CoffeeMachineApplication coffeeMachineApplication = new CoffeeMachineApplication();

        File file = null;
        try {
            file = new File(coffeeMachineApplication.getClass()
                    .getClassLoader()
                    .getResource(args[0])
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

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
