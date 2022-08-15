package pl.siuda.hotel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.siuda.hotel.dto.ReservationDTO;
import pl.siuda.hotel.dto.ReservationWithRoomDTO;
import pl.siuda.hotel.exception.NotFoundException;
import pl.siuda.hotel.mappers.ReservationMapper;
import pl.siuda.hotel.mappers.ReservationWithRoomMapper;
import pl.siuda.hotel.models.Guest;
import pl.siuda.hotel.models.Reservation;
import pl.siuda.hotel.models.ReservationStatus;
import pl.siuda.hotel.repositories.GuestRepository;
import pl.siuda.hotel.repositories.ReservationRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReservationService{

    @Autowired

    private final ReservationRepository reservationRepository;
    private final GuestRepository guestRepository;

    public ReservationService(
            ReservationRepository reservationRepository, GuestRepository guestRepository){
        this.reservationRepository = reservationRepository;
        this.guestRepository = guestRepository;
    }

    public void deleteReservation(UUID id){
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Reservation with id %d not found", id)));
        reservationRepository.delete(reservation);
    }

    public ReservationDTO findById(UUID id){
        return reservationRepository.findById(id)
                .map(r -> ReservationMapper.INSTANCE.entityToDTO(r))
                .orElseThrow(() -> new NotFoundException(String.format("Reservation with id %d not found", id)));
    }

    @Transactional
    public Reservation updateReservation(UUID id, Reservation request){
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

    public Page<ReservationDTO> findAllByGuest_GuestId(ReservationStatus status, UUID guestId, int page, int size){
        return reservationRepository.findByReservationStatusAndGuest_GuestId(status, guestId, PageRequest.of(page, size)).map(r -> ReservationMapper.INSTANCE.entityToDTO(r));
    }

    public Page<ReservationDTO> findAllReservationsOfCurrentUser(ReservationStatus status,  int page, int size){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        return reservationRepository.findByReservationStatusAndGuest_Email(status, currentPrincipalName, PageRequest.of(page, size)).map(r -> ReservationMapper.INSTANCE.entityToDTO(r));
    }

    public Page<ReservationWithRoomDTO> findAllByRoom_Hotel_HotelId(ReservationStatus status, UUID hotelId, int page, int size){
        return reservationRepository.findByReservationStatusAndRoom_Hotel_HotelId(status, hotelId,  PageRequest.of(page, size))
                .map(r -> ReservationWithRoomMapper.INSTANCE.entityToDTO(r));
    }

    public Page<ReservationDTO> findAllByReservationStatusAndRoom_RoomId(ReservationStatus status, UUID roomId, int page, int size){
        return reservationRepository.findByReservationStatusAndRoom_RoomId(status, roomId, PageRequest.of(page, size)).map(r -> ReservationMapper.INSTANCE.entityToDTO(r));
    }

    public Page<ReservationDTO> findAllByReservationStatusAndHotelIdAndGuestLastName(ReservationStatus status, String lastName, UUID hotelId, int page, int size){
        return reservationRepository.findByReservationStatusAndRoom_Hotel_HotelIdAndGuestLastNameContaining(
                status, hotelId, lastName, PageRequest.of(page, size)).map(r -> ReservationMapper.INSTANCE.entityToDTO(r));
    }
}

