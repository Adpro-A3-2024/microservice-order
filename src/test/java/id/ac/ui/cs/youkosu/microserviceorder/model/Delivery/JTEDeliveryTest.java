package id.ac.ui.cs.youkosu.microserviceorder.model.Delivery;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JTEDeliveryTest {

    @Test
    void testValidateTrackingNumber() {
        JTEDelivery delivery = new JTEDelivery();
        delivery.setTrackingNumber("JTE-012345678912");
        assertTrue(delivery.validateTrackingNumber());
    }

    @Test
    void testInvalidTrackingNumber() {
        JTEDelivery delivery = new JTEDelivery();
        delivery.setTrackingNumber("123");
        assertFalse(delivery.validateTrackingNumber());
    }

    @Test
    void testEquals() {
        JTEDelivery delivery1 = new JTEDelivery();
        delivery1.setTrackingNumber("JTE-012345678912");

        JTEDelivery delivery2 = new JTEDelivery();
        delivery2.setTrackingNumber("JTE-012345678912");

        assertEquals(delivery1, delivery2);
        assertEquals(delivery1.hashCode(), delivery2.hashCode());
    }

    @Test
    void testNotEquals() {
        JTEDelivery delivery1 = new JTEDelivery();
        delivery1.setTrackingNumber("JT123456789");

        JTEDelivery delivery2 = new JTEDelivery();
        delivery2.setTrackingNumber("JT987654321");

        assertNotEquals(delivery1, delivery2);
    }
}
