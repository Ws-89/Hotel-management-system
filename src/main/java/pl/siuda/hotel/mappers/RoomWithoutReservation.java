package pl.siuda.hotel.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.siuda.hotel.dto.RoomWithoutReservationsDTO;
import pl.siuda.hotel.models.Room;

@Mapper
public interface RoomWithoutReservation {

    RoomWithoutReservation INSTANCE = Mappers.getMapper(RoomWithoutReservation.class);

    @Mapping(source = "roomId", target = "roomId")
    @Mapping(source = "roomNumber", target = "roomNumber")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "roomType", target = "roomType")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "enabled", target = "enabled")
    RoomWithoutReservationsDTO entityToDTO(Room room);

}
