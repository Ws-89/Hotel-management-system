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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StripeController {

    private static Gson gson = new Gson();

    @Value("${app.stripe.key}")
    String appStripeKey;

    @PostMapping("/payment")
    public String paymentWithCheckoutPage(@RequestBody CheckoutPayment payment) throws StripeException {
        init(appStripeKey);

//        PaymentIntentCreateParams params2 = PaymentIntentCreateParams.builder()
//                .setAmount(1999L)
//                .setCurrency("usd")
//                .build();
//        PaymentIntent paymentIntent = PaymentIntent.create(params2);
//        Map<String, String> responseData2 = new HashMap<>();
//        responseData2.put("clientSecret", paymentIntent.getClientSecret());
//        return gson.toJson(responseData2);

        SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder().setQuantity(payment.getQuantity())
                .setPriceData(
                        SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency(payment.getCurrency()).setUnitAmount(payment.getAmount())
                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData
                                        .builder().setName(payment.getName()).build())
                                .build())
                .build();

        SessionCreateParams.LineItem lineItem2 = SessionCreateParams.LineItem.builder().setQuantity(payment.getQuantity())
                .setPriceData(
                        SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency(payment.getCurrency()).setUnitAmount(payment.getAmount())
                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData
                                        .builder().setName(payment.getName()).build())
                                .build())
                .build();

        List<SessionCreateParams.LineItem> lineItems = new ArrayList<SessionCreateParams.LineItem>();
        lineItems.add(lineItem);
        lineItems.add(lineItem2);

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT).setSuccessUrl(payment.getSuccessUrl())
                .setCancelUrl(payment.getCancelUrl())
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
