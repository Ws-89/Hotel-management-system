package pl.siuda.hotel.room;

import org.springframework.stereotype.Service;
import pl.siuda.hotel.hotel.HotelRepository;
import pl.siuda.hotel.exception.NotFoundException;
import pl.siuda.hotel.roomGroup.RoomGroupRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final RoomGroupRepository roomGroupRepository;

    public RoomService(RoomRepository roomRepository, HotelRepository hotelRepository, RoomGroupRepository roomGroupRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
        this.roomGroupRepository = roomGroupRepository;
    }

    public List<Room> getAllRooms(){
        return StreamSupport.stream(roomRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Room getRoomById(Long id){
        return getRoomByIdOrElseThrowException(id);
    }

//    public Room updateRoom(Long id, RoomRequest roomRequest){
//        Room room = getRoomByIdOrElseThrowException(id);
//        roomRequest.copyRequestToEntity(room);
//        return roomRepository.save(room);
//    }

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
        return roomRepository.getRoomListByHotelId(id);
    }

//    public void createRoomAtSpecifiedHotel(Long id, RoomRequest roomRequest){
//        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Hotel with id %s not found", id)));
//
//        RoomGroup roomGroup = new RoomGroup();
//        for(int i = 0; i < roomRequest.getNumber(); i++){
//            Room room = new Room();
//            roomRequest.copyRequestToEntity(room);
//            roomGroup.addRoom(room);
//        }
//
//        hotel.addRoomGroup(roomGroup);
//        hotelRepository.save(hotel);
//    }

//    public void addRoomToGroup(Long id, RoomRequest roomRequest){
//        Room room = getRoomByIdOrElseThrowException(id);
//        RoomGroup roomGroup = roomGroupRepository.findById(room.getRoom_group()).orElseThrow(
//                () -> new NotFoundException("Group with id %s not found " + room.getRoom_group()));
//
//    }

    private Room getRoomByIdOrElseThrowException(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Room with id %s not found", id)));
    }

}

