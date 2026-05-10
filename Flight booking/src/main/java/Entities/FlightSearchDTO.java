package Entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FlightSearchDTO {

    private Flight flight;
    private Fare fare;
}
