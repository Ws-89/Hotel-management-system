package pl.siuda.hotel.guest;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface GuestRepo extends CrudRepository<Guest, Long> {

    Optional<Guest> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE guest a " +
            "SET a.enabled = TRUE WHERE a.guest_id = :id")
    Long enableAppUser(Long id);

}
