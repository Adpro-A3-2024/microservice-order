package id.ac.ui.cs.youkosu.microserviceorder.model.Delivery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JTEDeliveryTest {

    @Test
    public void testGenerateTrackingNumber() {
        JTEDelivery delivery = new JTEDelivery();
        String trackingNumber = delivery.getTrackingNumber();
        Assertions.assertNotNull(trackingNumber);
        Assertions.assertTrue(delivery.validateTrackingNumber());
    }

    @Test
    public void testValidateTrackingNumber() {
        JTEDelivery delivery = new JTEDelivery();
        delivery.setTrackingNumber("JTE-123456789012");
        Assertions.assertTrue(delivery.validateTrackingNumber());

        delivery.setTrackingNumber("JTE-1234567890123");
        Assertions.assertFalse(delivery.validateTrackingNumber());

        delivery.setTrackingNumber("JTE-12345678901");
        Assertions.assertFalse(delivery.validateTrackingNumber());

        delivery.setTrackingNumber("JTE-1234567890AB");
        Assertions.assertFalse(delivery.validateTrackingNumber());
    }
}
