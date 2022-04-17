package pl.siuda.hotel.reservation.availabilityCart;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import pl.siuda.hotel.reservation.Availability;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AvailabilityCart {

    @Id
    private Long availability_cart_id;
    @MappedCollection(idColumn = "AVAILABILITY_CART_ID")
    private Set<Availability> cartItems = new HashSet<>();

    public AvailabilityCart() {
    }

    public AvailabilityCart(Long id, Set<Availability> cartItems) {
        this.availability_cart_id = id;
        this.cartItems = cartItems;
    }

    public Long getId() {
        return availability_cart_id;
    }

    public void setId(Long id) {
        this.availability_cart_id = id;
    }

    public Set<Availability> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<Availability> cartItems) {
        this.cartItems = cartItems;
    }

    public void addCartItem(Availability availability){
        this.cartItems.add(availability);
    }

    public void addCartItem(Availability[] availability){
        for(int i = 0; i < availability.length; i++) {
            this.cartItems.add(availability[i]);
        }
    }

    public void removeFromCartItems(Long id){
        this.cartItems.stream().filter(item -> item.getAvailability_id() != id);
    }
}
