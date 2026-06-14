package com.university.techcorp.events;

import com.university.techcorp.domain.Company;

public class MarketSlowdownEvent implements GameEvent {

    @Override
    public void apply(Company company) {
        company.reduceCash(5000);
    }

    @Override
    public String getDescription() {
        return "Market Slowdown! The company loses 5000 cash.";
    }
}
