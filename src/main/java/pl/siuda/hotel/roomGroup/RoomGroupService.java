package pl.siuda.hotel.roomGroup;

import org.springframework.stereotype.Service;
import pl.siuda.hotel.exception.NotFoundException;
import pl.siuda.hotel.hotel.Hotel;
import pl.siuda.hotel.hotel.HotelRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RoomGroupService {

    private final RoomGroupRepository roomGroupRepository;
    private final HotelRepository hotelRepository;

    public RoomGroupService(RoomGroupRepository roomGroupRepository, HotelRepository hotelRepository) {
        this.roomGroupRepository = roomGroupRepository;
        this.hotelRepository = hotelRepository;
    }

    public List<RoomGroup> getAllRoomGroups(){
        return StreamSupport.stream(roomGroupRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public List<RoomGroup> findRoomGroupsByHotelId(Long id){
        return this.roomGroupRepository.findRoomGroupsByHotelId(id);
    }

    public void createRoomGroup(Long id, RoomGroup roomGroupRequest){
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Hotel with id %s not found", id)));

        RoomGroup roomGroup = new RoomGroup();
        roomGroup.setDescription(roomGroupRequest.getDescription());
        roomGroup.setRoomType(roomGroupRequest.getRoomType());
        roomGroup.setQuantityOfRooms(roomGroupRequest.getQuantityOfRooms());

        for(int i = 0; i < roomGroupRequest.getQuantityOfRooms(); i++){
            Room room = new Room();
            roomGroup.addRoom(room);
        }
        hotel.addRoomGroup(roomGroup);
        hotelRepository.save(hotel);
    }

    public RoomGroup addToRoomGroup(Long id){
        RoomGroup roomGroup = roomGroupRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("RoomGroup with id %s not found", id)));
        Room room = new Room();
        roomGroup.addRoom(room);
        roomGroup.setQuantityOfRooms(roomGroup.getQuantityOfRooms() + 1);
        return roomGroupRepository.save(roomGroup);
    }

    public void removeFromGroup(Long id){
        RoomGroup roomGroup = roomGroupRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("RoomGroup with id %s not found", id)));
        roomGroup.removeFromGroup();
        roomGroupRepository.save(roomGroup);
    }

    public void deleteGroup(Long id){
        RoomGroup roomGroup = roomGroupRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("RoomGroup with id %s not found", id)));
        roomGroupRepository.delete(roomGroup);
    }

    public void updateRoomGroup(Long id, RoomGroup roomGroupRequest){
        RoomGroup roomGroup = roomGroupRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("RoomGroup with id %s not found", id)));
        roomGroup.setRoomType(roomGroupRequest.getRoomType());
        roomGroup.setDescription(roomGroupRequest.getDescription());
        roomGroupRepository.save(roomGroup);
    }

    public RoomGroup getRoomGroupById(Long id){
        return roomGroupRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("RoomGroup with id % not found", id)));
    }
}
