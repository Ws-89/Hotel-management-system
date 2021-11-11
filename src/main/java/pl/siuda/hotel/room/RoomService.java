package pl.siuda.hotel.room;

import org.springframework.stereotype.Service;
import pl.siuda.hotel.dao.HotelRepo;
import pl.siuda.hotel.dao.RoomRepo;
import pl.siuda.hotel.exception.NotFoundException;
import pl.siuda.hotel.hotel.Hotel;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepo roomRepository;
    private final HotelRepo hotelRepository;

    public RoomService(RoomRepo roomRepository, HotelRepo hotelRepository) {
        this.roomRepository = roomRepository;

        this.hotelRepository = hotelRepository;
    }

    public List<Room> getAllRooms(){
        return (List<Room>) roomRepository.findAll();
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
        Room result = roomRepository.save(room);
        if(result == null){
            throw new IllegalStateException("Something went wrong");
        }
    }

    public Room updateRoom(Long id, Room roomDetails){
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Room with id %s not found", id)));
        room.updateDetails(roomDetails);
        return roomRepository.save(room);
    }

    public void deleteRoom(Long id){
        Optional<Room> room = roomRepository.findById(id);
        room.ifPresentOrElse(arg -> {
            Room roomToDelete = room.get();
            roomRepository.delete(roomToDelete);
        }, () -> {
                throw new NotFoundException(String.format("Room with id %s not found", id));
                });
    }

    public List<Room> findByHotelId(Long id){
        return roomRepository.findByHotelId(id);
    }

    public void createRoomAtSpecifiedHotel(Long id, Room room){
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Hotel with id %s not found", id)));
        if(roomRepository.findByHotelId(id).stream().anyMatch(t -> t.getNumber().equals(room.getNumber()))){
            throw new IllegalStateException("Room already exists");
        }
        hotel.addRoom(room);
        hotelRepository.save(hotel);
    }

}

