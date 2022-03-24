package pl.siuda.hotel.reservation.availabilityCart;

import org.springframework.data.annotation.Id;
import pl.siuda.hotel.reservation.Availability;

import java.util.List;

public class AvailabilityCart {

    @Id
    private Long id;
    private List<Availability> cartItems;

    public AvailabilityCart() {
    }

    public AvailabilityCart(Long id, List<Availability> cartItems) {
        this.id = id;
        this.cartItems = cartItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Availability> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<Availability> cartItems) {
        this.cartItems = cartItems;
    }

    public void addCartItem(Availability availability){
        this.cartItems.add(availability);
    }

    public void removeFromCartItems(Long id){
        this.cartItems.stream().filter(item -> item.getReservation_id() != id);
    }
}
