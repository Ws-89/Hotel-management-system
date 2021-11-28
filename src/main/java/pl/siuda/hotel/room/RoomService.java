package pl.siuda.hotel.room;

import org.springframework.stereotype.Service;
import pl.siuda.hotel.hotel.HotelRepo;
import pl.siuda.hotel.exception.NotFoundException;
import pl.siuda.hotel.hotel.Hotel;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RoomService {

    private final RoomRepo roomRepository;
    private final HotelRepo hotelRepository;

    public RoomService(RoomRepo roomRepository, HotelRepo hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }

    public List<Room> getAllRooms(){
        return StreamSupport.stream(roomRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Room getRoomById(Long id){
        return getRoomByIdOrElseThrowException(id);
    }

    public Room getRoomByNumber(Integer number){
        return roomRepository.findByNumber(number)
                .orElseThrow(() -> new NotFoundException(String.format("Room with number %s not found", number)));
    }

    public Room updateRoom(Long id, RoomRequest roomRequest){
        Room room = getRoomByIdOrElseThrowException(id);
        roomRequest.copyRequestToEntity(room);
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

    public void createRoomAtSpecifiedHotel(Long id, RoomRequest roomRequest){
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Hotel with id %s not found", id)));
        if(roomRepository.findByHotelId(id).stream().anyMatch(t -> t.getNumber().equals(roomRequest.getNumber()))){
            throw new IllegalStateException("Room already exists");
        }
        Room room = new Room();
        roomRequest.copyRequestToEntity(room);
        hotel.addRoom(room);
        hotelRepository.save(hotel);
    }

    public Room findByRoomNumberAndHotelId(Integer room_number, Long hotel_id){
        return roomRepository.findByRoomNumberAndHotelId(room_number, hotel_id)
                .orElseThrow(() -> new NotFoundException(String.format("Room with number %s not found in hotel with %s id", room_number, hotel_id)));
    }

    private Room getRoomByIdOrElseThrowException(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Room with id %s not found", id)));
    }

}

