package com.dunzo.coffeemachine.helper;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * The type Rejected beverage processing handler - to handle the case when beverage processing task is rejected by
 * Thread Executor.
 */
public class RejectedBeverageProcessingHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(final Runnable runnable, final ThreadPoolExecutor executor) {
        System.out.println("Beverage processing has been rejected by CoffeeMachine.");
    }
}
