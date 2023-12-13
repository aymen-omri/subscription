package com.subscription.subscription.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subscription.subscription.Models.Provider;

public interface ProviderRepo extends JpaRepository<Provider, Long> {

}
