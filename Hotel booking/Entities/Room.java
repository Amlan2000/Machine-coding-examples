package Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Room {

    private String roomID;

    private RoomType roomType;
    private int baseRoomPrice;
    private String hotelID;

}

