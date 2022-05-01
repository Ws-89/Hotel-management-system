package pl.siuda.hotel.payment;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.model.checkout.Session;
import com.stripe.net.ApiResource;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.WebhookEndpointCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.siuda.hotel.reservation.Reservation;
import pl.siuda.hotel.reservation.ReservationRequest;
import pl.siuda.hotel.reservation.ReservationService;
import pl.siuda.hotel.reservation.pricingAlgorithm.CalculatePriceAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@EnableTransactionManagement
public class StripeController {

    private static Gson gson = new Gson();

    @Value("${app.stripe.key}")
    private String appStripeKey;
    private String successUrl = "http://localhost:4200/payment-success";
    private String cancelUrl = "http://localhost:4200/payment-canceled";

    private final CalculatePriceAlgorithm calculatePriceAlgorithm;
    private final ReservationService reservationService;
    private final PaymentService paymentService;

    public StripeController(CalculatePriceAlgorithm calculatePriceAlgorithm, ReservationService reservationService, PaymentService paymentService) {
        this.calculatePriceAlgorithm = calculatePriceAlgorithm;
        this.reservationService = reservationService;
        this.paymentService = paymentService;
    }

    @PostMapping("/pay")
    public void pay(@RequestBody EventPayment eventPayment){
        this.paymentService.sendMoney(eventPayment);
    }

    @PostMapping("/payment")
    @Transactional
    public String paymentWithCheckoutPage(@RequestBody ReservationRequest payment) throws StripeException {
        init(appStripeKey);

        List<SessionCreateParams.LineItem> lineItems = new ArrayList<SessionCreateParams.LineItem>();
        payment.getReservations().forEach(item -> {
            SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder().setQuantity(1L)
                    .setPriceData(
                            SessionCreateParams.LineItem.PriceData.builder()
                                    .setCurrency(payment.getCurrency()).setUnitAmount(calculatePriceAlgorithm.getPriceForReservation(item))
                                    .setProductData(SessionCreateParams.LineItem.PriceData.ProductData
                                            .builder().setName(lineItemNameBuilder(item)).build())
                                    .build())
                    .build();
            lineItems.add(lineItem);
            }
        );

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT).setSuccessUrl(successUrl)
                .setCancelUrl(cancelUrl)
                .addAllLineItem(lineItems)
                .build();

        Session session = Session.create(params);
        Map<String, String> responseData = new HashMap<>();
        responseData.put("id", session.getId());
        System.out.println("Session: ");
        System.out.println(session);
        System.out.println("Response data: ");
        System.out.println(responseData);

        return gson.toJson(responseData);
    }

    @GetMapping("{id}")
    public Event eventRetrieve(@PathVariable("id") String eventId){
        Event event = new Event();
        try {
            event =
                    Event.retrieve(eventId);
        } catch (StripeException e) {
            e.printStackTrace();
        }
        return event;
    }


    private static void init(String appStripeKey) {
        Stripe.apiKey = appStripeKey;
    }

    private String lineItemNameBuilder(Reservation reservation) {
        StringBuilder lineItemName = new StringBuilder();
        lineItemName.append(reservation.getHotelName() + ", ");
        lineItemName.append(reservation.getCity()+ ", ");
        lineItemName.append(reservation.getGrade()+ ", ");
        lineItemName.append(reservation.getRoomType()+ ", ");
        return lineItemName.toString();
    }
}
