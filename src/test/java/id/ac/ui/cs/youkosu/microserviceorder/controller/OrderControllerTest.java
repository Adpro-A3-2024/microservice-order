package id.ac.ui.cs.youkosu.microserviceorder.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.youkosu.microserviceorder.model.Order;
import id.ac.ui.cs.youkosu.microserviceorder.service.OrderServiceImpl;
import id.ac.ui.cs.youkosu.microserviceorder.tempModel.Product;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
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
        List<Product> products = new ArrayList<>();
        Product productA = new Product();
        productA.setProductReqId("1");
        productA.setProductReqName("Product A");
        productA.setProductReqPrice(10.0);
        productA.setProductReqPictureUrl("url1");
        productA.setProductReqSourceUrl("sourceUrl1");

        Product productB = new Product();
        productB.setProductReqId("2");
        productB.setProductReqName("Product B");
        productB.setProductReqPrice(20.0);
        productB.setProductReqPictureUrl("url2");
        productB.setProductReqSourceUrl("sourceUrl2");

        products.add(productA);
        products.add(productB);

        List<Order> orders = new ArrayList<>();
        orders.add(new Order("1", products));
        orders.add(new Order("2", products));

        // Mock service method
        when(orderService.findAll()).thenReturn(orders);

        // Perform GET request
        mockMvc.perform(get("/api/get-all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].orderId").value("1"))
                .andExpect(jsonPath("$[0].products[0].productReqId").value("1"))
                .andExpect(jsonPath("$[0].products[0].productReqName").value("Product A"))
                .andExpect(jsonPath("$[0].products[0].productReqPrice").value(10.0))
                .andExpect(jsonPath("$[0].products[0].productReqPictureUrl").value("url1"))
                .andExpect(jsonPath("$[0].products[0].productReqSourceUrl").value("sourceUrl1"))
                .andExpect(jsonPath("$[1].orderId").value("2"))
                .andExpect(jsonPath("$[1].products[1].productReqId").value("2"))
                .andExpect(jsonPath("$[1].products[1].productReqName").value("Product B"))
                .andExpect(jsonPath("$[1].products[1].productReqPrice").value(20.0))
                .andExpect(jsonPath("$[1].products[1].productReqPictureUrl").value("url2"))
                .andExpect(jsonPath("$[1].products[1].productReqSourceUrl").value("sourceUrl2"));
    }

    @Test
    void testFindOrderById() throws Exception {
        // Mock data
        List<Product> products = new ArrayList<>();
        products.add(new Product());

        Order order = new Order("1", products);

        // Mock service method
        when(orderService.findById("1")).thenReturn(order);

        // Perform GET request
        mockMvc.perform(get("/api/get/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value("1"))
                .andExpect(jsonPath("$.status").value("UNVERIFIED"));
    }

    // Helper method to convert object to JSON string
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
