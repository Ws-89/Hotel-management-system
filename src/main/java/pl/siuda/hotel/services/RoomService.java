package pl.siuda.hotel.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.siuda.hotel.dto.RoomWithoutReservationsDTO;
import pl.siuda.hotel.exception.NotFoundException;
import pl.siuda.hotel.mappers.RoomWithoutReservation;
import pl.siuda.hotel.models.Hotel;
import pl.siuda.hotel.models.Room;
import pl.siuda.hotel.repositories.HotelRepository;
import pl.siuda.hotel.repositories.RoomRepository;
import pl.siuda.hotel.requests.RoomRequest;

import java.util.UUID;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    public RoomService(RoomRepository roomRepository, HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }
    public Page<RoomWithoutReservationsDTO> findRoomsByHotelId(UUID id, int page, int size){
        return this.roomRepository.findByHotelHotelId(id, PageRequest.of(page, size)).map(r -> RoomWithoutReservation.INSTANCE.entityToDTO(r));
    }

    public void createRoom(UUID id, RoomRequest roomRequest){
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Hotel with id %s not found", id)));

        Room room = new Room();
        room.setDescription(roomRequest.getDescription());
        room.setRoomType(roomRequest.getRoomType());
        room.setPrice(roomRequest.getPrice());
        room.setEnabled(false);
        hotel.addRoom(room);
        roomRepository.save(room);
    }


    public void deleteRoom(UUID id){
        Room room = roomRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("RoomGroup with id %s not found", id)));
        roomRepository.delete(room);
    }

    public void updateRoom(UUID id, Room roomRequest){
        Room room = roomRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("RoomGroup with id %s not found", id)));
        room.setRoomType(roomRequest.getRoomType());
        room.setRoomNumer(roomRequest.getRoomNumber());
        room.setDescription(roomRequest.getDescription());
        room.setPrice(roomRequest.getPrice());
        roomRepository.save(room);
    }

    public void switchRoomActivation(UUID id, Boolean state){
        Room room = roomRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("RoomGroup with id %s not found", id)));
        room.setEnabled(state);
        roomRepository.save(room);
    }

    public RoomWithoutReservationsDTO getRoomById(UUID id){
        return roomRepository.findById(id)
                .map(r -> RoomWithoutReservation.INSTANCE.entityToDTO(r))
                .orElseThrow(() -> new NotFoundException(String.format("RoomGroup with id % not found", id)));
    }
}
