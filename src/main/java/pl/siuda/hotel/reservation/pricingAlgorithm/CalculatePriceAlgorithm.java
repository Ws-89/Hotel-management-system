package pl.siuda.hotel.reservation.pricingAlgorithm;

import pl.siuda.hotel.reservation.Availability;

import java.math.BigDecimal;

public interface CalculatePriceAlgorithm {

    public BigDecimal getPrice(Availability availability);
}
