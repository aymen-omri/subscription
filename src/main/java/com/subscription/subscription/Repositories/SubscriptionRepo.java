package com.subscription.subscription.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.subscription.subscription.Models.Subscription;

public interface SubscriptionRepo extends JpaRepository<Subscription, Long> {

    @Query("select s from Subscription s where s.provider.id_provider = ?1 ")
    List<Subscription> findByProviderId(long id);

    List<Subscription> findBySubscriberName(String name);

}
