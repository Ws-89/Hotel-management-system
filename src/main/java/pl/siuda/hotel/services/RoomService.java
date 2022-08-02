package pl.siuda.hotel.services;

import org.springframework.stereotype.Service;
import pl.siuda.hotel.exception.NotFoundException;
import pl.siuda.hotel.models.Hotel;
import pl.siuda.hotel.models.Room;
import pl.siuda.hotel.repositories.HotelRepository;
import pl.siuda.hotel.repositories.RoomRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    public RoomService(RoomRepository roomRepository, HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }

    public List<Room> getAllRoomGroups(){
        return StreamSupport.stream(roomRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public List<Room> findRoomsByHotelId(Long id){
        return this.roomRepository.findByHotelHotelId(id);
    }

    public void createRoom(Long id, Room roomRequest){
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Hotel with id %s not found", id)));

        Room room = new Room();
        room.setDescription(roomRequest.getDescription());
        room.setRoomType(roomRequest.getRoomType());
        hotel.addRoom(room);
        roomRepository.save(room);
    }


    public void deleteGroup(Long id){
        Room room = roomRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("RoomGroup with id %s not found", id)));
        roomRepository.delete(room);
    }

    public void updateRoom(Long id, Room roomRequest){
        Room roomGroup = roomRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("RoomGroup with id %s not found", id)));
        roomGroup.setRoomType(roomRequest.getRoomType());
        roomGroup.setDescription(roomRequest.getDescription());
        roomRepository.save(roomGroup);
    }

    public Room getRoomById(Long id){
        return roomRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("RoomGroup with id % not found", id)));
    }
}
