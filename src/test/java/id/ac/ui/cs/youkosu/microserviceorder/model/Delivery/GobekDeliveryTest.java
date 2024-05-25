package id.ac.ui.cs.youkosu.microserviceorder.model.Delivery;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GobekDeliveryTest {

    @Test
    void testValidateTrackingNumber() {
        GobekDelivery delivery = new GobekDelivery();
        delivery.setTrackingNumber("GBK-AB9123ED1384");
        assertTrue(delivery.validateTrackingNumber());
    }

    @Test
    void testInvalidTrackingNumber() {
        GobekDelivery delivery = new GobekDelivery();
        delivery.setTrackingNumber("123");
        assertFalse(delivery.validateTrackingNumber());
    }

    @Test
    void testEquals() {
        GobekDelivery delivery1 = new GobekDelivery();
        delivery1.setTrackingNumber("GB123456789");

        GobekDelivery delivery2 = new GobekDelivery();
        delivery2.setTrackingNumber("GB123456789");

        assertEquals(delivery1, delivery2);
        assertEquals(delivery1.hashCode(), delivery2.hashCode());
    }

    @Test
    void testNotEquals() {
        GobekDelivery delivery1 = new GobekDelivery();
        delivery1.setTrackingNumber("GB123456789");

        GobekDelivery delivery2 = new GobekDelivery();
        delivery2.setTrackingNumber("GB987654321");

        assertNotEquals(delivery1, delivery2);
    }
}
