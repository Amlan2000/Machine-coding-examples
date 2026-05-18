package Services.Impl;

import Entities.Provider;
import Enums.ChannelEnum;
import Enums.ProviderAvailability;
import Exceptions.NotificationException;
import Services.ProviderService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProviderServiceImpl implements ProviderService {


    public HashMap<String, Provider> providerHashMap = new HashMap<>();

    @Override
    public void addProvider(Provider provider) {

        if(!providerHashMap.isEmpty() && providerHashMap.containsKey(provider.getProviderID())){
            throw new NotificationException("Provider is already added. \n");
        }

        providerHashMap.put(provider.getProviderID(),provider);

        System.out.println("Provider "+ provider.getProviderName() + " is successfully added.\n");
    }

    @Override
    public List<Provider> getProviders() {

        List<Provider> providerList = new ArrayList<>();

        if(providerHashMap.isEmpty()){
            throw new NotificationException("Provider not found. \n");
        }

        providerHashMap.forEach((key,value)->{
            providerList.add(value);
        });

        return providerList;

    }

    @Override
    public ProviderAvailability checkProviderChannelAvailability(ChannelEnum channelName, String providerID) {

        if(providerHashMap.isEmpty() || !providerHashMap.containsKey(providerID)){
            throw new NotificationException("Provider not found. \n");
        }

        Provider provider = providerHashMap.get(providerID);
        HashMap<ChannelEnum,ProviderAvailability> availableChannelsMap = provider.getProviderChannelAvailabilityHashMap();

        if(availableChannelsMap.containsKey(channelName)){
            if(availableChannelsMap.get(channelName).equals(ProviderAvailability.UP)){
                return ProviderAvailability.UP;
            }
        }

        throw new NotificationException("Provider doesn't support this channel. \n");

    }

    @Override
    public void updateProviderChannelAvailability(String providerID, ChannelEnum channelName, ProviderAvailability providerAvailability) {

        if(providerHashMap.isEmpty() || !providerHashMap.containsKey(providerID)){
            throw new NotificationException("Provider not found. \n");
        }

        Provider provider = providerHashMap.get(providerID);
        HashMap<ChannelEnum,ProviderAvailability> availableChannelsMap = provider.getProviderChannelAvailabilityHashMap();

        if(availableChannelsMap.containsKey(channelName)){
            availableChannelsMap.put(channelName,providerAvailability);
            return;
        }

        throw new NotificationException("Provider doesn't support this channel. \n");


    }
}
