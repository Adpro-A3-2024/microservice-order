package id.ac.ui.cs.youkosu.microserviceorder.model.Order;

import id.ac.ui.cs.youkosu.microserviceorder.service.OrderStatusUpdateException;
import id.ac.ui.cs.youkosu.microserviceorder.tempModel.CartItem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderStatusCancelledTest {

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
        OrderStatusCancelled status = new OrderStatusCancelled();
        assertThrows(OrderStatusUpdateException.class, () -> {
            status.setStatusToVerified(order);
        });
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
        OrderStatusCancelled status = new OrderStatusCancelled();
        assertThrows(OrderStatusUpdateException.class, () -> {
            status.setStatusToCancelled(order);
        });
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
        OrderStatusCancelled status = new OrderStatusCancelled();
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
        OrderStatusCancelled status = new OrderStatusCancelled();
        assertThrows(OrderStatusUpdateException.class, () -> {
            status.setStatusToCompleted(order);
        });
    }
}
