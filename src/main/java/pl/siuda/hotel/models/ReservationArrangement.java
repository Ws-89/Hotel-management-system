//package pl.siuda.hotel.models;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.math.BigDecimal;
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Table(name = "tbl_reservation_arrangement")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class ReservationArrangement {
//
//    @Id
//    @SequenceGenerator(
//            name = "tbl_reservation_arrangement_sequence", sequenceName = "tbl_reservation_arrangement_sequence", allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE, generator = "tbl_reservation_arrangement_sequence"
//    )
//    private Long reservation_arrangement_id;
//    private Integer partySize;
//    private Integer numberOfRooms;
//
//    private Set<Reservation> reservations = new HashSet<>();
//    private String email;
//    private Boolean confirmed;
//    private BigDecimal price;
//
//    public Long getReservation_arrangement_id() {
//        return reservation_arrangement_id;
//    }
//
//    public void setReservation_arrangement_id(Long reservation_arrangement_id) {
//        this.reservation_arrangement_id = reservation_arrangement_id;
//    }
//
//    public int getPartySize() {
//        return partySize;
//    }
//
//    public void setPartySize(Integer partySize) {
//        this.partySize = partySize;
//    }
//
//    public int getNumberOfRooms() {
//        return numberOfRooms;
//    }
//
//    public void setNumberOfRooms(Integer numberOfRooms) {
//        this.numberOfRooms = numberOfRooms;
//    }
//
//    public Set<Reservation> getReservations() {
//        return reservations;
//    }
//
//    public void setReservations(Set<Reservation> reservations) {
//        this.reservations = reservations;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public boolean isConfirmed() {
//        return confirmed;
//    }
//
//    public void setConfirmed(boolean confirmed) {
//        this.confirmed = confirmed;
//    }
//
//    public BigDecimal getPrice() {
//        return price;
//    }
//
//    public void setPrice(BigDecimal price) {
//        this.price = price;
//    }
//}
