package pl.siuda.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.siuda.hotel.models.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

    Guest findByEmail(String email);

}
