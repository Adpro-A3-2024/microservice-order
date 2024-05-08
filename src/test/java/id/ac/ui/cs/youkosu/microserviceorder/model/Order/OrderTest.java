package id.ac.ui.cs.youkosu.microserviceorder.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import id.ac.ui.cs.youkosu.microserviceorder.tempModel.Product;
import id.ac.ui.cs.youkosu.microserviceorder.model.Order.Order;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest{
    private List<Product> products;
    @BeforeEach
    void setUp(){
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductReqId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductReqName("Sampo Cap Bambang");
        product1.setProductReqPrice(2);
        product1.setProductReqPictureUrl("https://images.tokopedia.net/img/cache/900/hDjmkQ/2024/1/18/73592f9a-ef15-417f-8c6f-fd79c2f4228d.jpg");
        product1.setProductReqSourceUrl("https://images.tokopedia.net/img/cache/900/hDjmkQ/2024/1/18/73592f9a-ef15-417f-8c6f-fd79c2f4228d.jpg");

        Product product2 = new Product();
        product2.setProductReqId("eb558e9f-1c39-460e-8860-71af6af63bd7");
        product2.setProductReqName("Sabun Cap Usep");
        product2.setProductReqPrice(3);
        product2.setProductReqPictureUrl("https://images.tokopedia.net/img/cache/900/hDjmkQ/2024/1/18/73592f9a-ef15-417f-8c6f-fd79c2f4228d.jpg");
        product2.setProductReqSourceUrl("https://images.tokopedia.net/img/cache/900/hDjmkQ/2024/1/18/73592f9a-ef15-417f-8c6f-fd79c2f4228d.jpg");
        this.products.add(product1);
        this.products.add(product2);
    }

    @Test
    void testCreateOrderEmptyProduct() {
        this.products.clear();

        assertThrows(IllegalArgumentException.class, () -> {
            Order order = new Order("13652556-012a0-4c07-b546-54eb1396d79b",
                    this.products);
        });
    }

    @Test
    void testCreateOrderDefaultStatus() {
        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                this.products);

        assertSame(this.products, order.getProducts());
        assertEquals(2, order.getProducts().size());
        assertEquals("Sampo Cap Bambang", order.getProducts().get(0).getProductReqName());
        assertEquals("Sabun Cap Usep", order.getProducts().get(1).getProductReqName());

        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", order.getOrderId());
        assertEquals("UNVERIFIED", order.getStatus().toString());
    }
    @Test
    void testSetStatusToVerified() {
        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                this.products);
        order.setStatusToVerified();
        assertEquals("VERIFIED", order.getStatus().toString());
    }

    @Test
    void testSetStatusToCancelled() {
        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                this.products);
        order.setStatusToCancelled();
        assertEquals("CANCELLED", order.getStatus().toString());
    }

    @Test
    void testSetStatusToShipped() {
        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                this.products);
        order.setStatusToVerified();
        order.setStatusToShipped();
        assertEquals("SHIPPED", order.getStatus().toString());
    }

    @Test
    void testSetStatusToCompleted() {
        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                this.products);
        order.setStatusToVerified();
        order.setStatusToShipped();
        order.setStatusToCompleted();
        assertEquals("COMPLETED", order.getStatus().toString());
    }

}