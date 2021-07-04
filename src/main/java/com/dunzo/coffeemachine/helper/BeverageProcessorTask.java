package com.dunzo.coffeemachine.helper;

/**
 * This tasks is responsible for invoking beverage processor for the given beverage
 */
public class BeverageProcessorTask implements Runnable {

    private final BeverageProcessor beverageProcessor;
    private final String beverage;

    /**
     * Instantiates a new Beverage processor task.
     *
     * @param beverageProcessor the beverage processor
     * @param beverage          the beverage
     */
    public BeverageProcessorTask(final BeverageProcessor beverageProcessor, final String beverage) {
        this.beverageProcessor = beverageProcessor;
        this.beverage = beverage;
    }

    @Override
    public void run() {

        beverageProcessor.process(this.beverage);

    }
}
