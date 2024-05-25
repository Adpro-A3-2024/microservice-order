package id.ac.ui.cs.youkosu.microserviceorder.model;

import id.ac.ui.cs.youkosu.microserviceorder.model.Order.Order;
import id.ac.ui.cs.youkosu.microserviceorder.tempModel.CartItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CartItemTest {
    private CartItem cartItem;
    private UUID cartItemId;
    private UUID productId;
    private String productName;
    private double productPrice;
    private int productStock;
    private double productDiscount;
    private int productDiscountDaysLeft;
    private String productPictureUrl;
    private Order order;

    @BeforeEach
    void setUp() {
        cartItemId = UUID.randomUUID();
        productId = UUID.randomUUID();
        productName = "Product A";
        productPrice = 10.0;
        productStock = 5;
        productDiscount = 0.0;
        productDiscountDaysLeft = 0;
        productPictureUrl = "https://example.com/productA.jpg";
        order = new Order();

        cartItem = new CartItem(cartItemId, productId, productName, productPrice, productStock, productDiscount, productDiscountDaysLeft, productPictureUrl, order);
    }

    @Test
    void testCartItemConstructor() {
        assertEquals(cartItemId, cartItem.getCartItemId());
        assertEquals(productId, cartItem.getProductId());
        assertEquals(productName, cartItem.getProductName());
        assertEquals(productPrice, cartItem.getProductPrice());
        assertEquals(productStock, cartItem.getProductStock());
        assertEquals(productDiscount, cartItem.getProductDiscount());
        assertEquals(productDiscountDaysLeft, cartItem.getProductDiscountDaysLeft());
        assertEquals(productPictureUrl, cartItem.getProductPictureUrl());
        assertEquals(order, cartItem.getOrder());
    }

    @Test
    void testSetters() {
        UUID newCartItemId = UUID.randomUUID();
        UUID newProductId = UUID.randomUUID();
        String newProductName = "Product B";
        double newProductPrice = 20.0;
        int newProductStock = 10;
        double newProductDiscount = 5.0;
        int newProductDiscountDaysLeft = 3;
        String newProductPictureUrl = "https://example.com/productB.jpg";
        Order newOrder = new Order();

        cartItem.setCartItemId(newCartItemId);
        cartItem.setProductId(newProductId);
        cartItem.setProductName(newProductName);
        cartItem.setProductPrice(newProductPrice);
        cartItem.setProductStock(newProductStock);
        cartItem.setProductDiscount(newProductDiscount);
        cartItem.setProductDiscountDaysLeft(newProductDiscountDaysLeft);
        cartItem.setProductPictureUrl(newProductPictureUrl);
        cartItem.setOrder(newOrder);

        assertEquals(newCartItemId, cartItem.getCartItemId());
        assertEquals(newProductId, cartItem.getProductId());
        assertEquals(newProductName, cartItem.getProductName());
        assertEquals(newProductPrice, cartItem.getProductPrice());
        assertEquals(newProductStock, cartItem.getProductStock());
        assertEquals(newProductDiscount, cartItem.getProductDiscount());
        assertEquals(newProductDiscountDaysLeft, cartItem.getProductDiscountDaysLeft());
        assertEquals(newProductPictureUrl, cartItem.getProductPictureUrl());
        assertEquals(newOrder, cartItem.getOrder());
    }

    @Test
    void testEqualsAndHashCode() {
        CartItem sameCartItem = new CartItem(cartItemId, productId, productName, productPrice, productStock, productDiscount, productDiscountDaysLeft, productPictureUrl, order);
        CartItem differentCartItem = new CartItem(UUID.randomUUID(), productId, productName, productPrice, productStock, productDiscount, productDiscountDaysLeft, productPictureUrl, order);

        assertEquals(cartItem, sameCartItem);
        assertNotEquals(cartItem, differentCartItem);
        assertEquals(cartItem.hashCode(), sameCartItem.hashCode());
        assertNotEquals(cartItem.hashCode(), differentCartItem.hashCode());
    }

    @Test
    void testEqualsDifferentTypes() {
        assertNotEquals(cartItem, new Object());
    }

    @Test
    void testEqualsNull() {
        assertNotEquals(cartItem, null);
    }

    @Test
    void testToString() {
        String expected = "CartItem(cartItemId=" + cartItemId + ", productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice + ", productStock=" + productStock + ", productDiscount=" + productDiscount + ", productDiscountDaysLeft=" + productDiscountDaysLeft + ", productPictureUrl=" + productPictureUrl + ", order=" + order + ")";
        assertEquals(expected, cartItem.toString());
    }

//    @Test
//    void testCanEqual() {
//        CartItem sameCartItem = new CartItem(cartItemId, productId, productName, productPrice, productStock, productDiscount, productDiscountDaysLeft, productPictureUrl, order);
//        assertTrue(cartItem.canEqual(sameCartItem));
//        assertFalse(cartItem.canEqual(new Object()));
//    }

    @Test
    void testHashCodeConsistency() {
        int initialHashCode = cartItem.hashCode();
        assertEquals(initialHashCode, cartItem.hashCode());
    }

    @Test
    void testHashCodeWithNullFields() {
        CartItem cartItemWithNulls = new CartItem();
        assertNotNull(cartItemWithNulls.hashCode());
    }
}
