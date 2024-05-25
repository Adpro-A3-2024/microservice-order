package id.ac.ui.cs.youkosu.microserviceorder.model.Delivery;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryTest {

    @Test
    void testSetAndGetTrackingNumber() {
        Delivery delivery = new Delivery();
        DeliveryMethod method = new GobekDelivery();
        delivery.setDeliveryMethod(method);
        delivery.setTrackingNumber();
        assertEquals(method.toString(), delivery.toString());
    }

    @Test
    void testSetAndGetDeliveryMethod() {
        Delivery delivery = new Delivery();
        DeliveryMethod method = new GobekDelivery();
        delivery.setDeliveryMethod(method);
        assertEquals(method, delivery.getDeliveryMethod());
    }

    @Test
    void testEqualsAndHashCode() {
        Delivery delivery1 = new Delivery();
        Delivery delivery2 = new Delivery();
        DeliveryMethod method = new GobekDelivery();

        delivery1.setDeliveryMethod(method);
        delivery2.setDeliveryMethod(method);

        assertEquals(delivery1, delivery2);
        assertEquals(delivery1.hashCode(), delivery2.hashCode());
    }

    @Test
    void testNotEqualsDifferentTrackingNumber() {
        Delivery delivery1 = new Delivery();
        Delivery delivery2 = new Delivery();
        DeliveryMethod method1 = new GobekDelivery();
        DeliveryMethod method2 = new GobekDelivery();

        method1.setTrackingNumber();
        method2.setTrackingNumber();

        delivery1.setDeliveryMethod(method1);
        delivery2.setDeliveryMethod(method2);

        assertNotEquals(delivery1, delivery2);
        assertNotEquals(delivery1.hashCode(), delivery2.hashCode());
    }

    @Test
    void testNotEqualsDifferentDeliveryMethod() {
        Delivery delivery1 = new Delivery();
        Delivery delivery2 = new Delivery();

        delivery1.setDeliveryMethod(new GobekDelivery());
        delivery2.setDeliveryMethod(new JTEDelivery());

        assertNotEquals(delivery1, delivery2);
        assertNotEquals(delivery1.hashCode(), delivery2.hashCode());
    }

    @Test
    void testCanEqual() {
        Delivery delivery = new Delivery();
        assertTrue(delivery.canEqual(new Delivery()));

        GobekDelivery gobekDelivery = new GobekDelivery();
        assertFalse(delivery.canEqual(gobekDelivery));
    }

    @Test
    void testToString() {
        Delivery delivery = new Delivery();
        DeliveryMethod method = new GobekDelivery();
        delivery.setDeliveryMethod(method);

        String expected = method.toString();
        assertEquals(expected, delivery.toString());
    }
}
