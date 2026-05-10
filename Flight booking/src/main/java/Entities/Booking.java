package Entities;

import lombok.Builder;
import lombok.Data;


import java.util.List;


@Data
@Builder
public class Booking {

    private String bookingID;
    private String userID;
    private String flightID;
    private List<String> seats;
    private double farePrice;
}
