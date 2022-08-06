//package pl.siuda.hotel.mappers;
//
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//import pl.siuda.hotel.models.Room;
//
//@Mapper
//public interface RoomWReservationsWoGuestMapper {
//
//    RoomWReservationsWoGuestMapper INSTANCE = Mappers.getMapper(RoomWReservationsWoGuestMapper.class);
//
//    @Mapping(source = "roomId", target = "roomId")
//    @Mapping(source = "description", target = "description")
//    @Mapping(source = "roomType", target = "roomType")
//    @Mapping(source = "reservations", target = "reservations")
//    @Mapping(source = "price", target = "price")
//    RoomWReservationsWoGuestDTO entityToDTO(Room room);
//}
