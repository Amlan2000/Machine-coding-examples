package Services.Impl;

import Entities.Provider;
import Enums.ChannelEnum;
import Enums.ProviderAvailability;
import Exceptions.NotificationException;
import Services.ProviderSelectionStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomSelectionStrategy
        implements ProviderSelectionStrategy {

    private final Random random = new Random();

    @Override
    public Provider selectProvider(
            List<Provider> providers,
            ChannelEnum channel
    ) {

        List<Provider> eligibleProviders = new ArrayList<>();

        for(Provider provider : providers) {

            Map<ChannelEnum, ProviderAvailability> map =
                    provider.getProviderChannelAvailabilityHashMap();

            if(map.containsKey(channel)
                    && map.get(channel) == ProviderAvailability.UP) {

                eligibleProviders.add(provider);
            }
        }

        if(eligibleProviders.isEmpty()) {
            throw new NotificationException(
                    "No provider available"
            );
        }

        int index = random.nextInt(eligibleProviders.size());

        return eligibleProviders.get(index);
    }
}