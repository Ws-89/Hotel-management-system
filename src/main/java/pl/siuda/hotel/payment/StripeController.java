package pl.siuda.hotel.payment;


import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.siuda.hotel.reservation.ReservationArrangement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StripeController {

    private static Gson gson = new Gson();

    @Value("${app.stripe.key}")
    private String appStripeKey;
    private String successUrl = "http://localhost:4200/payment-canceled";
    private String cancelUrl = "http://localhost:4200/payment-success";

    @PostMapping("/payment")
    public String paymentWithCheckoutPage(@RequestBody ReservationArrangement payment) throws StripeException {
        init(appStripeKey);

//        SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder().setQuantity(payment.getQuantity())
//                .setPriceData(
//                        SessionCreateParams.LineItem.PriceData.builder()
//                                .setCurrency(payment.getCurrency()).setUnitAmount(payment.getAmount())
//                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData
//                                        .builder().setName(payment.getName()).build())
//                                .build())
//                .build();
//
//        SessionCreateParams.LineItem lineItem2 = SessionCreateParams.LineItem.builder().setQuantity(payment.getQuantity())
//                .setPriceData(
//                        SessionCreateParams.LineItem.PriceData.builder()
//                                .setCurrency(payment.getCurrency()).setUnitAmount(payment.getAmount())
//                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData
//                                        .builder().setName(payment.getName()).build())
//                                .build())
//                .build();

        List<SessionCreateParams.LineItem> lineItems = new ArrayList<SessionCreateParams.LineItem>();
//        lineItems.add(lineItem);
//        lineItems.add(lineItem2);

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT).setSuccessUrl(successUrl)
                .setCancelUrl(cancelUrl)
//                .addLineItem(SessionCreateParams.LineItem.builder().setQuantity(payment.getQuantity())
//                        .setPriceData(
//                                SessionCreateParams.LineItem.PriceData.builder()
//                                        .setCurrency(payment.getCurrency()).setUnitAmount(payment.getAmount())
//                                        .setProductData(SessionCreateParams.LineItem.PriceData.ProductData
//                                                .builder().setName(payment.getName()).build())
//                                .build())
//                        .build())
                .addAllLineItem(lineItems)
                .build();

        Session session = Session.create(params);
        Map<String, String> responseData = new HashMap<>();
        responseData.put("id", session.getId());


        return gson.toJson(responseData);
    }

    private static void init(String appStripeKey) {
        Stripe.apiKey = appStripeKey;
    }
}
