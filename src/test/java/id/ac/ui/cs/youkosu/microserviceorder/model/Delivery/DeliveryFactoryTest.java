package id.ac.ui.cs.youkosu.microserviceorder.model.Delivery;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryFactoryTest {

    @Test
    void testGetGobekDelivery() {
        Delivery delivery = DeliveryFactory.getDelivery("Gobek");
        assertTrue(delivery.deliveryMethod instanceof GobekDelivery);
    }

    @Test
    void testGetJTEDelivery() {
        Delivery delivery = DeliveryFactory.getDelivery("JTE");
        assertTrue(delivery.deliveryMethod instanceof JTEDelivery);
    }

    @Test
    void testGetSiWuzzDelivery() {
        Delivery delivery = DeliveryFactory.getDelivery("SiWuzz");
        assertTrue(delivery.deliveryMethod instanceof SiWuzzDelivery);
    }

    @Test
    void testGetInvalidDelivery() {
        assertThrows(IllegalArgumentException.class, () -> {
            DeliveryFactory.getDelivery("InvalidDelivery");
        });
    }
}
