package pl.siuda.hotel.models;



import java.util.HashSet;
import java.util.Set;

public class AvailabilityCart {


    private Long availabilityCartId;

    private Set<Availability> cartItems = new HashSet<>();

    public AvailabilityCart() {
    }

    public AvailabilityCart(Long id, Set<Availability> cartItems) {
        this.availabilityCartId = id;
        this.cartItems = cartItems;
    }

    public Long getId() {
        return availabilityCartId;
    }

    public void setId(Long id) {
        this.availabilityCartId = id;
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
        this.cartItems.stream().filter(item -> item.getAvailabilityId() != id);
    }
}
