package Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
public class Booking {

    private String bookingID;
    private User user;
    private LocalDate startDate;
    private LocalDate endDate;
    private String roomID;
    public Status status;
    private double amount;
}
