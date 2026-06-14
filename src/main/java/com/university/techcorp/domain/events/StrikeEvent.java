package com.university.techcorp.events;

import com.university.techcorp.domain.Company;

public class StrikeEvent implements GameEvent {

    @Override
    public void apply(Company company) {
        company.reduceCash(8_000);
    }

    @Override
    public String getDescription() {
        return "Employee Strike! Work stops for a day. The company loses 8000 cash.";
    }
}
