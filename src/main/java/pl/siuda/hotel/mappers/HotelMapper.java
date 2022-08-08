package pl.siuda.hotel.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.siuda.hotel.dto.HotelDTO;
import pl.siuda.hotel.models.Hotel;

@Mapper
public interface HotelMapper {

    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);

    @Mapping(source = "hotelId", target = "hotelId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "contact", target = "contact")
    @Mapping(source = "grade", target = "grade")
    @Mapping(source = "image", target = "image")
    @Mapping(source = "rooms", target = "rooms")
    @Mapping(source = "enabled", target = "enabled")
    HotelDTO entityToDTO(Hotel hotel);
}
