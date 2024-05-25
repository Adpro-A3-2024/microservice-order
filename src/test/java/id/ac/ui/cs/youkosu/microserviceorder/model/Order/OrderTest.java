package id.ac.ui.cs.youkosu.microserviceorder.model.Order;

import id.ac.ui.cs.youkosu.microserviceorder.model.Delivery.Delivery;
import id.ac.ui.cs.youkosu.microserviceorder.model.Delivery.DeliveryMethod;
import id.ac.ui.cs.youkosu.microserviceorder.model.Delivery.JTEDelivery;
import id.ac.ui.cs.youkosu.microserviceorder.service.OrderStatusUpdateException;
import id.ac.ui.cs.youkosu.microserviceorder.tempModel.CartItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private List<CartItem> cartItems;
    private Order order;

    @BeforeEach
    void setUp() {
        this.cartItems = new ArrayList<>();
        CartItem item1 = new CartItem(
                UUID.randomUUID(),
                "Product A",
                10.0,
                5,
                0.0,
                0,
                "https://images.tokopedia.net/img/cache/900/hDjmkQ/2024/1/18/73592f9a-ef15-417f-8c6f-fd79c2f4228d.jpg"
        );

        CartItem item2 = new CartItem(
                UUID.randomUUID(),
                "Product B",
                20.0,
                10,
                0.0,
                0,
                "https://images.tokopedia.net/img/cache/900/hDjmkQ/2024/1/18/73592f9a-ef15-417f-8c6f-fd79c2f4228d.jpg"
        );

        this.cartItems.add(item1);
        this.cartItems.add(item2);
        this.order = new Order(UUID.randomUUID(), this.cartItems);
    }

    @Test
    void testCreateOrderEmptyProduct() {
        this.cartItems.clear();

        assertThrows(IllegalArgumentException.class, () -> {
            new Order(UUID.randomUUID(), this.cartItems);
        });
    }

    @Test
    void testCreateOrderEmptyProduct2() {
        this.cartItems.clear();

        assertThrows(IllegalArgumentException.class, () -> {
            new Order(this.cartItems);
        });
    }

    @Test
    void testCreateOrderDefaultStatus() {
        UUID orderId = UUID.randomUUID();
        Order order = new Order(orderId, this.cartItems);

        assertSame(this.cartItems, order.getCartItems());
        assertEquals(2, order.getCartItems().size());
        assertEquals("Product A", order.getCartItems().get(0).getProductName());
        assertEquals("Product B", order.getCartItems().get(1).getProductName());

        assertEquals(orderId, order.getOrderId());
        assertEquals("UNVERIFIED", order.getStatus());
    }

    @Test
    void testSetStatusToVerified() {
        order.setStatusToVerified();
        assertEquals("VERIFIED", order.getStatus());
    }

    @Test
    void testSetStatusToCancelled() {
        order.setStatusToCancelled();
        assertEquals("CANCELLED", order.getStatus());
    }

    @Test
    void testSetStatusToShipped() {
        order.setStatusToVerified();
        order.setStatusToShipped("Gobek");
        assertEquals("SHIPPED", order.getStatus());
    }

    @Test
    void testSetStatusToCompleted() {
        order.setStatusToVerified();
        order.setStatusToShipped("Gobek");
        order.setStatusToCompleted();
        assertEquals("COMPLETED", order.getStatus());
    }

    @Test
    void testCalculateTotalPrice() {
        assertEquals(30.0, order.getTotalPrice());
    }

    @Test
    void testInvalidStatusTransition() {
        order.setStatusToCancelled();
        OrderStatusUpdateException exception = assertThrows(OrderStatusUpdateException.class, order::setStatusToVerified);
        assertEquals("Cannot update order status to VERIFIED for order " + order.getOrderId(), exception.getMessage());
    }

    @Test
    void testInvalidStatusForShipping() {
        OrderStatusUpdateException exception = assertThrows(OrderStatusUpdateException.class, () -> order.setStatusToShipped("Gobek"));
        assertEquals("Cannot update order status to SHIPPED for order " + order.getOrderId(), exception.getMessage());
    }

    @Test
    void testValidStatusTransitions() {
        order.setStatusToVerified();
        order.setStatusToShipped("Gobek");
        order.setStatusToCompleted();
        assertEquals("COMPLETED", order.getStatus());
    }

    @Test
    void testEquals() {
        UUID orderId = UUID.randomUUID();
        Order order1 = new Order(orderId, this.cartItems);
        Order order2 = new Order(orderId, this.cartItems);

        assertEquals(order1, order2);
    }

    @Test
    void testSetters() {
        UUID newOrderId = UUID.randomUUID();
        List<CartItem> newCartItems = new ArrayList<>();
        CartItem newItem = new CartItem(
                UUID.randomUUID(),
                "Product C",
                30.0,
                15,
                0.0,
                0,
                "https://images.tokopedia.net/img/cache/900/hDjmkQ/2024/1/18/73592f9a-ef15-417f-8c6f-fd79c2f4228d.jpg"
        );
        newCartItems.add(newItem);

        order.setOrderId(newOrderId);
        order.setCartItems(newCartItems);
        order.setTotalPrice(30.0);
        order.setStatus("SHIPPED");
        order.setTrackingNumber("TRACK123");

        assertEquals(newOrderId, order.getOrderId());
        assertSame(newCartItems, order.getCartItems());
        assertEquals(30.0, order.getTotalPrice());
        assertEquals("SHIPPED", order.getStatus());
        assertEquals("TRACK123", order.getTrackingNumber());
    }

    @Test
    void testCanEqual() {
        UUID orderId = UUID.randomUUID();
        Order order1 = new Order(orderId, this.cartItems);
        Order order2 = new Order(orderId, this.cartItems);

        assertTrue(order1.canEqual(order2));
    }
}
