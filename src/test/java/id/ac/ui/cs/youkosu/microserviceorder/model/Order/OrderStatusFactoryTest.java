package id.ac.ui.cs.youkosu.microserviceorder.model.Order;

import id.ac.ui.cs.youkosu.microserviceorder.service.OrderStatusUpdateException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderStatusFactoryTest {

    @Test
    void testGetOrderStatus() {
        OrderStatus status = OrderStatusFactory.getOrderStatus("VERIFIED");
        assertTrue(status instanceof OrderStatusVerified);

        status = OrderStatusFactory.getOrderStatus("CANCELLED");
        assertTrue(status instanceof OrderStatusCancelled);

        status = OrderStatusFactory.getOrderStatus("SHIPPED");
        assertTrue(status instanceof OrderStatusShipped);

        status = OrderStatusFactory.getOrderStatus("COMPLETED");
        assertTrue(status instanceof OrderStatusCompleted);

        status = OrderStatusFactory.getOrderStatus("UNVERIFIED");
        assertTrue(status instanceof OrderStatusUnverified);
    }

    @Test
    void testGetOrderStatusWithInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            OrderStatus status = OrderStatusFactory.getOrderStatus("INVALID_STATUS");
        });
    }
}
