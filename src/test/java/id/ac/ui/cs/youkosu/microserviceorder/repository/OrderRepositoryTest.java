//package id.ac.ui.cs.youkosu.microserviceorder.repository;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//import id.ac.ui.cs.youkosu.microserviceorder.model.Order.Order;
//import id.ac.ui.cs.youkosu.microserviceorder.tempModel.CartItem;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//@SpringBootTest
//@Transactional
//public class OrderRepositoryTest {
//    @Autowired
//    OrderRepository orderRepository;
//    private List<Order> orders;
//
//    @BeforeEach
//    void setUp() {
////        orderRepository = new OrderRepository();
//        List<CartItem> cartItems = new ArrayList<>();
//        CartItem item1 = new CartItem(
//                UUID.randomUUID(),
//                "Product A",
//                10.0,
//                5,
//                0.0,
//                0,
//                "https://images.tokopedia.net/img/cache/900/hDjmkQ/2024/1/18/73592f9a-ef15-417f-8c6f-fd79c2f4228d.jpg"
//        );
//        cartItems.add(item1);
//
//        orders = new ArrayList<>();
//        Order order1 = new Order(UUID.randomUUID(), cartItems);
//        orders.add(order1);
//        Order order2 = new Order(UUID.randomUUID(), cartItems);
//        orders.add(order2);
//        Order order3 = new Order(UUID.randomUUID(), cartItems);
//        orders.add(order3);
//    }
//
//    @AfterEach
//    public void tearDown() {
//        orderRepository.deleteAll();
////        List<Order> order = null;
//        orders = null;
//    }
//
//
//
//    @Test
//    void testSaveCreate() {
//        Order order = orders.get(0);
//        Order result = orderRepository.save(order);
//        Order findResult = orderRepository.findById(order.getOrderId()).orElse(null);
//        assertEquals(order.getOrderId(), result.getOrderId());
//        assertEquals(order.getOrderId(), findResult.getOrderId());
//        assertEquals(order.getCartItems(), findResult.getCartItems());
//        assertEquals(order.getStatus(), findResult.getStatus());
//    }
//
//    @Test
//    void testUpdateSave() {
//        Order order = orders.get(1);
//        orderRepository.save(order);
//        Order newOrder = new Order(order.getOrderId(), order.getCartItems());
//        newOrder.setStatus(order.getStatus());
//
//        Order result = orderRepository.save(newOrder);
//        Order findResult = orderRepository.findById(order.getOrderId()).orElse(null);
////        assertEquals(order.getOrderId(), result.getOrderId());
////        assertEquals(order.getOrderId(), findResult.getOrderId());
//        assertEquals(order.getStatus(), findResult.getStatus());
//    }
//
//    @Test
//    void testFindAll() {
//        for (Order order : orders) {
//            orderRepository.save(order);
//        }
//
//        List<Order> result = orderRepository.findAll();
//        assertEquals(orders.size(), result.size());
//        for (int i = 0; i < orders.size(); i++) {
//            assertEquals(orders.get(i).getOrderId(), result.get(i).getOrderId());
//            assertEquals(orders.get(i).getCartItems(), result.get(i).getCartItems());
//            assertEquals(orders.get(i).getStatus(), result.get(i).getStatus());
//        }
//    }
//
//    @Test
//    void testFindByIdIfIdFound() {
//        for (Order order : orders) {
//            orderRepository.save(order);
//        }
//
//        Order findResult = orderRepository.findById(orders.get(1).getOrderId()).orElse(null);
//        assertEquals(orders.get(1).getOrderId(), findResult.getOrderId());
//        assertEquals(orders.get(1).getCartItems(), findResult.getCartItems());
//        assertEquals(orders.get(1).getStatus(), findResult.getStatus());
//    }
//
//    @Test
//    void testFindByIdIfIdNotFound() {
//        for (Order order : orders) {
//            orderRepository.save(order);
//        }
//
//        Order findResult = orderRepository.findById(UUID.randomUUID()).orElse(null);
//        assertNull(findResult);
//    }
//}
