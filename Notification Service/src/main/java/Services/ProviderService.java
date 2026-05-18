package Services;

import Entities.Provider;
import Enums.ChannelEnum;
import Enums.ProviderAvailability;

import java.util.List;

public interface ProviderService {

    void addProvider(Provider provider);
    List<Provider> getProviders();

    ProviderAvailability checkProviderChannelAvailability(ChannelEnum channelName, String providerID);
    void updateProviderChannelAvailability(String providerID, ChannelEnum channelName, ProviderAvailability providerAvailability);

}
