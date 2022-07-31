package pl.siuda.hotel.pricingAlgorithm;

import pl.siuda.hotel.models.embeddedClasses.BookingDetails;

import java.math.BigDecimal;

public interface CalculatePriceAlgorithm {

    BigDecimal getPrice(BookingDetails availability);

    Long getPriceForReservation(BookingDetails reservation);
}