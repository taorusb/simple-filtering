package com.gridnine.testing.filtermodule.impl;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.filtermodule.Rule;
import com.gridnine.testing.filtermodule.RuleHandler;
import com.gridnine.testing.model.Flight;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RuleHandlerTest {

    static RuleHandler ruleHandler = new RuleHandler();
    static List<Flight> flightList = FlightBuilder.createFlights();

    @BeforeAll
    static void setUp() {
        Rule rule1 = new ExcludeArrivalsBeforeDeparture();
        Rule rule2 = new ExcludeUpToCurrentMoment();
        Rule rule3 = new ExcludeTotalTimeSpentOnEarth();
        Stream.of(rule1, rule2, rule3).forEach(ruleHandler::addRule);
    }

    @Test
    void doFilter_throws_exception() {
        assertThrows(IllegalArgumentException.class, () -> ruleHandler.doFilter(null));
        assertThrows(IllegalArgumentException.class, () -> ruleHandler.doFilter(new ArrayList<Flight>()));
    }

    @Test
    void doFilter_executes_rules() {
        assertEquals(6, flightList.size());
        assertEquals(3, ruleHandler.doFilter(flightList).size());
    }
}