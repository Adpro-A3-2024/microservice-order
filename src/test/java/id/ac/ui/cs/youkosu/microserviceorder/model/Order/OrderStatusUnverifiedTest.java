package id.ac.ui.cs.youkosu.microserviceorder.model.Order;
import id.ac.ui.cs.youkosu.microserviceorder.service.OrderStatusUpdateException;
import id.ac.ui.cs.youkosu.microserviceorder.tempModel.CartItem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderStatusUnverifiedTest {

    @Test
    void testSetStatusToVerified() {
        List<CartItem> cartItems = new ArrayList<>();
        CartItem item1 = new CartItem(
                UUID.randomUUID(),
                "Product A",
                10.0,
                5,
                0.0,
                0,
                "url1"
        );
        cartItems.add(item1);

        Order order = new Order(UUID.randomUUID(), cartItems);
        OrderStatusUnverified status = new OrderStatusUnverified();
        status.setStatusToVerified(order);
        assertEquals("VERIFIED", order.getStatus());
    }

    @Test
    void testSetStatusToCancelled() {
        List<CartItem> cartItems = new ArrayList<>();
        CartItem item1 = new CartItem(
                UUID.randomUUID(),
                "Product A",
                10.0,
                5,
                0.0,
                0,
                "url1"
        );
        cartItems.add(item1);

        Order order = new Order(UUID.randomUUID(), cartItems);
        OrderStatusUnverified status = new OrderStatusUnverified();
        status.setStatusToCancelled(order);
        assertEquals("CANCELLED", order.getStatus());
    }

    @Test
    void testSetStatusToShipped() {
        List<CartItem> cartItems = new ArrayList<>();
        CartItem item1 = new CartItem(
                UUID.randomUUID(),
                "Product A",
                10.0,
                5,
                0.0,
                0,
                "url1"
        );
        cartItems.add(item1);

        Order order = new Order(UUID.randomUUID(), cartItems);
        OrderStatusUnverified status = new OrderStatusUnverified();

        assertThrows(OrderStatusUpdateException.class, () -> {
            status.setStatusToShipped(order, "Gobek");
        });
    }

    @Test
    void testSetStatusToCompleted() {
        List<CartItem> cartItems = new ArrayList<>();
        CartItem item1 = new CartItem(
                UUID.randomUUID(),
                "Product A",
                10.0,
                5,
                0.0,
                0,
                "url1"
        );
        cartItems.add(item1);

        Order order = new Order(UUID.randomUUID(), cartItems);
        OrderStatusUnverified status = new OrderStatusUnverified();
        assertThrows(OrderStatusUpdateException.class, () -> {
            status.setStatusToCompleted(order);
        });
    }
}
