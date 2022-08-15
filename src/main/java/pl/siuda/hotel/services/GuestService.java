package pl.siuda.hotel.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.siuda.hotel.dto.GuestDTO;
import pl.siuda.hotel.dto.ReservationDTO;
import pl.siuda.hotel.exception.NotFoundException;
import pl.siuda.hotel.mappers.GuestMapper;
import pl.siuda.hotel.mappers.ReservationMapper;
import pl.siuda.hotel.models.Guest;
import pl.siuda.hotel.models.ReservationStatus;
import pl.siuda.hotel.repositories.ReservationRepository;
import pl.siuda.hotel.util.EmailValidator;
import pl.siuda.hotel.repositories.GuestRepository;
import pl.siuda.hotel.security.CustomUserDetailsService;

import javax.transaction.Transactional;
import java.util.UUID;


@Service
public class GuestService {

    private final GuestRepository guestRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailValidator emailValidator;
    private final CustomUserDetailsService customUserDetailsService;
    private final ReservationRepository reservationRepository;

    public GuestService(GuestRepository guestRepository, BCryptPasswordEncoder bCryptPasswordEncoder, EmailValidator emailValidator, CustomUserDetailsService customUserDetailsService, ReservationRepository reservationRepository) {
        this.guestRepository = guestRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailValidator = emailValidator;
        this.customUserDetailsService = customUserDetailsService;
        this.reservationRepository = reservationRepository;
    }

    public Page<GuestDTO> guestList(int page, int size){
        return guestRepository.findAll(PageRequest.of(page, size)).map(g -> GuestMapper.INSTANCE.entityToDTO(g));
    }


    public Page<GuestDTO> guestListByHotel(UUID hotelId, ReservationStatus status, int page, int size){
        return guestRepository.getCurrentHotelGuests(hotelId, status, PageRequest.of(page, size))
                .map(g -> GuestMapper.INSTANCE.entityToDTO(g));
    }

    public GuestDTO getGuestById(UUID id){
        return guestRepository.findById(id).map(g -> GuestMapper.INSTANCE.entityToDTO(g))
                .orElseThrow(()-> new NotFoundException(String.format("Guest with id %s not found")));
    }

    public Guest signUpGuest(Guest guest){
        boolean userNotExists = customUserDetailsService.userNotExists(guest.getEmail());
        if(!userNotExists){
            throw new NotFoundException("email already in use");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(guest.getPassword());
        guest.setPassword(encodedPassword);

        return guestRepository.save(guest);
    }

    public Guest findByEmail(String email){
        Guest guest = guestRepository.findByEmail(email);
        if(guest == null){
            throw new NotFoundException("username not found");
        }
        return guest;
    }

    @Transactional
    public GuestDTO update(UUID id, Guest guest){
        return guestRepository.findById(id)
                .map(g -> {
                    Guest guestToUpdate = g;
                    guestToUpdate.setGuestAddress(guest.getGuestAddress());
                    guestToUpdate.setEmail(guest.getEmail());
                    guestToUpdate.setFirstName(guest.getFirstName());
                    guestToUpdate.setLastName(guest.getLastName());
                    guestToUpdate.setPhoneNumber(guest.getPhoneNumber());
                    return GuestMapper.INSTANCE.entityToDTO(this.guestRepository.save(guestToUpdate));
                })
                .orElseThrow(() -> new NotFoundException(String.format("Guest with id %d not found", id)));
    }
    public Page<ReservationDTO> findAllByGuest_GuestId(UUID guestId, ReservationStatus status, int page, int size) throws IllegalAccessException {
        checkAccesToGuestInformation(guestId);
        return reservationRepository.findByReservationStatusAndGuest_GuestId(status, guestId, PageRequest.of(page, size)).map(r -> ReservationMapper.INSTANCE.entityToDTO(r));
    }

    public GuestDTO getPrincipal(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        Guest guest = guestRepository.getPrincipal(currentPrincipalName).orElseThrow(() -> new NotFoundException(String.format("User not found")));

        return GuestMapper.INSTANCE.entityToDTO(guest);
    }

    public void deleteGuest(UUID id){
        Guest guest = guestRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Guest with id %s not found")));
        guestRepository.delete(guest);
    }

    private void checkAccesToGuestInformation(UUID guestId) throws IllegalAccessException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Guest principal = guestRepository.getPrincipal(currentPrincipalName).orElseThrow(() -> new NotFoundException(String.format("User not found")));
        if(principal.getGuestId() != guestId)
            throw new IllegalAccessException("You don't have permission to access this user account informations");
    }
}
