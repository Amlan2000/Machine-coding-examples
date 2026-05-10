package Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@RequiredArgsConstructor
public class Flight {

    private String flightID;
    private String flightNo;
    private String airline;
    private String dst;
    private String source;
    private int deptDate;
    private int deptTime;
    private int arrTime;

    private Map<String, Fare> fareMap= new HashMap<>();

    public void addFare(Fare fare) {
        this.fareMap.put(fare.getFareID(), fare);
    }

    public Flight(String flightID, String flightNo, String airline, String from, String to, int deptDate, int deptTime, int arrTime) {
        this.flightID=flightID;
        this.flightNo=flightNo;
        this.airline=airline;
        this.source=from;
        this.dst=to;
        this.deptDate=deptDate;
        this.deptTime=deptTime;
        this.arrTime=arrTime;
    }
}

//TODO: 1) Keep track of flightNo -> fareID and price
//      2) Keep track of flight -> seats available