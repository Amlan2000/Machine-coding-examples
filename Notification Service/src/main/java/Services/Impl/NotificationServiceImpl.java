package Services.Impl;

import Entities.ClientRequest;
import Entities.Provider;
import Services.NotificationService;
import Services.ProviderSelectionStrategy;
import Services.ProviderService;

import java.util.List;

public class NotificationServiceImpl
        implements NotificationService {

    private final ProviderService providerService;

    private final ProviderSelectionStrategy
            providerSelectionStrategy;

    public NotificationServiceImpl(ProviderService providerService, ProviderSelectionStrategy strategy) {
        this.providerService = providerService;
        this.providerSelectionStrategy = strategy;
    }

    @Override
    public void sendNotification(ClientRequest request) {

        List<Provider> providers = providerService.getProviders();

        Provider provider = providerSelectionStrategy.selectProvider(providers, request.getChannel());

        System.out.println("Selected provider: " + provider.getProviderName());
    }
}