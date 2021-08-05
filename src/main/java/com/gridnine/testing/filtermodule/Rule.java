package com.gridnine.testing.filtermodule;

import com.gridnine.testing.model.Flight;

import java.util.List;

public interface Rule {

    List<Flight> doFilter(List<Flight> flights);
}
