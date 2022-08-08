package pl.siuda.hotel.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.siuda.hotel.models.Hotel;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.*;

@Repository
public interface HotelRepository extends PagingAndSortingRepository<Hotel, Long> {

    Page<Hotel> findAll(Pageable page);


    @EntityGraph(value = "graph.availableHotels", type = FETCH)
    List<Hotel> findByAddressCityAndEnabled(String city, boolean enabled);

    @EntityGraph(value = "graph.availableHotels", type = FETCH)
    Optional<Hotel> findById(Long id);

    Optional<Hotel> findByName(String name);
}
