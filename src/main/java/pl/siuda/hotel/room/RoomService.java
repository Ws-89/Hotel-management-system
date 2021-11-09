package pl.siuda.hotel.room;

import org.springframework.stereotype.Service;
import pl.siuda.hotel.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    public List<Room> getAllRoomsInTheHotel(Long id){
        return roomRepository.findAllRoomsByHotelId(id);
    }

    public List<Room> getAllRoomsWithReservation(){
        return roomRepository.findAllWithReservation();
    }

    public Room getRoomById(Long id){
        return roomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Room with id %s not found", id)));
    }

    public Room getRoomByNumber(Integer number){
        return roomRepository.findByNumber(number)
                .orElseThrow(() -> new NotFoundException(String.format("Room with number %s not found", number)));
    }

    public void addNewRom(Room room){
        if(roomRepository.findByNumber(room.getNumber()).isPresent()){
            throw new IllegalStateException("Room already exists");
        }
        int result = roomRepository.create(room);
        if(result != 1){
            throw new IllegalStateException("Something went wrong");
        }
    }

    public int updateRoom(Long id, Room roomDetails){
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Room with id %s not found", id)));
        return roomRepository.update(roomDetails, id);
    }

    public void deleteRoom(Long id){
        Optional<Room> rooms = roomRepository.findById(id);
        rooms.ifPresentOrElse(room -> {
            int result = roomRepository.delete(id);
            if(result != 1){
                throw new IllegalStateException("could not delete room");
            }
        }, () -> {
                throw new NotFoundException(String.format("Room with id %s not found", id));
                });
    }
}
