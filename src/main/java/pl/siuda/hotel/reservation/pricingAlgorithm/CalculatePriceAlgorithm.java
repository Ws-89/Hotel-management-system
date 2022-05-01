package pl.siuda.hotel.reservation.pricingAlgorithm;

import pl.siuda.hotel.reservation.Availability;
import pl.siuda.hotel.reservation.Reservation;

import java.math.BigDecimal;

public interface CalculatePriceAlgorithm {

    public BigDecimal getPrice(Availability availability);

    public Long getPriceForReservation(Reservation reservation);
}
