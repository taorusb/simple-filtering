package com.gridnine.testing.filtermodule.impl;

import com.gridnine.testing.filtermodule.Rule;
import com.gridnine.testing.model.Flight;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ExcludeArrivalsBeforeDeparture implements Rule {

    @Override
    public List<Flight> doFilter(List<Flight> flights) {
        if (Objects.isNull(flights) || flights.size() == 0) {
            throw new IllegalArgumentException("Collection must not be null or empty.");
        }
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .noneMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }
}
