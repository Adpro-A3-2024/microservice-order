package id.ac.ui.cs.youkosu.microserviceorder.controller;

import id.ac.ui.cs.youkosu.microserviceorder.model.DTO.OrderUpdateStatusDTO;
import id.ac.ui.cs.youkosu.microserviceorder.model.Delivery.DeliveryException;
import id.ac.ui.cs.youkosu.microserviceorder.model.Order.Order;
import id.ac.ui.cs.youkosu.microserviceorder.service.OrderServiceImpl;
import id.ac.ui.cs.youkosu.microserviceorder.service.OrderStatusUpdateException;
import id.ac.ui.cs.youkosu.microserviceorder.tempModel.CartItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OrderServiceImpl orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    void testFindAllOrders() throws Exception {
        // Mock data
        List<CartItem> cartItems = new ArrayList<>();
        CartItem cartItemA = new CartItem(
                UUID.randomUUID(),
                "Product A",
                10.0,
                5,
                0.0,
                0,
                "url1"
        );

        CartItem cartItemB = new CartItem(
                UUID.randomUUID(),
                "Product B",
                20.0,
                10,
                0.0,
                0,
                "url2"
        );

        cartItems.add(cartItemA);
        cartItems.add(cartItemB);

        List<Order> orders = new ArrayList<>();
        Order order1 = new Order(UUID.randomUUID(),cartItems);
        Order order2 = new Order(UUID.randomUUID(), cartItems);

        UUID order1UUID = order1.getOrderId();
        UUID order2UUID = order2.getOrderId();

        orders.add(order1);
        orders.add(order2);

        // Mock service method
        when(orderService.findAll()).thenReturn(orders);

        // Perform GET request
        mockMvc.perform(get("/api/get-all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].orderId").value(order1UUID.toString()))
                .andExpect(jsonPath("$[0].cartItems[0].productName").value("Product A"))
                .andExpect(jsonPath("$[0].cartItems[0].productPrice").value(10.0))
                .andExpect(jsonPath("$[0].cartItems[0].productPictureUrl").value("url1"))
                .andExpect(jsonPath("$[1].orderId").value(order2UUID.toString()))
                .andExpect(jsonPath("$[1].cartItems[1].productName").value("Product B"))
                .andExpect(jsonPath("$[1].cartItems[1].productPrice").value(20.0))
                .andExpect(jsonPath("$[1].cartItems[1].productPictureUrl").value("url2"));
    }

    @Test
    void testFindOrderById() throws Exception {
        // Mock data
        List<CartItem> cartItems = new ArrayList<>();
        CartItem cartItem = new CartItem(
                UUID.randomUUID(),
                "Product A",
                10.0,
                5,
                0.0,
                0,
                "url1"
        );

        cartItems.add(cartItem);

        Order order = new Order(UUID.randomUUID(), cartItems);
        UUID orderUUID = order.getOrderId();

        // Mock service method
        when(orderService.findById(orderUUID)).thenReturn(order);

        // Perform GET request
        mockMvc.perform(get("/api/get?orderId=" + orderUUID.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value(orderUUID.toString()))
                .andExpect(jsonPath("$.cartItems[0].productName").value("Product A"))
                .andExpect(jsonPath("$.cartItems[0].productPrice").value(10.0))
                .andExpect(jsonPath("$.cartItems[0].productPictureUrl").value("url1"));
    }

    @Test
    void testUpdateStatus() throws Exception {
        // Mock data
        List<CartItem> cartItems = new ArrayList<>();
        CartItem cartItem = new CartItem(
                UUID.randomUUID(),
                "Product A",
                10.0,
                5,
                0.0,
                0,
                "url1"
        );

        cartItems.add(cartItem);

        Order order = new Order(UUID.randomUUID(), cartItems);
        UUID orderUUID = order.getOrderId();

        OrderUpdateStatusDTO updateStatusDTO = new OrderUpdateStatusDTO();
        updateStatusDTO.setOrderId(orderUUID);
        updateStatusDTO.setStatus("COMPLETED");
        updateStatusDTO.setDelivery("Gobek");

        when(orderService.updateStatus(any(OrderUpdateStatusDTO.class))).thenReturn(order);
        System.out.println("Line 155 "+order.getStatus());
        // Perform POST request
        mockMvc.perform(put("/api/edit-status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"orderId\": \"" + orderUUID.toString() + "\", \"status\": \"VERIFIED\", \"delivery\": \"Gobek\" }"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateProductPost() throws Exception {
        // Mock data
        List<CartItem> cartItems = new ArrayList<>();
        CartItem cartItem = new CartItem(
                UUID.randomUUID(),
                "Product A",
                10.0,
                5,
                0.0,
                0,
                "url1"
        );

        cartItems.add(cartItem);

        Order order = new Order(UUID.randomUUID(), cartItems);
        UUID orderUUID = order.getOrderId();

        when(orderService.createOrder(any(Order.class))).thenReturn(order);

        // Perform POST request
        mockMvc.perform(post("/api/create-order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"cartItems\": [{ \"productId\": \"" + cartItem.getProductId() + "\", \"productName\": \"Product A\", \"productPrice\": 10.0, \"productStock\": 5, \"productDiscount\": 0.0, \"productDiscountDaysLeft\": 0, \"productPictureUrl\": \"url1\" }] }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value(orderUUID.toString()))
                .andExpect(jsonPath("$.cartItems[0].productName").value("Product A"))
                .andExpect(jsonPath("$.cartItems[0].productPrice").value(10.0))
                .andExpect(jsonPath("$.cartItems[0].productPictureUrl").value("url1"));
    }

    @Test
    void testUpdateStatusOrderStatusUpdateException() throws Exception {
        UUID orderUUID = UUID.randomUUID();

        OrderUpdateStatusDTO updateStatusDTO = new OrderUpdateStatusDTO();
        updateStatusDTO.setOrderId(orderUUID);
        updateStatusDTO.setStatus("INVALID_STATUS");
        updateStatusDTO.setDelivery("Gobek");

        doThrow(new OrderStatusUpdateException("Cannot update order status")).when(orderService).updateStatus(any(OrderUpdateStatusDTO.class));

        mockMvc.perform(put("/api/edit-status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"orderId\": \"" + orderUUID.toString() + "\", \"status\": \"INVALID_STATUS\", \"delivery\": \"Gobek\" }"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateStatusDeliveryException() throws Exception {
        UUID orderUUID = UUID.randomUUID();

        OrderUpdateStatusDTO updateStatusDTO = new OrderUpdateStatusDTO();
        updateStatusDTO.setOrderId(orderUUID);
        updateStatusDTO.setStatus("SHIPPED");
        updateStatusDTO.setDelivery("InvalidDelivery");

        doThrow(new DeliveryException("Invalid delivery method")).when(orderService).updateStatus(any(OrderUpdateStatusDTO.class));

        mockMvc.perform(put("/api/edit-status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"orderId\": \"" + orderUUID.toString() + "\", \"status\": \"SHIPPED\", \"delivery\": \"InvalidDelivery\" }"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateStatusGenericException() throws Exception {
        UUID orderUUID = UUID.randomUUID();

        OrderUpdateStatusDTO updateStatusDTO = new OrderUpdateStatusDTO();
        updateStatusDTO.setOrderId(orderUUID);
        updateStatusDTO.setStatus("SHIPPED");
        updateStatusDTO.setDelivery("Gobek");

        doThrow(new RuntimeException("An unexpected error occurred")).when(orderService).updateStatus(any(OrderUpdateStatusDTO.class));

        mockMvc.perform(put("/api/edit-status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"orderId\": \"" + orderUUID.toString() + "\", \"status\": \"SHIPPED\", \"delivery\": \"Gobek\" }"))
                .andExpect(status().isInternalServerError());
    }
}
