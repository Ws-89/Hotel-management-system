package pl.siuda.hotel.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.siuda.hotel.dto.GuestDTO;
import pl.siuda.hotel.models.Guest;

@Mapper
public interface GuestMapper {

    GuestMapper INSTANCE = Mappers.getMapper(GuestMapper.class);

    @Mapping(source = "guestId", target = "guestId")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "guestAddress", target = "address")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "reservations", target = "reservations")
    GuestDTO entityToDTO(Guest guest);
}
