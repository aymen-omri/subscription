package com.subscription.subscription.Services;

import java.util.List;

import com.subscription.subscription.Models.Subscription;

public interface SubscriptionService {
    void addSubscription(Subscription subscription);

    void updateSubscription(Subscription subscription, long id);

    List<Subscription> getAll();

    List<Subscription> getAllByName(String name);

    Subscription getById(long id);

    void deleteSubscription(long id);
}
