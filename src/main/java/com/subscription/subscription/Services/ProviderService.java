package com.subscription.subscription.Services;

import com.subscription.subscription.Models.Provider;

import java.util.List;

public interface ProviderService {
    void addProvider(Provider provider);

    void updateProvider(Provider provider, long id);

    Provider getProviderById(long id);

    List<Provider> getAllProviders();

    void deleteProvider(long id);
}
