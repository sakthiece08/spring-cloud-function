package com.teqmonic.serverless.service;

import com.teqmonic.serverless.model.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SubscriptionService {

    private static final Logger log = LoggerFactory.getLogger(SubscriptionService.class);

    private final AtomicInteger id = new AtomicInteger();
    private final List<Subscription> list = new ArrayList<>();

    public List<Subscription> getSubscriptionList() {
        return list;
    }

    public Optional<Subscription> getSubscription(String name) {
        log.info("Get subscription for name {}", name);
        return list.stream().filter(subs -> subs.name().equalsIgnoreCase(name)).findFirst();
    }

    public void addSubscription(String name) {
        log.info("Adding subscription for name {} ", name);
        list.add(new Subscription(id.addAndGet(1), name));
    }

    public void deleteSubscription(String name) {
        log.info("Clearing a subscriptions {}", name);
        Optional<Subscription> element = list.stream().filter(subs -> subs.name().equalsIgnoreCase(name)).findFirst();
        element.ifPresent(list::remove);
    }
}
