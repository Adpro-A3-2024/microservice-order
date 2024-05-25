package id.ac.ui.cs.youkosu.microserviceorder.model.Delivery;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SiWuzzDeliveryTest {

    @Test
    void testValidateTrackingNumber() {
        SiWuzzDelivery delivery = new SiWuzzDelivery();
        delivery.setTrackingNumber("SWZ-QHWJAMSTYAZJ");
        assertTrue(delivery.validateTrackingNumber());
    }

    @Test
    void testInvalidTrackingNumber() {
        SiWuzzDelivery delivery = new SiWuzzDelivery();
        delivery.setTrackingNumber("123");
        assertFalse(delivery.validateTrackingNumber());
    }

    @Test
    void testEquals() {
        SiWuzzDelivery delivery1 = new SiWuzzDelivery();
        delivery1.setTrackingNumber("SW123456789");

        SiWuzzDelivery delivery2 = new SiWuzzDelivery();
        delivery2.setTrackingNumber("SW123456789");

        assertEquals(delivery1, delivery2);
        assertEquals(delivery1.hashCode(), delivery2.hashCode());
    }

    @Test
    void testNotEquals() {
        SiWuzzDelivery delivery1 = new SiWuzzDelivery();
        delivery1.setTrackingNumber("SW123456789");

        SiWuzzDelivery delivery2 = new SiWuzzDelivery();
        delivery2.setTrackingNumber("SW987654321");

        assertNotEquals(delivery1, delivery2);
    }
}