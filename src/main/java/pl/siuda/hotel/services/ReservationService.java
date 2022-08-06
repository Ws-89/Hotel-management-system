package pl.siuda.hotel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.siuda.hotel.dto.ReservationDTO;
import pl.siuda.hotel.dto.ReservationWithGuestDTO;
import pl.siuda.hotel.exception.NotFoundException;
import pl.siuda.hotel.mappers.ReservationMapper;
import pl.siuda.hotel.mappers.ReservationWithGuestMapper;
import pl.siuda.hotel.models.Reservation;
import pl.siuda.hotel.models.ReservationStatus;
import pl.siuda.hotel.repositories.ReservationRepository;

import javax.transaction.Transactional;

@Service
public class ReservationService{

    @Autowired

    private final ReservationRepository reservationRepository;

    public ReservationService(
            ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    public void deleteReservation(Long id){
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Reservation with id %d not found", id)));
        reservationRepository.delete(reservation);
    }

    public ReservationDTO findById(Long id){
        return reservationRepository.findById(id)
                .map(r -> ReservationMapper.INSTANCE.entityToDTO(r))
                .orElseThrow(() -> new NotFoundException(String.format("Reservation with id %d not found", id)));
    }

    @Transactional
    public Reservation updateReservation(Long id, Reservation request){
        return reservationRepository.findById(id)
                .map(r -> {
                  Reservation reservationUpdate = r;
                    reservationUpdate.setStartDate(request.getStartDate());
                    reservationUpdate.setEndDate(request.getEndDate());
                    reservationUpdate.setGuestName(request.getGuestName());
                    reservationUpdate.setGuestLastName(request.getGuestLastName());
                    reservationUpdate.setEmail(request.getEmail());
                    reservationUpdate.setRequestMessage(request.getRequestMessage());
                    reservationUpdate.setGuestAddress(request.getGuestAddress());
                    reservationUpdate.setReservationStatus(ReservationStatus.Initialized);
                    return reservationRepository.save(reservationUpdate);
                })
                .orElseThrow(() -> new NotFoundException(String.format("Reservation with id %d not found", id)));
    }

    public Page<ReservationDTO> findAllByGuest_GuestId(ReservationStatus status, Long guestId, int page, int size){
        return reservationRepository.findByReservationStatusAndGuest_GuestId(status, guestId, PageRequest.of(page, size)).map(r -> ReservationMapper.INSTANCE.entityToDTO(r));
    }

    public Page<ReservationWithGuestDTO> findAllByRoom_Hotel_HotelId(ReservationStatus status, Long hotelId, int page, int size){
        return reservationRepository.findByReservationStatusAndRoom_Hotel_HotelId(status, hotelId,  PageRequest.of(page, size))
                .map(r -> ReservationWithGuestMapper.INSTANCE.entityToDTO(r));
    }

    public Page<ReservationDTO> findAllByReservationStatusAndRoom_RoomId(ReservationStatus status, Long roomId, int page, int size){
        return reservationRepository.findByReservationStatusAndRoom_RoomId(status, roomId, PageRequest.of(page, size)).map(r -> ReservationMapper.INSTANCE.entityToDTO(r));
    }
}

