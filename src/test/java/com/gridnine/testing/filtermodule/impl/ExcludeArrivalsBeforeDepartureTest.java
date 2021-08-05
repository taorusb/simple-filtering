package com.gridnine.testing.filtermodule.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExcludeArrivalsBeforeDepartureTest {

    static ExcludeArrivalsBeforeDeparture beforeDeparture = new ExcludeArrivalsBeforeDeparture();
    static List<Flight> flightList = new ArrayList<>();
    static LocalDateTime time = LocalDateTime.now();

    @BeforeAll
    static void setUp() {
        List<Segment> segments = new ArrayList<>();
        Segment arrivalsBeforeDeparture = new Segment(time, time.minusHours(2));
        Segment simple = new Segment(time, time.plusHours(2L));
        segments.add(arrivalsBeforeDeparture);
        segments.add(simple);
        flightList.add(new Flight(segments));
        segments = new ArrayList<>();
        segments.add(simple);
        flightList.add(new Flight(segments));
    }

    @Test
    void doFilter_throws_exception() {
        assertThrows(IllegalArgumentException.class, () -> beforeDeparture.doFilter(null));
        assertThrows(IllegalArgumentException.class, () -> beforeDeparture.doFilter(new ArrayList<Flight>()));
    }

    @Test
    void doFilter_filters_result() {
        assertEquals(2, flightList.size());
        assertEquals(1, beforeDeparture.doFilter(flightList).size());
    }
}