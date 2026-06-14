package com.university.techcorp.events;

import com.university.techcorp.domain.Company;

public class TechBoomEvent implements GameEvent {

    @Override
    public void apply(Company company) {
        company.addCash(10_000);
    }

    @Override
    public String getDescription() {
        return "Tech Boom! Investor interest is high. The company gains 10000 cash.";
    }
}
