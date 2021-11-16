package pl.siuda.hotel.registration.token;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE confirmation_token c " +
            "SET c.confirmed_at = :confirmedAt " +
            "WHERE c.token = :token")
    int updateConfirmedAt(@Param("token")String token,
                          @Param("confirmedAt") LocalDateTime confirmedAt);

    @Query("SELECT guest_id " +
            "FROM confirmation_token " +
            "WHERE token = :token")
    Long getUserId(String token);

}
