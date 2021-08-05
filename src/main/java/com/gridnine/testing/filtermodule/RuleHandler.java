package com.gridnine.testing.filtermodule;

import com.gridnine.testing.model.Flight;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RuleHandler implements Rule {

    private List<Rule> rules = new ArrayList<>();

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public void removeRule(Rule rule) {
        rules.remove(rule);
    }

    @Override
    public List<Flight> doFilter(List<Flight> flights) {
        if (Objects.isNull(flights) || flights.size() == 0) {
            throw new IllegalArgumentException("Collection must not be null or empty.");
        }
        for (Rule rule : rules) {
            flights = rule.doFilter(flights);
        }
        return flights;
    }
}
