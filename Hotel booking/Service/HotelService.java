package Service;

import Entities.Booking;
import Entities.Hotel;
import Entities.Room;
import Entities.RoomType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class HotelService {

    private HashMap<String,Hotel> hotelMap= new HashMap<>();

    private HashMap<String,Room> roomMap = new HashMap<>();


    public void addHotel(String id, String hotelName, String city){
        Hotel hotel = new Hotel(id,hotelName,city);
        hotelMap.put(id,hotel);

    }

    public void addRoom(String id, String roomType, int price, String hotelID){

        boolean isHotelExist = checkIfHotelExists(hotelID);
        if(isHotelExist) {

            RoomType type;
            try {
                type = RoomType.valueOf(roomType.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Invalid Room Type provided");
            }

            Room room = new Room(id, type, price, hotelID);
            roomMap.put(id, room);
        }
        else throw new RuntimeException("Hotel is not present");
    }

    private boolean checkIfHotelExists(String hotelID){
        return hotelMap.containsKey(hotelID);
    }

    public boolean getRoom(String roomID){
        return roomMap.containsKey(roomID);
    }

    public void getAllRooms(){
        roomMap.forEach((key,value)->System.out.println("ROom type: " +value.getRoomType() + "Price: "+ value.getBaseRoomPrice()));
    }



}
