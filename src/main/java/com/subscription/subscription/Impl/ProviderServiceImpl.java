package com.subscription.subscription.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.subscription.subscription.Models.Provider;
import com.subscription.subscription.Repositories.ProviderRepo;
import com.subscription.subscription.Repositories.SubscriptionRepo;
import com.subscription.subscription.Services.ProviderService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepo providerRepo;
    private final SubscriptionRepo subscriptionRepo;

    @Override
    public void addProvider(Provider provider) {
        providerRepo.save(provider);
    }

    @Override
    @Transactional
    public void updateProvider(Provider provider, long id) {
        Provider provider2 = providerRepo.findById(id).orElse(null);
        if (provider2 != null) {
            provider2.setProviderName(provider.getProviderName());
        } else {
            throw new IllegalArgumentException("No such provider!");
        }
    }

    @Override
    public Provider getProviderById(long id) {
        return providerRepo.findById(id).orElse(null);
    }

    @Override
    public List<Provider> getAllProviders() {
        return providerRepo.findAll();
    }

    @Override
    public void deleteProvider(long id) {
        Provider provider = providerRepo.findById(id).orElse(null);
        if (provider != null) {
            subscriptionRepo.deleteAll(provider.getSubscriptions());
            providerRepo.deleteById(id);
        } else {
            throw new IllegalArgumentException("No such provider exists");
        }
    }

}
