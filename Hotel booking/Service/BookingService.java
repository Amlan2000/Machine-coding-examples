package Service;

import Entities.Booking;
import Entities.Room;
import Entities.Status;
import Entities.User;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookingService {

    private HashMap<String, List<Booking>> bookingMap = new HashMap<>();

    private HotelService hotelService;

    public BookingService(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    public boolean checkRoomAvailability(String roomID, LocalDate checkIn, LocalDate checkOut) {
        List<Booking> bookings = bookingMap.getOrDefault(roomID, new ArrayList<>());

        if (bookings.isEmpty()) return true;

        for (Booking b : bookings) {
            if (checkIn.isBefore(b.getEndDate()) && checkOut.isAfter(b.getStartDate())) {
                return false;
            }
        }
        return true;
    }

    void bookRoom(String roomID, LocalDate checkIn, LocalDate checkOut, String roomtype, double price, String bookingID, User user) {

        if (hotelService.getRoom(roomID)) {
        if (checkRoomAvailability(roomID, checkIn, checkOut)) {
            Booking booking = new Booking(bookingID, user, checkIn, checkOut,roomID, Status.valueOf(roomtype.toUpperCase()), price);
            List<Booking> bookingList= bookingMap.getOrDefault(roomID,new ArrayList<>());
            bookingList.add(booking);

            bookingMap.put(roomID, bookingList);
        }
    }

    }

    void cancelRoom(Room room){

    }

}
