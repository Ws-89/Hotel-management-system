package pl.siuda.hotel;


import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pl.siuda.hotel.requests.AdminRequest;
import pl.siuda.hotel.security.CustomUserDetailsService;
import pl.siuda.hotel.services.AdminService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;


@SpringBootApplication
@EnableSwagger2
@EnableEncryptableProperties
public class HotelApplication {

	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Autowired
	AdminService adminService;

	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);


//		port(4242);
//		post(
//				"/webhook",
//				(request, response) -> {
//					String payload = request.body();
//					Event event = null;
//
//					 Simple deserialization:
//					 try {
//					   event = ApiResource.GSON.fromJson(payload, Event.class);
//					 } catch (JsonSyntaxException e) {
//					   // Invalid payload
//					   response.status(400);
//					   return "";
//					 }
//
//					 With signature verification:
//					String endpointSecret = "whsec_e10d768b5b284bb79b7256dac9fc372627712b204ab2966aa3a15f8b30392e63";
//					String sigHeader = request.headers("Stripe-Signature");
//
//					try {
//						event = Webhook.constructEvent(
//								payload, sigHeader, endpointSecret
//						);
//					} catch (JsonSyntaxException e) {
//						// Invalid payload.
//						response.status(400);
//						return "";
//					} catch (SignatureVerificationException e) {
//						// Invalid signature.
//						System.out.println("Signature verification failed.");
//						System.out.println(e);
//						response.status(400);
//						return "";
//					}
//
//					System.out.println(event.getId());
//					System.out.println(event.getType());
//					System.out.println(event.getData().getObject().getClass());
//
//					 Deserialize the nested object inside the event.
//					EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
//					StripeObject stripeObject = null;
//					if (dataObjectDeserializer.getObject().isPresent()) {
//						stripeObject = dataObjectDeserializer.getObject().get();
//					} else {
//						 Deserialization failed, probably due to an API version mismatch.
//						 Refer to the Javadoc documentation on `EventDataObjectDeserializer` for
//						 instructions on how to handle this case, or return an error here.
//					}
//
//					switch(event.getType()) {
//						case "customer.created":
//							Customer customer = (Customer) stripeObject;
//							System.out.println(customer);
//							break;
//						default:
//							System.out.println("Unhandled event type: " + event.getType());
//					}
//
//					response.status(200);
//					return "success";
//				}
//		);

	}

	@PostConstruct
	private void init() {
		buildSuperAdmin();
	}

	private void buildSuperAdmin() {

		boolean adminExists = customUserDetailsService.userNotExists("wiktorsiuda@gmail.com");
		if (adminExists) {
			AdminRequest admin = new AdminRequest(
					"Wiktor",
					"Siuda",
					"wiktorsiuda@gmail.com",
					"password123"
			);
			adminService.saveAdmin(admin);
		}


	}
}

