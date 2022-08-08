package pl.siuda.hotel.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.siuda.hotel.dto.HotelWithoutRoomsDTO;
import pl.siuda.hotel.models.Hotel;

@Mapper
public interface HotelsWithoutRoomsMapper {

    HotelsWithoutRoomsMapper INSTANCE = Mappers.getMapper(HotelsWithoutRoomsMapper.class);

    @Mapping(source = "hotelId", target = "hotelId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "contact", target = "contact")
    @Mapping(source = "grade", target = "grade")
    @Mapping(source = "image", target = "image")
    @Mapping(source = "enabled", target = "enabled")
    HotelWithoutRoomsDTO entityToDTO(Hotel hotel);
}
