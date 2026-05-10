package Service;



import Entities.Flight;

import java.util.List;

public interface FlightService {

    void addFlight(String flightID, String flightNo, String airline, String from, String to, int deptDate, int deptTime, int arrTime);

    void searchFlight(String from, String to, int deptDate, int paxCount);

    void searchFlight(String from, String to, int deptDate, int paxCount,String airline,String sortField, String sortMethod);


    void addInventory(String flightID, String fareID, double farePrice, List<String> seats);

    Flight getFlight(String id);
}
