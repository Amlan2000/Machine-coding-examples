package Entities;


import Enums.ChannelEnum;
import Enums.ProviderAvailability;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@Builder
public class Provider {

    private String providerID;
    private String providerName;

    @Builder.Default
    private HashMap<ChannelEnum,ProviderAvailability> providerChannelAvailabilityHashMap = new HashMap<>(); // channel name -> provider availability for that channel
}
