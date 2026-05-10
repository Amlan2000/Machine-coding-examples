package Service.Impl;

import Entities.Booking;
import Entities.Fare;
import Entities.Flight;
import Entities.User;
import Service.BookingService;
import Service.FlightService;
import Service.UserService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BookingServiceImpl implements BookingService {

    public Map<String,Booking> bookingHashMap = new ConcurrentHashMap<>();

    private FlightService flightService;
    private UserService userService;

    public BookingServiceImpl(FlightService flightService,
                              UserService userService) {

        this.flightService = flightService;
        this.userService = userService;
    }

    @Override
    public void bookFlight(String flightID, String userID, String from, String to, String flightNumber, String airline, int departDate, String fareID, List<String> seats){


        Flight flight = flightService.getFlight(flightID);
        User user = userService.getUser(userID);
        Fare fare = flight.getFareMap().get(fareID);

        synchronized (fare){

            if(!fare.getSeats().containsAll(seats)){
                System.out.println("Seats not avl \n");
                return;
            }

            double totalPrice=seats.size() * fare.getFarePrice();
            if(user.getFunds()<totalPrice){
                System.out.println("\n Insufficient funds.\n");
                return;
            }

            user.setFunds(user.getFunds()-totalPrice);

            List<String> currentSeats = new ArrayList<>(fare.getSeats());
            currentSeats.removeAll(seats);
            fare.setSeats(currentSeats);

            // 4. Record Booking
            String bookingID = UUID.randomUUID().toString();
            Booking booking = Booking.builder()
                    .bookingID(bookingID)
                    .flightID(flightID)
                    .userID(userID)
                    .seats(seats)
                    .farePrice(totalPrice)
                    .build();

            bookingHashMap.put(bookingID, booking);
            System.out.println("Booking successful! ID: " + bookingID);

        }


    }
}
