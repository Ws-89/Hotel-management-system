package pl.siuda.hotel.hotel;

import java.util.List;

public interface HotelCreationInterface {

    public List<Hotel> getAllHotels();

    public Hotel getHotelById(Long id);

    public Hotel createHotel(Hotel hotel);

    public Hotel updateHotel(Long id, Hotel hotel);

    public void deleteHotel(Long id);
}
