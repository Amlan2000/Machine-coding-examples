package Entities;

import Enums.TierType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Tier {

    private String tierID;
    private TierType tierType;
    private final int maxRequests;
}
