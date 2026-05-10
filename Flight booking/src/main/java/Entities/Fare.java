package Entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Fare {

    private String fareID;
    private double farePrice;
    private List<String> seats;
}
