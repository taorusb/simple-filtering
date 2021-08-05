package com.gridnine.testing;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.filtermodule.Rule;
import com.gridnine.testing.filtermodule.RuleHandler;
import com.gridnine.testing.filtermodule.impl.ExcludeArrivalsBeforeDeparture;
import com.gridnine.testing.filtermodule.impl.ExcludeTotalTimeSpentOnEarth;
import com.gridnine.testing.filtermodule.impl.ExcludeUpToCurrentMoment;
import com.gridnine.testing.model.Flight;

import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Flight> flightList = FlightBuilder.createFlights();
        RuleHandler ruleHandler = new RuleHandler();
        Rule rule1 = new ExcludeTotalTimeSpentOnEarth();
        Rule rule2 = new ExcludeUpToCurrentMoment();
        Rule rule3 = new ExcludeArrivalsBeforeDeparture();
        Stream.of(rule1, rule2, rule3).forEach(ruleHandler::addRule);
        ruleHandler.doFilter(flightList).forEach(System.out::println);
    }
}
