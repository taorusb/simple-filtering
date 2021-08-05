package com.gridnine.testing.filtermodule.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExcludeUpToCurrentMomentTest {

    static ExcludeUpToCurrentMoment currentMoment = new ExcludeUpToCurrentMoment();
    static List<Flight> flightList = new ArrayList<>();
    static LocalDateTime time = LocalDateTime.now();

    @BeforeAll
    static void setUp() {
        List<Segment> segments = new ArrayList<>();
        Segment departureBeforeCurrentMoment = new Segment(time.minusDays(1L), time.plusDays(3L));
        Segment simple = new Segment(time.plusDays(1L), time.plusDays(2L));
        segments.add(departureBeforeCurrentMoment);
        segments.add(simple);
        flightList.add(new Flight(segments));
        segments = new ArrayList<>();
        segments.add(simple);
        flightList.add(new Flight(segments));
    }

    @Test
    void doFilter_throws_exception() {
        assertThrows(IllegalArgumentException.class, () -> currentMoment.doFilter(null));
        assertThrows(IllegalArgumentException.class, () -> currentMoment.doFilter(new ArrayList<Flight>()));
    }

    @Test
    void doFilter_filters_result() {
        assertEquals(2, flightList.size());
        assertEquals(1, currentMoment.doFilter(flightList).size());
    }
}