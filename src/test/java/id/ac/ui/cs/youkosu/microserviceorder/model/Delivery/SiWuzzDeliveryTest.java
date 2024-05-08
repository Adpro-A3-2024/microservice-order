package id.ac.ui.cs.youkosu.microserviceorder.model.Delivery;// SiWuzzDeliveryTest.java
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SiWuzzDeliveryTest {

    @Test
    public void testGenerateTrackingNumber() {
        SiWuzzDelivery delivery = new SiWuzzDelivery();
        String trackingNumber = delivery.getTrackingNumber();
        Assertions.assertNotNull(trackingNumber);
        Assertions.assertTrue(delivery.validateTrackingNumber());
    }

    @Test
    public void testValidateTrackingNumber() {
        SiWuzzDelivery delivery = new SiWuzzDelivery();
        delivery.setTrackingNumber("SWZ-QHWJAMSTYAZJ");
        Assertions.assertTrue(delivery.validateTrackingNumber());

        delivery.setTrackingNumber("SWZ-QHWJAMSTYAZJ1");
        Assertions.assertFalse(delivery.validateTrackingNumber());

        delivery.setTrackingNumber("SWZ-QHWJAMSTYAZ");
        Assertions.assertFalse(delivery.validateTrackingNumber());

        delivery.setTrackingNumber("SWZ-qhwjamstyazj");
        Assertions.assertFalse(delivery.validateTrackingNumber());
    }
}