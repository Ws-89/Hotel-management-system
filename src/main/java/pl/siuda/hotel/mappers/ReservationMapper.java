package pl.siuda.hotel.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.siuda.hotel.dto.ReservationDTO;
import pl.siuda.hotel.models.Reservation;

@Mapper
public interface ReservationMapper {

    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    @Mapping(source = "reservationId", target = "reservationId")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "guestName", target = "guestName")
    @Mapping(source = "guestLastName", target = "guestLastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "requestMessage", target = "requestMessage")
    @Mapping(source = "reservationStatus", target = "reservationStatus")
    @Mapping(source = "guestAddress", target = "guestAddress")
    ReservationDTO entityToDTO(Reservation reservation);
}
