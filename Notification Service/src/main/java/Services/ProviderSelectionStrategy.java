package Services;

import Entities.Provider;
import Enums.ChannelEnum;

import java.util.List;

public interface ProviderSelectionStrategy {

    Provider selectProvider(List<Provider> providersList, ChannelEnum channel);
}
