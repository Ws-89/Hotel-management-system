package pl.siuda.hotel.reservation;

public class Reservation {

    private Long reservationNo;

    public Reservation() {
    }

    public Long getReservationNo() {
        return reservationNo;
    }

    public void setReservationNo(Long reservationNo) {
        this.reservationNo = reservationNo;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationNo=" + reservationNo +
                '}';
    }
}
