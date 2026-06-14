package com.university.techcorp.events;

import com.university.techcorp.domain.Company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomEventService {

    private final List<GameEvent> events;
    private final Random random;

    public RandomEventService() {
        this.random = new Random();
        this.events = new ArrayList<>();
        events.add(new MarketSlowdownEvent());
        events.add(new TechBoomEvent());
        events.add(new StrikeEvent());
        events.add(new ClientLostEvent());
    }

    public GameEvent triggerRandomEvent(Company company) {
        int index = random.nextInt(events.size());
        GameEvent event = events.get(index);
        event.apply(company);
        return event;
    }

    public boolean shouldEventOccur() {
        return random.nextInt(3) == 0;
    }
}
