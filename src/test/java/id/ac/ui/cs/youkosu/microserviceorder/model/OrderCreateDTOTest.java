package id.ac.ui.cs.youkosu.microserviceorder.model.DTO;

import id.ac.ui.cs.youkosu.microserviceorder.tempModel.CartItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderCreateDTOTest {
    private OrderCreateDTO dto;
    private List<CartItem> cartItems;

    @BeforeEach
    void setUp() {
        cartItems = new ArrayList<>();
        CartItem item1 = new CartItem(
                UUID.randomUUID(),
                "Product A",
                10.0,
                5,
                0.0,
                0,
                "https://example.com/productA.jpg"
        );

        CartItem item2 = new CartItem(
                UUID.randomUUID(),
                "Product B",
                20.0,
                10,
                0.0,
                0,
                "https://example.com/productB.jpg"
        );

        cartItems.add(item1);
        cartItems.add(item2);

        dto = new OrderCreateDTO();
        dto.setCartItems(cartItems);
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(cartItems, dto.getCartItems());
    }

    @Test
    void testEqualsAndHashCode() {
        OrderCreateDTO sameDto = new OrderCreateDTO();
        sameDto.setCartItems(cartItems);

        OrderCreateDTO differentDto = new OrderCreateDTO();
        differentDto.setCartItems(new ArrayList<>());

        assertEquals(dto, sameDto);
        assertNotEquals(dto, differentDto);
        assertEquals(dto.hashCode(), sameDto.hashCode());
        assertNotEquals(dto.hashCode(), differentDto.hashCode());
    }

    @Test
    void testEqualsDifferentTypes() {
        assertNotEquals(dto, new Object());
    }

    @Test
    void testEqualsNull() {
        assertNotEquals(dto, null);
    }

    @Test
    void testToString() {
        String expected = "OrderCreateDTO(cartItems=" + cartItems + ")";
        assertEquals(expected, dto.toString());
    }

    @Test
    void testCanEqual() {
        OrderCreateDTO sameDto = new OrderCreateDTO();
        assertTrue(dto.canEqual(sameDto));
        assertFalse(dto.canEqual(new Object()));
    }
}
