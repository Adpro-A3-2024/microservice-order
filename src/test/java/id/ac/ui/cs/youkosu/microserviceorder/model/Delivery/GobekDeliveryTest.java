package id.ac.ui.cs.youkosu.microserviceorder.model.Delivery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GobekDeliveryTest {

    @Test
    public void testGenerateTrackingNumber() {
        GobekDelivery delivery = new GobekDelivery();
        String trackingNumber = delivery.getTrackingNumber();
        Assertions.assertNotNull(trackingNumber);
        Assertions.assertTrue(delivery.validateTrackingNumber());
    }

    @Test
    public void testValidateTrackingNumber() {
        GobekDelivery delivery = new GobekDelivery();
        delivery.setTrackingNumber("GBK-AB9123ED1384");
        Assertions.assertTrue(delivery.validateTrackingNumber());

        delivery.setTrackingNumber("GBK-AB9123ED13845");
        Assertions.assertFalse(delivery.validateTrackingNumber());

        delivery.setTrackingNumber("GBK-AB9123ED138");
        Assertions.assertFalse(delivery.validateTrackingNumber());

        delivery.setTrackingNumber("GBK-ab9123ed1384");
        Assertions.assertFalse(delivery.validateTrackingNumber());
    }
}