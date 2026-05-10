package Service;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    void bookFlight(String flightID, String userID, String from, String to, String flightNumber, String airline, int departDate, String fareType, List<String> seats);
}
