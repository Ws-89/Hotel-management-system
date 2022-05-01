package pl.siuda.hotel.reservation.pricingAlgorithm;

import org.springframework.stereotype.Component;
import pl.siuda.hotel.enums.Grade;
import pl.siuda.hotel.reservation.Availability;
import pl.siuda.hotel.reservation.Reservation;

import java.math.BigDecimal;

@Component
public class StarDependantAlgorithm implements CalculatePriceAlgorithm {

    public BigDecimal getPrice(Availability availability){
        BigDecimal price;
        switch(availability.getRoomType().toString()){
            case "SINGLE":
                price = BigDecimal.valueOf(50).multiply(rate(availability.getGrade()));
                break;
            case "DOUBLE":
                price = BigDecimal.valueOf(80).multiply(rate(availability.getGrade()));
                break;
            case "TRIPLE":
                price = BigDecimal.valueOf(120).multiply(rate(availability.getGrade()));
                break;
            case "QUAD":
                price = BigDecimal.valueOf(150).multiply(rate(availability.getGrade()));
                break;
            case "QUEEN":
                price = BigDecimal.valueOf(200).multiply(rate(availability.getGrade()));
                break;
            case "KING":
                price = BigDecimal.valueOf(200).multiply(rate(availability.getGrade()));
                break;
            case "DOUBLEDOUBLE":
                price = BigDecimal.valueOf(150).multiply(rate(availability.getGrade()));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + availability.getGrade().toString());
        }
        return price;
    }

    public Long getPriceForReservation(Reservation reservation){
        Long price;
        switch(reservation.getRoomType().toString()){
            case "SINGLE":
                price = 5000L * (rate(reservation.getGrade())).longValue();
                break;
            case "DOUBLE":
                price = 8000L * (rate(reservation.getGrade())).longValue();
                break;
            case "TRIPLE":
                price = 12000L * (rate(reservation.getGrade())).longValue();
                break;
            case "QUAD":
                price = 15000L * (rate(reservation.getGrade())).longValue();
                break;
            case "QUEEN":
                price = 20000L * (rate(reservation.getGrade())).longValue();
                break;
            case "KING":
                price = 20000L * (rate(reservation.getGrade())).longValue();
                break;
            case "DOUBLEDOUBLE":
                price = 15000L * (rate(reservation.getGrade())).longValue();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + reservation.getGrade().toString());
        }
        return price;
    }

    public BigDecimal rate(Grade grade){
        BigDecimal rate;
        switch(grade.toString()){
            case "ONESTAR":
                rate = BigDecimal.valueOf(0.7);
                break;
            case "TWOSTARS":
                rate = BigDecimal.valueOf(0.85);
                break;
            case "THREESTARS":
                rate = BigDecimal.valueOf(1.0);
                break;
            case "FOURSTARS":
                rate = BigDecimal.valueOf(1.5);
                break;
            case "FIVESTARS":
                rate = BigDecimal.valueOf(2.0);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + grade.toString());
        }
        return rate;
    }

}
