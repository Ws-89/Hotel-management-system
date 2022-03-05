package pl.siuda.hotel.amazonS3bucket;

import org.springframework.data.repository.CrudRepository;

public interface ImageRepo extends CrudRepository<Image, Long> {
}
