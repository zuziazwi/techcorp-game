package com.university.techcorp.events;

import com.university.techcorp.domain.Company;

public class ClientLostEvent implements GameEvent {

    @Override
    public void apply(Company company) {
        company.reduceCash(12_000);
    }

    @Override
    public String getDescription() {
        return "Client Lost! A major client cancelled their contract. The company loses 12000 cash.";
    }
}
