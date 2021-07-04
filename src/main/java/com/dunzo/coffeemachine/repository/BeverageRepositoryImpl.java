package com.dunzo.coffeemachine.repository;

import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Map;

/**
 * The type Beverage repository.
 * Please read java doc of interface.
 */
public class BeverageRepositoryImpl implements BeverageRepository {

    /**
     * I used immutable HashMap here because beverage composition is fixed and there was no mention of updating it
     * in problem statement.
     * However, if we want to extend the solution to allow changes in beverage compositions, then we can make it
     * mutable. In that case, we should also be using ConcurrentHashMap here and handle scenarios where composition is
     * being read and update at the same time.
     */
    private final ImmutableMap<String, Map<String, Integer>> beverageCompositions;

    /**
     * Instantiates a new Beverage repository.
     *
     * @param beverageCompositions the beverage compositions
     */
    public BeverageRepositoryImpl(final Map<String, Map<String, Integer>> beverageCompositions) {
        this.beverageCompositions = ImmutableMap.copyOf(beverageCompositions);
    }

    @Override
    public Map<String, Integer> getBeverageComposition(final String beverage) {
        return beverageCompositions.get(beverage);
    }

    @Override
    public List<String> getAvailableBeverages() {
        return beverageCompositions.keySet().asList();
    }
}
