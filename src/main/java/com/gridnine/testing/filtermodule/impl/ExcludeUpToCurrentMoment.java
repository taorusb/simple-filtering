package com.gridnine.testing.filtermodule.impl;

import com.gridnine.testing.filtermodule.Rule;
import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ExcludeUpToCurrentMoment implements Rule {

    @Override
    public List<Flight> doFilter(List<Flight> flights) {
        if (Objects.isNull(flights) || flights.size() == 0) {
            throw new IllegalArgumentException("Collection must not be null or empty.");
        }
        ChronoLocalDateTime localDate = LocalDateTime.from(ZonedDateTime.now());
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .noneMatch(segment -> segment.getDepartureDate().isBefore(localDate)))
                .collect(Collectors.toList());
    }
}
