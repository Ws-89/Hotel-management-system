package pl.siuda.hotel.guest;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.siuda.hotel.admin.Admin;

import java.util.Optional;

@Repository
public interface GuestRepository extends CrudRepository<Guest, Long> {

    Guest findByEmail(String email);

}
