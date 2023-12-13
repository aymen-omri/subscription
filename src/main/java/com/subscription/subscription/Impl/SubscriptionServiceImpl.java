package com.subscription.subscription.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.subscription.subscription.Models.Subscription;
import com.subscription.subscription.Repositories.SubscriptionRepo;
import com.subscription.subscription.Services.SubscriptionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepo subscriptionRepo;

    @Override
    public void addSubscription(Subscription subscription) {
        subscriptionRepo.save(subscription);
    }

    @Override
    @Transactional
    public void updateSubscription(Subscription subscription, long id) {
        Subscription subscription2 = subscriptionRepo.findById(id).orElse(null);
        if (subscription2 != null) {
            subscription2.setSubscriberName(subscription.getSubscriberName());
            subscription2.setEndDate(subscription.getEndDate());
            subscription2.setStartDate(subscription.getStartDate());
            subscription2.setActive(subscription.getActive());
        }
    }

    @Override
    public List<Subscription> getAll() {
        return subscriptionRepo.findAll();
    }

    @Override
    public List<Subscription> getAllByName(String name) {
        return subscriptionRepo.findBySubscriberName(name);
    }

    @Override
    public Subscription getById(long id) {
        return subscriptionRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteSubscription(long id) {
        subscriptionRepo.deleteById(id);
    }

}
