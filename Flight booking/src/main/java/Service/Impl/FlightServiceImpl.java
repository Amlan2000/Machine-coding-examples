package Service.Impl;

import Entities.Fare;
import Entities.Flight;
import Entities.FlightSearchDTO;
import Service.FlightService;

import java.util.*;
import java.util.stream.Collectors;

public class FlightServiceImpl implements FlightService {

    public HashMap<String, Flight> flightMap = new HashMap<>();

    @Override
    public Flight getFlight(String id) {
        return flightMap.get(id);
    }

    @Override
    public void addFlight(String flightID, String flightNo, String airline, String from, String to, int deptDate, int deptTime, int arrTime) {

        if(!flightMap.isEmpty() && flightMap.containsKey(flightID)){
            System.out.println("\n Flight is already added. \n");
            return;
        }

        Flight flight = new Flight(flightID,flightNo,airline,from,to,deptDate,deptTime,arrTime);

        flightMap.put(flightID,flight);
        System.out.println("\n Flight is successfully added.\n");
    }

    @Override
    public void searchFlight(String from, String to, int deptDate, int paxCount) {
        for(Flight flight: flightMap.values()){
            if(flight.getSource().equals(from) && flight.getDst().equals(to) && flight.getDeptDate()==deptDate){
                for(Fare fare:flight.getFareMap().values()){
                    if(fare.getSeats().size()>=paxCount){
                        System.out.println("\nFlightId: "+flight.getFlightID()+" is available\n");
                    }
                }
            }
            else{
                System.out.println("\nNo flights found\n");
            }
        }
    }

    @Override
    public void searchFlight(String from, String to, int deptDate, int paxCount,String airline, String sortField, String sortType ) {

        List<FlightSearchDTO> flightSearchDTOList = new ArrayList<>();
        for(Flight flight: flightMap.values()){
            if(flight.getSource().equals(from) && flight.getDst().equals(to) && flight.getDeptDate()==deptDate && flight.getAirline().equals(airline)){
                for(Fare fare:flight.getFareMap().values()){
                    if(fare.getSeats().size()>=paxCount){
                       flightSearchDTOList.add(new FlightSearchDTO(flight,fare));
                    }
                }
            }
            else{
                System.out.println("\nNo flights found\n");
            }
        }

        Comparator<FlightSearchDTO> comparator=null;
        switch(sortField) {
            case "PRICE":
                comparator = Comparator.comparingDouble(obj -> obj.getFare().getFarePrice());
                break;
            case "deptTime":
                comparator = Comparator.comparingInt(obj -> obj.getFlight().getDeptTime());
                break;
        }
        if(comparator==null) {
            return;
        }

        if(sortType.equalsIgnoreCase("desc")){
            comparator=comparator.reversed();
        }

        flightSearchDTOList.sort(comparator);


        for(FlightSearchDTO flightSearchDTO:flightSearchDTOList){
            System.out.println("Flight ID: "+ flightSearchDTO.getFlight().getFlightID()+" is available");
        }



    }

        @Override
    public void addInventory(String flightID, String fareID, double farePrice, List<String> seats){
        Flight flight = flightMap.get(flightID);

        if(flight!=null){
            Fare fare = new Fare(fareID,farePrice,seats);
            flight.addFare(fare);
        }
    }
}
