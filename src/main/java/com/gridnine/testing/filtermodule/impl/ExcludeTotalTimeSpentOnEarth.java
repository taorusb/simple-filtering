package com.gridnine.testing.filtermodule.impl;

import com.gridnine.testing.filtermodule.Rule;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExcludeTotalTimeSpentOnEarth implements Rule {

    @Override
    public List<Flight> doFilter(List<Flight> flights) {
        if (Objects.isNull(flights) || flights.size() == 0) {
            throw new IllegalArgumentException("Collection must not be null or empty.");
        }
        List<Flight> result = new ArrayList<>();
        List<Segment> segments;
        for (Flight flight : flights) {
            if (flight.getSegments().size() == 1) {
                result.add(flight);
                continue;
            }
            for (int i = 0; i < flight.getSegments().size() - 1; i++) {
                segments = flight.getSegments();
                if (!segments.get(i + 1).getDepartureDate().isAfter(segments.get(i).getArrivalDate().plusHours(2L))) {
                    result.add(flight);
                    break;
                }
            }
        }
        return result;
    }
}
