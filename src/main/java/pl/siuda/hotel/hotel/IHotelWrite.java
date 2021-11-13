package pl.siuda.hotel.hotel;

import java.util.List;

public interface IHotelWrite {

    Hotel createHotel(Hotel hotel);

    Hotel updateHotel(Long id, Hotel hotelDetails);

    void deleteHotel(Long id);
}
