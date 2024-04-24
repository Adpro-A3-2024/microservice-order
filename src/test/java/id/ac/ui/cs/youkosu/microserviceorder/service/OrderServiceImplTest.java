package id.ac.ui.cs.youkosu.microserviceorder.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import id.ac.ui.cs.youkosu.microserviceorder.model.OrderStatus;
import id.ac.ui.cs.youkosu.microserviceorder.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import id.ac.ui.cs.youkosu.microserviceorder.model.Order;
import id.ac.ui.cs.youkosu.microserviceorder.tempModel.Product;
import id.ac.ui.cs.youkosu.microserviceorder.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {
    @InjectMocks
    OrderServiceImpl orderService;
    @Mock
    OrderRepository orderRepository;
    List<Order> orders;
    @BeforeEach
    void setUp(){
        List<Product> products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductReqId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductReqName("Sampo Cap Bambang");
        product1.setProductReqPrice(2);
        product1.setProductReqPictureUrl("https://images.tokopedia.net/img/cache/900/hDjmkQ/2024/1/18/73592f9a-ef15-417f-8c6f-fd79c2f4228d.jpg");
        product1.setProductReqSourceUrl("https://images.tokopedia.net/img/cache/900/hDjmkQ/2024/1/18/73592f9a-ef15-417f-8c6f-fd79c2f4228d.jpg");

        products.add(product1);
        orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79a", products);
        orders.add(order1);
        Order order2 = new Order("13652556-012a-4c07-b546-54eb1396d79b", products);
        orders.add(order2);
    }

    @Test
    void testCreateOrder(){
        Order order = orders.get(1);
        doReturn(order).when(orderRepository).save(order);
        Order result = orderService.createOrder(order);
        verify(orderRepository, times(1)).save(order);
        assertEquals(order.getOrderId(), result.getOrderId());
    }

    @Test
    void testCreateOrderIfAlreadyExists(){
        Order order = orders.get(1);
        doReturn(order).when(orderRepository).findById(order.getOrderId());
        assertNull(orderService.createOrder(order));
        verify(orderRepository, times(0)).save(order);
    }

    @Test
    void testUpdateStatus(){
        Order order = orders.get(1);
        Order newOrderVerified = new Order(order.getOrderId(), order.getProducts());
        newOrderVerified.setStatusToVerified();

        doReturn(order).when(orderRepository).findById(order.getOrderId());
        doReturn(newOrderVerified).when(orderRepository).save(any(Order.class));

        Order result = orderService.updateStatus(order.getOrderId(), "VERIFIED");

        assertEquals(order.getOrderId(), result.getOrderId());
        assertEquals("VERIFIED", result.getStatus().toString());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testUpdateStatusInvalidStatus(){
        Order order = orders.get(1);

        Order newOrderVerified = new Order(order.getOrderId(), order.getProducts());
        newOrderVerified.setStatusToVerified();
        doReturn(order).when(orderRepository).findById(order.getOrderId());
        doReturn(newOrderVerified).when(orderRepository).save(any(Order.class));
        Order resultOrderCompleted = orderService.updateStatus(order.getOrderId(), "VERIFIED");

        doReturn(resultOrderCompleted).when(orderRepository).findById(order.getOrderId());
        newOrderVerified.setStatusToCancelled();
        doReturn(newOrderVerified).when(orderRepository).save(any(Order.class));
        Order resultOrderCancelled = orderService.updateStatus(order.getOrderId(), "CANCELLED");

        doReturn(resultOrderCancelled).when(orderRepository).findById(order.getOrderId());
        newOrderVerified.setStatusToCompleted();
        doReturn(newOrderVerified).when(orderRepository).save(any(Order.class));
        Order result = orderService.updateStatus(order.getOrderId(), "COMPLETED");

        assertEquals(order.getOrderId(), result.getOrderId());
        assertEquals("CANCELLED", result.getStatus().toString());
        verify(orderRepository, times(3)).save(any(Order.class));
    }

    @Test
    void testFindByIdIfIdFound(){
        Order order = orders.get(1);
        doReturn(order).when(orderRepository).findById(order.getOrderId());
        Order result = orderService.findById(order.getOrderId());
        assertEquals(order.getOrderId(), result.getOrderId());
    }
    @Test
    void testFindByIdIfIdNotFound(){
        doReturn(null).when(orderRepository).findById("zczc");
        Order result = orderService.findById("zczc");
        assertNull(result);
    }
}