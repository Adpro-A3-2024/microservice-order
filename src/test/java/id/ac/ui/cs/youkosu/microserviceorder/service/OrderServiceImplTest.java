package id.ac.ui.cs.youkosu.microserviceorder.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import id.ac.ui.cs.youkosu.microserviceorder.model.DTO.OrderUpdateStatusDTO;
import id.ac.ui.cs.youkosu.microserviceorder.repository.OrderRepository;
import id.ac.ui.cs.youkosu.microserviceorder.model.Order.Order;
import id.ac.ui.cs.youkosu.microserviceorder.tempModel.CartItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {
    @InjectMocks
    OrderServiceImpl orderService;
    @Mock
    OrderRepository orderRepository;
    List<Order> orders;

    @BeforeEach
    void setUp() {
        List<CartItem> cartItems = new ArrayList<>();

        CartItem cartItem1 = new CartItem(
                UUID.randomUUID(),
                "Product A",
                10.0,
                5,
                0.0,
                0,
                "https://images.tokopedia.net/img/cache/900/hDjmkQ/2024/1/18/73592f9a-ef15-417f-8c6f-fd79c2f4228d.jpg"
        );

        cartItems.add(cartItem1);
        orders = new ArrayList<>();
        Order order1 = new Order(UUID.randomUUID(), cartItems);
        orders.add(order1);
        Order order2 = new Order(UUID.randomUUID(), cartItems);
        orders.add(order2);
    }

    @Test
    void testCreateOrder() {
        Order order = orders.get(1);
        doReturn(order).when(orderRepository).save(order);
        Order result = orderService.createOrder(order);
        verify(orderRepository, times(1)).save(order);
        assertEquals(order.getOrderId(), result.getOrderId());
    }


    @Test
    void testUpdateStatus() {
        Order order = orders.get(1);
        OrderUpdateStatusDTO orderUpdateStatusDTO = new OrderUpdateStatusDTO();
        orderUpdateStatusDTO.setOrderId(order.getOrderId());
        orderUpdateStatusDTO.setStatus("VERIFIED");

        doReturn(java.util.Optional.of(order)).when(orderRepository).findById(order.getOrderId());

        Order newOrderVerified = new Order(order.getOrderId(), order.getCartItems());
        newOrderVerified.setStatusToVerified();
        doReturn(newOrderVerified).when(orderRepository).save(any(Order.class));

        Order result = orderService.updateStatus(orderUpdateStatusDTO);

        assertEquals(order.getOrderId(), result.getOrderId());
        assertEquals("VERIFIED", result.getStatus().toString());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testUpdateStatusInvalidStatus() {
        Order order = orders.get(1);

        order.setStatusToVerified();

        doReturn(java.util.Optional.of(order)).when(orderRepository).findById(order.getOrderId());


        OrderUpdateStatusDTO orderUpdateStatusDTO2 = new OrderUpdateStatusDTO();
        orderUpdateStatusDTO2.setOrderId(order.getOrderId());
        orderUpdateStatusDTO2.setStatus("CANCELLED");



        OrderStatusUpdateException exception = assertThrows(OrderStatusUpdateException.class, () ->
                orderService.updateStatus(orderUpdateStatusDTO2));

        assertEquals("Cannot update order status to CANCELLED for order " + order.getOrderId(), exception.getMessage());
    }

    @Test
    void testFindByAll() {
        doReturn(orders).when(orderRepository).findAll();
        List<Order> result = orderService.findAll();
        assertEquals(orders, result);
    }

    @Test
    void testFindByIdIfIdFound() {
        Order order = orders.get(1);
        doReturn(java.util.Optional.of(order)).when(orderRepository).findById(order.getOrderId());
        Order result = orderService.findById(order.getOrderId());
        assertEquals(order.getOrderId(), result.getOrderId());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        UUID randomUUID = UUID.randomUUID();
        doReturn(java.util.Optional.empty()).when(orderRepository).findById(randomUUID);
        Order result = orderService.findById(randomUUID);
        assertNull(result);
    }
}
