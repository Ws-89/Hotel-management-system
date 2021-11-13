package pl.siuda.hotel.hotel;

import java.util.List;

public interface IHotelRead {

    List<Hotel> getAllHotels();

    Hotel getHotelById(Long id);
}
