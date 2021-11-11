package pl.siuda.hotel.guest;

import org.springframework.data.repository.CrudRepository;

public interface GuestRepo extends CrudRepository<Guest, Long> {
}
