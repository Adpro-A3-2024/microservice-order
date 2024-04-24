package id.ac.ui.cs.youkosu.microserviceorder.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;
import id.ac.ui.cs.youkosu.microserviceorder.model.Order;
import id.ac.ui.cs.youkosu.microserviceorder.tempModel.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderRepositoryTest {
    OrderRepository orderRepository;
    List<Order> orders;
    @BeforeEach
    void setUp() {
        orderRepository = new OrderRepository();
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductReqId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductReqName("Sampo Cap Bambang");
        product1.setProductReqPrice(2);
        product1.setProductReqPictureUrl("https://images.tokopedia.net/img/cache/900/hDjmkQ/2024/1/18/73592f9a-ef15-417f-8c6f-fd79c2f4228d.jpg");
        product1.setProductReqSourceUrl("https://images.tokopedia.net/img/cache/900/hDjmkQ/2024/1/18/73592f9a-ef15-417f-8c6f-fd79c2f4228d.jpg");
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a0-4c07-b546-54eb1396d79a", products);
        orders.add(order1);
        Order order2 = new Order("13652556-012a0-4c07-b546-54eb1396d79b", products);
        orders.add(order2);
        Order order3 = new Order("13652556-012a0-4c07-b546-54eb1396d79c", products);
        orders.add(order3);
    }
    @Test
    void testSaveCreate(){
        Order order = orders.get(1);
        Order result = orderRepository.save(order);
        Order findResult = orderRepository.findById(orders.get(1).getOrderId());
        assertEquals(order.getOrderId(), result.getOrderId());
        assertEquals(order.getOrderId(), findResult.getOrderId());
        assertEquals(order.getProducts(), findResult.getProducts());
        assertEquals(order.getStatus(), findResult.getStatus());
    }
    @Test
    void testUpdateSave(){
        Order order = orders.get(1);
        orderRepository.save(order);
        Order newOrder = new Order(order.getOrderId(), order.getProducts());
        newOrder.setStatus(order.getStatus());

        Order result = orderRepository.save(newOrder);
        Order findResult = orderRepository.findById(orders.get(1).getOrderId());
        assertEquals(order.getOrderId(), result.getOrderId());
        assertEquals(order.getOrderId(), findResult.getOrderId());
        assertEquals(order.getStatus(), findResult.getStatus());
    }

    @Test
    void testFindAll() {
        for (Order order : orders) {
            orderRepository.save(order);
        }

        List<Order> result = orderRepository.findAll();
        assertEquals(orders, result);
    }

    @Test
    void testFindByIdIfIdFound() {
        for (Order order : orders) {
            orderRepository.save(order);
        }

        Order findResult = orderRepository.findById(orders.get(1).getOrderId());
        assertEquals(orders.get(1).getOrderId(), findResult.getOrderId());
        assertEquals(orders.get(1).getProducts(), findResult.getProducts());
        assertEquals(orders.get(1).getStatus(), findResult.getStatus());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        for (Order order : orders) {
            orderRepository.save(order);
        }

        Order findResult = orderRepository.findById("zczc");
        assertNull(findResult);
    }
}