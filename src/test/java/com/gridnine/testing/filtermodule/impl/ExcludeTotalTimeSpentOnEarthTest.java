package com.gridnine.testing.filtermodule.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExcludeTotalTimeSpentOnEarthTest {

    static ExcludeTotalTimeSpentOnEarth spentOnEarth = new ExcludeTotalTimeSpentOnEarth();
    static List<Flight> flightList = new ArrayList<>();
    static LocalDateTime time = LocalDateTime.now();

    @BeforeAll
    static void setUp() {
        List<Segment> segments = new ArrayList<>();
        Segment totalTimeSpentOnEarth = new Segment(time.plusDays(1L), time.plusDays(3L));
        Segment simple = new Segment(time.plusDays(3L).plusMinutes(121L), time.plusDays(4L));
        segments.add(totalTimeSpentOnEarth);
        segments.add(simple);
        flightList.add(new Flight(segments));
        segments = new ArrayList<>();
        segments.add(simple);
        flightList.add(new Flight(segments));
    }

    @Test
    void doFilter_throws_exception() {
        assertThrows(IllegalArgumentException.class, () -> spentOnEarth.doFilter(null));
        assertThrows(IllegalArgumentException.class, () -> spentOnEarth.doFilter(new ArrayList<Flight>()));
    }

    @Test
    void doFilter_filters_result() {
        assertEquals(2, flightList.size());
        assertEquals(1, spentOnEarth.doFilter(flightList).size());
    }
}