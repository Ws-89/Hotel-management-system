package pl.siuda.hotel.payment;

import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import pl.siuda.hotel.models.embeddedClasses.BookingDetails;
import pl.siuda.hotel.payment.event.TransactionFailureEvent;
import pl.siuda.hotel.dto.ReservationRequest;
import pl.siuda.hotel.services.ReservationService;
import pl.siuda.hotel.pricingAlgorithm.CalculatePriceAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final ApplicationEventPublisher eventPublisher;
    private static Gson gson = new Gson();

    @Value("${app.stripe.key}")
    private String appStripeKey;
    private String successUrl = "http://localhost:4200/payment-success";
    private String cancelUrl = "http://localhost:4200/payment-canceled";

    private final CalculatePriceAlgorithm calculatePriceAlgorithm;
    private final ReservationService reservationService;

    public PaymentServiceImpl(ApplicationEventPublisher eventPublisher, CalculatePriceAlgorithm calculatePriceAlgorithm, ReservationService reservationService) {
        this.eventPublisher = eventPublisher;
        this.calculatePriceAlgorithm = calculatePriceAlgorithm;
        this.reservationService = reservationService;
    }

    @Override
    public void sendMoney(EventPayment eventPayment) {

        try {
            if(eventPayment.condition == true){
                throw new RuntimeException("Transaction failed");
            }

            System.out.println("Hi" + eventPayment.name);
            System.out.println("sending amount " + eventPayment.amount + " is successful");
        }catch (Exception e) {
            eventPublisher.publishEvent(new TransactionFailureEvent(eventPayment.getName(), eventPayment.getAmount(), eventPayment.getCondition()));
            e.printStackTrace();
        }
    }

    public String payWithStripe(ReservationRequest payment) throws StripeException {
        init(appStripeKey);

        List<SessionCreateParams.LineItem> lineItems = new ArrayList<SessionCreateParams.LineItem>();
        payment.getReservations().forEach(item -> {
                    SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder().setQuantity(1L)
                            .setPriceData(
                                    SessionCreateParams.LineItem.PriceData.builder()
                                            .setCurrency(payment.getCurrency()).setUnitAmount(calculatePriceAlgorithm.getPriceForReservation(item.getBookingDetails()))
                                            .setProductData(SessionCreateParams.LineItem.PriceData.ProductData
                                                    .builder().setName(lineItemNameBuilder(item.getBookingDetails())).build())
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

    private static void init(String appStripeKey) {
        Stripe.apiKey = appStripeKey;
    }

    private String lineItemNameBuilder(BookingDetails reservation) {
        StringBuilder lineItemName = new StringBuilder();
        lineItemName.append(reservation.getHotelName() + ", ");
        lineItemName.append(reservation.getCity()+ ", ");
        lineItemName.append(reservation.getGrade()+ ", ");
        lineItemName.append(reservation.getRoomType()+ ", ");
        return lineItemName.toString();
    }
}
